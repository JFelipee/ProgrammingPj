package es.upm.pproject.sokoban.model;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GoalTest {
	
	private Goal goal;
	
	@BeforeEach
	public void init () {
		
		goal = new Goal(2,3);
		
	} // of ini
	
	@Test
	@DisplayName("Test axes")
	public void testGoal() {
		
		assertEquals(2, goal.getX());
		assertEquals(3, goal.getY());
		
	} // of testGoal
	
	@Test
	@DisplayName("Test axes")
	public void testGoal2() {
		
		Goal goalNew = new Goal (5,6);
		assertEquals(5, goalNew.getX());
		assertEquals(6,goalNew.getY());
		
	} // of testGoal2
	
	@Test
	@DisplayName("Test that the image is not null")
	public void testImGoal1() {
		
		Goal g = new Goal(5, 5);
		
		assertEquals(5, g.getX());
		assertEquals(5, g.getY());
		
		assertNotEquals(null, g.getImage());
		
	} // of thestImGoal1
	
	@Test
	@DisplayName("Test that the image is not null")
	public void testImGoal2() {
		
		Goal g = new Goal(0, 0);
		
		assertEquals(0, g.getX());
		assertEquals(0, g.getY());
		
		assertNotEquals(null, g.getImage());
		
	} // of thestImGoal2
	
	@Test
	@DisplayName("Test that the image is null")
	public void test_Exception() {
		
		Goal g = new Goal(6, 3);
		
		assertEquals(6, g.getX());
		assertEquals(3, g.getY());
		
		assertThrows(IOException.class, () -> {
			Image imagen = ImageIO.read(new File("src/main/resources/Floor/NoExist.png"));
			g.setImage(imagen);
		});
		
	} // of test_Error
	
} // of GoalTest
