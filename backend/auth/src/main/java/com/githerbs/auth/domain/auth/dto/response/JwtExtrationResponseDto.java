package com.githerbs.auth.domain.auth.dto.response;

import com.githerbs.auth.domain.auth.dto.common.JwtScopeDto;
import com.githerbs.auth.domain.auth.dto.common.MemberInfoDto;
import com.google.gson.annotations.SerializedName;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "JwtExtrationResponseDto", description = "JWT 정보추출 응답 클래스")
public class JwtExtrationResponseDto extends MemberInfoDto {
	@Schema(description = "무작위 문자열")
	private String state;

}
