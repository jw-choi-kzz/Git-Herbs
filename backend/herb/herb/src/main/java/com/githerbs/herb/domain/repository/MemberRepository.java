package com.githerbs.herb.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.githerbs.herb.domain.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
