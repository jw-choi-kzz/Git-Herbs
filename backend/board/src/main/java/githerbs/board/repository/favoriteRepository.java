package githerbs.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import githerbs.board.entity.Favorite;

@Repository
public interface favoriteRepository extends JpaRepository<Favorite,Integer> {
}
