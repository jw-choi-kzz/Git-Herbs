package com.happiness.githerbs.domain.herb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.happiness.githerbs.domain.herb.entity.HerbImage;

public interface HerbImageRepository extends JpaRepository<HerbImage, Integer>, HerbImageRepositoryCustom {
}
