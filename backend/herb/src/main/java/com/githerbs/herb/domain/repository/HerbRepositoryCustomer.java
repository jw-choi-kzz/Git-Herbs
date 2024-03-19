package com.githerbs.herb.domain.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.githerbs.herb.domain.dto.HerbDetailResponseDto;
import com.githerbs.herb.domain.dto.HerbResponseDto;

public interface HerbRepositoryCustomer {
	Slice<HerbResponseDto> findHerbListByUserId(Integer userId, Pageable pageable);

	HerbDetailResponseDto findHerbDetailByHerbId(Integer herbId);
}
