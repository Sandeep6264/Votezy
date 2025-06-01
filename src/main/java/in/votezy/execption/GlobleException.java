package in.votezy.execption;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobleException {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> HandleResourceNotFoundException(ResourceNotFoundException rnfe){
		ErrorDetails error=new ErrorDetails(LocalDateTime.now(),rnfe.getMessage(),"404 - Resource Not Found");
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<ErrorDetails> HandleDuplicateResourceException(DuplicateResourceException dre){
		ErrorDetails error=new ErrorDetails(LocalDateTime.now(),dre.getMessage(),"409 - Duplicate Resource Not Allowed");
		return new ResponseEntity<>(error,HttpStatus.CONFLICT);
	}
	@ExceptionHandler(VoteNotAllowedException.class)
	public ResponseEntity<ErrorDetails> HandleVoteNotAllowedException(VoteNotAllowedException vnae){
		ErrorDetails error=new ErrorDetails(LocalDateTime.now(),vnae.getMessage(),"409 - Forbidden Exception");
		return new ResponseEntity<>(error,HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> HandleMethodArgumentNotValidException(MethodArgumentNotValidException manve){
		Map<String,String> errors=new HashMap<>();
		BindingResult bResult=manve.getBindingResult();		
		List<FieldError>errorList=bResult.getFieldErrors();
		errorList.forEach(err->{
			errors.put(err.getField(),err.getDefaultMessage());
		});
		return new ResponseEntity<Map<String,String>>(errors,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> HandleAllException(Exception ex){
		ErrorDetails error=new ErrorDetails(LocalDateTime.now(),ex.getMessage(),"500 - Problem in execution");
		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
