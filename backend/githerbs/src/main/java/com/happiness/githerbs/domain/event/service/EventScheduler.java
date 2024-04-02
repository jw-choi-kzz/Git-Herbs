package com.happiness.githerbs.domain.event.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.happiness.githerbs.domain.event.entity.Quiz;
import com.happiness.githerbs.domain.event.repository.QuizRepository;
import com.happiness.githerbs.domain.herb.entity.Herb;
import com.happiness.githerbs.domain.herb.entity.HerbDaily;
import com.happiness.githerbs.domain.herb.repository.HerbDailyRepository;
import com.happiness.githerbs.domain.herb.repository.HerbRepository;
import com.happiness.githerbs.global.common.code.ErrorCode;
import com.happiness.githerbs.global.common.exception.BaseException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EventScheduler {

	private final HerbRepository herbRepository;
	private final QuizRepository quizRepository;
	private final HerbDailyRepository herbDailyRepository;

	@Transactional
	@Scheduled(cron = "0 0 0 * * ?")
	public void quizDaily() throws NoSuchAlgorithmException {
		SecureRandom random = SecureRandom.getInstanceStrong();

		Set<Integer> set = new HashSet<>();
		List<Herb> herbs = new ArrayList<>();

		while (set.size() < 4) {
			int id = random.nextInt(1, 136);
			if (!set.contains(id) && herbRepository.findById(id).isPresent()) {
				set.add(id);
				herbs.add(herbRepository.findById(id).orElseThrow(() -> new BaseException(ErrorCode.HERB_NOT_FOUND)));
			}
		}

		int answer = random.nextInt(0, 3);

		Quiz quiz = Quiz.builder()
			.id(1)
			.answer(answer)
			.question(herbs.get(answer).getHerbName() + " 약초를 찾아주세요!")
			.imgOne(herbs.get(0).getHerbImg())
			.imgTwo(herbs.get(1).getHerbImg())
			.imgThree(herbs.get(2).getHerbImg())
			.imgFour(herbs.get(3).getHerbImg())
			.build();
		quizRepository.saveAndFlush(quiz);
	}

	@Transactional
	@Scheduled(cron = "0 0 0 * * ?")
	public void herbDaily() throws NoSuchAlgorithmException {
		SecureRandom random = SecureRandom.getInstanceStrong();
		int number = -1;
		while (number < 0) {
			int id = random.nextInt(1, 136);
			if (herbRepository.findById(id).isPresent()) {
				number = id;
			}
		}
		Herb herb = herbRepository.findById(number).orElseThrow(() -> new BaseException(ErrorCode.HERB_NOT_FOUND));
		HerbDaily herbDaily = new HerbDaily(1, herb);
		herbDailyRepository.save(herbDaily);
	}

}
