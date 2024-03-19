package com.githerbs.herb.domain.service;

import static com.githerbs.herb.global.common.code.ErrorCode.*;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.githerbs.herb.domain.dto.MyHerbRequestDto;
import com.githerbs.herb.domain.dto.MyHerbResponseDto;
import com.githerbs.herb.domain.entity.Herb;
import com.githerbs.herb.domain.entity.Member;
import com.githerbs.herb.domain.entity.MyHerb;
import com.githerbs.herb.domain.repository.HerbRepository;
import com.githerbs.herb.domain.repository.MemberRepository;
import com.githerbs.herb.domain.repository.MyHerbRepository;
import com.githerbs.herb.global.common.exception.BaseException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyHerbService {

	private final HerbRepository herbRepository;
	private final MemberRepository memberRepository;
	private final MyHerbRepository myHerbRepository;

	@Transactional(readOnly = true)
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
