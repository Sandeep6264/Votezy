package in.votezy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.votezy.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate,Long> {
	public Boolean existsByEmail(String email);
	public List<Candidate> findAllByOrderByVoteCountDesc();
}
