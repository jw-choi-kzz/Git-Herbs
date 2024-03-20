package com.happiness.githerbs.domain.herb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "herb")
public class Herb {

	@Id
	@Column(name = "herb_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 30)
	private String herbName;

	private String herbImg;

	@Column(length = 100)
	private String herbScientificName;

	private String herbNickname;

	private String herbMedicalPart;

	private String herbHarvestingTime;

	@Lob
	@Column(columnDefinition = "MEDIUMTEXT")
	private String herbEnvironment;

	@Lob
	@Column(columnDefinition = "MEDIUMTEXT")
	private String herbQuality;
}
