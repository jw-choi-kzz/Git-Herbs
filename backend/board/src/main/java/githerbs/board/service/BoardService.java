package githerbs.board.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import githerbs.board.awsS3.S3Uploader;
import githerbs.board.dto.request.BoardRequestDto;
import githerbs.board.dto.response.BoardResponseDto;
import githerbs.board.entity.Board;
import githerbs.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	private final S3Uploader s3Uploader;

	public static Board toEntity(BoardRequestDto boardRequestDto) {
		return Board.builder()
			.imgUrl(boardRequestDto.getImgUrl())
			.similar(boardRequestDto.getSimilar())
			.myHerbId(boardRequestDto.getHerbId())
			.herbName(boardRequestDto.getHerbName())
			.build();
	}

	public BoardResponseDto writeBoard(BoardRequestDto boardRequestDto) {
		Board newBoard = boardRepository.save(toEntity(boardRequestDto));
		return new BoardResponseDto().entityTo(newBoard);
	}

	public List<BoardResponseDto> getAll(){
		List<Board> boards = boardRepository.findAllByFlagFalse();
		ArrayList<BoardResponseDto> boardResponseDtos = new ArrayList<>();
		for(Board board : boards){
			BoardResponseDto boardResponseDto = BoardResponseDto.builder().build().entityTo(board);
		}
		return Collections.emptyList();
	}


}
