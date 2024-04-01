package com.happiness.githerbs.domain.member.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.happiness.githerbs.domain.member.entity.MemberDaily;

public interface MemberDailyRepository extends JpaRepository<MemberDaily, Integer>, MemberDailyRepositoryCustom {

	Optional<MemberDaily> findByMemberIdAndDate(Integer memberId, LocalDate date);

}
