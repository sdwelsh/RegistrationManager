package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
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
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		//CourseRoll roll = new CourseRoll(10); //Update as below
		CourseRoll courseRoll = c.getCourseRoll();
		assertEquals(10, courseRoll.getEnrollmentCap());
		assertEquals(10, courseRoll.getOpenSeats());
		
		
		try {
			@SuppressWarnings("unused")
			CourseRoll courseRoll2 = new CourseRoll(9, c);
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
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		//CourseRoll roll = new CourseRoll(10); //Update as below
		CourseRoll courseRoll = c.getCourseRoll();
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
		
		Student s = new Student("stephen", "welsh", "sdwelsh", "sdwelsh@gmail.com", " uh");
		Student s1 = new Student("stephen", "welsh", "stwelsh", "sdwelsh@gmail.com", " uh");
		Student s2 = new Student("stephen", "welsh", "sttwelsh", "sdwelsh@gmail.com", " uh");
		Student s3 = new Student("stephen", "welsh", "stttwelsh", "sdwelsh@gmail.com", " uh");
		Student s4 = new Student("stephen", "welsh", "stttttwelsh", "sdwelsh@gmail.com", " uh");
		Student s5 = new Student("stephen", "welsh", "sttttttwelsh", "sdwelsh@gmail.com", " uh");
		Student s6 = new Student("stephen", "welsh", "swelsh", "sdwelsh@gmail.com", " uh");
		Student s7 = new Student("stephen", "welsh", "welsh", "sdwelsh@gmail.com", " uh");
		Student s8 = new Student("stephen", "welsh", "elsh", "sdwelsh@gmail.com", " uh");
		Student s9 = new Student("stephen", "welsh", "lsh", "sdwelsh@gmail.com", " uh");
		courseRoll.enroll(s);
		courseRoll.enroll(s1);
		courseRoll.enroll(s2);
		courseRoll.enroll(s3);
		courseRoll.enroll(s4);
		courseRoll.enroll(s5);
		courseRoll.enroll(s6);
		courseRoll.enroll(s7);
		courseRoll.enroll(s8);
		courseRoll.enroll(s9);
		assertEquals(10, courseRoll.getNumberOnWaitlist());
		
		courseRoll.drop(std.getStudentById("lberg"));
		assertEquals(0, courseRoll.getOpenSeats());
		assertEquals(9, courseRoll.getNumberOnWaitlist());
		
		
		
		try {
			Student student = null;
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
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		//CourseRoll roll = new CourseRoll(10); //Update as below
		CourseRoll courseRoll = c.getCourseRoll();
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
		
		
			
	}
	
	/**
	 * Test if the canEnroll method in CourseRoll
	 */
	@Test
	public void testCanEnroll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		//CourseRoll roll = new CourseRoll(10); //Update as below
		CourseRoll courseRoll = c.getCourseRoll();
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
		assertTrue(courseRoll.canEnroll(student));
		
		courseRoll.enroll(student);
		assertEquals(1, courseRoll.getNumberOnWaitlist());
		
		courseRoll.setEnrollmentCap(20);
		assertEquals(20, courseRoll.getEnrollmentCap());
		assertEquals(10, courseRoll.getOpenSeats());
	}

}
