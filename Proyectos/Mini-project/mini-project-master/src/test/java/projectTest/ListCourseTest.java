package projectTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InvalidInputArgumentException;
import project.Course;
import project.MiniProject;
import project.Student;

class ListCourseTest {
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
	public void listCourseTest1() {
		List <Course> course1 = cursoAlumno.listCourse();
		assertEquals(this.courses.toString(), course1.toString());
	}
	

}
