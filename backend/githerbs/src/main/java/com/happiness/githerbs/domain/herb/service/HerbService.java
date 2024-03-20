package com.happiness.githerbs.domain.herb.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happiness.githerbs.domain.herb.dto.response.HerbDetailResponseDto;
import com.happiness.githerbs.domain.herb.dto.response.HerbResponseDto;
import com.happiness.githerbs.domain.herb.dto.response.HerbSeasonResponseDto;
import com.happiness.githerbs.domain.herb.repository.HerbRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HerbService {

	private final HerbRepository herbRepository;

	@Transactional(readOnly = true)
	public Slice<HerbResponseDto> getHerListByUserId(Integer userId, Pageable pageable) {
		return herbRepository.findHerbListByUserId(userId, pageable);
	}

	@Transactional(readOnly = true)
	public List<HerbSeasonResponseDto> getHerbSeasonByHerdId() {
		return herbRepository.findHerbSeasonListByDate();
	}

	@Transactional(readOnly = true)
	public HerbDetailResponseDto getHerbDetailByHerbId(Integer herbId) {
		return herbRepository.findHerbDetailByHerbId(herbId);
	}
}
