package com.happiness.githerbs.domain.manual.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.happiness.githerbs.domain.manual.dto.response.Address;
import com.happiness.githerbs.domain.manual.dto.response.Document;
import com.happiness.githerbs.domain.manual.dto.response.KakaoLocalResponseDto;
import com.happiness.githerbs.domain.manual.dto.response.RegionCode;
import com.happiness.githerbs.domain.manual.dto.response.TipResponseDto;
import com.happiness.githerbs.domain.manual.repository.AnimalRepositoryCustom;
import com.happiness.githerbs.domain.manual.repository.TipRepository;
import com.happiness.githerbs.global.common.code.ErrorCode;
import com.happiness.githerbs.global.common.exception.BaseException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManualServiceImpl implements ManualService {

	private final TipRepository tipRepository;
	private final AnimalRepositoryCustom animalRepositoryCustom;

	@Value("${kakao.api.secret}")
	private String kakaoApiKey;

	@Override
	public TipResponseDto findTip() {
		return tipRepository.findTip();
	}

	@Override
	public List<String> findAnimal(Double lat, Double lng) {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", kakaoApiKey);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
				"https://dapi.kakao.com/v2/local/geo/coord2address.JSON")
			.queryParam("x", String.valueOf(lng))
			.queryParam("y", String.valueOf(lat));

		HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);

		ResponseEntity<KakaoLocalResponseDto> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
			httpEntity,
			KakaoLocalResponseDto.class);

		if (Objects.requireNonNull(response.getBody()).getDocuments() != null && !response.getBody()
			.getDocuments().isEmpty()) {
			Document document = response.getBody().getDocuments().get(0);
			Address address = document.getValidAddress();

			if (address != null) {
				String region1depthName = address.region1depthName();
				String region2depthName = address.region2depthName();
				Integer code = RegionCode.getCode(region1depthName, region2depthName);
				return animalRepositoryCustom.findAnimal(code);
			} else {
				throw new BaseException(ErrorCode.REGION_ERROR);
			}
		} else {
			throw new BaseException(ErrorCode.LOCATION_ERROR);
		}

	}

}
