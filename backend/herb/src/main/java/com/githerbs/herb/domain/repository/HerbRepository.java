package com.githerbs.herb.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.githerbs.herb.domain.entity.Herb;

public interface HerbRepository extends JpaRepository<Herb, Integer>, HerbRepositoryCustomer {
}
