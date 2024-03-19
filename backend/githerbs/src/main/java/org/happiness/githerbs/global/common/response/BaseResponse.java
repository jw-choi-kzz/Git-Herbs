package org.happiness.githerbs.global.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseResponse<T> {
	private int code;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T data;
}
