package in.votezy.service;

import java.util.List;

import in.votezy.entity.Vote;

public interface IVoteMgmtService {
	public Vote custVote(Long voterId,Long candidateId);
	public List<Vote> getAllVotes();
}
