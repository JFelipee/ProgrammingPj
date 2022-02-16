package es.upm.pproject.sokoban.model;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WarehouseManTest {
	
	private WarehouseMan wman;
	
	@BeforeEach
	public void init() {
		
		wman = new WarehouseMan(1, 1);
		
	} // de init
	
	@Test
	@DisplayName("Test position1")
	public void test1() {
		
		assertEquals(1, wman.getX());
		assertEquals(1, wman.getY());
		
	} // de test1
	
	@Test
	@DisplayName("Test position2")
	public void test2() {
		
		WarehouseMan wman1 = new WarehouseMan (2, 1);
		assertEquals(2, wman1.getX());
		assertEquals(1, wman1.getY());
		
	} // de test2
	
	@Test
	@DisplayName("Test position3")
	public void test3() {
		
		WarehouseMan wman2 = new WarehouseMan (2, 2);
		assertEquals(2, wman2.getX());
		assertEquals(2, wman2.getY());
		
	} // de test2
	
	@Test
	@DisplayName("Test move1")
	public void test4() {
		
		wman.move(3, 1);
		
		assertEquals(4, wman.getX());
		assertEquals(2, wman.getY());
		
	} // de test4
	
	@Test
	@DisplayName("Test move2")
	public void test5() {
		
		wman.move(1, 1);
		
		assertEquals(2, wman.getX());
		assertEquals(2, wman.getY());
		
	} // de test4
	
	@Test
	@DisplayName("Test move3")
	public void test6() {
		
		wman.move(0, 0);
		
		assertEquals(1, wman.getX());
		assertEquals(1, wman.getY());
		
	} // de test4
	
	@Test
	@DisplayName("Test that the image is null")
	public void test_Exception() {
		
		wman.move(6, 3);
		
		assertEquals(7, wman.getX());
		assertEquals(4, wman.getY());
		
		assertThrows(IOException.class, () -> {
			Image imagen = ImageIO.read(new File("src/main/resources/Floor/NoExist.png"));
			wman.setImage(imagen);
		});
		
	} // of test_Error
	
} // de WarehouseManTest
