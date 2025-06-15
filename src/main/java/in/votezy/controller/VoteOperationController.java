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

import in.votezy.dto.VoteRequestDTO;
import in.votezy.dto.VoteResponseDTO;
import in.votezy.entity.Vote;
import in.votezy.service.IVoteMgmtService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vote")
@CrossOrigin
public class VoteOperationController {
	@Autowired
	private IVoteMgmtService service;
	@PostMapping("/cast")
	public ResponseEntity<VoteResponseDTO> castVote(@RequestBody @Valid VoteRequestDTO dto) {
		Vote vote=service.custVote(dto.getVoterId(), dto.getCandidateId());
		return new ResponseEntity<VoteResponseDTO>(new VoteResponseDTO("Vote Casted Successfully",true,vote.getVoterId(),vote.getCandidateId()),HttpStatus.CREATED);
	}
	@GetMapping("/")
	public ResponseEntity<List<Vote>>getAllVote(){
		List<Vote>voteList=service.getAllVotes();
		return new ResponseEntity<>(voteList,HttpStatus.OK);
	}
}
