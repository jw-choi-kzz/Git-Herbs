package com.happiness.githerbs.domain.member.repository;

import com.happiness.githerbs.domain.member.entity.MemberBadge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberBadgeRepository extends JpaRepository<MemberBadge,Integer> {

    Optional<List<MemberBadge>> findByMemberId(Integer memberId);

    Optional<MemberBadge> findByMemberIdAndBadgeId(Integer memberId, Integer badgeId);
}
