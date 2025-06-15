package in.votezy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.votezy.entity.Candidate;
import in.votezy.entity.Vote;
import in.votezy.entity.Voter;
import in.votezy.execption.ResourceNotFoundException;
import in.votezy.execption.VoteNotAllowedException;
import in.votezy.repository.CandidateRepository;
import in.votezy.repository.VoteRepository;
import in.votezy.repository.VoterRepository;
import jakarta.transaction.Transactional;

@Service
public class VoteMgmtServiceImpl implements IVoteMgmtService {
	@Autowired
	private VoterRepository voterRepo;
	@Autowired
	private VoteRepository voteRepo;
	@Autowired
	private CandidateRepository candRepo;
	@Override
	@Transactional
	public Vote custVote(Long voterId, Long candidateId) {
		Voter voter=voterRepo.findById(voterId).orElseThrow(()->new ResourceNotFoundException("Voter with ::  "+voterId +" Id not found"));
		Candidate candidate=candRepo.findById(candidateId).orElseThrow(()->new ResourceNotFoundException("Candidate with :: "+candidateId+ " Id not found"));
		Vote vote=new Vote();
		vote.setCandidate(candidate);
		vote.setVoter(voter);
		candidate.setVoteCount(candidate.getVoteCount()+1);
		candRepo.save(candidate);
		if(voter.getHas_voted())
			throw new VoteNotAllowedException("You already voted");
		voter.setHas_voted(true);
		voter.setVote(vote);
		voterRepo.save(voter);
		return vote;
	}
	public List<Vote> getAllVotes(){
		return voteRepo.findAll();
	}

}
