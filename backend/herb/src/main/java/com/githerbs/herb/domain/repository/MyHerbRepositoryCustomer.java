package com.githerbs.herb.domain.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.githerbs.herb.domain.dto.response.MyHerbResponseDto;

public interface MyHerbRepositoryCustomer {
	Slice<MyHerbResponseDto> findMyHerbListByHerbIdAndMemberId(Integer memberId, Integer herId,
		Pageable pageable);
}
