package com.happiness.githerbs.domain.search.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.happiness.githerbs.domain.search.dto.response.KeywordResponseDto;
import com.happiness.githerbs.domain.search.dto.response.RecentSearchResponseDto;
import com.happiness.githerbs.domain.search.dto.response.SearchImageResponseDto;
import com.happiness.githerbs.domain.search.dto.response.SearchResponseDto;

public interface SearchService {
	List<SearchResponseDto> search(Integer memberId, String keyword);

	List<RecentSearchResponseDto> findRecent(Integer memberId);

	List<KeywordResponseDto> recommendKeyword(Integer herbId);

	SearchImageResponseDto searchImage(String accessToken, MultipartFile img) throws IOException;
}
