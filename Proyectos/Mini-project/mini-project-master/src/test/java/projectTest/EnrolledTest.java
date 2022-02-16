package projectTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.DuplicatedException;
import exceptions.InvalidInputArgumentException;
import exceptions.LimitListException;
import exceptions.NotFoundInSystemException;
import project.Course;
import project.MiniProject;
import project.Student;

class EnrolledTest {
	private ArrayList <Student> students;
	private ArrayList <Course> courses;
	private MiniProject cursoAlumno;
	
	@BeforeEach
	private void init () throws InvalidInputArgumentException{
		this.students = new ArrayList <Student>();
		this.courses = new ArrayList <Course>();
		this.cursoAlumno = new MiniProject (students, courses);
	}
	
	@Test
	public void enrolledTest1 () throws InvalidInputArgumentException,DuplicatedException, LimitListException,NotFoundInSystemException{
		cursoAlumno.newStudent(33, "Jose Antonio " ,"jose.marquez.ordonez@alumnos.upm.es");
		cursoAlumno.newCourse(1, "Maths", "Sebastian");
		cursoAlumno.enrolled(33, 1);
		assertEquals(1, courses.get(0).getListStudent().size());
		assertEquals(this.students.toString(), courses.get(0).getListStudent().toString());	
	}
	
	@Test
	public void enrolledTest2 () throws InvalidInputArgumentException,DuplicatedException, LimitListException,NotFoundInSystemException{
		cursoAlumno.newStudent(21, "Jason Taco " ,"jason@alumnos.upm.es");
		cursoAlumno.newCourse(2, "Programmin Project", "Guillermo");
		cursoAlumno.enrolled(21, 2);
		assertEquals(1, courses.get(0).getListStudent().size());
		assertEquals(this.students.toString(), courses.get(0).getListStudent().toString());	
	}
	
	@Test
	public void enrolledTest3 () throws InvalidInputArgumentException,DuplicatedException, LimitListException,NotFoundInSystemException{
		cursoAlumno.newStudent(21, "Jason Taco " ,"jason@alumnos.upm.es");
		cursoAlumno.newCourse(2, "Programmin Project", "Guillermo");
		cursoAlumno.enrolled(21, 2);
		cursoAlumno.newStudent(33, "Jose Antonio " ,"jose.marquez.ordonez@alumnos.upm.es");
		cursoAlumno.enrolled(33, 2);
		assertEquals(2, courses.get(0).getListStudent().size());
		assertEquals(this.students.toString(), courses.get(0).getListStudent().toString());	
	}
	
	@Test 
	public void enrolledtDuplicatedStudentTest1() throws NotFoundInSystemException, DuplicatedException, 
		LimitListException, InvalidInputArgumentException{
		cursoAlumno.newStudent(33, "Jose Antonio " ,"jose.marquez.ordonez@alumnos.upm.es");
		cursoAlumno.newCourse(1, "Maths", "Sebastian");
		cursoAlumno.enrolled(33, 1);
		assertThrows(DuplicatedException.class, ()->{
			cursoAlumno.enrolled(33, 1);
		});
	}	
	
	
	@Test 
	public void enrolledtDuplicatedStudentTest2() throws InvalidInputArgumentException, DuplicatedException, 
		LimitListException,NotFoundInSystemException {
		cursoAlumno.newStudent(21, "Jason Taco " ,"jason@alumnos.upm.es");
		cursoAlumno.newCourse(2, "Programmin Project", "Guillermo");
		cursoAlumno.enrolled(21, 2);
		assertThrows(DuplicatedException.class, ()->{
			cursoAlumno.enrolled(21, 2);
		});
	}	
	

	@Test
	public void enrolledLimitListTest1() throws NotFoundInSystemException, 
						LimitListException, InvalidInputArgumentException, DuplicatedException {
		String nameStudent = "Jose Antonio";
		cursoAlumno.newCourse(2, "Maths", "Guillermo");
		for (int i = 1; i <= 51; i++) {
			cursoAlumno.newStudent(i, nameStudent, "jose@alumnos.upm.es");
			cursoAlumno.enrolled(i, 2);
		}
		assertThrows(LimitListException.class, ()->{
	    	  cursoAlumno.newStudent(52, " Jason Taco", "jason@alumnos.upm.es");
	    	  cursoAlumno.enrolled(52, 2);
	        });
	}
	
	
	@Test 
	public void enrolledNotFoundInSystemTest1() throws NotFoundInSystemException, 
	LimitListException, InvalidInputArgumentException, DuplicatedException{
		cursoAlumno.newStudent(2, "Jose Antonio", "jose@alumnos.upm.es");
		assertThrows (NotFoundInSystemException.class, ()->{
		cursoAlumno.enrolled (2,5);
		});
	}
	
	@Test 
	public void enrolledNotFoundInSystemTest2() throws NotFoundInSystemException, 
	LimitListException, InvalidInputArgumentException, DuplicatedException{
		cursoAlumno.newCourse(45, "Maths", "Sebastian");
		assertThrows (NotFoundInSystemException.class, ()->{
		cursoAlumno.enrolled (1,45);
		});
	}
	
}
