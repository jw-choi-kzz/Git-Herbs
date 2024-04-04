package com.happiness.githerbs.domain.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FavoriteResponseDto {
	int boardId;
	boolean flag;

}
