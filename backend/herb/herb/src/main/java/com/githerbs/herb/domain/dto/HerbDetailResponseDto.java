package com.githerbs.herb.domain.dto;

import java.util.Set;

public record HerbDetailResponseDto(
	Integer herbId,
	String herbName,
	String herbScientificName,
	String herbNickname,
	String herbHarvestingTime,
	String herbEnvironment,
	String herbQuality,
	Set<HerbImageResponseDto> imgIds,
	Set<HerbMedicinalEffectResponseDto> medicinalEffects
) {
}
