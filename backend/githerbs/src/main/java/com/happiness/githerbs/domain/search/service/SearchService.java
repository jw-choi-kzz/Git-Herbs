package com.happiness.githerbs.domain.search.service;

import java.util.List;

import com.happiness.githerbs.domain.search.dto.response.SearchResponseDto;

public interface SearchService {
	List<SearchResponseDto> search(String keyword);
}
