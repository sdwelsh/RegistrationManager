/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;


/**
 * Test cases for schedule class
 * @author Jeff Li
 *
 */
public class ScheduleTest {

	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Programming Concepts - Java";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 4;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Courses max capacity */
	private static final int ENROLLMENT_CAP = 10;
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;


	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule#Schedule()}.
	 */
	@Test
	public void testSchedule() {
		Schedule schedule = new Schedule();
		assertEquals("My Schedule", schedule.getTitle());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule#getTitle()}.
	 */
	@Test
	public void testGetTitle() {
		Schedule schedule = new Schedule();
		assertEquals("My Schedule", schedule.getTitle());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule#addCourseToSchedule(edu.ncsu.csc216.pack_scheduler.course.Course)}.
	 */
	@Test
	public void testAddCourseToSchedule() {
		// make schedule
		Schedule schedule = new Schedule();

		// adding valid course
		Course course = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME,
				              END_TIME);
		schedule.addCourseToSchedule(course);
		assertArrayEquals(course.getShortDisplayArray(), schedule.getScheduledCourses()[0]);
		// adding course that is already in schedule
		Course course2 = new Course(NAME, TITLE, "002", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "A");
		try {
			schedule.addCourseToSchedule(course2);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("You are already enrolled in CSC216", e.getMessage());
		}
		// adding course that is conflict
		Course course3 = new Course("CSC226", "Discrete Math", SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS,
				               START_TIME, END_TIME);
		try {
			schedule.addCourseToSchedule(course3);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("The course cannot be added due to a conflict.", e.getMessage());
		}
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule#removeCourseFromSchedule(edu.ncsu.csc216.pack_scheduler.course.Course)}.
	 */
	@Test
	public void testRemoveCourseFromSchedule() {
		// make schedule
		Schedule schedule = new Schedule();
		// make courses
		Course course = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME,
				                   END_TIME);
		Course course2 = new Course("CLA001", "Class", "005", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 930,
				                    1025);

		// test removing from empty list
		assertFalse(schedule.removeCourseFromSchedule(course));

		// add courses to schedule
		schedule.addCourseToSchedule(course);
		schedule.addCourseToSchedule(course2);

		// remove first course
		assertTrue(schedule.removeCourseFromSchedule(course));
		// make sure c2 is now first course
		assertArrayEquals(course2.getShortDisplayArray(), schedule.getScheduledCourses()[0]);
		
	}
	
	/**
	 * test method that checks if a course can be added to a students schedule
	 */
	@Test
	public void testCanAdd() {
		// make schedule
		Schedule schedule = new Schedule();
		// make courses
		Course course = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME,
				                   END_TIME);
		Course course2 = new Course("CLA001", "Class", "005", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 930,
				                    1025);

		Course course3 = new Course("CLA002", "Class2", "006", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 930,
                1025);
		// add courses to schedule
		schedule.addCourseToSchedule(course);
		schedule.addCourseToSchedule(course2);
		
		//test if you can add null course
		assertFalse(schedule.canAdd(null));
		//test if you can add duplicate course
		assertFalse(schedule.canAdd(course2));
		//test if you can add course with conflict
		assertFalse(schedule.canAdd(course3));
	}
	/**
	 * tests the method for returning the number of credits in a schedule
	 */
	@Test
	public void testGetScheduleCredits() {
		// make schedule
		Schedule schedule = new Schedule();
		// make courses
		Course course = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME,
				                   END_TIME);
		Course course2 = new Course("CLA001", "Class", "005", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 930,
				                    1025);
		// add courses to schedule
		schedule.addCourseToSchedule(course);
		schedule.addCourseToSchedule(course2);
		
		assertEquals(schedule.getScheduleCredits(), 8);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule#resetSchedule()}.
	 */
	@Test
	public void testResetSchedule() {
		// make schedule
		Schedule schedule = new Schedule();
		assertEquals(0, schedule.getScheduledCourses().length);

		// add one course and check length
		Course course = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME,
				              END_TIME);
		schedule.addCourseToSchedule(course);
		assertEquals(1, schedule.getScheduledCourses().length);

		// reset the schedule
		schedule.resetSchedule();
		assertEquals(0, schedule.getScheduledCourses().length);
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule#getScheduledCourse()}.
	 */
	@Test
	public void testGetScheduledCourse() {
		// make schedule
		Schedule schedule = new Schedule();

		// adding valid course
		Course course = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME,
				              END_TIME);
		schedule.addCourseToSchedule(course);
		assertArrayEquals(course.getShortDisplayArray(), schedule.getScheduledCourses()[0]);
		
	}

}
