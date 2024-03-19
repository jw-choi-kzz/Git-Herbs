package com.githerbs.auth.domain.auth.dto.response;

import com.githerbs.auth.domain.auth.dto.common.AuthorizationTokenDto;
import com.google.gson.annotations.SerializedName;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "JwtResponseDto", description = "JWT 응답 클래스")
public class JwtResponseDto extends AuthorizationTokenDto {
	@Builder.Default
	@Schema(description = "JWT 발급 타입")
	private String tokenType = "Bearer";
	@Schema(description = "무작위 문자열")
	private String state;
	@Schema(description = "기기 고유 아이디")
	private String deviceId;
}
