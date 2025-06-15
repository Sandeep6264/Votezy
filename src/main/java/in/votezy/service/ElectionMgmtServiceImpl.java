package in.votezy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.votezy.entity.Candidate;
import in.votezy.entity.ElectionResult;
import in.votezy.execption.ResourceNotFoundException;
import in.votezy.repository.CandidateRepository;
import in.votezy.repository.ElectionResultRepository;
import in.votezy.repository.VoteRepository;

@Service
public class ElectionMgmtServiceImpl implements IElectionMgmtService {
	@Autowired
	private ElectionResultRepository electionRepo;
	@Autowired
	private CandidateRepository candRepo;
	@Autowired
	private VoteRepository voteRepo;
	@Override
	public ElectionResult declearElection(String electionName) {
		Optional<ElectionResult> election=electionRepo.findByElectionName(electionName);
		if(election.isPresent()) {
			return election.get();
		}
		if(voteRepo.count()==0)
			throw new IllegalStateException("Cannot delcare the result as no votes have been");
		List<Candidate>allCandidate=candRepo.findAllByOrderByVoteCountDesc();
		if(allCandidate.isEmpty()) {
			throw new ResourceNotFoundException("No Candidate Avaliable");
		}
		Candidate winner =allCandidate.get(0);
		Integer totalVotes=0;
		for(Candidate candidate:allCandidate) {
			totalVotes+=candidate.getVoteCount();
		}
		ElectionResult ele=new ElectionResult();
		ele.setElectionName(electionName);
		ele.setTotalVotes(totalVotes);
		ele.setCandidate(winner);
		return electionRepo.save(ele);
	}
	public List<ElectionResult> getAllElections() {
		return electionRepo.findAll();
	}

}
