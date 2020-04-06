package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;
/**
 * Test the course roll class and all of its methods.
 * @author stephenwelsh
 */
public class CourseRollTest {

	/**
	 * Test the constructor of the course Roll
	 */
	@Test
	public void testCourseRollConstructor() {
		CourseRoll courseRoll = new CourseRoll(30);
		assertEquals(30, courseRoll.getEnrollmentCap());
		assertEquals(30, courseRoll.getOpenSeats());
		
		
		try {
			@SuppressWarnings("unused")
			CourseRoll courseRoll2 = new CourseRoll(9);
			fail();
		} catch(IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
	}
	
	/**
	 * Test the add method in the course roll
	 */
	@Test 
	public void testCourseRollAdd() {
		CourseRoll courseRoll = new CourseRoll(10);
		assertEquals(10, courseRoll.getEnrollmentCap());
		assertEquals(10, courseRoll.getOpenSeats());
		
		StudentDirectory std = new StudentDirectory();
		std.loadStudentsFromFile("test-files/student_records.txt");
		
		courseRoll.enroll(std.getStudentById("daustin"));
		assertEquals(10, courseRoll.getEnrollmentCap());
		assertEquals(9, courseRoll.getOpenSeats());
		
		courseRoll.enroll(std.getStudentById("lberg"));
		courseRoll.enroll(std.getStudentById("rbrennan"));
		courseRoll.enroll(std.getStudentById("efrost"));
		courseRoll.enroll(std.getStudentById("shansen"));
		courseRoll.enroll(std.getStudentById("ahicks"));
		courseRoll.enroll(std.getStudentById("zking"));
		courseRoll.enroll(std.getStudentById("dnolan"));
		courseRoll.enroll(std.getStudentById("cschwartz"));
		courseRoll.enroll(std.getStudentById("gstone"));
		
		try {
			courseRoll.enroll(std.getStudentById("daustin"));
			fail();
		} catch(IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
		
		try {
			Student student = null;
			courseRoll.enroll(student);
			fail();
		} catch(IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
		
		try {
			Student student = new Student("Stephen", "Welsh", "sdwelsh", "sdwelsh@ncsu.edu", "password");
			courseRoll.enroll(student);
			fail();
		} catch(IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
	}
	
	/**
	 * Test the drop method in the CourseRoll
	 */
	@Test
	public void testDrop() {
		CourseRoll courseRoll = new CourseRoll(10);
		assertEquals(10, courseRoll.getEnrollmentCap());
		assertEquals(10, courseRoll.getOpenSeats());
		
		StudentDirectory std = new StudentDirectory();
		std.loadStudentsFromFile("test-files/student_records.txt");
		
		courseRoll.enroll(std.getStudentById("daustin"));
		assertEquals(10, courseRoll.getEnrollmentCap());
		assertEquals(9, courseRoll.getOpenSeats());
		
		courseRoll.enroll(std.getStudentById("lberg"));
		courseRoll.enroll(std.getStudentById("rbrennan"));
		courseRoll.enroll(std.getStudentById("efrost"));
		courseRoll.enroll(std.getStudentById("shansen"));
		courseRoll.enroll(std.getStudentById("ahicks"));
		courseRoll.enroll(std.getStudentById("zking"));
		courseRoll.enroll(std.getStudentById("dnolan"));
		courseRoll.enroll(std.getStudentById("cschwartz"));
		courseRoll.enroll(std.getStudentById("gstone"));
		assertEquals(10, courseRoll.getEnrollmentCap());
		assertEquals(0, courseRoll.getOpenSeats());
		
		courseRoll.drop(std.getStudentById("lberg"));
		assertEquals(10, courseRoll.getEnrollmentCap());
		assertEquals(1, courseRoll.getOpenSeats());
		
		try {
			Student student = null;
			courseRoll.drop(student);
			fail();
		} catch(IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
		
		courseRoll.drop(std.getStudentById("lberg"));
		assertEquals(10, courseRoll.getEnrollmentCap());
		assertEquals(1, courseRoll.getOpenSeats());
			
	}
	
	/**
	 * Test if the canEnroll method in CourseRoll
	 */
	@Test
	public void testCanEnroll() {
		CourseRoll courseRoll = new CourseRoll(10);
		assertEquals(10, courseRoll.getEnrollmentCap());
		assertEquals(10, courseRoll.getOpenSeats());
		
		StudentDirectory std = new StudentDirectory();
		std.loadStudentsFromFile("test-files/student_records.txt");
		
		courseRoll.enroll(std.getStudentById("daustin"));
		assertEquals(10, courseRoll.getEnrollmentCap());
		assertEquals(9, courseRoll.getOpenSeats());
		
		assertFalse(courseRoll.canEnroll(std.getStudentById("daustin")));
		
		assertTrue(courseRoll.canEnroll(std.getStudentById("lberg")));
		courseRoll.enroll(std.getStudentById("lberg"));
		assertTrue(courseRoll.canEnroll(std.getStudentById("rbrennan")));
		courseRoll.enroll(std.getStudentById("rbrennan"));
		courseRoll.enroll(std.getStudentById("efrost"));
		courseRoll.enroll(std.getStudentById("shansen"));
		courseRoll.enroll(std.getStudentById("ahicks"));
		courseRoll.enroll(std.getStudentById("zking"));
		courseRoll.enroll(std.getStudentById("dnolan"));
		courseRoll.enroll(std.getStudentById("cschwartz"));
		courseRoll.enroll(std.getStudentById("gstone"));
		assertEquals(10, courseRoll.getEnrollmentCap());
		assertEquals(0, courseRoll.getOpenSeats());
		
		Student student = new Student("Stephen", "Welsh", "sdwelsh", "sdwelsh@ncsu.edu", "password");
		assertFalse(courseRoll.canEnroll(student));
	}

}
