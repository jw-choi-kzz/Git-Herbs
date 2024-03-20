package com.happiness.githerbs.domain.board.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.happiness.githerbs.domain.board.entity.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite,Integer> {
	int countFavoritesByBoardBoardId(int boardId);

	Optional<List<Favorite>> findByMemberMemberIdAndFlagFalse(int memberId);

	Optional<Favorite> findByMemberMemberIdAndBoardBoardIdAndFlagTrue(int memberId,int boardId);
	Optional<Favorite> findByMemberMemberIdAndBoardBoardId(int memberId,int boardId);

}
