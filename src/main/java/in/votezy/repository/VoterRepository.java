package in.votezy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.votezy.entity.Voter;

public interface VoterRepository extends JpaRepository<Voter, Long> {
	public Boolean existsByEmail(String email);
	
}
