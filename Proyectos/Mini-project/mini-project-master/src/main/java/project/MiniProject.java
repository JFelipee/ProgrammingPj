package project;
import java.util.List;

import exceptions.*;

public class MiniProject implements Programming {
	
	private List  <Student> listOfStudent;
	private List  <Course>  listOfCourse;

	public MiniProject (List <Student> listOfStudent, List <Course> listOfCourse ) throws InvalidInputArgumentException{
		if (listOfStudent == null || listOfCourse == null) {
			throw new InvalidInputArgumentException();
		}
		this.listOfStudent = listOfStudent;
		this.listOfCourse = listOfCourse;
	}
	
	
	@Override
	public void newCourse(int code, String name, String coordinator) throws InvalidInputArgumentException, DuplicatedException {
		if(code == 0|| name.equals("")|| coordinator.equals("")) {
			throw new InvalidInputArgumentException();
		}// de if 
		
		else {
			Course course = new Course(code,name,coordinator);
			insertListCourse(listOfCourse,course);
		}// de else
	} // of newCourse

	
	@Override
	public void newStudent(int id, String name, String email) throws InvalidInputArgumentException, DuplicatedException {
		if( id == 0 || name.equals("") || email.equals("") ) {
			throw new InvalidInputArgumentException();
		}
		
		else if(email.indexOf('@')==-1 || email.lastIndexOf('.') == email.length()-1)   {
			throw new InvalidInputArgumentException();
		}
		
		else {
			Student student = new Student(id, name, email);
			insertListStudent(listOfStudent, student);
		}
	} 
	
	@Override
	public void enrolled (int id, int code) throws DuplicatedException, LimitListException, NotFoundInSystemException{
		Course courses= null;
		Student students= null;
		
		if (this.listOfCourse.isEmpty()) {
			throw new NotFoundInSystemException();
		}

		for (Student student : this.listOfStudent)  {
			if (student.getId() == id ) {
				students = student;
				break;
			} 
		} 
		for (Course curso : this.listOfCourse) {
			if (curso.getCode() == code) {
				courses= curso; 
			}
		}
		
		if (students == null) {
			throw new NotFoundInSystemException();
		}  
		
		if(courses == null) {
			throw new NotFoundInSystemException();
		}
		
		if (courses.getListStudent().size() > 50) {
			throw new LimitListException();
		}
		
		if (courses.getListStudent().contains(students)) {
			throw new DuplicatedException();
		}
		
		insertListStudent (courses.getListStudent(), students);
	}
	
	
	
	@Override
	public List<Student> matriculatedStudent(int code)
			throws InvalidInputArgumentException, EmptyListException, NotFoundInSystemException {
		Course courses = null;
		List <Student> studentList;
		if (this.listOfCourse.isEmpty()) {
			throw new EmptyListException();
		}
		
		if (code==0) {
			throw new NotFoundInSystemException();
		}
		
		for (Course curso1 : this.listOfCourse) {
			if (curso1.getCode()== code ) {
				courses = curso1;
				break;
			}
		} 
		
		if (courses == null) {
			throw new NotFoundInSystemException();
		}
		
		studentList= courses.getListStudent();
		return studentList;
	}
	
	
	@Override
	public void cancelEnrollment(Student student, Course course)
			throws InvalidInputArgumentException, EmptyListException, NotFoundInSystemException {
		if(student == null || course == null) {
			throw new InvalidInputArgumentException();
		}
		
		if (this.listOfStudent.isEmpty() || this.listOfCourse.isEmpty()) {
			throw new EmptyListException();
		}
		
		if (this.listOfStudent.contains(student)) {
			if (this.listOfCourse.contains(course)) {
				if (this.listOfCourse.get(this.listOfCourse.indexOf(course)).getListStudent().contains(student)) {
					this.listOfCourse.get(this.listOfCourse.indexOf(course)).getListStudent().remove(student);
				}
			}
			else {
				throw new NotFoundInSystemException();
			}
		}
		else {
			throw new NotFoundInSystemException();
		}
	}
	
	@Override
	public void restarted(Course course)
			throws InvalidInputArgumentException, NotFoundInSystemException, EmptyListException {
		if (course == null) {
			throw new InvalidInputArgumentException();
		}
		
		if (this.listOfCourse.isEmpty()) {
			throw new EmptyListException();
		}
		
		if(this.listOfCourse.contains(course)) {
			while (!this.listOfCourse.get(this.listOfCourse.indexOf(course)).getListStudent().isEmpty()) {
				cancelEnrollment(this.listOfCourse.get(this.listOfCourse.indexOf(course)).getListStudent().get(0), this.listOfCourse.get(this.listOfCourse.indexOf(course)));
			}
		}
		
		else {
			throw new NotFoundInSystemException();
		}
	}
	
	@Override
	public List<Student> listUser() {
		return this.listOfStudent;
	}


	@Override
	public List<Course> listCourse() {
		return this.listOfCourse;
	}
	
	/**
	 * Auxiliar
	 */
	private void insertListCourse (List<Course> list,  Course course)throws DuplicatedException {
		if(list.isEmpty()) {
			list.add(course);
		}// de 
		
		else {
			for(Course courseList : list) {

				if (courseList.getCode() == course.getCode()) {
					throw new DuplicatedException();
				}// de if 
				
				else if (list.get(list.size()-1).getCode() < course.getCode()) {
					list.add(course);
					break;
				}// de else if 
			}// de for 	
		}// de else 
	} // de insertListCourse
	
	/**
	 * Auxiliar
	 * */
	
	private void insertListStudent(List <Student>list, Student student) throws DuplicatedException{
		if(list.isEmpty()) {
			list.add(student);
		}// de if
		
		else {
			for(Student studentList:list) {			
				if(studentList.getId() == student.getId()) {
					throw new DuplicatedException();
				}// de if 
				else if (student.getName().toLowerCase().compareTo(studentList.getName().toLowerCase())== 0){	
					if(student.getId() < studentList.getId()) {
						list.add(list.indexOf(studentList),student);
						break;
					}// de if
					
					else {
						list.add(list.indexOf(studentList)+1,student);
						break;
					}// de else			
				}// de else if 
			
				else if (student.getName().toLowerCase().compareTo(studentList.getName().toLowerCase())<0) {
					list.add(list.indexOf(studentList),student);
					break;
				} // de else if 
				
				else if (list.get(list.size()-1).getName().toLowerCase().compareTo(student.getName().toLowerCase())<0){
					list.add(student);
					break;
				}// de else if		
			}// de for
		}// de else
	}// de insertListStudent

}
