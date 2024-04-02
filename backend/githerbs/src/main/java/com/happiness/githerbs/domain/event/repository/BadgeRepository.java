package com.happiness.githerbs.domain.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.happiness.githerbs.domain.event.entity.Badge;

public interface BadgeRepository extends JpaRepository<Badge,Integer> {
}
