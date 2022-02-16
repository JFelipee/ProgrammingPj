package project;

import java.util.List;
import exceptions.*;

public interface Programming {
	
	/**
	 * A new course can be registered.
	 * @param code
	 * @param date
	 * @param coordinator
	 * @throws InvalidInputArgumentException  The code, the name and the coordinator, cannot be blank
	 * @throws DuplicatedException	Code duplicated
	 * 
	 * 
	 */
	public void newCourse (int code, String date, String coordinator)throws InvalidInputArgumentException, DuplicatedException;
	
	/**
	 * A new student can be registered.
	 * @param id
	 * @param name
	 * @param email
	 * @throws InvalidInputArgumentException The identification number, the name and the email cannot be blank The format of the email address must be correct, that is, 
	 * it must contain the character ’@’ and it cannot end with the character ’.’.
	 * @throws DuplicatedException Id duplicated
	 */
	public void newStudent (int id, String name, String email) throws InvalidInputArgumentException, DuplicatedException;
	
	/**
	 * A student can be enrolled in a course, by using the identification number of the student and the course code. 
	 * @param id
	 * @param code
	 * @throws DuplicatedException A student cannot be enrolled in the same course twice.
	 * @throws LimitListException A course could have, at most, 50 students matriculated.
	 * @throws NotFoundInSystemException The student must be already registered in the system, The course must be already registered in the system.
	 */
	public void enrolled (int id, int code) throws DuplicatedException, LimitListException, NotFoundInSystemException;
	
	
	/**
	 * The system must return the list of its matriculated students.The list must be sorted by the identification number.
	 * @param code
	 * @throws InvalidInputArgumentException The code cannot be blank
	 * @throws EmptyListException The list cannot be empty
	 * @throws NotFoundInSystemException
	 */
	public  List <Student> matriculatedStudent (int code) throws InvalidInputArgumentException, EmptyListException, NotFoundInSystemException;
	
	
	/**
	 * A student can cancel its enrollment in a course.
	 * @param student
	 * @param course
	 * @throws InvalidInputArgumentException student and course cannot be blank
	 * @throws EmptyListException	the list cannot be blank
	 * @throws NotFoundInSystemException
	 */
	public void cancelEnrollment (Student student, Course course) throws InvalidInputArgumentException, EmptyListException, NotFoundInSystemException;
	
	
	/**
	 * A course can be restarted and this operation must remove all students matriculated in the course.
	 * @param course
	 * @throws InvalidInputArgumentException Course cannot be blank
	 * @throws NotFoundInSystemException
	 * @throws EmptyListException	List cannot be blank
	 */
	public void restarted (Course course) throws InvalidInputArgumentException,NotFoundInSystemException,EmptyListException;
	
	/**
	 * The user can obtain a list of all users registered in the system.
	 * @return List
	 */
	public List <Student> listUser();
	
	/**
	 * The user can obtain a list of all courses, sorted by their code.
	 * @return List
	 */
	public List <Course> listCourse();
} // de programming
