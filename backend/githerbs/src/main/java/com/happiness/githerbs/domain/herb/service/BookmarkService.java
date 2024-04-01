package com.happiness.githerbs.domain.herb.service;

import static com.happiness.githerbs.global.common.code.ErrorCode.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happiness.githerbs.domain.herb.entity.Bookmark;
import com.happiness.githerbs.domain.herb.entity.Herb;
import com.happiness.githerbs.domain.herb.repository.BookmarkRepository;
import com.happiness.githerbs.domain.herb.repository.HerbRepository;
import com.happiness.githerbs.domain.member.entity.Member;
import com.happiness.githerbs.domain.member.entity.MemberDaily;
import com.happiness.githerbs.domain.member.repository.MemberDailyRepository;
import com.happiness.githerbs.domain.member.repository.MemberRepository;
import com.happiness.githerbs.global.common.code.ErrorCode;
import com.happiness.githerbs.global.common.exception.BaseException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookmarkService {

	private final BookmarkRepository bookmarkRepository;
	private final HerbRepository herbRepository;
	private final MemberRepository memberRepository;
	private final MemberDailyRepository memberDailyRepository;

	@Transactional
	public void addBookmark(Integer memberId, Integer herbId) {
		Member member = memberRepository.findById(memberId).orElseThrow(() -> new BaseException(USER_NOT_FOUND));
		Herb herb = herbRepository.findById(herbId).orElseThrow(() -> new BaseException(HERB_NOT_FOUND));

		Optional<Bookmark> bookmark = bookmarkRepository.findByHerbIdAndMemberId(herbId, memberId);
		if (bookmark.isEmpty()) {
			bookmarkRepository.save(Bookmark.builder().member(member).herb(herb).build());

			MemberDaily memberDaily = memberDailyRepository.findFirstByMemberOrderByDateDesc(
				memberRepository.findById(memberId)
					.orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND)));

			if (!memberDaily.getDate().equals(LocalDate.now())) {
				throw new BaseException(ErrorCode.INTERNAL_SERVER_ERROR);
			}
			if (!memberDaily.isBookmark()) {
				memberDailyRepository.updateDailyBookmark(memberId);
			}

		} else {
			throw new BaseException(BOOKMARK_DUPLICATED);
		}
	}

	@Transactional
	public void deleteBookmark(Integer memberId, Integer herbId) {
		Bookmark bookmark = bookmarkRepository.findByHerbIdAndMemberId(herbId, memberId)
			.orElseThrow(() -> new BaseException(BOOKMARK_NOT_FOUND));
		bookmarkRepository.delete(bookmark);
	}


	public boolean getBookmark(Integer memberId, Integer herbId) {
		Member member = memberRepository.findById(memberId).orElseThrow(() -> new BaseException(USER_NOT_FOUND));
		Herb herb = herbRepository.findById(herbId).orElseThrow(() -> new BaseException(HERB_NOT_FOUND));

		Optional<Bookmark> bookmark = bookmarkRepository.findByHerbIdAndMemberId(herb.getId(), member.getId());
        return bookmark.isPresent();
	}


	public Integer recentBookmark(Integer memberId){
		List<Bookmark> bookmarkList = bookmarkRepository.findByMemberIdOrderByCreatedAtDesc(memberId);

		if(!bookmarkList.isEmpty()){
			return bookmarkList.get(0).getHerb().getId();
		}
		return 0;
	}
}