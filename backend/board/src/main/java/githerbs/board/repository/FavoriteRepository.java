package githerbs.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import githerbs.board.entity.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite,Integer> {
	int countFavoritesByBoardBoardId(int boardId);

	Optional<Favorite> findByMemberIdAndBoardBoardIdAndFlagTrue(int memberId,int boardId);
	Optional<Favorite> findByMemberIdAndBoardBoardId(int memberId,int boardId);

}
