package com.happiness.githerbs.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.happiness.githerbs.domain.member.entity.MemberDaily;

public interface MemberDailyRepository extends JpaRepository<MemberDaily, Integer>, MemberDailyRepositoryCustom {
}
