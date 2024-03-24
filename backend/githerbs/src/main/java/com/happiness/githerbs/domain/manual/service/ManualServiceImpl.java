package com.happiness.githerbs.domain.manual.service;

import org.springframework.stereotype.Service;

import com.happiness.githerbs.domain.manual.dto.response.TipResponseDto;
import com.happiness.githerbs.domain.manual.repository.TipRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManualServiceImpl implements ManualService {

	private final TipRepository tipRepository;

	@Override
	public TipResponseDto findTip() {
		return tipRepository.findTip();
	}

}
