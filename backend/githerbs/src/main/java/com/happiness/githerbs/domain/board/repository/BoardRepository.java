package com.happiness.githerbs.domain.board.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.happiness.githerbs.domain.board.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

	//전체 글 조회
	Optional<List<Board>> findAllByDeletedFalse();

	//맴버 번호가 작성한 글 조회
	Optional<List<Board>> findByMemberIdAndDeletedFalse(Integer memberId);


}
