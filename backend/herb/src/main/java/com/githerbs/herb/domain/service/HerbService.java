package com.githerbs.herb.domain.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.githerbs.herb.domain.dto.response.HerbDetailResponseDto;
import com.githerbs.herb.domain.dto.response.HerbResponseDto;
import com.githerbs.herb.domain.dto.response.HerbSeasonResponseDto;
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
	public List<HerbSeasonResponseDto> getHerbSeasonByHerdId() {
		return herbRepository.findHerbSeasonListByDate();
	}

	@Transactional(readOnly = true)
	public HerbDetailResponseDto getHerbDetailByHerbId(Integer herbId) {
		return herbRepository.findHerbDetailByHerbId(herbId);
	}
}
