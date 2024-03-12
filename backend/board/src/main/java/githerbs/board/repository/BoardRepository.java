package githerbs.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import githerbs.board.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board,Integer> {
	int countFavoritesByBoardId(int boardId);

}
