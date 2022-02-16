package project;

import java.util.*;

public class Course {
	private int code;
	private String name;
	private String coordinator;
	private List <Student> listStudent;
	
	public Course(int code, String name, String coordinator) {
		this.code = code;
		this.name = name;
		this.coordinator = coordinator; 
		this.listStudent = new ArrayList<>();
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getCoordinator() {
		return this.coordinator;
	}
	
	
	public String toString() {
		return "(" + this.getCode() + "," + this.getName() + "," + this.getCoordinator()+ ")";
	}
	
	public List <Student> getListStudent() {
		return this.listStudent;
	}
	
}// de Course 
