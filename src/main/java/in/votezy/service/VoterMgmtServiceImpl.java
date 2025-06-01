package in.votezy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import in.votezy.entity.Voter;
import in.votezy.execption.DuplicateResourceException;
import in.votezy.execption.ResourceNotFoundException;
import in.votezy.repository.CandidateRepository;
import in.votezy.repository.VoteRepository;
import in.votezy.repository.VoterRepository;
public class VoterMgmtServiceImpl implements IVoterMgmtService {
	@Autowired
	private VoterRepository voterRepo;
	@Autowired
	private CandidateRepository candidateRepo;
	@Autowired
	private VoteRepository voteRepo;
	@Override
	public Voter registerVoter(Voter voter) {
		if(voterRepo.existsByEmail(voter.getEmail()))
			throw new DuplicateResourceException("Voter with email id :: "+voter.getEmail()+" Already Exists");
		return voterRepo.save(voter);
	}
	@Override
	public List<Voter> getAllVoter() {
	return voterRepo.findAll();
	}
	@Override
	public Voter getVoterById(Long id) {
		Voter voter=voterRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Voter with id :: "+id+" Not Found"));
		return voter;
	}
}
