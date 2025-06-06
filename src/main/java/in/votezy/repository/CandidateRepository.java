package in.votezy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.votezy.entity.Candidate;
@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long> {
	public Boolean existsByName(String name);
	public List<Candidate> findAllByOrderByVoteCountDesc();
}
