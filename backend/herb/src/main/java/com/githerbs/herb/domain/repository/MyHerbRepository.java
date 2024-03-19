package com.githerbs.herb.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.githerbs.herb.domain.entity.MyHerb;

public interface MyHerbRepository extends JpaRepository<MyHerb, Integer>, MyHerbRepositoryCustomer {
	Optional<MyHerb> findByIdAndDeleted(Integer myHerbId, Boolean deleted);
}
