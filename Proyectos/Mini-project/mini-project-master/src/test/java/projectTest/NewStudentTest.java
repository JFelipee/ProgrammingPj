package projectTest;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.DuplicatedException;
import exceptions.InvalidInputArgumentException;
import project.Course;
import project.MiniProject;
import project.Student;

import java.util.*;

public class NewStudentTest {
	private ArrayList <Student> students;
	private ArrayList <Course> courses;
	private MiniProject cursoAlumno;
	
	@BeforeEach
	public void init () throws InvalidInputArgumentException {
		this.students = new ArrayList <Student>();
		this.courses = new ArrayList <Course>();
		this.cursoAlumno= new MiniProject(students,courses);
	}
	
	@Test
	public void NewStudentTest1() throws InvalidInputArgumentException, DuplicatedException {
		cursoAlumno.newStudent(6666, "Jose Marquez" , "jose.marquez.ordonez@alumnos.upm.es");
		assertEquals(1,this.students.size());
		Student student1 = new Student (6666, "Jose Marquez" , "jose.marquez.ordonez@alumnos.upm.es");
		assertEquals (student1.toString(),this.students.get(0).toString());
	}
	
	@Test
	public void NewStudentTest2() throws InvalidInputArgumentException, DuplicatedException {
		cursoAlumno.newStudent(6666, "Jose Marquez" , "jose.marquez.ordonez@alumnos.upm.es");
		cursoAlumno.newStudent (1234,"Jason Taco " , "jason.taco.paredes@alumnos.upm.es");
		assertEquals(2,this.students.size());
		Student student1 = new Student (6666, "Jose Marquez" , "jose.marquez.ordonez@alumnos.upm.es");
		Student student2 = new Student (1234,"Jason Taco " , "jason.taco.paredes@alumnos.upm.es");
		assertEquals (student2.toString(),this.students.get(0).toString());
		assertEquals (student1.toString(),this.students.get(1).toString());
	}
	
	@Test 
	public void NewStudentTest3() throws InvalidInputArgumentException, DuplicatedException {
		  cursoAlumno.newStudent(6666, "Jose Marquez", "jose.marquez.ordonez@alumnos.upm.es");
	      cursoAlumno.newStudent(1234, "Jose Marquez", "jose.marquez.ordonez@alumnos.upm.es");
	      assertEquals(2, this.students.size());
	      Student student1 = new Student(6666, "Jose Marquez", "jose.marquez.ordonez@alumnos.upm.es");
	      Student student2 = new Student(1234, "Jose Marquez", "jose.marquez.ordonez@alumnos.upm.es");
	      assertEquals (student1.toString(), this.students.get(1).toString());
	      assertEquals (student2.toString(), this.students.get(0).toString());
	}
	
	@Test 
	public void NewStudentTest4() throws InvalidInputArgumentException, DuplicatedException {
		  cursoAlumno.newStudent(6666, "Jose Marquez", "jose.marquez.ordonez@alumnos.upm.es");
	      cursoAlumno.newStudent(2234, "Jason Taco ", "otro@alumnos.upm.es");
	      assertEquals(2, this.students.size());
	      Student student1 = new Student(6666, "Jose Marquez", "jose.marquez.ordonez@alumnos.upm.es");
	      Student student2 = new Student(2234, "Jason Taco ", "otro@alumnos.upm.es");
	      assertEquals (student1.toString(), this.students.get(1).toString());
	      assertEquals (student2.toString(), this.students.get(0).toString());
	}
	
	@Test 
	public void NewStudentTest5() throws InvalidInputArgumentException, DuplicatedException {
		  cursoAlumno.newStudent(6666, "Jose Marquez", "jose.marquez.ordonez@alumnos.upm.es");
		  cursoAlumno.newStudent(1234, "Jose Marquez", "jose.marquez.ordonez@alumnos.upm.es");
	      assertEquals(2, this.students.size());
	      Student student1 = new Student(6666, "Jose Marquez", "jose.marquez.ordonez@alumnos.upm.es");
	      Student student2 = new Student(1234, "Jose Marquez", "jose.marquez.ordonez@alumnos.upm.es");
	      assertEquals (student1.toString(), this.students.get(1).toString());
	      assertEquals (student2.toString(), this.students.get(0).toString());
	}
	
	 @Test
	  public void newStudentDuplicatedTest1(){
		  assertThrows(DuplicatedException.class, ()->{
			  cursoAlumno.newStudent(1, "alumno1", "duplicate@duplicate.com");
			  cursoAlumno.newStudent(2, "alumno2", "duplicate@duplicate.com");
			  cursoAlumno.newStudent(2, "alumno2", "duplicate@duplicate.com");
			  cursoAlumno.newStudent(1, "alumno1", "duplicate@duplicate.com");
		  });
		  
	  }
	 
	 @Test 
	 public void newStudentDuplicatedTest2(){
		  assertThrows(DuplicatedException.class, ()->{
			  cursoAlumno.newStudent(6666, "Jose Marquez", "jose.marquez.ordonez@alumnos.upm.es");
			  cursoAlumno.newStudent(6666, "Jose Marquez", "jose.marquez.ordonez@alumnos.upm.es");
		  });  
	  }
	 
	 @Test 
	 public void newStudentIdBlankTest1() {
		 assertThrows(InvalidInputArgumentException.class, ()->{
			  cursoAlumno.newStudent(0, "Jose Marquez", "jose.marquez.ordonez@alumnos.upm.es");
		  });  
	 }
	 
	 @Test 
	 public void newStudentNameBlankTest1() {
		 assertThrows(InvalidInputArgumentException.class, ()->{
			  cursoAlumno.newStudent(6666, "", "jose.marquez.ordonez@alumnos.upm.es");
		  });  
	 }
	 
	 @Test
	 public void newStudentEmailBlankTest1() {
		 assertThrows(InvalidInputArgumentException.class, ()->{
			  cursoAlumno.newStudent(6666, "Jose Marquez", "");
		  });  
	 }
	 @Test
	 public void newStudentBadEmailTest1(){
		 assertThrows(InvalidInputArgumentException.class, ()->{
			  cursoAlumno.newStudent(6666, "Jose Marquez", "jose.marquez.ordonez.alumnos.upm.es");
		  });  
	 }
	 
	 @Test
	 public void newStudentBadEmailTest2() {
		 assertThrows(InvalidInputArgumentException.class, ()->{
			  cursoAlumno.newStudent(6666, "Jose Marquez", "jose.marquez.ordonez@alumnos.upm.es.");
		  });  
	 }
	 
	 @Test
	 public void newStudenBadEmailTest3() {
		 assertThrows(InvalidInputArgumentException.class, ()->{
			  cursoAlumno.newStudent(6666, "Jose Marquez", "jose.marquez.ordonez");
		  });  
	 }
	 
	 @Test 
	 public void NewStudentAllBadTest4() {
		 assertThrows(InvalidInputArgumentException.class, ()->{
			  cursoAlumno.newStudent(0, "", "jose.marquez.ordonez@alumnos.upm.es.");
		  });  
	 }
}
