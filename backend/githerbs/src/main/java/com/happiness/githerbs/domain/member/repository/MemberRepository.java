package com.happiness.githerbs.domain.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.happiness.githerbs.domain.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
	Optional<Member> findByKakaoIdAndDeleted(Long kakaoId, boolean deleted);
}
