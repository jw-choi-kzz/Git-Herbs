package com.happiness.githerbs.domain.member.dto.request;

import feign.form.FormProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Schema(description = "카카오 토큰 발급 DTO")
public class  KakaoTokenRequestDto{
	@Schema(description = "인증 타입")
	@FormProperty("grant_type")
	@Builder.Default
	String grantType = "authorization_code";
	@Schema(description = "앱 REST API 키")
	@NotBlank(message = "클라이언트 아이디는 필수값입니다")
	@FormProperty("client_id")
	String clientId;
	@Schema(description = "인가코드가 리다이렉트된 URI")
	@NotBlank(message = "리다이렉트 URI는 필수값입니다")
	@FormProperty("redirect_uri")
	String redirectUri;
	@Schema(description = "인가코드")
	@NotBlank(message = "인가코드는 필수값입니다")
	@FormProperty("code")
	String code;
	@Schema(description = "토큰 발급 시 보안을 강화하기 위해 추가 확인하는 코드, 필수값 아님")
	@FormProperty("client_secret")
	String clientSecret;

}
