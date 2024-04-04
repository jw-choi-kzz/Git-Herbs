package com.happiness.githerbs.domain.auth.dto.common;

import com.google.gson.annotations.SerializedName;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "MemberInfoDto", description = "사용자 정보 클래스")
public class MemberInfoDto {
	@Schema(description = "사용자 아이디")
	@NotBlank(message = "memberId는 필수값입니다.")
	private int memberId;

	@Schema(description = "사용자 닉네임")
	@NotBlank(message = "memberNickname은 필수값입니다.")
	private String memberNickname;

	@Builder.Default
	@Schema(description = "사용자 권한")
	@SerializedName("scope")
	private JwtScopeDto scope = JwtScopeDto.MEMBER;
}
