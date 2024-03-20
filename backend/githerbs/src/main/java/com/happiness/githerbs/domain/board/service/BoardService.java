package com.happiness.githerbs.domain.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.happiness.githerbs.domain.board.dto.request.BoardRequestDto;
import com.happiness.githerbs.domain.board.dto.response.BoardResponseDto;
import com.happiness.githerbs.domain.board.dto.response.FavoriteResponseDto;
import com.happiness.githerbs.domain.board.entity.Board;
import com.happiness.githerbs.domain.board.entity.Favorite;
import com.happiness.githerbs.domain.board.repository.BoardRepository;
import com.happiness.githerbs.domain.member.entity.Member;
import com.happiness.githerbs.domain.member.repository.MemberRepository;
import com.happiness.githerbs.global.common.code.ErrorCode;
import com.happiness.githerbs.global.common.exception.BaseException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	private final FavoriteService favoriteService;
	private final MemberRepository memberRepository;

	public Member findMember(Integer memberId){
		return memberRepository.findById(memberId).orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));
	}


	public Board toEntity(BoardRequestDto boardRequestDto) {
		return Board.builder()
			.imgUrl(boardRequestDto.getImgUrl())
			.memberId(findMember(boardRequestDto.getMemberId()))
			.build();
	}

	@Transactional
	public BoardResponseDto writeBoard(int memberId, BoardRequestDto boardRequestDto) {
		boardRequestDto.setMemberId(memberId);
		Board newBoard = boardRepository.save(toEntity(boardRequestDto));
		return new BoardResponseDto().entityTo(newBoard);
	}

	@Transactional
	public void removeBoard(int memberId, int boardId) {
		Board board = boardRepository.findById(boardId).orElseThrow(() -> new BaseException(ErrorCode.BOARD_NOT_FOUND));
		if(board.getMemberId().getId() != memberId) throw new BaseException(ErrorCode.NOT_MATCH_MEMBER);

		boardRepository.save(Board.builder()
			.boardId(board.getBoardId())
			.imgUrl(board.getImgUrl())
			.deleted(true)
			.memberId(board.getMemberId())
			.build());
	}

	public List<BoardResponseDto> getAll() {
		List<Board> boards = boardRepository.findAllByFlagFalse();
		ArrayList<BoardResponseDto> boardResponseDtos = new ArrayList<>();
		for (Board board : boards) {
			BoardResponseDto boardResponseDto = BoardResponseDto.builder().build().entityTo(board);
			boardResponseDto.setLikeCnt(favoriteService.getFavoriteCnt(board.getBoardId()));
			boardResponseDtos.add(boardResponseDto);
		}
		return boardResponseDtos;
	}

	public List<BoardResponseDto> myFavorite(int memberId) {
		List<BoardResponseDto> boardResponseDtos = new ArrayList<>();
		List<Favorite> favoriteBoardIds = favoriteService.getFavoriteList(memberId);

		return boardResponseDtos;

	}

	public FavoriteResponseDto toggleFavorite(int memberId, int boardId) {
		boolean check = favoriteService.favoriteCheck(memberId, boardId);
		return new FavoriteResponseDto(1, check);
	}

}
