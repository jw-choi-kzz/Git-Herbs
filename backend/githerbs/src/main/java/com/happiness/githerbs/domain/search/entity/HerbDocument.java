package com.happiness.githerbs.domain.search.entity;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Document(indexName = "herb")
public class HerbDocument {
	@Id
	private Integer id;

	private String herbName;

	private String herbScientificName;

	private String herbNickname;

	private String herbMedicalPart;

	private String herbHarvestingTime;

	private String herbEnvironment;

	private String herbQuality;

	private List<String> herbMedicinalEffect;
}
