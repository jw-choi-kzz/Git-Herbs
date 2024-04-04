package com.happiness.githerbs.domain.herb.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.happiness.githerbs.domain.herb.dto.response.MyHerbResponseDto;

public interface MyHerbRepositoryCustomer {
	Slice<MyHerbResponseDto> findMyHerbListByHerbIdAndMemberId(Integer memberId, Integer herId,
		Pageable pageable);
}
