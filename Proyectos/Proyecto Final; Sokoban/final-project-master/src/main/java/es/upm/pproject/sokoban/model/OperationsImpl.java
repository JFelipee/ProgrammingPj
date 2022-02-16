package es.upm.pproject.sokoban.model;

import java.io.IOException;

public class OperationsImpl implements Operations{

	private static final String PATH = "src/main/resources/Level/";

	
	/**
	 * Starts a new game
	 * return  A Game object
	 * @throws IOException							If it is not possible to access to the path or the file
	 * @throws InvalidFileFormatException			If the level file does not fulfill the requirements
	 * @throws InvalidBoardElementException			If the board contains an element not allowed
	 * @throws InvalidBoardFormatException 			If the board does not fulfill the requirements
	 */
	public Game startNewGame() throws InvalidFileFormatException, IOException, InvalidBoardElementException, InvalidBoardFormatException {
		Game game = new Game(PATH, "Level_1.txt", true);
		game.getLevel().assignGame(game);
		return game;
	}
	
	/**
	 * Restarts the current level to its initial state
	 * @param game			Specifies the game to be saved
	 * @throws IOException							If it is not possible to access to the path or the file
	 * @throws InvalidFileFormatException			If the level file does not fulfill the requirements
	 * @throws InvalidBoardElementException			If the board contains an element not allowed
	 * @throws InvalidBoardFormatException 			If the board does not fulfill the requirements
	 */
	public void restartLevel(Game game) throws InvalidFileFormatException, IOException, InvalidBoardElementException, InvalidBoardFormatException {
		String levelName = game.getCurrentLevelName();
		LevelFile file = LevelFileReader.readFile(PATH, levelName);
		game.setLevel(new LevelImpl(file, true));
	}
	
	/**
	 * Undo an action done in the level
	 * @param game 			Specifies the game associated to the level where to undo the action
	 */
	public void undo(Game game) {
		try{
			game.getLevel().undo();
		}
		catch(Exception e) {
			
		}
	}
	
	/**
	 * Redo an action undone in the level
	 * @param game			Specifies the game associated to the level where to redo the action
	 */
	public void redo(Game game) {
		game.getLevel().redo();
	}
	
	/**
	 * Save the current state of the game
	 * @param game			Specifies the game to be saved
	 * @param path			Specifies the location where the game will be saved
	 * @param file			Specifies the file where the game will be saved
	 * @throws IOException		If the path or the file cannot be accessed
	 */
	public void saveGame(Game game, String path, String file) throws IOException {
		SaverGameFile.saveGame(game,path, file);
	}
	
	/**
	 * Load a previously saved game
	 * @param path			Specifies where is the game file to be loaded
	 * @param file			Specifies the game file to load a game
	 * @throws IOException							If it is not possible to access to the path or the file
	 * @throws InvalidFileFormatException			If the level file does not fulfill the requirements
	 * @throws InvalidBoardElementException			If the board contains an element not allowed
	 * @return 				A Game object
	 */
	public Game loadGame(String path, String file) throws IOException, InvalidFileFormatException, InvalidBoardElementException,
							InvalidBoardFormatException {
		return LoaderGameFile.loadGame(path, file);
	}
	
	
	/**
	 * The game passes to the next level
	 * @throws IOException							If it is not possible to access to the path or the file
	 * @throws InvalidFileFormatException			If the level file does not fulfill the requirements
	 * @throws InvalidBoardElementException			If the board contains an element not allowed
	 * @throws InvalidBoardFormatException 			If the board does not fulfill the requirements
	 */
	public void passLevel(Game game) throws InvalidFileFormatException, IOException, InvalidBoardElementException, InvalidBoardFormatException {
		game.nextLevel();
	}
	
}
