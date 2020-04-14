package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;
/**
 * creates the functionality that allows students to register 
 * for classes that our taught by the faculty
 * @author jonle
 *
 */
public class RegistrationManager { 
	/** Creates the private instance of Registration Manager */
	private static RegistrationManager instance;
	/** calls the courseCatalog object*/
	private CourseCatalog courseCatalog;
	/**calls the studentDirectory object*/
	private StudentDirectory studentDirectory;
	/**creates blank registrar user*/
	private User registrar;
	/**creates blank student user*/
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** sets PROP_FILE to the registrar.properties file */
	private static final String PROP_FILE = "registrar.properties";
	/** Creates a new private faculty directory for the registration manager */
	private FacultyDirectory facultyDirectory;
	/** calls the createRegistrar method that creates the Registration Manager Object*/
	private RegistrationManager() {
		createRegistrar();
		currentUser = getCurrentUser();
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
		facultyDirectory = new FacultyDirectory();
	}
	
	/**
	 * Creates an instance of Registrar that will be used by the Registration manager.
	 * @throws IllegalArgumentException if registrar cannot be created in Registration Manager
	 */
	private void createRegistrar() {
		Properties prop = new Properties();
		
		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);
			
			String hashPW = hashPW(prop.getProperty("pw"));
			
			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"), prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}
	
	/**
	 * Method that hashes the users password to a unique identifier so that the users password is not 
	 * easily readable by outside users. 
	 * @param pw is the users password being passed to be hashed.
	 * @return returns a string with the hashed password in the new string.
	 * @throws IllegalArgumentException if the password cannot be hashed
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}
	
	/**
	 * Returns an instance of the Registration manager
	 * @return an instance of Registration manager if the instance is not null
	 */
	public static RegistrationManager getInstance() {
		  if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}
	
	/**
	 * Returns the Course Catalog from the RegistrationManager. 
	 * @return the current Course catalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}
	
	/**
	 * Returns the Student Directory from the RegistrationManager.
	 * @return the Student Directory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory; 
	}
	
	/**
	 * Returns a new faculty directory
	 * @return the faculty directory
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}

	/**
	 * Make student login
	 * @param id id that user input
	 * @param password password the user input
	 * @return true or false based on the input
	 */
	public boolean login(String id, String password) {
		// check to ensure nobody else is logged in
		if (currentUser == null) {
			
			if (registrar.getId().equals(id)) {
				MessageDigest digest;
				try {
					digest = MessageDigest.getInstance(HASH_ALGORITHM);
					digest.update(password.getBytes());
					String localHashPW = new String(digest.digest());
					if (registrar.getPassword().equals(localHashPW)) {
						currentUser = registrar;
						return true;
					} else {
						return false;
						
					}
				}
				catch (NoSuchAlgorithmException e) {
					throw new IllegalArgumentException(); 
				}  
			}
			
			User s = studentDirectory.getStudentById(id);
			
			if (s != null) {  
				try {

					MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
					digest.update(password.getBytes());
					String localHashPW = new String(digest.digest());
					if (s.getPassword().equals(localHashPW)) {
						currentUser = s;
						return true;
					} else {
						return false;
					} 
				}
				catch (NoSuchAlgorithmException e) {
					throw new IllegalArgumentException();
				} catch (NullPointerException e) {
					throw new IllegalArgumentException("User doesn't exist.");
				}
			}
			
			Faculty f = facultyDirectory.getFacultyById(id);
			
			if (f != null) {  
				try {

					MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
					digest.update(password.getBytes());
					String localHashPW = new String(digest.digest());
					if (f.getPassword().equals(localHashPW)) {
						currentUser = f;
						return true;
					} else {
						return false;
					} 
				}
				catch (NoSuchAlgorithmException e) {
					throw new IllegalArgumentException();
				} 
			}
			else {
				throw new IllegalArgumentException("User doesn't exist.");
			}
			
		} 
		 
		return false;
		
	}

	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        CourseRoll roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canEnroll(s)) {
	            schedule.addCourseToSchedule(c);
	            roll.enroll(s);
	            return true;
	        }
	        
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	    return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        c.getCourseRoll().drop(s);
	        return s.getSchedule().removeCourseFromSchedule(c);
	    } catch (IllegalArgumentException e) {
	        return false; 
	    }
	}

	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 */
	public void resetSchedule() {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	        //do nothing 
	    }
	}

	/**
	 * Make user logout
	 */
	public void logout() {
		currentUser = null; 
	}
	  
	/**
	 * Gets the current user from the prop file specified by the registrar
	 * @return the current user
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	
	/**
	 * Make user clear the data
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory(); 
	}
	
	
	 
	/**
	 * RegistrationManager class for PackScheduler
	 * 
	 * @author Jon Lett
	 * @author Stephen Welsh & Jeff Li
	 * @author Jeff Li
	 *
	 */
	private static class Registrar extends User {
		/**
		 * Create a registrar user with the user id and password 
		 * in the registrar.properties file.
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
}

