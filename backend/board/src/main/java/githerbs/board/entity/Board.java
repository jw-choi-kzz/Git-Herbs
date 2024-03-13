package githerbs.board.entity;

import java.util.ArrayList;
import java.util.List;

import githerbs.favorite.entity.Favorite;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "board")
public class Board extends BaseTime{

	@Id @GeneratedValue
	@Column(name = "board_id")
	int boardId;
	int memberId;
	int myHerbId;
	double similar;
	String herbName;
	String imgUrl;

	@OneToMany(mappedBy = "board")
	List<Favorite> favorites = new ArrayList<>();

}
