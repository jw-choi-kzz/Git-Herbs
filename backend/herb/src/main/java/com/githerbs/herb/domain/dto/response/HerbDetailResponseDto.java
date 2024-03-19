package com.githerbs.herb.domain.dto.response;

import java.util.Set;

public record HerbDetailResponseDto(
	Integer herbId,
	String herbName,
	String herbImgId,
	String herbScientificName,
	String herbNickname,
	String herbMedicalPart,
	String herbHarvestingTime,
	String herbEnvironment,
	String herbQuality,
	Set<HerbMedicinalEffectResponseDto> herMedicinalEffects
) {
}
