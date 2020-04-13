/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Class created to test the Student.java class
 * 
 * @author Kraig Denny
 */
public class FacultyTest {
	/** First name */
	private static final String FIRST_NAME = "first";
	/** Last Name */
	private static final String LAST_NAME = "last";
	/** Id */
	private static final String ID = "id";
	/** Email */
	private static final String EMAIL = "email@ncsu.edu";
	/** Password */
	private static final String PASSWORD = "hashedpassword";
	/** Max Credit */
	private static final int MAX_CREDIT = 3;
	
	/**
	 * Tests the faculty constructor with all field parameters
	 */
	@Test
	public void testFacultyStringStringStringStringStringInt() {

		Faculty s = null; // Initialize a student reference to null
		try {
			s = new Faculty(null, "last", "id", "email@ncsu.edu", "hashedpassword");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		s = null;
		try {
			s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 3);
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(3, s.getMaxCourses());
		} catch (IllegalArgumentException e) {
			fail();
		}
		// Testing for null first name
		s = null;
		try {
			s = new Faculty(null, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDIT);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		// Testing for null last name
		s = null;
		try {
			s = new Faculty(FIRST_NAME, null, ID, EMAIL, PASSWORD, MAX_CREDIT);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		// Testing for null id
		s = null;
		try {
			s = new Faculty(FIRST_NAME, LAST_NAME, null, EMAIL, PASSWORD, MAX_CREDIT);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		// Testing for null email
		s = null;
		try {
			s = new Faculty(FIRST_NAME, LAST_NAME, ID, null, PASSWORD, MAX_CREDIT);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		// Testing for null password
		s = null;
		try {
			s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, null, MAX_CREDIT);
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

	}

	/**
	 * Test the Student constructor with 5 parameters
	 */
	@Test
	public void testFacultyStringStringStringStringString() {
		Faculty s = null;
		try {
			s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAX_CREDIT, s.getMaxCourses());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Test setFirstName().
	 */
	@Test
	public void testSetFirstName() {
		Faculty s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		// Test that setting FirstName to null does not change the firstName (or
		// anything else)
		try {
			s.setFirstName(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAX_CREDIT, s.getMaxCourses());
		}

		// Test that setting FirstName to empty string does not change the firstName (or
		// anything else)
		try {
			s.setFirstName("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAX_CREDIT, s.getMaxCourses());
		}

		// Test valid first name
		s.setFirstName("John");
		assertEquals("John", s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(MAX_CREDIT, s.getMaxCourses());

	}

	/**
	 * Test setLastName().
	 */
	@Test
	public void testSetLastName() {
		Faculty s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		// Test that setting LastName to null does not change the lastName (or
		// anything else)
		try {
			s.setLastName(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAX_CREDIT, s.getMaxCourses());
		}
		// Test that setting LastName to empty string does not change the lastName (or
		// anything else)
		try {
			s.setLastName("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAX_CREDIT, s.getMaxCourses());
		}

		// Test valid last name
		s.setLastName("Smith");
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals("Smith", s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(MAX_CREDIT, s.getMaxCourses());
	}

	/**
	 * Test setEmail().
	 */
	@Test
	public void testSetEmail() {
		Faculty s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		// Test that setting email to null does not change the email (or
		// anything else)
		try {
			s.setEmail(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAX_CREDIT, s.getMaxCourses());
		}
		// Test that setting email to empty string does not change the email (or
		// anything else)
		try {
			s.setEmail("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAX_CREDIT, s.getMaxCourses());
		}
		// Test that setting email without @
		try {
			s.setEmail("email.ncsu.edu");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAX_CREDIT, s.getMaxCourses());
		}
		// Test that setting email without "."
		try {
			s.setEmail("email@ncsuedu");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAX_CREDIT, s.getMaxCourses());
		}
		// Test that "." before @
		try {
			s.setEmail("first.last@ncsuedu");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAX_CREDIT, s.getMaxCourses());
		}
		
		
		// Test valid email
		s.setEmail("student@ncsu.edu");
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals("student@ncsu.edu", s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(MAX_CREDIT, s.getMaxCourses());

	}

	/**
	 * Test setPassword().
	 */
	@Test
	public void testSetPassword() {
		Faculty s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		// Test that setting password to null does not change the password (or
		// anything else)
		try {
			s.setPassword(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAX_CREDIT, s.getMaxCourses());
		}
		// Test that setting password to empty string does not change the password (or
		// anything else)
		try {
			s.setPassword("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAX_CREDIT, s.getMaxCourses());
		}

		// Test valid password
		s.setPassword("password");
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals("password", s.getPassword());
		assertEquals(MAX_CREDIT, s.getMaxCourses());
	}

	/**
	 * Test setMaxCredits
	 */
	@Test
	public void testSetMaxCredits() {
		Faculty s = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		// Test above max credit limit
		try {
			s.setMaxCourses(19);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAX_CREDIT, s.getMaxCourses());
		}
		// Test below min credit
		try {
			s.setMaxCourses(0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAX_CREDIT, s.getMaxCourses());

		}

		// Test valid min credit limit
		s.setMaxCourses(3);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(3, s.getMaxCourses());

		// Test valid max credit limit
		s.setMaxCourses(3);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(3, s.getMaxCourses());

		// Test valid credit
		s.setMaxCourses(2);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(2, s.getMaxCourses());
	}

	/**
	 * Test hashCode().
	 */
	@Test
	public void testHashCode() {
		User s1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDIT);
		User s2 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDIT);
		User s3 = new Faculty(FIRST_NAME, "john", ID, EMAIL, PASSWORD, MAX_CREDIT);
		User s4 = new Faculty(FIRST_NAME, LAST_NAME, "ids", EMAIL, PASSWORD, MAX_CREDIT);
		User s5 = new Faculty(FIRST_NAME, LAST_NAME, ID, "notValid@ncsu.edu", PASSWORD, MAX_CREDIT);
		User s6 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "word", MAX_CREDIT);
		User s7 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 2);

		// Test for the same hash code for the same values
		assertEquals(s1.hashCode(), s2.hashCode());

		// Test for each of the fields
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
	}

	/**
	 * Test equalsObject().
	 */
	@Test
	public void testEqualsObject() {
		User s1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDIT);
		User s2 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDIT);
		User s3 = new Faculty(FIRST_NAME, "john", ID, EMAIL, PASSWORD, MAX_CREDIT);
		User s4 = new Faculty(FIRST_NAME, LAST_NAME, "ids", EMAIL, PASSWORD, MAX_CREDIT);
		User s5 = new Faculty(FIRST_NAME, LAST_NAME, ID, "notValid@ncsu.edu", PASSWORD, MAX_CREDIT);
		User s6 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "word", MAX_CREDIT);
		User s7 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 2);

		// Test for equality in both directions
		assertTrue(s1.equals(s2));
		assertTrue(s2.equals(s1));

		// Test for each of the fields
		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
	}

	/**
	 * Test toString().
	 */
	@Test
	public void testToString() {
		User s1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 3);
		User s2 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		String string1 = FIRST_NAME + "," + LAST_NAME + "," + ID + "," + EMAIL + "," + PASSWORD + "," + 3;
		String string2 = FIRST_NAME + "," + LAST_NAME + "," + ID + "," + EMAIL + "," + PASSWORD + "," + MAX_CREDIT;
		assertEquals(string1, s1.toString());
		assertEquals(string2, s2.toString());
	}
	
	/**
	 * Test the comparable function for Student
	 * 
	 */
	@Test
	public void testCompareTo() {
		Faculty s1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 3);
		Faculty s2 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		Faculty s3 = new Faculty("john", "smith", "jsmith", EMAIL, PASSWORD);
		Faculty s4 = new Faculty("abby", "box", "abox", EMAIL, PASSWORD);
		Faculty s5 = new Faculty(FIRST_NAME, LAST_NAME, "notId", EMAIL, PASSWORD);
		
		try {
			s1.compareTo(null);
			fail();
		}
		catch(NullPointerException e) {
			assertEquals(0, s1.compareTo(s2));
			
			assertTrue(s1.compareTo(s3) < 0);
			assertTrue(s1.compareTo(s4) > 0);
			assertTrue(s1.compareTo(s5) < 0);
		}
	}


}
