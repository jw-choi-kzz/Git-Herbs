package com.happiness.githerbs.domain.board.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happiness.githerbs.domain.board.dto.request.BoardRequestDto;
import com.happiness.githerbs.domain.board.service.BoardService;
import com.happiness.githerbs.domain.board.service.FavoriteService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/board")
@Slf4j
public class BoardController {

	private final BoardService boardService;
	private final FavoriteService favoriteService;


	// 자랑하기 글 작성
	@PostMapping
	public ResponseEntity<Object> writeBoard(@RequestBody BoardRequestDto request) {
		Integer memberId = 1;
		return ResponseEntity.ok().body(boardService.writeBoard(memberId, request));
	}

	// 자랑하기 글 전체 조회
	@GetMapping
	public ResponseEntity<Object> getAllBoard() {
		Integer memberId = 1;
		return ResponseEntity.ok().body(boardService.getAll(memberId));
		// return ResponseEntity.ok().body("test");
	}

	//내가 올린 자랑하기 게시글 삭제
	@DeleteMapping("/{boardId}")
	public ResponseEntity<Object> deleteBoard(@PathVariable Integer boardId) {
		Integer memberId = 1;
		boardService.removeBoard(memberId, boardId);
		return ResponseEntity.ok().body("삭제 처리가 완료되었습니다.");
	}

	// 내가 올린 게시글 확인
	@GetMapping("/my-board")
	public ResponseEntity<Object> myBoard() {
		Integer memberId = 1 ;
		return ResponseEntity.ok().body(boardService.getMyBoard(memberId));
	}

	// 내가 좋아요 누른 게시글 확인
	@GetMapping("/my-like")
	public ResponseEntity<Object> myFavorite() {
		Integer memberId = 1;
		return ResponseEntity.ok().body(boardService.myFavorite(memberId) );
	}

	//좋아요 누르기
	@PutMapping("/{boardId}")
	public ResponseEntity<Object> favoriteWrite(@PathVariable int boardId) {
		Integer memberId = 1;
		return ResponseEntity.ok().body(favoriteService.saveFavorite(memberId,boardId));
	}
}
