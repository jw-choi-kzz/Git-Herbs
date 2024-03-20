package com.happiness.githerbs.domain.board.controller;

import java.io.IOException;

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

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/board")
@Slf4j
public class BoardController {

	private final BoardService boardService;

	// 자랑하기 글 작성
	@PostMapping
	public ResponseEntity<Object> writeBoard(@RequestBody BoardRequestDto request)  {
		int memberId = 1;
		return ResponseEntity.ok().body(boardService.writeBoard(memberId,request));
	}

	// 자랑하기 글 전체 조회
	@GetMapping
	public ResponseEntity<Object> getAllBoard(){
		return ResponseEntity.ok().body(boardService.getAll());
		// return ResponseEntity.ok().body("test");
	}

	//내가 올린 자랑하기 게시글 삭제
	@DeleteMapping("/{boardId}")
	public ResponseEntity<Object> deleteBoard(@PathVariable int boardId){
		int memberId = 1;
		boardService.removeBoard(memberId,boardId);
		return  ResponseEntity.ok().body("삭제 처리가 완료되었습니다.");
	}

	// 내가 올린 게시글 확인
	@GetMapping("/my-board")
	public  ResponseEntity<Object> myBoard(){
		int memberId ;

		return ResponseEntity.ok().body("");
	}

	// 내가 좋아요 누른 게시글 확인
	@GetMapping("/my-like")
	public  ResponseEntity<Object> myFavorite(){
		return ResponseEntity.ok().body("");
	}


	@PutMapping("/{boardId}")
	public ResponseEntity<Object> favoriteWrite(@PathVariable int boardId){
		return ResponseEntity.ok().body("");
	}
}
