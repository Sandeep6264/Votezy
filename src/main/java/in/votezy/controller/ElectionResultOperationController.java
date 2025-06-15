package in.votezy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.votezy.dto.ResultRequestDTO;
import in.votezy.dto.ResultResponseDTO;
import in.votezy.entity.ElectionResult;
import in.votezy.service.IElectionMgmtService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/election-results")
@CrossOrigin
public class ElectionResultOperationController {
	@Autowired
	private IElectionMgmtService service;
	@PostMapping("/declare")
	public ResponseEntity<ResultResponseDTO> declareElectionResult(@RequestBody @Valid ResultRequestDTO electionResult){
		ResultResponseDTO dto=new ResultResponseDTO();
		ElectionResult result=service.declearElection(electionResult.getElectionName());
		dto.setElectionName(result.getElectionName());
		dto.setWinnerId(result.getCandidateId());
		dto.setTotalVotes(result.getTotalVotes());
		dto.setWinnerVotes(result.getCandidate().getVoteCount());
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<List<ElectionResult>> getAllElectionDetails(){
		return new ResponseEntity<>(service.getAllElections(),HttpStatus.OK);
	}
	
}
