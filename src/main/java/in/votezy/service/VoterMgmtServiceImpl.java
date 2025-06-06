package in.votezy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.votezy.entity.Candidate;
import in.votezy.entity.Vote;
import in.votezy.entity.Voter;
import in.votezy.execption.DuplicateResourceException;
import in.votezy.execption.ResourceNotFoundException;
import in.votezy.repository.CandidateRepository;
import in.votezy.repository.VoteRepository;
import in.votezy.repository.VoterRepository;
import jakarta.transaction.Transactional;
@Service
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
	@Override
	public Voter updateVoter(Long id, Voter updateVoter) {
		Voter voter=voterRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Voter with id :: "+id+" Not Found"));
		if(updateVoter.getEmail()!=null)
		voter.setEmail(updateVoter.getEmail());
		if(updateVoter.getName()!=null)
		voter.setName(updateVoter.getName());
		return voterRepo.save(voter);
	}
	@Override
	@Transactional
	public void deleteVoter(Long id) {
		Voter voter=voterRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cannot delete voter with :: "+id+" as it doesn't exists"));
		Vote vote=voter.getVote();
		if(vote!=null) {
			Candidate candidate=vote.getCandidate();
			candidate.setVoteCount(candidate.getVoteCount()-1);
			candidateRepo.save(candidate);
		}voterRepo.delete(voter);
	}
}
