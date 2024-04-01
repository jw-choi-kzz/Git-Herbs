package com.happiness.githerbs.domain.herb.repository;

import com.happiness.githerbs.domain.herb.entity.MyHerb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MyHerbRepository extends JpaRepository<MyHerb, Integer>, MyHerbRepositoryCustomer {
	Optional<MyHerb> findByIdAndMemberIdAndDeleted(Integer myHerbId, Integer memberId, Boolean deleted);

	Optional<List<MyHerb>> findByMemberId(Integer memberId);
}
