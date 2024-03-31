package com.happiness.githerbs.domain.board.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.happiness.githerbs.domain.board.entity.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite,Integer> {

	//회원이 좋아요 한 거 반환
	Optional<List<Favorite>> findByMemberIdAndDeletedTrue(int memberId);


	//좋아요 했는지 확인
	Optional<Favorite> findByMemberIdAndBoardBoardId(int memberId, int boardId);

}
