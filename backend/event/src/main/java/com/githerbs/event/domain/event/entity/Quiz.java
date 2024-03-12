package com.githerbs.event.domain.event.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quiz")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Quiz {

	@Id
	@Column(name = "quiz_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
