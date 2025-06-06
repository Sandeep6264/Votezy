package in.votezy.service;

import java.util.List;

import in.votezy.entity.Candidate;

public interface ICandidateMgmtService {
	public Candidate registerCandidate(Candidate candidate);
	public List<Candidate> getAllCandidate();
	public Candidate getCandidateById(Long id);
	public Candidate  updateCandidate(Long id,Candidate updateCandidate);
	public void deleteCandidate(Long id);
}
