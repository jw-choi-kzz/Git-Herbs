package githerbs.board.entity;

import java.util.ArrayList;
import java.util.List;

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
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "board")
@ToString
public class Board extends BaseTime{

	@Id @GeneratedValue
	@Column(name = "board_id")
	int boardId;
	int memberId;
	String imgUrl;
	private boolean deleted;
	@OneToMany(mappedBy = "board")
	final List<Favorite> favorites = new ArrayList<>();

}
