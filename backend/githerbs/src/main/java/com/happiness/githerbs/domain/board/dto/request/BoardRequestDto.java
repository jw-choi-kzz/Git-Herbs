package com.happiness.githerbs.domain.board.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString
public class BoardRequestDto {
	String imgUrl;
	double similar;
	Integer memberId;
}

