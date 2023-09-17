package es.upm.pproject.sokoban.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



public class LevelFileTest{
	
	private LevelFile level;
	private String result;
	private String [] tablero = {"+++++","+.*.+","+.#.+","+W..+","+++++"};
	
	@BeforeEach
	void createNewLevel() {
		level = new LevelFile("Level 1",5,5,tablero);
		result = "Name: Level 1\n" + "Dimension: 5 x 5\n" + 
				 "+++++\n" + "+.*.+\n" + "+.#.+\n" + "+W..+\n" + "+++++\n";
	}
	
	@Test
	@DisplayName("Test the whole level constructor")
	void createCorrectLevel() {
		assertEquals(result,level.toString());
	}
	
	@Test
	@DisplayName("Test the level constructor using the name")
	void createLevelCorrectName() {
		assertEquals("Level 1", level.getName());
	}
	
	@Test
	@DisplayName("Test the level constructor using the number of rows")
	void createLevelCorrectNrows() {
		assertEquals(5, level.getNrows());
	}
	
	@Test
	@DisplayName("Test the level constructor using the number of columns")
	void createLevelCorrectNcolumns() {
		assertEquals(5, level.getNcolumns());
	}
	
	@Test
	@DisplayName("Test the level constructor using the board elements")
	void createLevelCorrectElems() {
//		String [] tablero = {"+++++","+.*.+","+.#.+","+W..+","+++++"};
		assertArrayEquals(tablero, level.getElems());
	}
	
	@Test
	@DisplayName("Try to get the second row of elements")
	void getCorrectRows() {
//		String [] tablero = {"+++++","+.*.+","+.#.+","+W..+","+++++"};
		assertEquals("+.*.+",level.getRow(2));
	}
	
	@Test
	@DisplayName("Try to get the first column of elements")
	void getCorrectColumn() {
//		String [] tablero = {"+++++","+.*.+","+.#.+","+W..+","+++++"};
		assertEquals("+++++",level.getColumn(1));
	}
	
	
}