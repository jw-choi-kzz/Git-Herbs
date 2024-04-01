package com.happiness.githerbs.domain.board.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.happiness.githerbs.domain.event.entity.Badge;
import com.happiness.githerbs.domain.event.repository.BadgeRepository;
import com.happiness.githerbs.domain.member.entity.MemberBadge;
import com.happiness.githerbs.domain.member.repository.MemberBadgeRepository;
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
	private final BadgeRepository badgeRepository;
	private final MemberBadgeRepository memberBadgeRepository;


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
		List<BoardResponseDto>  boardResponseDtos = makeResponseDtoList(boards,memberId);
		if(!boardResponseDtos.isEmpty()) badgeCheck(boardResponseDtos,memberId);
		return boardResponseDtos;
	}

	//Board 엔티티를 ResponseDtoList 형태로 반환

	public List<BoardResponseDto> makeResponseDtoList ( List<Board> boards,Integer memberId){
		ArrayList<BoardResponseDto> boardResponseDtoList = new ArrayList<>();
		for (Board board : boards) {
			BoardResponseDto boardResponseDto = BoardResponseDto.builder().build().entityTo(board);
			boardResponseDto.setLikeCheck(favoriteService.favoriteCheck(board.getBoardId(),memberId));
			boardResponseDtoList.add(boardResponseDto);
		}
		Collections.reverse(boardResponseDtoList);
		return boardResponseDtoList;
	}
	//Dto - > Entity로 변경

	public Board toEntity(BoardRequestDto boardRequestDto) {
		return Board.builder()
			.imgUrl(boardRequestDto.getImgUrl())
			.member(findMember(boardRequestDto.getMemberId()))
			.build();
	}

	private void badgeCheck(List<BoardResponseDto> boardResponseDtos, Integer memberId) {
		Member member = memberRepository.findById(memberId).orElseThrow(() -> new BaseException(ErrorCode.NOT_MATCH_MEMBER));
		int size = boardResponseDtos.size();
		boolean case1 = memberBadgeRepository.findByMemberIdAndBadgeId(memberId,28).isPresent();
		boolean case2 = memberBadgeRepository.findByMemberIdAndBadgeId(memberId,30).isPresent();
		boolean case3 = memberBadgeRepository.findByMemberIdAndBadgeId(memberId,31).isPresent();
		boolean case4 = memberBadgeRepository.findByMemberIdAndBadgeId(memberId,32).isPresent();
		boolean case5 = memberBadgeRepository.findByMemberIdAndBadgeId(memberId,33).isPresent();
		if( case5 && case3) return;
		if( !case1){
			Badge badge = badgeRepository.findById(28).orElseThrow(() -> new BaseException(ErrorCode.NOT_MATCH_MEMBER));
			memberBadgeRepository.save(MemberBadge.builder()
					.badge(badge)
					.member(member)
					.build());
		}
		if( size>=5 && !case2) {
			Badge badge = badgeRepository.findById(30).orElseThrow(() -> new BaseException(ErrorCode.NOT_MATCH_MEMBER));
			memberBadgeRepository.save(MemberBadge.builder()
					.badge(badge)
					.member(member)
					.build());
		}
		if( size>=10 && !case3) {
			Badge badge = badgeRepository.findById(31).orElseThrow(() -> new BaseException(ErrorCode.NOT_MATCH_MEMBER));
			memberBadgeRepository.save(MemberBadge.builder()
					.badge(badge)
					.member(member)
					.build());
		}
		int sum =0;
		for(BoardResponseDto boardRequestDto : boardResponseDtos){
			sum += boardRequestDto.getLikeCnt();
		}
		if( !case4 && sum >=10) {
			Badge badge = badgeRepository.findById(32).orElseThrow(() -> new BaseException(ErrorCode.NOT_MATCH_MEMBER));
			memberBadgeRepository.save(MemberBadge.builder()
					.badge(badge)
					.member(member)
					.build());
		}
		if( !case5 && sum >=50) {
			Badge badge = badgeRepository.findById(33).orElseThrow(() -> new BaseException(ErrorCode.NOT_MATCH_MEMBER));
			memberBadgeRepository.save(MemberBadge.builder()
					.badge(badge)
					.member(member)
					.build());
		}

	}




}
