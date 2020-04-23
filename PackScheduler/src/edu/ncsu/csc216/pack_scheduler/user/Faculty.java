package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * Faculty code will set data for faculty, set their firstName, lastName, id,
 * email, password, and max courses
 * 
 * @author Kraig Denny
 */
public class Faculty extends User {

	/** Max amount of courses allowed for faculty */
	private int maxCourses;
	/** Constant of max amount of courses */
	public static final int MAX_COURSES = 3;
	/** Creates a new faculty state*/
	private FacultySchedule facultySchedule;
	
	/**
	 * Constructor used to declare a faculty Object
	 * 
	 * @param firstName Faculty first name
	 * @param lastName  Faculty last name
	 * @param id        Faculty id
	 * @param email     Faculty email
	 * @param password  Faculty password
	 * @param maxCourse max amount of courses allowed for faculty
	 */
	public Faculty(String firstName, String lastName, String id, String email, String password, int maxCourse) {
		super(firstName, lastName, id, email, password);
		setMaxCourses(maxCourse);
		facultySchedule = new FacultySchedule(id);
	}

	/**
	 * Constructor used to declare a faculty Object
	 * 
	 * @param firstName Faculty first name
	 * @param lastName  Faculty last name
	 * @param id        Faculty id
	 * @param email     Faculty email
	 * @param password  Faculty password
	 */
	public Faculty(String firstName, String lastName, String id, String email, String password) {
		super(firstName, lastName, id, email, password);
		setMaxCourses(MAX_COURSES);
		facultySchedule = new FacultySchedule(id);
	}

	/**
	 * Returns the Max Courses the faculty can have
	 * 
	 * @return the maxCourses
	 */
	public int getMaxCourses() {
		return maxCourses;
	}

	/**
	 * Sets the max courses this faculty can take. Throws an
	 * IllegalArgumentException if the courses are bellow 1 or greater than 3.
	 * 
	 * @param maxCourses the maxCourses to set
	 * @throws IllegalArgumentException if the maxCredits is greater than 3 or less
	 *                                  than 1
	 */
	public void setMaxCourses(int maxCourses) {

		if (maxCourses > MAX_COURSES || maxCourses < 1) {
			throw new IllegalArgumentException("Invalid max courses");
		}

		this.maxCourses = maxCourses;
	}

	/**
	 * Hashcode for faculty that calls the super hashing
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}

	/**
	 * Equals for faculty that calls users equals object.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		if (maxCourses != other.maxCourses)
			return false;
		return true;
	}

	/**
	 * Returns a string representation of the faculty object.
	 * 
	 * @return string representation of the objects variables
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + ","
				+ maxCourses;
	}

	/**
	 * Implementation of the compareTo method
	 * 
	 * @param s2 the Faculty object being compared
	 * @return -1, 0, or 1 based on the result of the compareTo method
	 */
	public int compareTo(Faculty s2) {
		if (s2 == null) {
			throw new NullPointerException();
		}
		String objLast = s2.getLastName();
		String objFirst = s2.getFirstName();
		String objId = s2.getId();
		if (objLast.toLowerCase().compareTo(this.getLastName().toLowerCase()) == 0) {
			if (objFirst.toLowerCase().compareTo(this.getFirstName().toLowerCase()) == 0) {
				if (objId.toLowerCase().compareTo(this.getId().toLowerCase()) == 0) {
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
	 * Returns the faculty current schedule.
	 * 
	 * @return the faculty schedule of the faculty.
	 */
	public FacultySchedule getSchedule() {
		return facultySchedule;
	}

	/**
	 * returns true if the scheduled courses is greater than the max courses
	 * 
	 * @return true if scheduled course is higher than max courses
	 */
	public boolean isOverloaded() {
		return facultySchedule.getNumScheduledCourses() > maxCourses;
	}
}
