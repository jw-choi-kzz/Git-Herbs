package com.happiness.githerbs.domain.herb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.happiness.githerbs.domain.herb.entity.MyHerb;

public interface MyHerbRepository extends JpaRepository<MyHerb, Integer>, MyHerbRepositoryCustomer {
	Optional<MyHerb> findByIdAndDeleted(Integer myHerbId, Boolean deleted);
}
