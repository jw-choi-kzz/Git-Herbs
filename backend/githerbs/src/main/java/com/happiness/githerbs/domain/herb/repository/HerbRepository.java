package com.happiness.githerbs.domain.herb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.happiness.githerbs.domain.herb.entity.Herb;

public interface HerbRepository extends JpaRepository<Herb, Integer>, HerbRepositoryCustomer {
}
