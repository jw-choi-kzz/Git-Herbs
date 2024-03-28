package com.happiness.githerbs.domain.search.repository;

import java.util.List;

import com.happiness.githerbs.domain.search.dto.response.RecentSearchResponseDto;

public interface SearchLogRepositoryCustom {
	List<RecentSearchResponseDto> findRecent(Integer memberId);
}
