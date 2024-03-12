package com.githerbs.event.domain.event.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "herb_medicinal_effect")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class HerbMedicinalEffect {

	@Id
	@Column(name = "herb_effect_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "herb_id")
	private Herb herbId;

	@Lob
	@Column(columnDefinition = "MEDIUMTEXT")
	private String medicinalEffect;
}
