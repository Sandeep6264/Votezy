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

import in.votezy.entity.Candidate;
import in.votezy.service.ICandidateMgmtService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/candidate")
@CrossOrigin
public class CandidateOperationController {
	@Autowired
	private ICandidateMgmtService candService;
	@PostMapping("/register")
	public  ResponseEntity<Candidate> registerCandidate(@RequestBody @Valid Candidate candidate){
		return new ResponseEntity<>( candService.registerCandidate(candidate),HttpStatus.CREATED);
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<Candidate> getCandidateById(@PathVariable("id") Long id){
		return new ResponseEntity<>(candService.getCandidateById(id),HttpStatus.OK);
	}
	@GetMapping("/getall")
	public ResponseEntity<List<Candidate>> getAllCandidate(){
		return new ResponseEntity<>(candService.getAllCandidate(),HttpStatus.OK);
	} 
	@PutMapping("/update/{id}")
	public ResponseEntity<Candidate> updateCandidate(@PathVariable("id") Long id,@RequestBody Candidate Candidate){
		return new ResponseEntity<>(candService.updateCandidate(id, Candidate),HttpStatus.OK);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCandidate(@PathVariable("id") Long id){
		candService.deleteCandidate(id);
		return new ResponseEntity<>("Candidate with :: "+id +" deleted successfully",HttpStatus.OK);
	}
	
	
}
