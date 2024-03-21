package com.happiness.githerbs.domain.event.service;

import java.util.List;

import com.happiness.githerbs.domain.event.dto.response.RankingResponse;

public interface EventService {

	List<RankingResponse> findRanker();

}
