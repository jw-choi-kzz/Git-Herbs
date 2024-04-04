package com.happiness.githerbs.domain.event.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.happiness.githerbs.domain.event.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer>, QuizRepositoryCustom {

	Optional<Quiz> findFirstBy();

}
