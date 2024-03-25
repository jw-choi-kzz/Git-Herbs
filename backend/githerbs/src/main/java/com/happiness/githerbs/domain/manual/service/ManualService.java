package com.happiness.githerbs.domain.manual.service;

import com.happiness.githerbs.domain.manual.dto.response.AnimalResponseDto;
import com.happiness.githerbs.domain.manual.dto.response.TipResponseDto;

public interface ManualService {
	TipResponseDto findTip();

	AnimalResponseDto findAnimal(Double lat, Double lng);
}
