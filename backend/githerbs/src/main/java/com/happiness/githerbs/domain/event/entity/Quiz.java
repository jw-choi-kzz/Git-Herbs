package com.happiness.githerbs.domain.event.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quiz")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Quiz {

	@Id
	@Column(name = "quiz_id")
	private Integer id;

	@Column(length = 100)
	private String question;

	private Integer answer;

	@Column(length = 150)
	private String imgOne;

	@Column(length = 150)
	private String imgTwo;

	@Column(length = 150)
	private String imgThree;

	@Column(length = 150)
	private String imgFour;

}
