package com.happiness.githerbs.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.happiness.githerbs.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
