package es.upm.pproject.sokoban.model;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoxTest  {
	
	private Box box;
	@BeforeEach
	public void init () {
		box = new Box(0,0);
	}
	
	@Test
	@DisplayName("Test x")
	public void testMove() {
		box.move(1,1);
		assertEquals(1, box.getX());
		assertEquals(1,box.getY());
	}
	
	@Test
	@DisplayName("Test y")
	public void testMove2() {
		box.move(4, 5);
		assertEquals(4, box.getX());
		assertEquals(5,box.getY());
	}
	
	@Test
	@DisplayName("Test that the image is not null")
	public void testImMove1() {
		
		Box b = new Box(2, 3);
		
		assertEquals(2, b.getX());
		assertEquals(3, b.getY());
		
		assertNotEquals(null, b.getImage());
		
	} // of thestImMove
	
	@Test
	@DisplayName("Test that the image is not null")
	public void testImMove2() {
		
		Box b = new Box(4, 0);
		
		assertEquals(4, b.getX());
		assertEquals(0, b.getY());
		
		assertNotEquals(null, b.getImage());
		
	} // of thestImMove2
	
	@Test
	@DisplayName("Test that the image is null")
	public void test_Exception() {
		
		Box b = new Box(6, 3);
		
		assertEquals(6, b.getX());
		assertEquals(3, b.getY());
		
		assertThrows(IOException.class, () -> {
			Image imagen = ImageIO.read(new File("src/main/resources/Floor/NoExist.png"));
			b.setImage(imagen);
		});
		
	} // of test_Error
	
}