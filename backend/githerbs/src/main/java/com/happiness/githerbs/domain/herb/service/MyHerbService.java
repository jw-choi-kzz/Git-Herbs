package com.happiness.githerbs.domain.herb.service;

import static com.happiness.githerbs.global.common.code.ErrorCode.*;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happiness.githerbs.domain.herb.dto.request.MyHerbRequestDto;
import com.happiness.githerbs.domain.herb.dto.response.MyHerbResponseDto;
import com.happiness.githerbs.domain.herb.entity.Herb;
import com.happiness.githerbs.domain.herb.entity.MyHerb;
import com.happiness.githerbs.domain.herb.repository.HerbRepository;
import com.happiness.githerbs.domain.herb.repository.MyHerbRepository;
import com.happiness.githerbs.domain.member.entity.Member;
import com.happiness.githerbs.domain.member.repository.MemberRepository;
import com.happiness.githerbs.global.common.exception.BaseException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MyHerbService {

	private final HerbRepository herbRepository;
	private final MemberRepository memberRepository;
	private final MyHerbRepository myHerbRepository;

	public Slice<MyHerbResponseDto> getMyHerbList(Integer memberId, Integer herbId, Pageable pageable) {
		Member member = memberRepository.findById(memberId).orElseThrow(() -> new BaseException(USER_NOT_FOUND));
		Herb herb = herbRepository.findById(herbId)
			.orElseThrow(() -> new BaseException(HERB_NOT_FOUND));

		return myHerbRepository.findMyHerbListByHerbIdAndMemberId(memberId, herbId, pageable);
	}

	@Transactional
	public void addMyHerb(Integer memberId, MyHerbRequestDto myHerbRequestDto) {
		Member member = memberRepository.findById(memberId).orElseThrow(() -> new BaseException(USER_NOT_FOUND));
		Herb herb = herbRepository.findById(myHerbRequestDto.herbId())
			.orElseThrow(() -> new BaseException(HERB_NOT_FOUND));
		myHerbRepository.save(MyHerb.builder()
			.member(member)
			.herb(herb)
			.imgId(myHerbRequestDto.imgId())
			.similarity(myHerbRequestDto.similarity())
			.build());
	}

	@Transactional
	public void deleteMyHerb(Integer myHerbId) {
		MyHerb myHerb = myHerbRepository.findByIdAndDeleted(myHerbId, false)
			.orElseThrow(() -> new BaseException(MY_HERB_NOT_FOUND));

		myHerb.deleteMyHerb();
	}
}
