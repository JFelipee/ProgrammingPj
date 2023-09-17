package es.upm.pproject.sokoban.model;


import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BoardTest {
	
	private Board board;
	
	
	@BeforeEach
	void initializeBoard() throws InvalidFileFormatException, IOException, InvalidBoardElementException, InvalidBoardFormatException {
		String path = "src/test/resources/LevelFileReader/";
		String file = "Level_1.txt";
		LevelFile level = LevelFileReader.readFile(path, file);
		board = new Board(level.getNrows(),level.getNcolumns(),level.getElems(), true);
	}
	
	
	@Test
	@DisplayName("Check if reconognizes a correct board")
	void correctBoard() throws InvalidBoardElementException, InvalidBoardFormatException {
		String [] elems = {"+++++","+.*.+","+.#.+","+W..+","+++++"};
		Board boardTest1 = new Board(5,5,elems, true);
		assertEquals(board.toString(),boardTest1.toString());
	}
	
	@Test
	@DisplayName("Check if it recognizes an incorrect board")
	void incorrectBoard() throws InvalidBoardElementException, InvalidBoardFormatException {
		String [] elems = {"+++++","+.*.+","+.#.+","+W..+"};
		Board boardTest2 = new Board(4,5,elems, true);
		assertNotEquals(board.toString(),boardTest2.toString());
	}
	
	@Test
	@DisplayName("Check if the set of a row has worked")
	void setFifthRow() throws InvalidBoardElementException, InvalidBoardFormatException {
		String [] elems = {"+++++","+.*.+","+.#.+","+W..+","++..+"};
		Board boardTest3 = new Board(5,5,elems, true);
		boardTest3.setRow(5, "+++++");
		assertEquals(board.toString(),boardTest3.toString());		
	}
	
	@Test
	@DisplayName("Check if the set of a column has worked")
	void setFirstColumn() throws InvalidBoardElementException, InvalidBoardFormatException {
		String [] elems = {".++++","+.*.+","+.#.+","+W..+","+++++"};
		Board boardTest4 = new Board(5,5,elems, true);
		boardTest4.setColumn(1, "+++++");
		assertEquals(board.toString(),boardTest4.toString());		
	}
	
	@Test
	@DisplayName("Check if the set of an element has worked")
	void setElementRow2Coolumn3() throws InvalidBoardElementException, InvalidBoardFormatException {
		String [] elems = {"+++++","+.*.+","+.#.+","+W..+","+++++"};
		Board boardTest5 = new Board(5,5,elems, true);
		boardTest5.setElement(2,3,'+');
		boardTest5.setElement(2,3,'*');
		assertEquals(board.toString(),boardTest5.toString());
	}
	
	
	@Test
	@DisplayName("Check whether the second row of the board is correct")
	void getSecondRow() {
		Moves[] row = {new Wall(1,2),new Floor(2,2), new Goal(3,2), new Floor(4,2), new Wall(5,2)};
		assertEquals(Arrays.toString(row),Arrays.toString(board.getRow(2)));		
	}
	
	@Test
	@DisplayName("Check if the first column is correct")
	void getFirstColumn() {
		Moves[] row = {new Wall(1,1),new Wall(1,2), new Wall(1,3), new Wall(1,4), new Wall(1,5)};
		assertEquals(Arrays.toString(row),Arrays.toString(board.getColumn(1)));		
	}
	
	@Test
	@DisplayName("Check if the element [1,2] is correct")
	void getFirstRowSecondColumnElement() {
		Moves elem = new Wall(2,1);
		assertEquals(elem.toString(),board.getElem(1, 2).toString());
	}
	
	
	@Test
	@DisplayName("Check whether the board obtained is correct or not")
	void getCorrectBoard() throws InvalidBoardElementException, InvalidBoardFormatException {
		String [] elems = {"+#","W*"};
		Board board2 = new Board(2,2,elems, true);
		
		Moves [][] board3 = {{new Wall(1,1),new Box(2,1)},
							{new WarehouseMan(1,2),new Goal(2,2)}};
		
		String firstRowBoard2 = Arrays.toString(board2.getBoard()[0]);
		String SecondRowBoard2 = Arrays.toString(board2.getBoard()[1]);
		
		
		assertEquals(Arrays.toString(board3[0]),firstRowBoard2);
		assertEquals(Arrays.toString(board3[1]),SecondRowBoard2);
	}
	
	
	@Test
	@DisplayName("Check if the dimension of the board is correct")
	void getNRowsAndColumns() {
		
		assertEquals(5,board.getNrows());
		assertEquals(5,board.getNcolumns());
	}
	
	@Test
	@DisplayName("Check if it throws an error when an incorrect element is tried to set")
	void setAnIncorrectElement() {
		assertThrows(InvalidBoardElementException.class, () -> board.setElement(2, 2, 'z'));
	}
	
	@Test
	@DisplayName("Check if it detects a greater number of warehouseman than allowed")
	void incorrectNumberWarehouseman() throws InvalidBoardElementException, InvalidBoardFormatException {
		String [] elems = {".++++","+.*.+","W.#.+","+W..+","+++++"};
		assertThrows(InvalidBoardFormatException.class, () -> new Board(5,5,elems, true));	
	}
	
	
	@Test
	@DisplayName("Check if it detects a lower number of warehouseman than allowed")
	void incorrectNumberWarehouseman2() throws InvalidBoardElementException, InvalidBoardFormatException {
		String [] elems = {".++++","+.*.+","+.#.+","+...+","+++++"};
		assertThrows(InvalidBoardFormatException.class, () -> new Board(5,5,elems, true));	
	}
	
	@Test
	@DisplayName("Check if it detects a lower number of boxes than allowed")
	void incorrectNumberOfBoxes() throws InvalidBoardElementException, InvalidBoardFormatException {
		String [] elems = {".++++","+.*.+","+...+","+W..+","+++++"};
		assertThrows(InvalidBoardFormatException.class, () -> new Board(5,5,elems, true));	
	}
	
	@Test
	@DisplayName("Check if it detects a lower number of goals than allowed")
	void incorrectNumberOfGoals() throws InvalidBoardElementException, InvalidBoardFormatException {
		String [] elems = {".++++","+...+","+.#.+","+W..+","+++++"};
		assertThrows(InvalidBoardFormatException.class, () -> new Board(5,5,elems, true));	
	}
	
	@Test
	@DisplayName("Check if the number of boxes and goals are different")
	void incorrectNumberBoxesGoals() throws InvalidBoardElementException, InvalidBoardFormatException {
		String [] elems = {".++++","+.**+","+.#.+","+W..+","+++++"};
		assertThrows(InvalidBoardFormatException.class, () -> new Board(5,5,elems, true));	
	}
	
	@Test
	void testEquals() throws InvalidBoardElementException, InvalidBoardFormatException {
		String [] elems = {"+++++","+.*.+","+.#.+","+W..+","+++++"};
		Board boardTest1 = new Board(5,5,elems, true);
		Moves wall = new Wall(1,1);
		Moves player = new WarehouseMan(2,4);
		assertEquals(boardTest1.getElem(1, 1), wall);
		assertEquals(boardTest1.getElem(4, 2), player);
	}
	
	@Test
	void testNotEquals() throws InvalidBoardElementException, InvalidBoardFormatException {
		String [] elems = {"+++++","+.*.+","+.#.+","+W..+","+++++"};
		Board boardTest1 = new Board(5,5,elems, true);
		Moves wall = new Wall(1,1);
		Moves player = new WarehouseMan(2,4);
		assertNotEquals(boardTest1.getElem(2, 3), wall);
		assertNotEquals(boardTest1.getElem(3, 3), player);
	}
	
	@Test
	void testNotEquals2() {
		Moves player = new WarehouseMan(2,4);
		Moves wall = new Wall(1,1);
		assertFalse(player.equals(null));
		assertFalse(player.equals(wall));
	}
	
	@Test
	void testHashCode() throws InvalidBoardElementException, InvalidBoardFormatException {
		String [] elems = {"+++++","+.*.+","+.#.+","+W..+","+++++"};
		Board boardTest1 = new Board(5,5,elems, true);
		Moves wall = new Wall(1,1);
		Moves player = new WarehouseMan(2,4);
		assertEquals(boardTest1.getElem(1, 1).hashCode(), wall.hashCode());
		assertEquals(boardTest1.getElem(4, 2).hashCode(), player.hashCode());
	}

}
