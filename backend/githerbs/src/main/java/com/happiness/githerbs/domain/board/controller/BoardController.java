package com.happiness.githerbs.domain.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happiness.githerbs.domain.auth.service.JwtService;
import com.happiness.githerbs.domain.board.dto.request.BoardRequestDto;
import com.happiness.githerbs.domain.board.service.BoardService;
import com.happiness.githerbs.domain.board.service.FavoriteService;
import com.happiness.githerbs.global.common.response.SuccessResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/board")
@Slf4j
public class BoardController {

	private final BoardService boardService;
	private final FavoriteService favoriteService;
	private final JwtService token;

	// 자랑하기 글 작성
	@PostMapping
	public ResponseEntity<SuccessResponse<?>> writeBoard(@RequestHeader String authorization,@RequestBody BoardRequestDto request) {
		Integer memberId = token.validateToken(authorization).getMemberId();
		return ResponseEntity.ok(
			new SuccessResponse<>(HttpStatus.OK.value() , boardService.writeBoard(memberId,request)));

	}

	// 자랑하기 글 전체 조회
	@GetMapping
	public ResponseEntity<SuccessResponse<?>> getAllBoard(@RequestHeader(required = false) String authorization) {
		int memberId = 0 ;
		if(authorization != null) memberId = token.validateToken(authorization).getMemberId();
		return ResponseEntity.ok(
			new SuccessResponse<>(HttpStatus.OK.value() , boardService.getAll(memberId)));
	}

	//내가 올린 자랑하기 게시글 삭제
	@DeleteMapping("/{boardId}")
	public ResponseEntity<SuccessResponse<?>> deleteBoard(@RequestHeader String authorization ,@PathVariable Integer boardId) {
		Integer memberId = token.validateToken(authorization).getMemberId();
		System.out.println(memberId);
		boardService.removeBoard(memberId, boardId);
		return ResponseEntity.ok(
			new SuccessResponse<>(HttpStatus.OK.value(), "삭제가 완료되었습니다."));
	}

	// 내가 올린 게시글 확인
	@GetMapping("/my-board")
	public ResponseEntity<SuccessResponse<?>> myBoard(@RequestHeader String authorization) {
		Integer memberId = token.validateToken(authorization).getMemberId();
		return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK.value(), boardService.getMyBoard(memberId)));
	}

	// 내가 좋아요 누른 게시글 확인
	@GetMapping("/my-like")
	public ResponseEntity<SuccessResponse<?>> myFavorite(@RequestHeader String authorization) {
		Integer memberId = token.validateToken(authorization).getMemberId();
		return ResponseEntity.ok(
			new SuccessResponse<>(HttpStatus.OK.value(), boardService.myFavorite(memberId)));
	}

	//좋아요 누르기
	@PutMapping("/{boardId}")
	public ResponseEntity<SuccessResponse<?>> favoriteWrite(@RequestHeader String authorization, @PathVariable int boardId) {
		Integer memberId = token.validateToken(authorization).getMemberId();
		return ResponseEntity.ok(
			new SuccessResponse<>(HttpStatus.OK.value(), favoriteService.saveFavorite(memberId,boardId)));
	}
}
