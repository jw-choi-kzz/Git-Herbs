package com.githerbs.herb.domain.service;

import static com.githerbs.herb.global.common.code.ErrorCode.*;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.githerbs.herb.domain.entity.Bookmark;
import com.githerbs.herb.domain.entity.Herb;
import com.githerbs.herb.domain.entity.Member;
import com.githerbs.herb.domain.repository.BookmarkRepository;
import com.githerbs.herb.domain.repository.HerbRepository;
import com.githerbs.herb.domain.repository.MemberRepository;
import com.githerbs.herb.global.common.exception.BaseException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookmarkService {

	private final BookmarkRepository bookmarkRepository;
	private final HerbRepository herbRepository;
	private final MemberRepository memberRepository;

	public void addBookmark(Integer memberId, Integer herbId) {
		Member member = memberRepository.findById(memberId).orElseThrow(() -> new BaseException(USER_NOT_FOUND));
		Herb herb = herbRepository.findById(herbId).orElseThrow(() -> new BaseException(HERB_NOT_FOUND));

		Optional<Bookmark> bookmark = bookmarkRepository.findByHerbIdAndMemberId(herbId, memberId);
		if (bookmark.isEmpty()) {
			bookmarkRepository.save(Bookmark.builder().member(member).herb(herb).build());
		} else {
			throw new BaseException(BOOKMARK_DUPLICATED);
		}
	}

	public void deleteBookmark(Integer memberId, Integer herbId) {
		Bookmark bookmark = bookmarkRepository.findByHerbIdAndMemberId(herbId, memberId)
			.orElseThrow(() -> new BaseException(BOOKMARK_NOT_FOUND));
		bookmarkRepository.delete(bookmark);
	}
}