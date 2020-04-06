package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * The Course name validator that will take a course name submitted and make sure that the name fits 
 * the requirements
 * @author Jeff
 */
public class CourseNameValidator {

	/** Number of letter */
	private int letterCount;

	/** Number of digits */
	private int digitCount;

	/** Used to determine if FSM is in number state */
	private State numberState;

	/** Used to determine if FSM is in initial state */
	private State initialState;

	/** Used to determine if FSM is in suffix state */
	private State suffixState;

	/** Used to determine if FSM is in letter state */
	private State letterState;

	/** Used to determine the current state */
	private State currentState;

	/**
	 * Constructor of the CourseNameValidator class
	 */
	public CourseNameValidator() {
		// Empty constructor
	}

	/**
	 * Check if the name is valid or not
	 * 
	 * @param name the name of the course to be validated 
	 * @return state based on input
	 */
	public boolean isValid(String name) throws InvalidTransitionException {
		// set the state, char, and char index
		numberState = new NumberState();
		initialState = new InitialState();
		suffixState = new SuffixState();
		letterState = new LetterState();
		currentState = initialState;
		int index = 0;
		char ch;

		// check case
		while (index < name.length()) {
			ch = name.charAt(index);
			try {
				if (Character.isLetter(ch)) {
					currentState.onLetter();
				} else if (Character.isDigit(ch)) {
					currentState.onDigit();
				} else {
					currentState.onOther();
				}
			} catch (InvalidTransitionException e) {
				throw new InvalidTransitionException(e.getMessage());
			}
			index++;
		}
		if (digitCount < 3) {
			return false;
		}
		return currentState == numberState || currentState == suffixState;
	}

	/**
	 * Creates an abstract state class
	 * 
	 * @author stephenwelsh
	 * @author Jeff Li
	 */
	public abstract class State {

		/**
		 * Creates the constructor for the abstract state class.
		 */
		public State() {
			//Do nothing 
		}

		/**
		 * Abstract method on letter tells if the current state is on a letter and then
		 * transitions to the next state if it is.
		 * 
		 * @throws InvalidTransitionException
		 */
		public abstract void onLetter() throws InvalidTransitionException;

		/**
		 * Tells the FSM that the current state is on a digit and then transitions to
		 * the next state.
		 */
		public abstract void onDigit() throws InvalidTransitionException;

		/**
		 * throws and exception if called because the transition was invalid and lead to
		 * an invalid state.
		 * 
		 * @throws InvalidTransitionException if called because the transition was
		 *                                    invalid
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}

	/**
	 * State if we read letter
	 * 
	 * @author Jeff Li
	 *
	 */
	public class LetterState extends State {

		/** Max prefix letters */
		private static final int MAX_PREFIX_LETTERS = 4;

		/**
		 * LetterState constructor.
		 */
		private LetterState() {

		}

		/**
		 * Track how many letter entered
		 * 
		 * @throws InvalidTransitionException
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			if (letterCount < MAX_PREFIX_LETTERS) {
				letterCount++;
			} else {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
		}

		@Override
		public void onDigit() {
			currentState = numberState;
			digitCount++;
		}
	}

	/**
	 * State if we have suffix letter
	 * 
	 * @author Jeff Li
	 *
	 */
	public class SuffixState extends State {

		/**
		 * SuffixState constructor
		 */
		private SuffixState() {

		}

		/**
		 * Track letter if entered
		 * 
		 * @throws InvalidTransitionException
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
		}

		/**
		 * Track digit if entered
		 * 
		 * @throws InvalidTransitionException
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}
	}

	/**
	 * State when we start
	 * 
	 * @author Jeff Li
	 *
	 */
	public class InitialState extends State {

		/**
		 * Default the value
		 */
		private InitialState() {
			letterCount = 0;
			digitCount = 0;
		}

		/**
		 * Check if the user input the letter
		 */
		@Override
		public void onLetter() {
			currentState = letterState;
			letterCount++;
		}

		/**
		 * Check if the user input digit
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
	}

	/**
	 * State if we have number
	 * 
	 * @author Jeff Li
	 *
	 */
	public class NumberState extends State {

		/** Max number of numbers a name can have */
		private static final int COURSE_NUMBER_LENGTH = 3;

		/**
		 * Constructor of the NumberState class
		 */
		private NumberState() {
			// Empty constructor
		}

		/**
		 * Check if user input a letter
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			if (digitCount == COURSE_NUMBER_LENGTH) {
				currentState = suffixState;
			} else {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
		}

		/**
		 * Check if user input a digit
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			if (digitCount < COURSE_NUMBER_LENGTH) {
				digitCount++;
			} else {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}
		}
	}

}
