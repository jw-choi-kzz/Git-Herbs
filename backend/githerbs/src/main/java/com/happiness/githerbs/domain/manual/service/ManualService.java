package com.happiness.githerbs.domain.manual.service;

import java.util.List;

import com.happiness.githerbs.domain.manual.dto.response.TipResponseDto;

public interface ManualService {
	TipResponseDto findTip();

	List<String> findAnimal(Double lat, Double lng);
}
