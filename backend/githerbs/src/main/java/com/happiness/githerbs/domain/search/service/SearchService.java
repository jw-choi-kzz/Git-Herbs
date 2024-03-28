package com.happiness.githerbs.domain.search.service;

import java.util.List;

import com.happiness.githerbs.domain.search.dto.response.SearchResponseDto;
import com.happiness.githerbs.domain.search.dto.response.KeywordResponseDto;

public interface SearchService {
	List<SearchResponseDto> search(Integer memberId, String keyword);

	List<String> findRecent(Integer memberId);

	List<KeywordResponseDto> recommendKeyword(Integer herbId);
}
