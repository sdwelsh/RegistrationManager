package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;
import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;

/**
 * This class wil manage faculty members in much a similar way to how StudentDirectory manages
 * students.
 * @author matthew jensen
 */
public class FacultyDirectory {
	/** The algorithm for hashing */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** All of the faculty members in a linked list */
	private LinkedList<Faculty> facultyDirectory;
	/**
	* This will initialize the FacultyDirectory
	*/
	public FacultyDirectory()
	{
		newFacultyDirectory();
	}
	/**
	 * This will create a blank directory of faculty members.
	*/
	public void newFacultyDirectory()
	{
		facultyDirectory = new LinkedList<Faculty>();
	}
	/**
	* This will create a faculty directory using FacultyRecordIO
	*  to read in each member
	* @throws IllegalArgumentException if theres an error reading the file
	* @param fileName The file which includes members
	*/
	public void loadFacultyFromFile(String fileName)
	{
		try
		{
			facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
		} catch (FileNotFoundException e)
		{
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}

	/**
	 * This will attempt to add a faculty member to the linked list. Returns true if successfull
	 * false if it cant be added.
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param id the id
	 * @param email the email
	 * @param password the password
	 * @param repeatPassword the password again
	 * @param maxCredits the max credits
	 * @return true whether or not member was added successfully
	 */
	public boolean addFaculty(String firstName, String lastName, String id, String email, String password,
			String repeatPassword, int maxCredits)
	{
		String hashedPass = "";
		String hashedPass2 = ""; 
		if (password == null || repeatPassword == null || repeatPassword.equals("") || password.equals(""))
		{
			throw new IllegalArgumentException("Invalid password");
		}
		
		try
		{ 
			MessageDigest digester = MessageDigest.getInstance(HASH_ALGORITHM);
			digester.update(password.getBytes());
			hashedPass = new String(digester.digest()); 
			MessageDigest digester2 = MessageDigest.getInstance(HASH_ALGORITHM);
			digester2.update(repeatPassword.getBytes());
			hashedPass2 = new String(digester2.digest());
		}
		catch (NoSuchAlgorithmException e)
		{
			throw new IllegalArgumentException("Cannot hash password");
		}

		if (!hashedPass.equals(hashedPass2)) // two passwords different
		{
			throw new IllegalArgumentException("Passwords do not match");
		}
		
		Faculty newMember = new Faculty(firstName, lastName, id, email, hashedPass, maxCredits);

		for (int i = 0; i < facultyDirectory.size(); i++)
		{
			User existingMember = facultyDirectory.get(i);
			
			if (newMember.getId().equals(existingMember.getId()))
			{
				return false; // faculty member already in directory
			}
		}
		
		facultyDirectory.add(newMember); // adds member
		return true;
	}

	/**
	 * Removes a member of the faculty by their given ID
	 * 
	 * @param facultyId the id
	 * @return whether or not the member was removed
	 */
	public boolean removeFaculty(String facultyId) {
		
		for (int i = 0; i < facultyDirectory.size(); i++)
		{
			User existingMember = facultyDirectory.get(i);
			if (facultyId.equals(existingMember.getId()))
			{
				facultyDirectory.remove(i);
				return true;
			}
		}
		
		return false; 
	}

	/**
	 * This will return a double string array of the faculty directory
	 * with a certain format for each field of a member
	 * @return The double array of member fields
	 */
	public String[][] getFacultyDirectory()
	{
		String[][] fDirectory = new String[facultyDirectory.size()][3];
		for (int i = 0; i < facultyDirectory.size(); i++)
		{
			User fMember = facultyDirectory.get(i);
			fDirectory[i][0] = fMember.getFirstName();
			fDirectory[i][1] = fMember.getLastName();
			fDirectory[i][2] = fMember.getId();
		}
		return fDirectory;   
	}

	/**
	 * This will create a new file of the faculty directory using 
	 * faculty record io to write the records
	 * @param fileName The name of the file to be created
	 */
	public void saveFacultyDirectory(String fileName)
	{
		try
		{
			FacultyRecordIO.writeFacultyRecords(fileName, facultyDirectory);
		} catch (IOException e)
		{
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}
}