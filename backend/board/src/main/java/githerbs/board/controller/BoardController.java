package githerbs.board.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import githerbs.board.dto.request.BoardRequestDto;
import githerbs.board.service.BoardService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/board")
public class BoardController {

	private final BoardService boardService;

	@PostMapping
	public ResponseEntity<Object> writeBoard(@RequestBody BoardRequestDto request) throws IOException {
		int memberId = 1;
			return ResponseEntity.ok().body(boardService.writeBoard(memberId,request));
	}
	@GetMapping
	public ResponseEntity<Object> getAllBoard(){
		return ResponseEntity.ok().body(boardService.getAll());
		// return ResponseEntity.ok().body("test");
	}
	@DeleteMapping("/{boardId}")
	public ResponseEntity<Object> deleteBoard(@PathVariable int boardId){
		int memberId = 1;
		boardService.removeBoard(memberId,boardId);
		return  ResponseEntity.ok().body("삭제 처리가 완료되었습니다.");
	}

	@GetMapping("/my-board")
	public  ResponseEntity<Object> myBoard(){
		int memberId ;

		return ResponseEntity.ok().body("");
	}

	@GetMapping("/my-like")
	public  ResponseEntity<Object> myFavorite(){
		return ResponseEntity.ok().body("");
	}

}
