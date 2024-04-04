package com.happiness.githerbs.domain.herb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.happiness.githerbs.domain.herb.entity.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
	Optional<Bookmark> findByHerbIdAndMemberId(Integer herbId, Integer memberId);
	List<Bookmark> findByMemberIdOrderByCreatedAtDesc(Integer memberId);
}
