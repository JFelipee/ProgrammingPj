package es.upm.pproject.sokoban.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LevelImplTest {
	
	private LevelImpl board;
	
	@BeforeEach
	void initializeLevelImpl() throws InvalidFileFormatException, IOException, InvalidBoardElementException, InvalidBoardFormatException {
		String path = "src/test/resources/LevelFileReader/";
		String file = "Level_1.txt";
		LevelFile level = LevelFileReader.readFile(path, file);
		this.board = new LevelImpl(level,true);
	}
	
	@Test
	@DisplayName("Check if get a board")
	void getBoardTest() throws InvalidBoardElementException {
		Moves [][] board1 = {{new Wall(1,1),new Wall(2,1), new Wall(3,1), new Wall(4,1), new Wall(5,1)},};
		
		String firstRowBoard2 = Arrays.toString(board.getBoard().getBoard()[0]);
		assertEquals(Arrays.toString(board1[0]),firstRowBoard2);
	}
	
	@Test
	@DisplayName("Check if get a board")
	void getBoardTest1() throws InvalidBoardElementException {
		Moves [][] board2 = {{new Wall(1,2), new Floor(2,2), new Goal(3,2), new Floor(4,2), new Wall(5,2)}};
		String firstRowBoard2 = Arrays.toString(board.getBoard().getBoard()[1]);
		assertEquals(Arrays.toString(board2[0]),firstRowBoard2);
	}
	
	
	@Test
	@DisplayName("Check if it detects a greater number of warehouseman than allowed")
	void incorrectNumberWarehouseman() throws InvalidBoardElementException, InvalidBoardFormatException {
		String [] elems = {"....+","+.*.+","W.#.+","+++++","++W++"};
		assertThrows(InvalidBoardFormatException.class, () -> new Board(5,5,elems, true));	
	}
	
	
	@Test
	@DisplayName("Check if it detects a lower number of warehouseman than allowed")
	void incorrectNumberWarehouseman2() throws InvalidBoardElementException, InvalidBoardFormatException {
		String [] elems = {".++++","+.+++","#.+++","....+","+++++"};
		assertThrows(InvalidBoardFormatException.class, () -> new Board(5,5,elems, true));	
	}
	
	@Test
	@DisplayName("Check if it detects a lower number of boxes than allowed")
	void incorrectNumberOfBoxes() throws InvalidBoardElementException, InvalidBoardFormatException {
		String [] elems = {".....",".++.+","W.+++",".++++","....."};
		assertThrows(InvalidBoardFormatException.class, () -> new Board(5,5,elems, true));	
	}
	
	@Test
	@DisplayName("Check if it detects a lower number of goals than allowed")
	void incorrectNumberOfGoals() throws InvalidBoardElementException, InvalidBoardFormatException {
		String [] elems = {".++++","+...+","+.#.+","+W..+","....W"};
		assertThrows(InvalidBoardFormatException.class, () -> new Board(5,5,elems, true));	
	}
	
	@Test
	@DisplayName("Check if the number of boxes and goals are different")
	void incorrectNumberBoxesGoals() throws InvalidBoardElementException, InvalidBoardFormatException {
		String [] elems = {".++++",".***.","+.#.+","+W..+","W.+++"};
		assertThrows(InvalidBoardFormatException.class, () -> new Board(5,5,elems, true));	
	}
	
	@Test
	@DisplayName("Check if it detects a movement towards up")
	void moveUpOnce() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		board.moveUp();
		WarehouseMan player = new WarehouseMan(2,3);
		Floor floor = new Floor(2,4);
		assertEquals(board.getBoard().getElem(4, 2),floor);
		assertEquals(board.getBoard().getElem(3, 2),player);
	}
	
	@Test
	@DisplayName("Check if it detects a movement towards up")
	void moveDownOnce() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		board.moveUp();
		board.moveDown();
		WarehouseMan player = new WarehouseMan(2,4);
		Floor floor = new Floor(2,3);
		assertEquals(board.getBoard().getElem(3, 2),floor);
		assertEquals(board.getBoard().getElem(4, 2),player);
	}
	
	
	@Test
	@DisplayName("Check if it detects a movement towards up")
	void moveRightOnce() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		board.moveRight();
		WarehouseMan player = new WarehouseMan(3,4);
		Floor floor = new Floor(2,4);
		assertEquals(board.getBoard().getElem(4, 2),floor);
		assertEquals(board.getBoard().getElem(4, 3),player);
	}
	
	@Test
	@DisplayName("Check if it detects a movement towards up")
	void moveLeftOnce() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		board.moveRight();
		board.moveLeft();
		WarehouseMan player = new WarehouseMan(2,4);
		Floor floor = new Floor(3,4);
		assertEquals(board.getBoard().getElem(4, 3),floor);
		assertEquals(board.getBoard().getElem(4, 2),player);
	}
	
	@Test
	@DisplayName("Check if it detects a movement towards up")
	void moveBox() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		board.moveUp();
		board.moveRight();
		WarehouseMan player = new WarehouseMan(3,3);
		Box box = new Box(4,3);
		assertEquals(board.getBoard().getElem(3, 4),box);
		assertEquals(board.getBoard().getElem(3, 3),player);
	}
	
	@Test
	@DisplayName("Check if it detects a movement towards up")
	void moveBoxToGoal() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		board.moveRight();
		board.moveUp();
		WarehouseMan player = new WarehouseMan(3,3);
		Box box = new Box(3,2);
		assertEquals(board.getBoard().getElem(3, 3),player);
		assertTrue(board.isBoxInGoal(box));
	}
	
	@Test
	@DisplayName("Check if it detects a movement towards up")
	void moveFromGoal() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		board.moveUp();
		board.moveUp();
		board.moveRight();
		WarehouseMan player = new WarehouseMan(3,2);
		assertEquals(board.getBoard().getElem(2, 3),player);
		board.moveRight();
		Goal goal = new Goal(3,2);
		assertEquals(board.getBoard().getElem(2, 3),goal);
	}
	
	@Test
	void moveTowardsWall() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		board.moveLeft();
		WarehouseMan player = new WarehouseMan(2,4);
		Wall wall = new Wall(1,4);
		assertEquals(board.getBoard().getElem(4, 2),player);
		assertEquals(board.getBoard().getElem(4, 1),wall);
	}
	
	@Test
	void moveBoxInFrontOfWall() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		board.moveUp();
		board.moveRight();
		board.moveRight();
		Box box = new Box(4,3);
		Wall wall = new Wall(5,3);
		assertEquals(board.getBoard().getElem(3, 4),box);
		assertEquals(board.getBoard().getElem(3, 5),wall);
	}
	
	@Test
	void moveBoxInGoal() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		board.moveRight();
		board.moveUp();
		board.moveRight();
		board.moveUp();
		board.moveLeft();
		Box box = new Box(3,2);
		WarehouseMan player = new WarehouseMan(4,2);
		assertEquals(board.getBoard().getElem(2, 3),box);
		assertEquals(board.getBoard().getElem(2, 4),player);
	}
	
	@Test
	void moveBoxInGoal2() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		board.setBoardElement(3, 2, '*');
		board.setBoardElement(3, 4, 'W');
		board.setBoardElement(4, 2, '.');
		board.moveLeft();
		board.moveLeft();
		Box box = new Box(2,3);
		WarehouseMan player = new WarehouseMan(3,3);
		assertEquals(board.getBoard().getElem(3, 2),box);
		assertEquals(board.getBoard().getElem(3, 3),player);
	}
	
	@Test
	void moveBoxInGoal3() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		board.setBoardElement(4, 3, '*');
		board.setBoardElement(2, 3, 'W');
		board.setBoardElement(4, 2, '.');
		board.moveDown();
		board.moveDown();
		Box box = new Box(3,4);
		WarehouseMan player = new WarehouseMan(3,3);
		assertEquals(board.getBoard().getElem(4, 3),box);
		assertEquals(board.getBoard().getElem(3, 3),player);
	}
	
	@Test
	void moveBoxInGoalTowardsWall() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		board.moveRight();
		board.moveUp();
		board.moveUp();
		Box box = new Box(3,2);
		WarehouseMan player = new WarehouseMan(3,3);
		assertEquals(board.getBoard().getElem(2, 3),box);
		assertEquals(board.getBoard().getElem(3, 3),player);
	}
	
	@Test
	void moveTowardsGoal() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		Goal goal = new Goal(3,3);
		WarehouseMan player = new WarehouseMan(3,3);
		board.getBoard().setElement(3, 3, '*');
		board.getBoard().setElement(2, 3, '#');
		board.moveRight();
		board.moveUp();
		assertEquals(board.getBoard().getElem(3, 3),player);
		board.moveRight();
		assertEquals(board.getBoard().getElem(3, 3),goal);
	}
	
	@Test
	void moveTowardsGoal2() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		Goal goal = new Goal(3,3);
		WarehouseMan player = new WarehouseMan(3,3);
		board.setBoardElement(3, 3, '*');
		board.setBoardElement(2, 3, 'W');
		board.setBoardElement(4, 2, '#');
		board.moveDown();
		assertEquals(board.getBoard().getElem(3, 3),player);
		board.moveDown();
		assertEquals(board.getBoard().getElem(3, 3),goal);
	}
	
	@Test
	void moveTowardsGoal3() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		Goal goal = new Goal(3,3);
		WarehouseMan player = new WarehouseMan(3,3);
		board.setBoardElement(3, 3, '*');
		board.setBoardElement(2, 3, 'W');
		board.setBoardElement(4, 2, '#');
		board.moveRight();
		board.moveDown();
		board.moveLeft();
		assertEquals(board.getBoard().getElem(3, 3),player);
		board.moveDown();
		assertEquals(board.getBoard().getElem(3, 3),goal);
	}
	
	@Test
	void undoAction() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		board.moveUp();
		WarehouseMan player = new WarehouseMan(2,3);
		assertEquals(board.getBoard().getElem(3, 2),player);
		player = new WarehouseMan(2,4);
		board.undo();
		assertEquals(board.getBoard().getElem(4, 2),player);
	}
	
	@Test
	void redoAction() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		board.moveUp();
		WarehouseMan player = new WarehouseMan(2,3);
		assertEquals(board.getBoard().getElem(3, 2),player);
		board.undo();
		board.redo();
		assertEquals(board.getBoard().getElem(3, 2),player);
	}
	
	@Test
	void redoSeveralActions() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		board.moveUp();
		WarehouseMan player = new WarehouseMan(2,3);
		assertEquals(board.getBoard().getElem(3, 2),player);
		board.moveUp();
		board.undo();
		board.undo();
		board.redo();
		board.redo();
		player = new WarehouseMan(2,2);
		assertEquals(board.getBoard().getElem(2, 2),player);
	}
	
	@Test
	void alternateUndoRedo() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		WarehouseMan player = new WarehouseMan(2,3);
		board.moveUp();
		board.undo();
		board.redo();
		board.undo();
		board.redo();
		assertEquals(board.getBoard().getElem(3, 2),player);
	}
	
	@Test
	void redoWhenNoPossible() {
		assertThrows(NoSuchElementException.class, () -> board.undo());
		assertThrows(NoSuchElementException.class, () -> board.redo());
	}
	
	@Test
	void scoreWhenMove() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		board.moveRight();
		board.moveRight();
		board.moveUp();
		assertEquals(3,board.getScore());
	}
	
	@Test
	void scoreWhenUndo() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		board.moveRight();
		board.moveRight();
		board.moveUp();
		board.undo();
		assertEquals(2,board.getScore());
		board.undo();
		board.undo();
		assertEquals(0,board.getScore());
	}
	
	@Test
	void undoBoxInGoal() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		board.moveRight();
		List<Moves> boxesInGoal = board.getBoxesInGoal();	
		board.moveUp();
		board.undo();
		assertEquals(boxesInGoal,board.getBoxesInGoal());
	}
	
	

}
