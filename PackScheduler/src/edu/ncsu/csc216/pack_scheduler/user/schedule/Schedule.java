/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;
import edu.ncsu.csc216.pack_scheduler.course.ConflictException;

/**
 * Schedule class that handle a user's schedule
 * 
 * @author Jeff Li
 *
 */
public class Schedule {

	/** Name of the schedule */
	private String title;
	
	/** ArrayList for the class */
	private ArrayList<Course> courses;
	
	/**
	 * Constructor of the Schedule class
	 */
	public Schedule() {
		courses = new ArrayList<Course>();
		setTitle("My Schedule");
	}

	/**
	 * Set the default title of the schedule
	 * @param name title name
	 */
	public void setTitle(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = name;
	}
	
	/**
	 * Get the title
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Add the Course to the end of the schedule
	 * @param course course that should be added
	 * @return true if the Course was added
	 */
	public boolean addCourseToSchedule(Course course) {
		if (course == null) {
			throw new NullPointerException();
		}
		try {
			for (int i = 0; i < courses.size(); i++) {
				course.checkConflict(courses.get(i));
				if (course.isDuplicate(courses.get(i))) {
					throw new IllegalArgumentException();
				}
			}
			courses.add(courses.size(), course);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("You are already enrolled in " + course.getName());
		} catch (NullPointerException e) {
			throw new IllegalArgumentException("No item selected in the schedule.");
		} catch (ConflictException e) {
			throw new IllegalArgumentException("The course cannot be added due to a conflict.");
		} catch (IndexOutOfBoundsException e) {
			throw new IllegalArgumentException("No course selected.");
		}
		return true;
	}
	
	/**
	 * Remove a course from the schedule
	 * @param course course that user wants remove
	 * @return true if the course could be removed
	 */
	public boolean removeCourseFromSchedule(Course course) {
		if (course == null) {
			return false;
		}
		for (int i = 0; i < courses.size(); i++) {
			if (course.equals(courses.get(i))) {
				courses.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Reset a schedule and reset the title to the default name
	 */
	public void resetSchedule() {
		ArrayList<Course> newSchedule = new ArrayList<Course>();
		courses = newSchedule;
		setTitle("My Schedule");
	}
	
	/**
	 * Return a 2D-Array of the course schedule
	 * @return a 2D array of the course schedule
	 */
	public String[][] getScheduledCourses() {
		String[][] scheduledCourse = new String[courses.size()][5];
		for (int i = 0 ; i < scheduledCourse.length; i++) {
			for (int j = 0; j < 5; j++) {
				scheduledCourse[i][j] = courses.get(i).getShortDisplayArray()[j];
			}
		}
		return scheduledCourse;
	}
	/**
	 * method returns the total number of credits in the schedule
	 * @return total credits number of credits in schedule
	 */
	public int getScheduleCredits() {
		int totalCredits = 0;
		int adder;
		for (int i = 0; i < courses.size(); i++) {
			adder = courses.get(i).getCredits();
			totalCredits += adder;
		}
		return totalCredits;
	}
	/**
	 * method checks if a course can be added to the schedule
	 * @param course the course being checked for ability to be added to the schedule
	 * @return boolean whether course can be added
	 */
	public boolean canAdd(Course course) {
		if (course == null) {
			return false;
		}
		for (int i = 0; i < courses.size(); i++) {
			if (course.getName().equals(courses.get(i).getName())) {
				return false;
			}
		}
		try {
			for (int i = 0; i < courses.size(); i++) {
				course.checkConflict(courses.get(i));
			}
		} catch (ConflictException e) {
			return false;
		}
		return true;
	}
}
