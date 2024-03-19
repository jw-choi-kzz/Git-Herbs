package githerbs.board.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
			return ResponseEntity.ok().body(boardService.writeBoard(request));
	}

	@GetMapping
	public ResponseEntity<Object> getAllBoard(){
		return ResponseEntity.ok().body(boardService.getAll());
		// return ResponseEntity.ok().body("test");
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
