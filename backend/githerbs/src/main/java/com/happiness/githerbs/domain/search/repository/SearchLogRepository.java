package com.happiness.githerbs.domain.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.happiness.githerbs.domain.search.entity.SearchLog;

public interface SearchLogRepository extends JpaRepository<SearchLog, Integer>, SearchLogRepositoryCustom {
}
