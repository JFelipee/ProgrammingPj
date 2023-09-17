package es.upm.pproject.sokoban.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OperationsTest {

	private Game game;
	private String savedGame;
	private String levelPath;
	private Operations menu;
	
	@BeforeEach
	void initialize() throws InvalidFileFormatException, IOException, InvalidBoardElementException, InvalidBoardFormatException {
		savedGame = "src/test/resources/SaverGameFile/";
		levelPath = "src/test/resources/LevelFileReader/";
		menu = new OperationsImpl();
		game = menu.startNewGame();
	}
	

	
	@Test
	void newGame() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		Level level = new LevelImpl(LevelFileReader.readFile(levelPath, "Level_1.txt"), true);
		Board board = level.getBoard();
		Board gameBoard = game.getLevel().getBoard();
		assertEquals(board.toString(),gameBoard.toString());
	}
		
	@Test
	void saveAndLoad() throws IOException, InvalidFileFormatException, InvalidBoardElementException, InvalidBoardFormatException {
		menu.saveGame(game, savedGame, "Level1_saved.txt");
		String board = game.getLevel().getBoard().toString();
		game = menu.loadGame(savedGame, "Level1_saved.txt");
		String board2 = game.getLevel().getBoard().toString();
		assertEquals(board,board2);
	}
	
	@Test
	void startNewGame() throws IOException, InvalidFileFormatException, InvalidBoardElementException, InvalidBoardFormatException {
		Level level = game.getLevel();
		String board = level.getBoard().toString();
		level.moveUp();
		assertEquals(new WarehouseMan(2,3),level.getBoard().getElem(3, 2));
		level.moveUp();
		game = menu.startNewGame();
		String board2 = game.getLevel().getBoard().toString();
		assertEquals(board,board2);
	}
	
	@Test
	void restartGame() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		Level level = game.getLevel();
		String board = level.getBoard().toString();
		level.moveRight();
		level.moveRight();
		level.moveUp();
		menu.restartLevel(game);
		String board2 = game.getLevel().getBoard().toString();
		assertEquals(board,board2);
	}
	
	@Test
	void undoAndRedo() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		Level level = game.getLevel();
		level.moveRight();
		String undoneBoard = level.getBoard().toString();
		level.moveRight();
		String redoneBoard = level.getBoard().toString();
		menu.undo(game);
		assertEquals(undoneBoard,level.getBoard().toString());
		menu.redo(game);
		assertEquals(redoneBoard,level.getBoard().toString());
	}
	
	@Test
	void loadGameIncorrectName() {
		//	Empty file
		assertThrows(InvalidFileFormatException.class, () -> menu.loadGame(savedGame, "Level_1.txt"));
		
		//	Incorrect level name
		assertThrows(InvalidFileFormatException.class, () -> menu.loadGame(savedGame, "Level_2.txt"));
	}
	
	
	@Test
	void loadGameWithIncorrectDimensionBoard() {
		//	File with letters in the dimension field
		assertThrows(InvalidFileFormatException.class, () -> menu.loadGame(savedGame, "Level_3.txt"));
		
		//	File with only the number of rows
		assertThrows(InvalidFileFormatException.class, () -> menu.loadGame(savedGame, "Level_4.txt"));
	}
	
	@Test
	void loadGameIncorrectBoard() {
		assertThrows(InvalidFileFormatException.class, () -> menu.loadGame(savedGame, "Level_5.txt"));
		assertThrows(InvalidFileFormatException.class, () -> menu.loadGame(savedGame, "Level_6.txt"));
	}
	
	@Test
	void loadGameIncorrectScores() {
		assertThrows(InvalidFileFormatException.class, () -> menu.loadGame(savedGame, "Level_7.txt"));
		assertThrows(InvalidFileFormatException.class, () -> menu.loadGame(savedGame, "Level_8.txt"));
	}
	
	
	
}
