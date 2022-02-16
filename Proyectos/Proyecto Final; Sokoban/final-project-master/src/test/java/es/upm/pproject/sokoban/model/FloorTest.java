package es.upm.pproject.sokoban.model;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FloorTest {
	
	private Floor floor;
	Image image;
	
	@BeforeEach
	public void init() {
		
		floor = new Floor(2, 2);
		
	} // of init
	
	@Test
	@DisplayName("Test for the position of the floor")
	public void testPositionF1() {
		
		assertEquals(2, floor.getX());
		assertEquals(2, floor.getY());
		
	} // of testPositionF1
	
	@Test
	@DisplayName("Test for the position of the floor")
	public void testPositionF2() {
		
		Floor f = new Floor(1, 6);
		
		assertEquals(1, f.getX());
		assertEquals(6, f.getY());
		
	} // of testPositionF2
	
	@Test
	@DisplayName("Test for the origin position")
	public void testOPFloor() {
		
		Floor f = new Floor(0, 0);
		
		assertEquals(0, f.getX());
		assertEquals(0, f.getY());
		
	} // of testOPFloor
	
	@Test
	@DisplayName("Test the position of the floor")
	public void testPositionF3() {
		
		Floor f = new Floor(3, 5);
		
		assertEquals(3, f.getX());
		assertEquals(5, f.getY());
		
	} // of testPositionF3
	
	@Test
	@DisplayName("Test that the image is not null")
	public void testImFloor1() {
		
		Floor f = new Floor(5, 5);
		
		assertEquals(5, f.getX());
		assertEquals(5, f.getY());
		
		assertNotEquals(null, f.getImage());
		
	} // of testImFloor1
	
	@Test
	@DisplayName("Test that the image is not null")
	public void testImFloor2() {
		
		Floor f = new Floor(7, 5);
		
		assertEquals(7, f.getX());
		assertEquals(5, f.getY());
		
		assertNotEquals(null, f.getImage());
		
	} // of testImFloor
	
	@Test
	@DisplayName("Test that the image is not null")
	public void testImFloor3() {
		
		Floor f = new Floor(6, 2);
		
		assertEquals(6, f.getX());
		assertEquals(2, f.getY());
		
		assertNotEquals(null, f.getImage());
		
	} // of testImFloor3
	
	@Test
	@DisplayName("Test that the image is null")
	public void test_Exception() {
		
		Floor f = new Floor(6, 3);
		
		assertThrows(IOException.class, () -> {
			Image imagen = ImageIO.read(new File("src/main/resources/Floor/NoExist.png"));
			f.setImage(imagen);
		});
		
	} // of test_Error

} // of FloorTest
