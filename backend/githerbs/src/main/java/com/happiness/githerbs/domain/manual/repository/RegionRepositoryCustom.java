package com.happiness.githerbs.domain.manual.repository;

public interface RegionRepositoryCustom {
	Integer getCode(String oneDepth, String twoDepth);

	String getRegion(Integer code);

	Integer getRandomCode();
}
