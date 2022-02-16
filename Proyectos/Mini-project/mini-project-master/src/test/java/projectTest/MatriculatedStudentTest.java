package projectTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.DuplicatedException;
import exceptions.EmptyListException;
import exceptions.InvalidInputArgumentException;
import exceptions.LimitListException;
import exceptions.NotFoundInSystemException;
import project.MiniProject;
import project.Student;
import project.*;
class MatriculatedStudentTest {
	private ArrayList <Student> students;
	private ArrayList <Course> courses;
	private MiniProject cursoAlumno;
	
	@BeforeEach
	public void init() throws InvalidInputArgumentException{
		this.students= new ArrayList <Student>();
		this.courses = new ArrayList <Course>();
		this.cursoAlumno = new MiniProject(students, courses);
	}
	
	@Test
	public void ListOfStudentCodeInListEmptyTest1() throws InvalidInputArgumentException, DuplicatedException, NotFoundInSystemException,EmptyListException{
		cursoAlumno.newCourse(33, "maths", "Sebastian");
		List <Student> Student1 = cursoAlumno.matriculatedStudent(33);
		assertEquals (this.students.toString(),Student1.toString());
		assertEquals (0, Student1.size()); 
	}
	
	@Test 
	public void ListOfStudentCodeIdInCourse() throws InvalidInputArgumentException,DuplicatedException,NotFoundInSystemException,EmptyListException, LimitListException {
		cursoAlumno.newCourse(33, "maths", "Sebastian");
		cursoAlumno.newStudent(22, "Jose Antonio", "jose@alumnos.upm.es");
		cursoAlumno.enrolled(22, 33);
		List <Student> ListaStudent1 = new ArrayList<Student>();
		Student Student1 = new Student(22, "Jose Antonio", "jose@alumnos.upm.es"); 
		ListaStudent1.add(Student1); 
		List <Student> ListaStudent = cursoAlumno.matriculatedStudent(33);
		assertEquals(ListaStudent1.toString(), ListaStudent.toString());
		assertEquals (1,ListaStudent.size());
	}

	@Test
	public void ListOfStudentEnrolledEmptyListCourseTest1() throws InvalidInputArgumentException {
		assertThrows (EmptyListException.class, () ->{
			List <Student> student1 = cursoAlumno.matriculatedStudent(0);
		});
	}
	
	@Test
	public void ListOfStudentEnrolledEmptyCodeListTest1()  throws InvalidInputArgumentException, DuplicatedException{
		cursoAlumno.newCourse(33, "maths", "Sebastian");
		assertThrows (NotFoundInSystemException.class, () ->{
			List <Student> Student1 = cursoAlumno.matriculatedStudent(0);
		});
	}
	
	@Test
	public void ListOfStudentCodeNotInListTest1() throws InvalidInputArgumentException, DuplicatedException {
		cursoAlumno.newCourse(33, "maths", "Sebastian");
		assertThrows (NotFoundInSystemException.class, () ->{
			List <Student> Student1 = cursoAlumno.matriculatedStudent(3);
		});
	}
	
	
}
