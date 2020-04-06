package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Student code will set data for student, set their firstName, lastName, id,
 * email, password, and credits
 * 
 * @author ZHongke Ma
 * @author Anton Nikulsin
 * @author Stephen Welsh
 *
 */
public class Student extends User implements Comparable<Student> {

	/** Max amount of credits allowed for student */
	private int maxCredits;
	/** Constant of max amount of credits */
	public static final int MAX_CREDITS = 18;
	
	/** The Schedule of the students */
	private Schedule schedule;

	/**
	 * Constructor used to declare a student Object
	 * 
	 * @param firstName  Students first name
	 * @param lastName   Students last name
	 * @param id         students id
	 * @param email      Students email
	 * @param password   Students password
	 * @param maxCredits max amount of credits allowed for student
	 */
	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) {
		super(firstName, lastName, id, email, password);
		setMaxCredits(maxCredits);
		schedule = new Schedule();
	}

	/**
	 * Constructor used to declare a student Object
	 * 
	 * @param firstName Students first name
	 * @param lastName  Students last name
	 * @param id        students id
	 * @param email     Students email
	 * @param password  Students password
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		super(firstName, lastName, id, email, password);
		setMaxCredits(MAX_CREDITS);
		schedule = new Schedule();
	}

	/**
	 * Returns the Max Credits the student can take.
	 * 
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the max credits this student can take. Throws an
	 * IllegalArgumentException if the credits are bellow 3 or greater than 18.
	 * 
	 * @param maxCredits the maxCredits to set
	 * @throws IllegalArgumentException if the maxCredits is greater than 18 or less
	 *                                  than 3
	 */
	public void setMaxCredits(int maxCredits) {
 
		if (maxCredits > MAX_CREDITS || maxCredits < 3) {
			throw new IllegalArgumentException("Invalid max credits");
		}

		this.maxCredits = maxCredits;
	}
	
	/**
	 * Hashcode for student that calls the super hashing
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

	/**
	 * Equals for student that calls users equals object.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (maxCredits != other.maxCredits)
			return false;
		return true;
	}

	/**
	 * Returns a string representation of the student object.
	 * 
	 * @return string representation of the objects variables
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + "," + maxCredits;
	}

	/**
	 * Implementation of the compareTo method
	 * @param o the Student object being compared
	 * @return -1, 0, or 1 based on the result of the compareTo method
	 */
	@Override
	public int compareTo(Student o) { 
		if(o == null) { 
			throw new NullPointerException();
		}
		String objLast = o.getLastName();
		String objFirst = o.getFirstName();
		String objId = o.getId();
		if (objLast.toLowerCase().compareTo(this.getLastName().toLowerCase()) == 0) {
			if(objFirst.toLowerCase().compareTo(this.getFirstName().toLowerCase()) == 0) {
				if(objId.toLowerCase().compareTo(this.getId().toLowerCase()) == 0) {
					return 0;
				} else {
					return -1 * (objId.toLowerCase().compareTo(this.getId().toLowerCase()));
				}
			} else {
				return -1 * (objFirst.toLowerCase().compareTo(this.getFirstName().toLowerCase()));
			}
		} else {
			return -1 * (objLast.toLowerCase().compareTo(this.getLastName().toLowerCase()));
		}
	}
	
	/**
	 * tests if course can be added to the 
	 * schedule based off of total credits and max credits
	 * @param course the course that is being checked to be added
	 * @return true if the course can be added
	 * 		   false if the course cannot be added
	 */
	public boolean canAdd(Course course) {
		if(schedule.canAdd(course)) {
			int courseCredits = course.getCredits();
			int studentCredits = schedule.getScheduleCredits();
			if( (courseCredits + studentCredits) <= maxCredits) {
				return true; 
			} 
		}
		return false;
	}
	
	/**
	 * Returns the students current schedule.
	 * @return the schedule of the student.
	 */
	public Schedule getSchedule() {
		return schedule;
	}
}
