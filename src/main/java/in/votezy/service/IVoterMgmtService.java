package in.votezy.service;

import java.util.List;

import in.votezy.entity.Voter;

public interface IVoterMgmtService {
		public Voter registerVoter(Voter voter);
		public List<Voter> getAllVoter();
		public Voter getVoterById(Long id);
}
