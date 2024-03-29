package com.happiness.githerbs.domain.search.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.HighlightQuery;
import org.springframework.data.elasticsearch.core.query.highlight.Highlight;
import org.springframework.data.elasticsearch.core.query.highlight.HighlightField;
import org.springframework.data.elasticsearch.core.query.highlight.HighlightFieldParameters;
import org.springframework.stereotype.Service;

import com.happiness.githerbs.domain.herb.entity.Herb;
import com.happiness.githerbs.domain.herb.repository.HerbRepository;
import com.happiness.githerbs.domain.member.repository.MemberRepository;
import com.happiness.githerbs.domain.search.dto.response.KeywordResponseDto;
import com.happiness.githerbs.domain.search.dto.response.RecentSearchResponseDto;
import com.happiness.githerbs.domain.search.dto.response.SearchResponseDto;
import com.happiness.githerbs.domain.search.entity.HerbDocument;
import com.happiness.githerbs.domain.search.entity.SearchLog;
import com.happiness.githerbs.domain.search.repository.SearchLogRepository;
import com.happiness.githerbs.global.common.code.ErrorCode;
import com.happiness.githerbs.global.common.exception.BaseException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

	private final HerbRepository herbRepository;
	private final MemberRepository memberRepository;
	private final SearchLogRepository searchLogRepository;
	private final ElasticsearchOperations elasticsearchOperations;
	private final FastApiClient fastApiClient;

	@Override
	@Transactional
	public List<SearchResponseDto> search(Integer memberId, String keyword) {
		if (memberId != null) {
			SearchLog searchLog = SearchLog.builder()
				.member(
					memberRepository.findById(memberId).orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND)))
				.keyword(keyword)
				.createdAt(LocalDate.now())
				.build();
			searchLogRepository.save(searchLog);
		}
		String herbMedicinalEffect = "herbMedicinalEffect";
		Criteria criteria = new Criteria()
			.subCriteria(
				(new Criteria("herbName").matches(keyword).boost(1.0f))
					.or(new Criteria("herbNickname").matches(keyword).boost(1.0f))
					.or(new Criteria("herbScientificName").matches(keyword).boost(1.0f))
					.or(new Criteria("herbHarvestingTime").matches(keyword).boost(1.0f))
					.or(new Criteria("herbEnvironment").matches(keyword).boost(1.0f))
					.or(new Criteria("herbQuality").matches(keyword).boost(1.0f))
					.or(new Criteria("herbMedicalPart").matches(keyword).boost(1.0f))
					.or(new Criteria(herbMedicinalEffect).matches(keyword).boost(1.0f)));

		HighlightFieldParameters highlightFieldParameters = HighlightFieldParameters.builder()
			.withPreTags("<br>")
			.withPostTags("</br>")
			.withFragmentSize(50)
			.build();

		HighlightField highlightField = new HighlightField(herbMedicinalEffect, highlightFieldParameters);

		Highlight highlight = new Highlight(List.of(highlightField));

		HighlightQuery highlightQuery = new HighlightQuery(highlight, String.class);

		CriteriaQuery searchQuery = new CriteriaQuery(criteria);
		searchQuery.setHighlightQuery(highlightQuery);

		SearchHits<HerbDocument> searchHits = elasticsearchOperations.search(searchQuery, HerbDocument.class);

		return searchHits.stream().map(hit -> {
			Integer herbId = hit.getContent().getId();
			Herb herb = herbRepository.findById(herbId).orElseThrow(() -> new BaseException(ErrorCode.HERB_NOT_FOUND));
			String description = hit.getHighlightField(herbMedicinalEffect)
				.stream()
				.findFirst()
				.orElseGet(() -> hit.getContent().getHerbMedicinalEffect().get(0).substring(0, 50));
			return SearchResponseDto.builder()
				.id(herbId)
				.herbName(herb.getHerbName())
				.herbImg(herb.getHerbImg())
				.scientificName(herb.getHerbScientificName())
				.medicinalPart(herb.getHerbMedicalPart())
				.description(description)
				.build();
		}).toList();
	}

	@Override
	public List<RecentSearchResponseDto> findRecent(Integer memberId) {
		return searchLogRepository.findRecent(memberId);
	}

	@Override
	@Transactional
	public List<KeywordResponseDto> recommendKeyword(Integer herbId) {
		FastApiClient.KeywordListResponseDto keywords = fastApiClient.getKeyword(herbId);
		List<KeywordResponseDto> result = new ArrayList<>();

		for (int id : keywords.herbIds) {
			Herb herb = herbRepository.findById(id).orElseThrow(() -> new BaseException(ErrorCode.HERB_NOT_FOUND));
			result.add(new KeywordResponseDto(id, herb.getHerbName()));
		}
		return result;
	}
}
