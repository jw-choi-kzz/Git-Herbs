package githerbs.board.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import githerbs.board.awsS3.S3Uploader;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/board")
public class BoardController {
	private final S3Uploader s3Uploader;


	@PostMapping
	public ResponseEntity<Object> uploadtest(@RequestPart  MultipartFile img ,@RequestPart String a) throws IOException {
		System.out.println(a);
		System.out.println(s3Uploader.upload(img, "hello"));

			return ResponseEntity.ok().body(new Object())  ;
	}

	@PostMapping("/test")
	public ResponseEntity<Object> test(File img ) throws IOException {
		s3Uploader.upload((MultipartFile)img,"herbs205");
		return ResponseEntity.ok().body(new Object())  ;
	}
}
