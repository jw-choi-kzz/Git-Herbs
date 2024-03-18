package com.githerbs.herb.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.githerbs.herb.domain.entity.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
	Optional<Bookmark> findByHerbIdAndMemberId(Integer herbId, Integer memberId);
}
