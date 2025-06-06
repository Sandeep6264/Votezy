package in.votezy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.votezy.entity.Voter;
import in.votezy.service.IVoterMgmtService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/voter")
@CrossOrigin
public class VoterOperationController {
	@Autowired
	private IVoterMgmtService voterService;
	@PostMapping("/register")
	public  ResponseEntity<Voter> registerVoter(@RequestBody @Valid Voter voter){
		return new ResponseEntity<>( voterService.registerVoter(voter),HttpStatus.CREATED);
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<Voter> getVoterById(@PathVariable("id") Long id){
		return new ResponseEntity<>(voterService.getVoterById(id),HttpStatus.OK);
	}
	@GetMapping("/getall")
	public ResponseEntity<List<Voter>> getAllVoter(){
		return new ResponseEntity<>(voterService.getAllVoter(),HttpStatus.OK);
	} 
	@PutMapping("/update/{id}")
	public ResponseEntity<Voter> updateVoter(@PathVariable("id") Long id,@RequestBody Voter voter){
		return new ResponseEntity<>(voterService.updateVoter(id, voter),HttpStatus.OK);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteVoter(@PathVariable("id") Long id){
		voterService.deleteVoter(id);
		return new ResponseEntity<>("Voter with :: "+id +" deleted",HttpStatus.OK);
	}
	
	
}
