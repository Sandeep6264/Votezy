package in.votezy.service;

import java.util.List;

import in.votezy.entity.ElectionResult;

public interface IElectionMgmtService {
	public ElectionResult declearElection(String electionName);
	public List<ElectionResult> getAllElections();
}
