package com.happiness.githerbs.domain.search.service;

import java.util.List;

import com.happiness.githerbs.domain.search.dto.response.KeywordResponseDto;
import com.happiness.githerbs.domain.search.dto.response.RecentSearchResponseDto;
import com.happiness.githerbs.domain.search.dto.response.SearchResponseDto;

public interface SearchService {
	List<SearchResponseDto> search(Integer memberId, String keyword);

	List<RecentSearchResponseDto> findRecent(Integer memberId);

	List<KeywordResponseDto> recommendKeyword(Integer herbId);
}
