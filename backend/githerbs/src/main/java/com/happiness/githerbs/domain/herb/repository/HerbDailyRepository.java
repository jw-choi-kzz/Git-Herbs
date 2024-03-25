package com.happiness.githerbs.domain.herb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.happiness.githerbs.domain.herb.entity.HerbDaily;

@Repository
public interface HerbDailyRepository extends JpaRepository<HerbDaily, Integer> {
	Optional<HerbDaily> findFirstBy();
}
