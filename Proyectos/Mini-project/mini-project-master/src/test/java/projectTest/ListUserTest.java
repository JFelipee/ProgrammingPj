package projectTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.DuplicatedException;
import exceptions.InvalidInputArgumentException;
import project.Course;
import project.MiniProject;
import project.Student;

class ListUserTest {

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
	public void ListUserTest1() throws InvalidInputArgumentException, DuplicatedException {
		cursoAlumno.newStudent(4, "jose antontio", "jose@alumnos.upm.es");
		List <Student> student1= cursoAlumno.listUser();
		assertEquals(this.students.toString(), student1.toString());
		assertEquals(1, student1.size());
	}
	
	@Test 
	public void ListUserTest2() {
		List <Student> student1 = cursoAlumno.listUser();
		assertEquals(this.students.toString(), student1.toString());
		assertEquals(0, student1.size());
	}
	
}
