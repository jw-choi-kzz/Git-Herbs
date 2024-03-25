package com.happiness.githerbs.domain.manual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.happiness.githerbs.domain.manual.dto.response.AnimalResponseDto;
import com.happiness.githerbs.domain.manual.dto.response.RegionCode;
import com.happiness.githerbs.domain.manual.dto.response.TipResponseDto;
import com.happiness.githerbs.domain.manual.repository.AnimalRepositoryCustom;
import com.happiness.githerbs.domain.manual.repository.TipRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManualServiceImpl implements ManualService {

	private final TipRepository tipRepository;
	private final AnimalRepositoryCustom animalRepositoryCustom;
	private final KakaoLocalClient kakaoLocalClient;

	@Value("${kakao.api.secret}")
	private String kakaoApiKey;

	@Override
	public TipResponseDto findTip() {
		return tipRepository.findTip();
	}

	@Override
	public AnimalResponseDto findAnimal(Double lat, Double lng) {
		KakaoLocalClient.KakaoLocalResponseDto response = kakaoLocalClient.getAddress(kakaoApiKey, String.valueOf(lng),
			String.valueOf(lat));

		int code = RegionCode.findRandomCode();
		String region = RegionCode.findKeysByCode(code);
		if (response != null && response.getDocuments() != null && !response.getDocuments().isEmpty()) {
			KakaoLocalClient.KakaoLocalResponseDto.Document document = response.getDocuments().get(0);
			String region1depthName = getRegionDepthName(document.getAddress(), document.getRoadAddress(), 1);
			String region2depthName = getRegionDepthName(document.getAddress(), document.getRoadAddress(), 2);
			if (region1depthName != null && region2depthName != null
				&& RegionCode.getCode(region1depthName, region2depthName) != null
				&& (!animalRepositoryCustom.findAnimal(RegionCode.getCode(region1depthName, region2depthName))
				.isEmpty())) {
				code = RegionCode.getCode(region1depthName, region2depthName);
				region = region1depthName + " " + region2depthName;

			}
		}
		List<String> animals = animalRepositoryCustom.findAnimal(code);
		return new AnimalResponseDto(region, animals);

	}

	private String getRegionDepthName(KakaoLocalClient.KakaoLocalResponseDto.Address address,
		KakaoLocalClient.KakaoLocalResponseDto.Address roadAddress, int depth) {
		if (address != null) {
			return depth == 1 ? address.getRegion1depthName() : address.getRegion2depthName();
		} else if (roadAddress != null) {
			return depth == 1 ? roadAddress.getRegion1depthName() : roadAddress.getRegion2depthName();
		}
		return null;
	}

}
