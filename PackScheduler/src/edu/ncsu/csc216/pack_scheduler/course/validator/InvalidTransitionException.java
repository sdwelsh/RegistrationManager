package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Creates a new thrown checked exception for if the FSM state transition is on an invalid state 
 * then it will call this exception.
 * @author stephenwelsh
 * @author Jon 
 * @author Jeff Li
 */
public class InvalidTransitionException extends Exception {
	
	/** Initializes a general state Id */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that initializes the transition exception. Allows the user to 
	 * add their own message to the exception
	 * @param message the message that is to be shown
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}
	
	/**
	 * Constructors that initializes Invalid transition and shows the default message of
	 * "Invalid FSM Transition"
	 */
	public InvalidTransitionException() {
		super("Invalid FSM Transition.");
	}
}
