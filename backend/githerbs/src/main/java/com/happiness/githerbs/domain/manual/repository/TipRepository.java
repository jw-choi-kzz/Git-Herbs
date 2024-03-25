package com.happiness.githerbs.domain.manual.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.happiness.githerbs.domain.manual.entity.Tip;

public interface TipRepository extends JpaRepository<Tip, Integer>, TipRepositoryCustom {
}
