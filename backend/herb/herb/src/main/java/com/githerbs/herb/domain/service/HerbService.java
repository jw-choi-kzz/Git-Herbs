package com.githerbs.herb.domain.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.githerbs.herb.domain.dto.HerbDetailResponseDto;
import com.githerbs.herb.domain.dto.HerbResponseDto;
import com.githerbs.herb.domain.repository.HerbRepository;

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
	public HerbDetailResponseDto getHerbDetailByHerbId(Integer herbId) {
		return herbRepository.findHerbDetailByHerbId(herbId);
	}
}
