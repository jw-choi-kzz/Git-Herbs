package com.happiness.githerbs.domain.event.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.happiness.githerbs.domain.event.entity.Quiz;
import com.happiness.githerbs.domain.event.repository.QuizRepository;
import com.happiness.githerbs.domain.herb.repository.HerbImageRepository;
import com.happiness.githerbs.domain.herb.repository.HerbRepository;
import com.happiness.githerbs.global.common.code.ErrorCode;
import com.happiness.githerbs.global.common.exception.BaseException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QuizScheduler {

	private final HerbRepository herbRepository;
	private final HerbImageRepository herbImageRepository;
	private final QuizRepository quizRepository;

	@Scheduled(cron = "0 0 0 * * ?")
	public void quizDaily() throws NoSuchAlgorithmException {
		SecureRandom random = SecureRandom.getInstanceStrong();

		Set<Integer> set = new HashSet<>();

		while (set.size() < 4) {
			int id = random.nextInt(1, 9);
			if (!set.contains(id) && herbRepository.findById(id).isPresent()) {
				set.add(id);
			}
		}

		List<Integer> list = set.stream().toList();
		int answer = random.nextInt(0, 3);

		String herbName = herbRepository.findById(list.get(answer))
			.orElseThrow(() -> new BaseException(ErrorCode.INTERNAL_SERVER_ERROR))
			.getHerbName();

		Quiz quiz = Quiz.builder()
			.id(1)
			.answer(answer)
			.question(LocalDate.now() + " 퀴즈 !! \n" + herbName + " 약초를 찾아주세요!")
			.imgOne(herbImageRepository.findByHerb(list.get(0)))
			.imgTwo(herbImageRepository.findByHerb(list.get(1)))
			.imgThree(herbImageRepository.findByHerb(list.get(2)))
			.imgFour(herbImageRepository.findByHerb(list.get(3)))
			.build();
		quizRepository.saveAndFlush(quiz);
	}

}
