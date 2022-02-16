package projectTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.*;

import java.util.*;

import project.Course;
import project.MiniProject;
import project.Student;

class NewCourseTest {
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
	public void newCourseTest1() throws InvalidInputArgumentException, DuplicatedException {
		cursoAlumno.newCourse(3, "Programming Project", "Jose");
		assertEquals(1,this.courses.size());
		Course c1= new Course(3, "Programming Project", "Jose");
		assertEquals (c1.toString(),this.courses.get(0).toString());
	}
	
	@Test
	public void NewCourseTest2() throws InvalidInputArgumentException, DuplicatedException{
		cursoAlumno.newCourse(1, "Programming Project", "Jose");
		cursoAlumno.newCourse(2, "English", "Adriana");
		assertEquals(2, this.courses.size());
		Course c1 = new Course(1, "Programming Project", "Jose");
		Course c2 = new Course(2, "English", "Adriana");
		assertEquals (c1.toString(), this.courses.get(0).toString());
		assertEquals (c2.toString(), this.courses.get(1).toString());
	}
	
	@Test 
	public void newCourseTest3() throws InvalidInputArgumentException, DuplicatedException{
		cursoAlumno.newCourse(2, "Programming Project", "Josito");
		cursoAlumno.newCourse(3, "Math", "Samuel");
		assertEquals(2, this.courses.size());
		Course c2 = new Course(3, "Math", "Samuel");
		Course c1 = new Course(2, "Programming Project", "Josito");
		assertEquals (c1.toString(), this.courses.get(0).toString());
		assertEquals (c2.toString(), this.courses.get(1).toString());
	}
	
	@Test
	public void newCourseDuplicatedTest1() {
		assertThrows(DuplicatedException.class, ()->{
			cursoAlumno.newCourse(1, "Programming Project", "Jose");
			cursoAlumno.newCourse(1, "Math", "Juan");
		});
	}
	
	@Test
	public void newCourseCodeInBlankTest1 (){
		assertThrows(InvalidInputArgumentException.class, ()->{
			cursoAlumno.newCourse(0, "Programming Project", "Josito");
		});
	}
	
	@Test
	public void newCourseNameInBlankTest1 (){
		assertThrows(InvalidInputArgumentException.class, ()->{
			cursoAlumno.newCourse(1 ,"", "Jose");
		});
	}
	
	@Test
	public void newCourseCoordinatorInBlankTest1 (){
		assertThrows(InvalidInputArgumentException.class, ()->{
			cursoAlumno.newCourse(1, "Programming Project", "");
		});
	}
}
