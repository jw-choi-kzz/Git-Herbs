package com.happiness.githerbs.domain.event.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.happiness.githerbs.domain.event.dto.response.RankingResponse;
import com.happiness.githerbs.domain.member.repository.MemberDailyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

	private final MemberDailyRepository memberDailyRepository;

	@Override
	public List<RankingResponse> findRanker() {
		LocalDate current = LocalDate.now();
		LocalDate start = current.withDayOfMonth(1);
		return memberDailyRepository.findRanker(start, current);
	}

}
