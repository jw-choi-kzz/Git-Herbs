package com.happiness.githerbs.domain.search.service;

import java.util.List;

import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import com.happiness.githerbs.domain.herb.entity.Herb;
import com.happiness.githerbs.domain.herb.repository.HerbRepository;
import com.happiness.githerbs.domain.search.dto.response.SearchResponseDto;
import com.happiness.githerbs.domain.search.entity.HerbDocument;
import com.happiness.githerbs.domain.search.repository.SearchRepository;
import com.happiness.githerbs.global.common.code.ErrorCode;
import com.happiness.githerbs.global.common.exception.BaseException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

	private final SearchRepository searchRepository;
	private final HerbRepository herbRepository;

	@Override
	public List<SearchResponseDto> search(String keyword) {
		SearchHits<HerbDocument> searchHits = searchRepository.searchByKeyword(keyword);

		return searchHits.stream().map(hit -> {
			Integer herbId = hit.getContent().getId();
			Herb herb = herbRepository.findById(herbId).orElseThrow(() -> new BaseException(ErrorCode.HERB_NOT_FOUND));

			String description = hit.getHighlightField("herbMedicinalEffect")
				.stream()
				.findFirst()
				.orElseGet(() -> hit.getContent().getHerbMedicinalEffect().get(0));
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

}
