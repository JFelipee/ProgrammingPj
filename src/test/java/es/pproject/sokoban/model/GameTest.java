package es.upm.pproject.sokoban.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {

	private Game game;
	private Operations menu;
	private String levelPath;
	
	@BeforeEach
	void initializeGame() throws InvalidFileFormatException, IOException, InvalidBoardElementException, InvalidBoardFormatException {
		menu = new OperationsImpl();
		game = menu.startNewGame();
		levelPath = "src/test/resources/LevelFileReader/";
	}
	
	@Test
	void newGame() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		Level level = new LevelImpl(LevelFileReader.readFile(levelPath, "Level_1.txt"), true);
		Board board = level.getBoard();
		Board gameBoard = game.getLevel().getBoard();
		assertEquals(board.toString(),gameBoard.toString());
	}
	
	@Test
	void passLevel() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		String path = "src/test/resources/LevelFileReader/";
		String file = "Level_9.txt";
		LevelFile levelFile = LevelFileReader.readFile(path, file);
		Level level2 =  new LevelImpl(levelFile, true);
		Level level = game.getLevel();
		level.moveRight();
		level.moveUp();
		menu.passLevel(game);
		level = game.getLevel();
		assertEquals(level2.getBoard().toString(),level.getBoard().toString());
	}

}
