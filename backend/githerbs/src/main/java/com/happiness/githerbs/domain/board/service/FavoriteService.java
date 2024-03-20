package com.happiness.githerbs.domain.board.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.happiness.githerbs.domain.board.entity.Favorite;
import com.happiness.githerbs.domain.board.repository.FavoriteRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class FavoriteService {
	private final FavoriteRepository favoriteRepository;

	public boolean favoriteCheck(int memberId, int feedId) {
		Optional<Favorite> optionalFavorite = favoriteRepository.findByMemberMemberIdAndBoardBoardIdAndFlagTrue(memberId, feedId);
		return optionalFavorite.isPresent();
	}

	public List<Favorite>  getFavoriteList(int memberId){
		return favoriteRepository.findByMemberMemberIdAndFlagFalse(memberId)
				.orElseGet(Collections::emptyList);

	}


	public boolean saveFavorite(int memberId, int boardId) {
		Optional<Favorite> optionalFavorite = favoriteRepository.findByMemberMemberIdAndBoardBoardId(memberId, boardId);
		boolean flag;
		if (optionalFavorite.isPresent()) {
			Favorite favorite = Favorite.builder()
				.favoriteId(optionalFavorite.get().getFavoriteId())
				.build();
			favoriteRepository.save(favorite);
			return favorite.isFlag();
		}
		Favorite favorite = Favorite.builder()
			.build();
		favoriteRepository.save(favorite);

		return true;

	}

	public int getFavoriteCnt(int boardId) {
		return  favoriteRepository.countFavoritesByBoardBoardId(boardId);

	}
}