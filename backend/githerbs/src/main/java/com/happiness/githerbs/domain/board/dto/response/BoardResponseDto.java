package com.happiness.githerbs.domain.board.dto.response;

import java.time.LocalDate;

import com.happiness.githerbs.domain.board.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BoardResponseDto {
	int boardId;
	int memberId;
	String userNickname;
	String userImgUrl;
	String imgUrl;
	int likeCnt;
	boolean likeCheck;
	LocalDate createAt;

	public BoardResponseDto(){};
	public BoardResponseDto entityTo(Board board) {
		LocalDate createdAt = null;
		if (board.getCreatedAt() != null) {
			createdAt = LocalDate.from(board.getCreatedAt());
		}
		return BoardResponseDto.builder()
			.boardId(board.getBoardId())
			.memberId(board.getMemberId().getId())
			.imgUrl(board.getImgUrl())
			.userNickname("")
			.userImgUrl("")
			.likeCnt(0)
			.likeCheck(false)
			.createAt(createdAt)
			.build();
	}


}

