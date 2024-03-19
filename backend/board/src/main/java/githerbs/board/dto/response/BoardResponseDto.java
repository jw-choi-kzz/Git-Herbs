package githerbs.board.dto.response;

import java.time.LocalDate;

import githerbs.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BoardResponseDto {
	int boardId;
	int userId;
	String userNickname;
	String userImgUrl;
	String imgUrl;
	int likeCnt;
	boolean likeCheck;
	String herbName;
	double similar;
	int herbId;
	LocalDate createAt;

	public BoardResponseDto(){};
	public BoardResponseDto entityTo(Board board) {
		return BoardResponseDto.builder()
			.boardId(board.getBoardId())
			.userId(board.getMemberId())
			.userNickname("")
			.userImgUrl("")
			.likeCnt(0)
			.likeCheck(false)
			.herbName(board.getHerbName())
			.similar(board.getSimilar())
			.herbId(board.getMyHerbId())
			.createAt(LocalDate.from(board.getCreatedAt()))
			.build();
	}


}

