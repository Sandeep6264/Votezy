package in.votezy.execption;

public class VoteNotAllowedException extends RuntimeException {
	public VoteNotAllowedException(String msg) {
		super(msg);
	}
}
