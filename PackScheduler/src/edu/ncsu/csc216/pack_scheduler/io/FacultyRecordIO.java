package edu.ncsu.csc216.pack_scheduler.io;

import java.util.*;
import java.io.*;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;


/**
 * Read file and store the data as linked list
 * 
 * @author Kraig Denny
 *
 */
public class FacultyRecordIO {

	/**
	 * Read through the file and save the data of student records, throw
	 * FileNotFoundException when file can not be find. Create Sorted Array list of Student
	 * to store the data
	 * 
	 * @param fileName the file to read
	 * @throws FileNotFoundException When file isn't find
	 * @return studentRecords the student records data
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		LinkedList<Faculty> facultyRecords = new LinkedList<Faculty>();
		while (fileReader.hasNextLine()) {
			try {
				String fileLines = fileReader.nextLine();
				Faculty facultyRecord = readFaculty(fileLines);
				facultyRecords.add(facultyRecord);

			} catch (IllegalArgumentException e) {
				// skip the line
			}
		}
		fileReader.close();
		return facultyRecords;
	}

	/**
	 * Store the student from the file to Student, to check if they are valid data
	 * 
	 * @param faculty the student record info
	 * @return the student record
	 */
	private static Faculty readFaculty(String faculty) {
		String firstName = null;
		String lastName = null;
		String id = null;
		String email = null;
		String password = null;
		int maxCourse = 0;
		try {
			Scanner read = new Scanner(faculty);
			read.useDelimiter(",");
			int i = 0;
			while (read.hasNext()) {
				if (i == 0) {
					firstName = read.next();
				}
				if (i == 1) {
					lastName = read.next();
				}
				if (i == 2) {
					id = read.next();
				}
				if (i == 3) {
					email = read.next();
				}
				if (i == 4) {
					password = read.next();
				}
				if (i == 5) {
					maxCourse = read.nextInt();
				}
				if (i == 6) {
					break; 
				}
				i++;
			}
			read.close();
		} catch (IllegalArgumentException e) {
			//skip line
		}
		Faculty newFaculty = new Faculty(firstName, lastName, id, email, password, maxCourse);
		return newFaculty;
	}

	/**
	 * Write the faculty records to the file, with new faculty records.
	 *   
	 * @param fileName         the file to write in
	 * @param facultyDirectory the arrayList contains all faculty directory
	 * @throws IOException when File can not be write to
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
		// TODO Auto-generated method stub
		PrintStream fileWriter = new PrintStream(new File(fileName));
		for (int i = 0; i < facultyDirectory.size(); i++) {
			fileWriter.println(facultyDirectory.get(i).toString());
		}
		fileWriter.close();
	}

}
