package com.happiness.githerbs.domain.board.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.happiness.githerbs.domain.board.dto.request.BoardRequestDto;
import com.happiness.githerbs.domain.board.dto.response.BoardResponseDto;
import com.happiness.githerbs.domain.board.entity.Board;
import com.happiness.githerbs.domain.board.entity.Favorite;
import com.happiness.githerbs.domain.board.repository.BoardRepository;
import com.happiness.githerbs.domain.member.entity.Member;
import com.happiness.githerbs.domain.member.repository.MemberRepository;
import com.happiness.githerbs.global.common.code.ErrorCode;
import com.happiness.githerbs.global.common.exception.BaseException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BoardService {

	private final BoardRepository boardRepository;
	private final FavoriteService favoriteService;
	private final MemberRepository memberRepository;
	//해당 하는 멤버 찾기
	public Member findMember(Integer memberId){
		return memberRepository.findById(memberId).orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));
	}

	// 게시글 등록하기

	@Transactional
	public BoardResponseDto writeBoard(Integer memberId, BoardRequestDto boardRequestDto) {
		boardRequestDto.setMemberId(memberId);
		Board newBoard = boardRepository.save(toEntity(boardRequestDto));
		return new BoardResponseDto().entityTo(newBoard);
	}

	//게시글 삭제

	@Transactional
	public void removeBoard(Integer memberId, Integer boardId) {
		Board board = boardRepository.findById(boardId).orElseThrow(() -> new BaseException(ErrorCode.BOARD_NOT_FOUND));
		if(!Objects.equals(board.getMember().getId(), memberId)) throw new BaseException(ErrorCode.NOT_MATCH_MEMBER);

		boardRepository.save(Board.builder()
			.boardId(board.getBoardId())
			.imgUrl(board.getImgUrl())
			.deleted(true)
			.member(board.getMember())
			.build());
	}

	//전체 글 조회
	public List<BoardResponseDto> getAll(Integer memberId) {
		List<Board> boards = boardRepository.findAllByDeletedFalse().orElseGet(Collections::emptyList);
		return makeResponseDtoList(boards,memberId);
	}


	//내가 좋아요 한 글 불러오기
	public List<BoardResponseDto> myFavorite(Integer memberId) {
		List<BoardResponseDto> boardResponseDtos = new ArrayList<>();
		List<Favorite> favoriteBoardIds = favoriteService.getFavoriteList(memberId);

		for(Favorite favorite : favoriteBoardIds){
			if(favorite.getBoard().isDeleted()) continue;
			boardResponseDtos.add(new BoardResponseDto().entityTo(favorite.getBoard(),1));
		}
		return boardResponseDtos;
	}

	//내가 쓴 글 조회
	public List<BoardResponseDto> getMyBoard(Integer memberId) {
		List<Board> boards = boardRepository.findByMemberIdAndDeletedFalse(memberId).orElseGet(Collections::emptyList);
		return makeResponseDtoList(boards,memberId);
	}

	//Board 엔티티를 ResponseDtoList 형태로 반환
	public List<BoardResponseDto> makeResponseDtoList ( List<Board> boards,Integer memberId){
		ArrayList<BoardResponseDto> boardResponseDtoList = new ArrayList<>();
		for (Board board : boards) {
			BoardResponseDto boardResponseDto = BoardResponseDto.builder().build().entityTo(board);
			boardResponseDto.setLikeCheck(favoriteService.favoriteCheck(board.getBoardId(),memberId));
			boardResponseDtoList.add(boardResponseDto);
		}
		return boardResponseDtoList;
	}

	//Dto - > Entity로 변경
	public Board toEntity(BoardRequestDto boardRequestDto) {
		return Board.builder()
			.imgUrl(boardRequestDto.getImgUrl())
			.member(findMember(boardRequestDto.getMemberId()))
			.build();
	}
}
