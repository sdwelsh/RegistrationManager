/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;

/**
 * Creates a Roll for each course to make sure that each student enrolled in the course is accounted for
 * @author stephenwelsh
 */
public class CourseRoll {
	/** Creates a new Course Roll of linked student objects */ 
	private LinkedAbstractList<Student> roll;
	
	/** Creates an enrollment Capacity that is defined by the user */
	private int enrollmentCap; 
	
	/** Defines the minimum amount of students that can be enrolled in the course */
	private static final int MIN_CAPACITY = 10;
	
	/** Defines the maximum capacity of students that can be enrolled in the course */
	private static final int MAX_CAPACITY = 250;
	
	/**
	 * Creates the constructor for the CourseRoll making a new linked list and setting the 
	 * enrollment capacity of the current linked list.
	 * @param enrollmentCap the capacity of the course for student to enroll in
	 */
	public CourseRoll(int enrollmentCap) {
		setEnrollmentCap(enrollmentCap);
		roll = new LinkedAbstractList<Student>(this.enrollmentCap);
		
	}
	
	/**
	 * Creates the setEnrollment method to check if the enrollment is within the minimum and maximum capacity.
	 * If the enrollment is outside the capacity then an IllegalArguementExcpetion is thrown.
	 * @param enrollmentCap is the enrollment capacity of the course
	 * @throws IllegalArgumentException if the capacity is between the min and max capacity's
	 */
	public void setEnrollmentCap(int enrollmentCap) {
		if(enrollmentCap < MIN_CAPACITY || enrollmentCap > MAX_CAPACITY) {
			throw new IllegalArgumentException();
		}
		if(roll != null && roll.size() > enrollmentCap) {
			throw new IllegalArgumentException();
		}
		this.enrollmentCap = enrollmentCap;
	}
	
	/**
	 * Returns the enrollment Capacity of the course
	 * @return enrollmentCap of the course
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}
	
	/**
	 * Returns the difference of the enrollment cap and the list size to get the seats that are open
	 * in the given course.
	 * @return the difference between enrollmentCap and roll size
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}
	
	/**
	 * Adds a student to the roll of a course.
	 * @param student the student that is being added to the roll
	 * @throws IllegalArgumentException if the student is null or if there isn't space in the class
	 * @throws IllegalArgumentException if the student is already enrolled
	 * @throws IllegalArgumentException if the linked list throws an exception
	 */
	public void enroll(Student student) {
		try {
			if(student == null) {
				throw new IllegalArgumentException();
			}
			else if(!canEnroll(student)) {
				throw new IllegalArgumentException();
			}
			roll.add(student);
		} catch(Exception e) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Drops a selected student from the class roll and throws an exception if the student is null.
	 * @param s is the selected student
	 * @throws IllegalArgumentException if the user is null or the list throws null
	 */
	public void drop(Student s) {
		try {
			if(s == null) {
				throw new IllegalArgumentException();
			}
			roll.remove(s);
		} catch(Exception e) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Tells the user if that student is eligible to be able to enroll in a particular class.
	 * returns false if the student is not eligible. 
	 * @param s is the student that you are checking eligibility for.
	 * @return true if the student can enroll
	 * 		   false if the student can't enroll
	 */
	public boolean canEnroll(Student s) {
		if(getOpenSeats() <= 0) {
			return false;
		}
		for(int i = 0; i < roll.size(); i++) {
			if(roll.get(i).getId().equals(s.getId())) {
				return false;
			}
		}
		return true;
	}
	
	
}
