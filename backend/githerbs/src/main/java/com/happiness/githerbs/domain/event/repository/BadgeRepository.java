package com.happiness.githerbs.domain.event.repository;

import com.happiness.githerbs.domain.event.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeRepository extends JpaRepository<Badge,Integer> {
}
