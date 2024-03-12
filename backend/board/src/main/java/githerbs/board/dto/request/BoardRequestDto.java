package githerbs.board.dto.request;

import githerbs.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BoardRequestDto {
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

	public BoardRequestDto entityTo(Board board) {
		return BoardRequestDto.builder()
			.boardId(board.getBoardId())
			.userId(board.getUserId())
			.userNickname("")
			.userImgUrl("")
			.likeCnt(0)
			.likeCheck(false)
			.herbName("")
			.similar(0)
			.herbId(0)
			.build();
	}

}

