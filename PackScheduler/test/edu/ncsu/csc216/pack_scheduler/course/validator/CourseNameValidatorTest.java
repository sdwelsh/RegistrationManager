package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This is the test for CourseNameValidator and test the valid transitions and invalid transitions
 * @author stephenwelsh
 *
 */
public class CourseNameValidatorTest {

	/**
	 * test if the transitions are valid and test all the transitions for 100% coverage
	 */
	@Test
	public void testIsValid() {
		CourseNameValidator fsm = new CourseNameValidator();

		try {
			fsm.isValid("2");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must start with a letter.", e.getMessage());
		}

		try {
			fsm.isValid("eeee2e");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}

		try {
			fsm.isValid("ee22e");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}

		try {
			fsm.isValid("eee222ee");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
		}

		try {
			fsm.isValid("eee222e2");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
		}

		try {
			fsm.isValid("eee2222");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}

		try {
			fsm.isValid("eeeeee");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
		}

		try {
			fsm.isValid("!");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		try {
			fsm.isValid("e!");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		try {
			fsm.isValid("ee!");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		try {
			fsm.isValid("eee!");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		try {
			fsm.isValid("eeee!");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		try {
			fsm.isValid("eeee2!");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		try {
			fsm.isValid("eeee22!");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		try {
			fsm.isValid("eeee222!");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		try {
			fsm.isValid("eeee222e!");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}

		try {
			assertTrue(fsm.isValid("eeee222e"));
		} catch (InvalidTransitionException e) {
			fail();
		}

		try {
			assertTrue(fsm.isValid("e222e"));
		} catch (InvalidTransitionException e) {
			fail();
		}

	}

}
