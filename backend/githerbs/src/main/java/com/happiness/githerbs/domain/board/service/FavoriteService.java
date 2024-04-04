package com.happiness.githerbs.domain.board.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.happiness.githerbs.domain.board.dto.response.FavoriteResponseDto;
import com.happiness.githerbs.domain.board.entity.Board;
import com.happiness.githerbs.domain.board.entity.Favorite;
import com.happiness.githerbs.domain.board.repository.BoardRepository;
import com.happiness.githerbs.domain.board.repository.FavoriteRepository;
import com.happiness.githerbs.domain.member.entity.Member;
import com.happiness.githerbs.domain.member.entity.MemberDaily;
import com.happiness.githerbs.domain.member.repository.MemberDailyRepository;
import com.happiness.githerbs.domain.member.repository.MemberRepository;
import com.happiness.githerbs.global.common.code.ErrorCode;
import com.happiness.githerbs.global.common.exception.BaseException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class FavoriteService {
	private final FavoriteRepository favoriteRepository;
	private final BoardRepository boardRepository;
	private  final MemberRepository memberRepository;
	private final MemberDailyRepository memberDailyRepository;


	//해당 글에 좋아요 누른 게 있는지 확인
	public boolean favoriteCheck( Integer boardId, Integer memberId) {
		Optional<Favorite> optionalFavorite = favoriteRepository.findByMemberIdAndBoardBoardIdAndDeletedTrue(memberId, boardId);
		return optionalFavorite.isPresent();
	}

	// 좋아요 한 글 반환
	public List<Favorite>  getFavoriteList(int memberId){
		return favoriteRepository.findByMemberIdAndDeletedTrue(memberId).orElseGet(Collections::emptyList);
	}



	// 좋아요 기능
	@Transactional
	public FavoriteResponseDto saveFavorite(Integer memberId, Integer boardId) {
		Optional<Favorite> optionalFavorite = favoriteRepository.findByMemberIdAndBoardBoardId(memberId, boardId);
		//좋아요 누른적이 있다면
		if (optionalFavorite.isPresent()) {
			if(optionalFavorite.get().getBoard().isDeleted()) throw new BaseException(ErrorCode.NOT_VALID_FAVORITE);
			Favorite favorite = Favorite.builder()
				.favoriteId(optionalFavorite.get().getFavoriteId())
				.board(optionalFavorite.get().getBoard())
				.member(optionalFavorite.get().getMember())
				.deleted(!optionalFavorite.get().isDeleted())
				.build();
			favoriteRepository.save(favorite);
			return new FavoriteResponseDto(boardId,favorite.isDeleted())  ;
		}
		//없다면 조회해서 객체 생성 후 저장
		Board board = boardRepository.findById(boardId).orElseThrow(() -> new BaseException(ErrorCode.BOARD_NOT_FOUND));
		Member member = memberRepository.findById(memberId).orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

		Favorite favorite = Favorite.builder()
			.member(member)
			.board(board)
			.deleted(true)
			.build();
		favoriteRepository.save(favorite);

		var byMemberIdAndDate = memberDailyRepository.findByMemberIdAndDate(memberId,
			LocalDate.now());
		MemberDaily memberDaily;
		if (byMemberIdAndDate.isPresent()) {
			memberDaily = byMemberIdAndDate.get();
		} else {
			memberDaily = MemberDaily
				.builder()
				.member(memberRepository.findById(memberId).orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND)))
				.date(LocalDate.now())
				.build();
			memberDailyRepository.saveAndFlush(memberDaily);
		}
		if (!memberDaily.getDate().equals(LocalDate.now())) {
			throw new BaseException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		if (!memberDaily.isFavorite()) {
			memberDailyRepository.updateDailyFavorite(memberId);
		}



		return new FavoriteResponseDto(boardId,favorite.isDeleted());

	}

}