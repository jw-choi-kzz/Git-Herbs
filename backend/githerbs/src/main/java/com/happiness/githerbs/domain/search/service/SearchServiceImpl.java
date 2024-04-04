package com.happiness.githerbs.domain.search.service;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.HighlightQuery;
import org.springframework.data.elasticsearch.core.query.highlight.Highlight;
import org.springframework.data.elasticsearch.core.query.highlight.HighlightField;
import org.springframework.data.elasticsearch.core.query.highlight.HighlightFieldParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.happiness.githerbs.domain.auth.service.JwtService;
import com.happiness.githerbs.domain.herb.entity.Herb;
import com.happiness.githerbs.domain.herb.repository.HerbRepository;
import com.happiness.githerbs.domain.member.entity.MemberDaily;
import com.happiness.githerbs.domain.member.repository.MemberDailyRepository;
import com.happiness.githerbs.domain.member.repository.MemberRepository;
import com.happiness.githerbs.domain.search.dto.common.HerbCandidateDto;
import com.happiness.githerbs.domain.search.dto.response.HerbSimilarityResponseDto;
import com.happiness.githerbs.domain.search.dto.response.KeywordResponseDto;
import com.happiness.githerbs.domain.search.dto.response.RecentSearchResponseDto;
import com.happiness.githerbs.domain.search.dto.response.SearchImageResponseDto;
import com.happiness.githerbs.domain.search.dto.response.SearchResponseDto;
import com.happiness.githerbs.domain.search.entity.HerbDocument;
import com.happiness.githerbs.domain.search.entity.SearchLog;
import com.happiness.githerbs.domain.search.repository.SearchLogRepository;
import com.happiness.githerbs.global.common.code.ErrorCode;
import com.happiness.githerbs.global.common.exception.BaseException;
import com.happiness.githerbs.global.config.S3Uploader;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

	private final HerbRepository herbRepository;
	private final MemberRepository memberRepository;
	private final SearchLogRepository searchLogRepository;
	private final ElasticsearchOperations elasticsearchOperations;
	private final JwtService jwt;
	private final S3Uploader s3;
	private final FastApiClient fastApiClient;
	private final MemberDailyRepository dailyRepo;

	@Value("${feign.fast-api.img}")
	private String fastApiImgUrl;
	@Value("${kakao.login.tmp-path}")
	private String tmpPath;

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
			.withPreTags("<b>")
			.withPostTags("</b>")
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
			int length = Math.min(hit.getContent().getHerbMedicinalEffect().get(0).length(), 50);
			String description = hit.getHighlightField(herbMedicinalEffect)
				.stream()
				.findFirst()
				.orElseGet(() -> hit.getContent().getHerbMedicinalEffect().get(0).substring(0, length));
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
			result.add(new KeywordResponseDto(herb.getId(), herb.getHerbImg(), herb.getHerbName()));
		}
		return result;
	}

	@Override
	public SearchImageResponseDto searchImage(String accessToken, MultipartFile img) throws IOException {
		// validate token
		var memberInfo = jwt.validateToken(accessToken);

		// change file extension if image is not jpg if raise exception

		// save image to S3
		var uuid = UUID.randomUUID().toString().replaceAll("-", "");
		var s3Url = s3.upload(img, "search", uuid);

		// send image to fastapi using feign client
		var uri = URI.create(fastApiImgUrl);
		// convert MultiPartFile to File
		var similarityMap = fastApiClient.getSimilarityClient(uri, img);
		var similarity = convertToSimilarity(similarityMap);

		// return herb information
		var candidate = convetToCandidate(similarity);

		// check member daily analysis flag
		var byMemberIdAndDate = dailyRepo.findByMemberIdAndDate(memberInfo.getMemberId(), LocalDate.now());
		MemberDaily memberDaily;
		if (byMemberIdAndDate.isPresent()) {
			memberDaily = byMemberIdAndDate.get();
		} else {
			memberDaily = MemberDaily.builder()
				.member(memberRepository.findById(memberInfo.getMemberId()).orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND)))
				.date(LocalDate.now())
				.build();
			dailyRepo.saveAndFlush(memberDaily);
		}
		if (!memberDaily.getDate().equals(LocalDate.now())) {
			throw new BaseException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		if (!memberDaily.isAnalysis()) {
			dailyRepo.updateDailyAnalysis(memberInfo.getMemberId());
		}

		// return response
		return SearchImageResponseDto.builder()
			.pictureUrl(s3Url)
			.candidates(candidate)
			.build();
	}

	private List<HerbSimilarityResponseDto> convertToSimilarity(Map<String, Double> similarity) {
		List<HerbSimilarityResponseDto> result = new ArrayList<>();
		for (var entry : similarity.entrySet()) {
			result.add(HerbSimilarityResponseDto.builder().herbClass(entry.getKey()).similarity(entry.getValue()).build());
		}
		return result;
	}

	private List<HerbCandidateDto> convetToCandidate(List<HerbSimilarityResponseDto> similarity) {
		List<HerbCandidateDto> result = new ArrayList<>();
		// get herb information from repository
		for (HerbSimilarityResponseDto dto : similarity) {
			var split = dto.herbClass().split("_");
			var herb = herbRepository.findById(Integer.parseInt(split[0]))
				.orElseThrow(() -> new BaseException(ErrorCode.HERB_NOT_FOUND));
			result.add(HerbCandidateDto.builder()
				.herbId(herb.getId())
				.herbImgUrl(herb.getHerbImg())
				.herbName(herb.getHerbName())
				.similarity(dto.similarity())
				.build());
		}
		return result;
	}
}
