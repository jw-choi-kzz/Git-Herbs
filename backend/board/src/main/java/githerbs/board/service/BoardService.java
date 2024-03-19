package githerbs.board.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import githerbs.board.awsS3.S3Uploader;
import githerbs.board.dto.request.BoardRequestDto;
import githerbs.board.dto.response.BoardResponseDto;
import githerbs.board.dto.response.FavoriteResponseDto;
import githerbs.board.entity.Board;
import githerbs.board.repository.BoardRepository;
import githerbs.global.exception.CustomException;
import githerbs.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	private final FavoriteService favoriteService;

	public static Board toEntity(BoardRequestDto boardRequestDto) {
		return Board.builder()
			.imgUrl(boardRequestDto.getImgUrl())
			.memberId(boardRequestDto.getMemberId())
			.imgUrl(boardRequestDto.getImgUrl())
			.build();
	}

	@Transactional
	public BoardResponseDto writeBoard(int memberId,BoardRequestDto boardRequestDto) {
		boardRequestDto.setMemberId(memberId);
		System.out.println(boardRequestDto);
		Board newBoard = boardRepository.save(toEntity(boardRequestDto));
		return new BoardResponseDto().entityTo(newBoard);
	}

	@Transactional
	public void removeBoard(int memberId, int boardId){
		Board board = boardRepository.findById(boardId).orElseThrow(()-> new CustomException(ErrorCode.BOARD_NOT_FOUND));
		System.out.println(board);
		if(board.getMemberId() != memberId) throw new CustomException(ErrorCode.NOT_BOARD_OWNER);
		boardRepository.save(Board.builder()
				.boardId(board.getBoardId())
				.imgUrl(board.getImgUrl())
				.deleted(true)
				.memberId(board.getMemberId())
				.build());
	}

	public List<BoardResponseDto> getAll(){
		List<Board> boards = boardRepository.findAllByFlagFalse();
		ArrayList<BoardResponseDto> boardResponseDtos = new ArrayList<>();
		for(Board board : boards){
			BoardResponseDto boardResponseDto = BoardResponseDto.builder().build().entityTo(board);
			boardResponseDto.setLikeCheck(favoriteService.favoriteCheck(board.getMemberId(),board.getBoardId()));
			boardResponseDto.setLikeCnt(favoriteService.getFavoirteCnt(board.getBoardId()));
			boardResponseDtos.add(boardResponseDto);
		}
		return boardResponseDtos;
	}


	public FavoriteResponseDto toggleFavorite(int memberId, int boardId){
		boolean check = favoriteService.favoriteCheck(memberId,boardId);
		return new FavoriteResponseDto(1,check);
	}



}
