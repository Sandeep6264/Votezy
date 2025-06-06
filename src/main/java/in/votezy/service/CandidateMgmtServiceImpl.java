package in.votezy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.votezy.entity.Candidate;
import in.votezy.entity.Vote;
import in.votezy.execption.DuplicateResourceException;
import in.votezy.execption.ResourceNotFoundException;
import in.votezy.repository.CandidateRepository;
import in.votezy.repository.VoteRepository;
import jakarta.transaction.Transactional;

@Service
public class CandidateMgmtServiceImpl implements ICandidateMgmtService {
	@Autowired
	private CandidateRepository candRepo;
	@Autowired
	private VoteRepository voteRepo;
	public Candidate registerCandidate(Candidate candidate){
		return candRepo.save(candidate);
	}
	@Override
	public List<Candidate> getAllCandidate() {
		return candRepo.findAll();
	}
	@Override
	public Candidate getCandidateById(Long id) {
		Candidate candidate=candRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Candidate with id :: "+id +" Not Found"));
		return candidate;
	}
	@Override
	public Candidate updateCandidate(Long id, Candidate updateCandidate) {
		Candidate candidate=candRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Candidate with id :: "+id +" Not Found"));
		if(updateCandidate.getName()!=null)
			candidate.setName(updateCandidate.getName());
		if(updateCandidate.getPatry()!=null)
			candidate.setPatry(updateCandidate.getPatry());
		return candRepo.save(candidate);
	}
	@Override
	@Transactional
	public void deleteCandidate(Long id) {
//		System.out.println("Coming here");
		Candidate candidate=candRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Candidate with id :: "+id +" Not Found"));
		List<Vote> voteList=candidate.getVoteList();
		voteList.forEach(v->{
			v.setCandidate(null);
		});
		candidate.getVoteList().clear();
		candRepo.deleteById(id);
	}
}
	

