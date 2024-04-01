package com.happiness.githerbs.domain.herb.service;

import static com.happiness.githerbs.global.common.code.ErrorCode.*;

import com.happiness.githerbs.domain.event.entity.Badge;
import com.happiness.githerbs.domain.event.repository.BadgeRepository;
import com.happiness.githerbs.domain.member.entity.MemberBadge;
import com.happiness.githerbs.domain.member.repository.MemberBadgeRepository;
import com.happiness.githerbs.global.common.code.ErrorCode;
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

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MyHerbService {

	private final HerbRepository herbRepository;
	private final MemberRepository memberRepository;
	private final MyHerbRepository myHerbRepository;
	private final BadgeRepository badgeRepository;
	private final MemberBadgeRepository memberBadgeRepository;

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
		Optional<List<MyHerb>> myHerbs = myHerbRepository.findByMemberId(memberId);
		if( myHerbs.isPresent()){
			int size = myHerbs.get().size();
			if( size == 1 ){
				Badge badge = badgeRepository.findById(25).orElseThrow(()-> new BaseException(ErrorCode.BAD_REQUEST_ERROR));
				memberBadgeRepository.save(MemberBadge.builder()
						.badge(badge)
						.member(member)
						.build());

			}
			if( size == 5 ){
				Badge badge = badgeRepository.findById(26).orElseThrow(()-> new BaseException(ErrorCode.BAD_REQUEST_ERROR));
				memberBadgeRepository.save(MemberBadge.builder()
						.badge(badge)
						.member(member)
						.build());
			}

			if( size== 10){
				Badge badge = badgeRepository.findById(27).orElseThrow(()-> new BaseException(ErrorCode.BAD_REQUEST_ERROR));
				memberBadgeRepository.save(MemberBadge.builder()
						.badge(badge)
						.member(member)
						.build());
			}
		}


	}

	@Transactional
	public void deleteMyHerb(Integer memberId, Integer myHerbId) {
		MyHerb myHerb = myHerbRepository.findByIdAndMemberIdAndDeleted(myHerbId, memberId, false)
			.orElseThrow(() -> new BaseException(MY_HERB_NOT_FOUND));

		myHerb.deleteMyHerb();
	}
}
