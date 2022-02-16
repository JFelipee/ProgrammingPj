package projectTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

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

class RestartedTest {
	private List <Student> students;
	private List <Course> courses;
	private MiniProject cursoAlumno;
	
	@BeforeEach
	private void init () throws InvalidInputArgumentException {
		this.students = new ArrayList <Student>();
		this.courses = new ArrayList <Course>();
		this.cursoAlumno= new MiniProject(students,courses);
	}
	
	@Test
	public void restartedExceptionTest1() throws InvalidInputArgumentException, DuplicatedException {
		cursoAlumno.newCourse(33, "Maths", "Sebastian");
		assertThrows(InvalidInputArgumentException.class, () ->{
			cursoAlumno.restarted(null);
		});
	}
	
	@Test
	public void restartedExceptionTest2() {
		Course curso1= new Course (44, "Programming Project","Guillermo");
		assertThrows(EmptyListException.class, () -> {
			cursoAlumno.restarted (curso1);
		});
	}
	
	@Test
	public void restartedTest1() throws InvalidInputArgumentException, DuplicatedException, LimitListException, NotFoundInSystemException, EmptyListException {
		Course curse1 = new Course (33, "Maths", "Sebastian");
		cursoAlumno.newCourse(33, "Maths", "Sebastian");
		cursoAlumno.newStudent(44, "Jason Taco ", "Jason@alumnos.upm.es");
		cursoAlumno.enrolled(44, 33);
		cursoAlumno.restarted(this.courses.get(0));
		this.courses = cursoAlumno.listCourse();
		assertEquals(curse1.getListStudent().toString(), this.courses.get(0).getListStudent().toString());
		assertEquals(0, this.courses.get(0).getListStudent().size());
		assertEquals(1,this.students.size());
	}
	
	@Test
	public void restartedTest2() throws InvalidInputArgumentException, DuplicatedException, LimitListException, NotFoundInSystemException, EmptyListException {
		Course curse1 = new Course (33, "Maths", "Sebastian");
		cursoAlumno.newCourse(33, "Maths", "Sebastian");
		cursoAlumno.newStudent(22, "Jose Antonio", "jose@alumnos.upm.es");
		cursoAlumno.newStudent(44, "Jason Taco ", "Jason@alumnos.upm.es");
		cursoAlumno.enrolled(22, 33);
		cursoAlumno.enrolled(44, 33);
		cursoAlumno.restarted(this.courses.get(0));
		this.courses = cursoAlumno.listCourse();
		assertEquals(curse1.getListStudent().toString(), this.courses.get(0).getListStudent().toString());
		assertEquals(0, this.courses.get(0).getListStudent().size());
		assertEquals(2,this.students.size());
	}
	
	@Test
	public void restartedTest3() throws InvalidInputArgumentException, DuplicatedException, LimitListException, NotFoundInSystemException, EmptyListException {
		Course curse1 = new Course (33, "Maths", "Sebastian");
		cursoAlumno.newCourse(33, "Maths", "Sebastian");
		cursoAlumno.restarted(this.courses.get(0));
		assertEquals(curse1.getListStudent().toString(), this.courses.get(0).getListStudent().toString());
		assertEquals(0, this.courses.get(0).getListStudent().size());
		assertEquals(0,this.students.size());
	}
	
	@Test 
	public void restartedTestExceptiontest3() throws InvalidInputArgumentException, DuplicatedException {
		Course curse1= new Course (21, "Maths", "Sebastian");
		cursoAlumno.newCourse(2, "Programming Project", "Sebastian");
		assertThrows(NotFoundInSystemException.class, () -> {
			cursoAlumno.restarted(curse1);
		});
	}
	
}
