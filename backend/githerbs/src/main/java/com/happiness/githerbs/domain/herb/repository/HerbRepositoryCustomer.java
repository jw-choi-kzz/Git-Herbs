package com.happiness.githerbs.domain.herb.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.happiness.githerbs.domain.event.dto.response.MonthlyHerbResponse;
import com.happiness.githerbs.domain.herb.dto.response.HerbDetailResponseDto;
import com.happiness.githerbs.domain.herb.dto.response.HerbResponseDto;
import com.happiness.githerbs.domain.herb.dto.response.HerbSeasonResponseDto;

public interface HerbRepositoryCustomer {
	Slice<HerbResponseDto> findHerbListByUserId(Integer userId, Pageable pageable);

	HerbDetailResponseDto findHerbDetailByHerbId(Integer herbId);

	List<HerbSeasonResponseDto> findHerbSeasonListByDate();

	MonthlyHerbResponse findMonthlyHerb(int month);
}
