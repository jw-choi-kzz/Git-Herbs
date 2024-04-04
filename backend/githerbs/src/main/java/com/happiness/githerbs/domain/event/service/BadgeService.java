package com.happiness.githerbs.domain.event.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.happiness.githerbs.domain.board.repository.BoardRepository;
import com.happiness.githerbs.domain.board.repository.FavoriteRepository;
import com.happiness.githerbs.domain.event.dto.response.BadgeDto;
import com.happiness.githerbs.domain.event.entity.Badge;
import com.happiness.githerbs.domain.event.repository.BadgeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BadgeService {

	private final BoardRepository boardRepository;
	private final FavoriteRepository favoriteRepository;
	private final BadgeRepository badgeRepository;


	public List<BadgeDto> getBadge(Integer memberId){
		List<BadgeDto> badgeDtos = new ArrayList<>();
		if(favoriteRepository.findByMemberId(memberId).get().size() >0){
			Optional<Badge> optionalBadge = badgeRepository.findById(29);
			if( optionalBadge.isPresent()) {
				Badge badge = optionalBadge.get();
				badgeDtos.add(entityTo(badge));
			}
		} else{
			Optional<Badge> optionalBadge = badgeRepository.findById(29);
			if( optionalBadge.isPresent()) {
				Badge badge = optionalBadge.get();
				badgeDtos.add(falseEntityTo(badge));
			}
		}

		if(boardRepository.findByMemberIdAndDeletedFalse(memberId).get().size()>0) {
			Optional<Badge> optionalBadge = badgeRepository.findById(28);
			if( optionalBadge.isPresent()) {
				Badge badge = optionalBadge.get();
				badgeDtos.add(entityTo(badge));
			}
		} else{
			Optional<Badge> optionalBadge = badgeRepository.findById(28);
			if( optionalBadge.isPresent()) {
				Badge badge = optionalBadge.get();
				badgeDtos.add(falseEntityTo(badge));
			}
		}

		badgeDtos.add(BadgeDto.builder()
				.badgeId(23)
				.name("약초 비기너")
				.details("최초 가입을 완료했을 때 얻는 뱃지")
				.check(true)
				.build());

		badgeDtos.add(BadgeDto.builder()
			.badgeId(33)
			.name("좋아요 부자")
			.details("게시글에 50개의 좋아요를 받았을 때 얻는 뱃지")
			.check(false)
			.build());



		return badgeDtos;
	}

	public BadgeDto entityTo(Badge badge){
		return BadgeDto.builder()
			.badgeId(badge.getId())
			.details(badge.getDetails())
			.name(badge.getName())
			.check(true)
			.build();
	}

	public BadgeDto falseEntityTo(Badge badge){
		return BadgeDto.builder()
			.badgeId(badge.getId())
			.details(badge.getDetails())
			.name(badge.getName())
			.check(false)
			.build();
	}

}
