package projectTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.DuplicatedException;
import exceptions.EmptyListException;
import exceptions.InvalidInputArgumentException;
import exceptions.LimitListException;
import exceptions.NotFoundInSystemException;
import project.Course;
import project.MiniProject;
import project.Student;

class CancelTest {
	private ArrayList <Student> students;
	private ArrayList <Course> courses;
	private MiniProject cursoAlumno; 
	
	@BeforeEach
	private void init () throws InvalidInputArgumentException {
		this.students = new ArrayList <Student>();
		this.courses = new ArrayList <Course>();
		this.cursoAlumno = new MiniProject (students, courses);
	}
	
	@Test
	public void cancelStudentTest() {
		assertThrows (InvalidInputArgumentException.class, ()->{
			this.cursoAlumno.newCourse(22, "Maths", "Sebastian");
			this.cursoAlumno.cancelEnrollment(null, this.courses.get(0));
		}); 
	}

	@Test
	public void cancelCourseTest() {
		assertThrows (InvalidInputArgumentException.class, ()->{
			this.cursoAlumno.newStudent(33, "jason Paul", "jason@alumnos.upm.es");
			this.cursoAlumno.cancelEnrollment(this.students.get(0), null);
		});
	}
	
	@Test
	public void cancelListOfStudentEmptyTest() {
		assertThrows(EmptyListException.class, ()->{
			this.cursoAlumno.newCourse(3, "Maths", "Sebastian");
			Student student1= new Student(33, "Jason taco", "jason@alumnos.upm.es");
			this.cursoAlumno.cancelEnrollment(student1, this.courses.get(0));
		});
	}
	
	@Test
	public void cancelListOfCourseEmptyTest() {
		assertThrows(EmptyListException.class, () -> {
			this.cursoAlumno.newStudent(33, "jason Taco ", "jason@alumnos.upm.es");
			Course course1 = new Course(3, "Maths", "Sebastian");
			this.cursoAlumno.cancelEnrollment(this.students.get(0), course1);
		});
	}
	
	@Test
	public void cancelTest1() throws InvalidInputArgumentException, DuplicatedException, EmptyListException, LimitListException, NotFoundInSystemException{
		this.cursoAlumno.newStudent(2,"Jason Taco ", "jaso@alumnos.upm.es");
		this.cursoAlumno.newCourse(33, "Maths", "Sebastian");
		this.cursoAlumno.enrolled(2, 33);
		this.cursoAlumno.cancelEnrollment(this.students.get(0), this.courses.get(0));
		assertEquals(1, this.students.size());
		assertEquals (0,this.courses.get(0).getListStudent().size());
	}
	
	
	
	@Test 
	public void cancelTestExceptionTest1() {
		assertThrows(NotFoundInSystemException.class, () ->{
			this.cursoAlumno.newStudent(33, "Jason", "jason@alumnos.upm.es");
			this.cursoAlumno.newCourse(2, "Maths", "Sebastian");
			Course course1 = new Course  (5,"Programming Project", "Guillermo");
			this.cursoAlumno.cancelEnrollment(this.students.get(0), course1);
		});
	}
	
	@Test 
	public void cancelTestExceptionTest2() {
		assertThrows (NotFoundInSystemException.class,() ->{
			this.cursoAlumno.newStudent(33, "Jason", "jason@alumnos.upm.es");
			this.cursoAlumno.newCourse(2, "Maths", "Sebastian");
			Student student1 = new Student  (5,"jose", "jose@alumnos.upm.eso");
			this.cursoAlumno.cancelEnrollment(student1, this.courses.get(0));
		});
	}
}
