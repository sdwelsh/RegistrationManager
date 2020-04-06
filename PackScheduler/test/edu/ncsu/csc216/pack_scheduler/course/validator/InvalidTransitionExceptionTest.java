package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Test Invalid transitions for the invalid transitions exception class
 * @author stephenwelsh
 *
 */
public class InvalidTransitionExceptionTest {

	/**
	 * Creates a test for the invalid transition test.
	 */
	@Test
	public void invalidTransitionTest() {
		try {
			throw new InvalidTransitionException();
		} catch (InvalidTransitionException e) {
			assertEquals("Invalid FSM Transition.", e.getMessage());
		}

		try {
			throw new InvalidTransitionException("Invalid string");
		} catch (InvalidTransitionException e) {
			assertEquals("Invalid string", e.getMessage());
		}
	}

}
