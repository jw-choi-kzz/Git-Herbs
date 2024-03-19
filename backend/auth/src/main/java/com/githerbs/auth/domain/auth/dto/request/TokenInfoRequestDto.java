package com.githerbs.auth.domain.auth.dto.request;

import com.githerbs.auth.domain.auth.dto.common.JwtScopeDto;
import com.githerbs.auth.domain.auth.dto.common.MemberInfoDto;
import com.google.gson.annotations.SerializedName;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "TokenInfoRequestDto", description = "토큰 요청 클래스")
public class TokenInfoRequestDto extends MemberInfoDto {
	@Schema(description = "JWT 발급 타입")
	@SerializedName("grant_type")
	@NotBlank(message = "grant_type은 필수값입니다.")
	private JwtGrantTypeDto grantType;
	@Schema(description = "무작위 문자열")
	private String state;
	@Schema(description = "refresh_token")
	private String refreshToken;
	@Schema(description = "기기 고유 아이디")
	private String deviceId;

}
