package in.votezy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultResponseDTO {
	private String electionName;
	private Integer totalVotes;
	private Long winnerId;
	private Integer winnerVotes;

	
}
