package es.upm.pproject.sokoban.model;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WallTest {
	
	private Wall wall;
	
	@BeforeEach
	public void init () { 
		
		wall = new Wall (3,3);
		
	} // of ini
		
	@Test
	@DisplayName("Test wall")
	public void testWall() {
		
		assertEquals(3, wall.getX());
		assertEquals(3,wall.getY());
		
	} // of testWall
	
	@Test
	@DisplayName("Test wall")
	public void testWall2() {
		Wall wall2= new Wall(4,4);
		assertEquals(4, wall2.getX());
		assertEquals(4,wall2.getY());
	} // of testWall2
	
	@Test
	@DisplayName("Test that the image is not null")
	public void testImWall1() {
		
		Wall w = new Wall(5, 5);
		
		assertEquals(5, w.getX());
		assertEquals(5, w.getY());
		
		assertNotNull(w.getImage());
		
	} // of thestImWall1
	
	@Test
	@DisplayName("Test that the image is not null")
	public void testImWall2() {
		
		Wall w = new Wall(0, 0);
		
		assertEquals(0, w.getX());
		assertEquals(0, w.getY());
		
		assertNotNull(w.getImage());
		
	} // of thestImWall2
	
	@Test
	@DisplayName("Test that the image is null")
	public void test_Exception() {
		
		Wall w = new Wall(6, 3);
		
		assertEquals(6, w.getX());
		assertEquals(3, w.getY());
		
		assertThrows(IOException.class, () -> {
			Image imagen = ImageIO.read(new File("src/main/resources/Floor/NoExist.png"));
			w.setImage(imagen);
		});
		
	} // of test_Error
	
} // of WallTest
