package githerbs.board.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.Response;

import githerbs.board.awsS3.S3Uploader;
import githerbs.board.dto.request.BoardRequestDto;
import githerbs.board.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

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

}
