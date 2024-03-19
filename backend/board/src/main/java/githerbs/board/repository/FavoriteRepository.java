package githerbs.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import githerbs.board.entity.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite,Integer> {
	int countFavoritesByBoardId(int boardId);

	Optional<Favorite> findByMemberIdAndBoardIdANDFlagTrue(int memberId,int boardId);
	Optional<Favorite> findByMemberIdAndBoardId(int memberId,int boardId);

}
