package projectTest;


import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InvalidInputArgumentException;
import project.Course;
import project.MiniProject;
import project.Student;

class MiniProjectTest {
	private ArrayList <Student> students;
	private ArrayList <Course> courses;
	private MiniProject cursoAlumno;
	
	@BeforeEach
	private void init () throws InvalidInputArgumentException{
		this.students = new ArrayList <Student>();
		this.courses = new ArrayList <Course>();
		this.cursoAlumno = new MiniProject (students,courses);
	}
	@Test
	public void StudentNullTest() {
		assertThrows (InvalidInputArgumentException.class, () ->{
			this.cursoAlumno = new MiniProject (this.students, null);
		});
	}
	
	@Test
	public void courseNullTest () {
		assertThrows (InvalidInputArgumentException.class, ()->{
			this.cursoAlumno= new MiniProject (null, this.courses);
		});
	}

}
