package githerbs.board.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import githerbs.board.entity.Board;
import githerbs.board.repository.BoardRepository;
import githerbs.board.entity.Favorite;
import githerbs.board.repository.FavoriteRepository;
import githerbs.global.exception.CustomException;
import githerbs.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class FavoriteService {
	private final FavoriteRepository favoriteRepository;
	private final BoardRepository boardRepository;

	public boolean favoriteCheck(int memberId, int feedId) {
		Optional<Favorite> optionalFavorite = favoriteRepository.findByMemberIdAndBoardBoardIdAndFlagTrue(memberId, feedId);
		return optionalFavorite.isPresent();
	}

	public boolean saveFavorite(int memberId, int boardId) {
		Board board = boardRepository.findByBoardIdAndFlagFalse(boardId)
			.orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
		Optional<Favorite> optionalFavorite = favoriteRepository.findByMemberIdAndBoardBoardId(memberId, boardId);
		boolean flag;
		if (optionalFavorite.isPresent()) {
			Favorite favorite = Favorite.builder()
				.favoriteId(optionalFavorite.get().getFavoriteId())
				.memberId(memberId)
				.board(board)
				.build();
			favorite.setFlag(!optionalFavorite.get().isFlag());
			favoriteRepository.save(favorite);
			return favorite.isFlag();
		}
		Favorite favorite = Favorite.builder()
			.memberId(memberId)
			.board(board)
			.build();
		favorite.setFlag(true);
		favoriteRepository.save(favorite);

		return true;

	}

	public int getFavoirteCnt(int boardId) {
		return  favoriteRepository.countFavoritesByBoardBoardId(boardId);

	}
}
