package com.happiness.githerbs.domain.search.repository;

import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.HighlightParameters;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.happiness.githerbs.domain.search.entity.HerbDocument;

@Repository
public interface SearchRepository extends ElasticsearchRepository<HerbDocument, Integer> {
	@Query("{\"bool\": {\"should\": [{\"match_phrase_prefix\": {\"herbName\": {\"query\": \"?0\", \"analyzer\": \"my_analyzer\"}}}, {\"match_phrase_prefix\": {\"herbNickname\": {\"query\": \"?0\", \"analyzer\": \"my_analyzer\"}}}, {\"match_phrase_prefix\": {\"herbScientificName\": {\"query\": \"?0\", \"analyzer\": \"my_analyzer\"}}}, {\"match_phrase_prefix\": {\"herbHarvestingTime\": {\"query\": \"?0\", \"analyzer\": \"my_analyzer\"}}}, {\"match_phrase_prefix\": {\"herbEnvironment\": {\"query\": \"?0\", \"analyzer\": \"my_analyzer\"}}}, {\"match_phrase_prefix\": {\"herbQuality\": {\"query\": \"?0\", \"analyzer\": \"my_analyzer\"}}}, {\"match_phrase_prefix\": {\"herbMedicalPart\": {\"query\": \"?0\", \"analyzer\": \"my_analyzer\"}}}, {\"match_phrase_prefix\": {\"herbMedicinalEffect\": {\"query\": \"?0\", \"analyzer\": \"my_analyzer\"}}}]}}")
	@Highlight(fields = {
		@HighlightField(name = "herbMedicinalEffect", parameters = @HighlightParameters(fragmentSize = 50))
	})
	SearchHits<HerbDocument> searchByKeyword(String keyword);
}
