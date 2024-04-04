package com.happiness.githerbs.domain.auth.dto.response;

import com.happiness.githerbs.domain.auth.dto.common.MemberInfoDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
// @AllArgsConstructor
@Schema(name = "JwtExtrationResponseDto", description = "JWT 정보추출 응답 클래스")
public class JwtExtrationResponseDto extends MemberInfoDto {

	public static JwtExtrationResponseDto of(MemberInfoDto memberInfo) {
		return JwtExtrationResponseDto.builder()
			.memberId(memberInfo.getMemberId())
			.memberNickname(memberInfo.getMemberNickname())
			.scope(memberInfo.getScope())
			.build();
	}
}
