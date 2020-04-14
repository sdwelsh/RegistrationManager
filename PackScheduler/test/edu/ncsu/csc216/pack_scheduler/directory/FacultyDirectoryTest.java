/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;

/**
 * @author stephenwelsh
 *
 */
public class FacultyDirectoryTest {
	
	/** Valid course records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Invalid course records*/
	private final String invalidTestFile = "test-files/nosuchfile.txt";
	/** Test first name */
	private static final String FIRST_NAME = "Stu";
	/** Test last name */ 
	private static final String LAST_NAME = "Dent";
	/** Test id */
	private static final String ID = "sdent";
	/** Test email */
	private static final String EMAIL = "sdent@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	private static final int MAX_CREDITS = 3;
	
	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {		
		//Reset student_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_faculty_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "faculty_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#FacultyDirectory()}.
	 */
	@Test
	public void testFacultyDirectory() {
		FacultyDirectory sd = new FacultyDirectory();
		assertFalse(sd.removeFaculty("sesmith5"));
		assertEquals(0, sd.getFacultyDirectory().length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#newFacultyDirectory()}.
	 */
	@Test
	public void testNewFacultyDirectory() {
		FacultyDirectory sd = new FacultyDirectory();
		
		assertEquals(0, sd.getFacultyDirectory().length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#loadFacultyFromFile(java.lang.String)}.
	 */
	@Test
	public void testLoadFacultyFromFile() {
		FacultyDirectory f = new FacultyDirectory();
		try {
			f.loadFacultyFromFile(invalidTestFile);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to read file test-files/nosuchfile.txt", e.getMessage());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#addFaculty(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)}.
	 */ 
	@Test
	public void testAddFaculty() {
		FacultyDirectory f = new FacultyDirectory();
		
		f.loadFacultyFromFile(validTestFile);
		assertEquals(3, f.getFacultyDirectory().length);
		
		f.newFacultyDirectory();
		assertEquals(0, f.getFacultyDirectory().length);
		
		FacultyDirectory f2 = new FacultyDirectory();
		
		//Test valid Student
		f2.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		String [][] studentDirectory = f2.getFacultyDirectory();
		assertEquals(1, studentDirectory.length);
		assertEquals(FIRST_NAME, studentDirectory[0][0]);
		assertEquals(LAST_NAME, studentDirectory[0][1]);
		assertEquals(ID, studentDirectory[0][2]);
		
		FacultyDirectory f3 = new FacultyDirectory();
		try {
			f3.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, null, PASSWORD, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
		try {
			f3.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "", PASSWORD, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
		try {
			f3.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, null, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
		try {
			f3.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "", MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory#removeFaculty(java.lang.String)}.
	 */
	@Test
	public void testRemoveFaculty() { 
		FacultyDirectory f = new FacultyDirectory();
		
		f.loadFacultyFromFile(validTestFile);
		assertEquals(3, f.getFacultyDirectory().length);
		
		f.removeFaculty("fmeadow");
		assertEquals(2, f.getFacultyDirectory().length);
	}

}
