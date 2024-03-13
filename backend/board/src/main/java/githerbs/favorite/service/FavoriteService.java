package githerbs.favorite.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import githerbs.favorite.entity.Favorite;
import githerbs.favorite.repository.FavoriteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class FavoriteService {
	private final FavoriteRepository favoriteRepository;


	public boolean favoriteCheck(int memberId,int feedId){
		Optional<Favorite> optionalFavorite = favoriteRepository.findByMemberIdAndBoardId(memberId ,feedId);
		return optionalFavorite.isPresent();
	}
}
