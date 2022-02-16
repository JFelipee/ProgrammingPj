package es.upm.pproject.sokoban.model;

import java.io.IOException;

public class Game {


	private Level level;
	private String [] levels;
	private String currentLevelName;
	private int totalScore;
	private String path;

	/**
	 * The constructor of this Game object
	 * The path argument must exist in the system and must contain the level files to create the levels
	 * The levelName must be the name of an existing level
	 * @param path											Specifies where the level files are in the system
	 * @param levelName										Specifies the name of the actual level in the game
	 * @param isNewGame										Specifies if it is a new game or not
	 * @throws InvalidFileFormatException					If the level file does not fulfill the requirements
	 * @throws IOException									If it is not able to access to the path or the level file
	 * @throws InvalidBoardElementException					If there is an element not allowed in the game
	 * @throws InvalidBoardFormatException					If the board of the game does not fulfill the requirements
	 */
	public Game(String path, String levelName, boolean isNewGame) throws InvalidFileFormatException, IOException, 
	InvalidBoardElementException, InvalidBoardFormatException {
		levels = new String[4];
		for(int i = 0; i < 4; i++) {
			levels[i] = "Level_" + (i+1) + ".txt";
		}
		this.path = path;
		LevelFile file = LevelFileReader.readFile(this.path, levelName);
		this.level = new LevelImpl(file, isNewGame);
		this.currentLevelName = levelName;
		this.totalScore = 0;
		
	}


	/**
	 * Another constructor of the Game object
	 * It is used when it is not a new game but is loaded from one previously saved
	 * @param path					Specifies where the level files are in the system
	 * @param board					Specifies the board of the level in the game
	 * @param levelScore			Specifies the score accumulated in the level
	 */
	public Game(String path, Board board, int levelScore) {
		levels = new String[4];
		for(int i = 0; i < 4; i++) {
			levels[i] = "Level_" + i+1 + ".txt";
		}
		this.path = path;
		this.level = new LevelImpl(board, levelScore, board.getBoxesInGoals());
		this.currentLevelName = "";
		this.totalScore = 0;
	}


	/**
	 * Passes to the next level of the game
	 * @throws InvalidFileFormatException				If the level file does not fulfill the requirements
	 * @throws IOException
	 * @throws InvalidBoardElementException
	 * @throws InvalidBoardFormatException
	 */
	public void nextLevel() throws InvalidFileFormatException, IOException, InvalidBoardElementException, InvalidBoardFormatException {
		this.totalScore = this.totalScore + this.level.getScore();
		int pos = getLevelNumber(this.currentLevelName);
		if(pos + 1 != levels.length) {
			LevelFile file = LevelFileReader.readFile(this.path, this.levels[pos + 1]);
			this.level = new LevelImpl(file, true);
			this.currentLevelName = this.levels[pos + 1];
		}
		
		else {
			finishGame();
		}
	}


	/**
	 * Gets the level object of the game
	 * @return		An Level object
	 */
	public Level getLevel() {
		return this.level;
	}

	/**
	 * Gets the name of the current level
	 * @return		A string
	 */
	public String getCurrentLevelName() {
		return this.currentLevelName;
	}

	/**
	 * Gets the total score accumulated in the game
	 * @return		A number
	 */
	public int getTotalScore() {
		return this.totalScore;
	}

	/**
	 * Sets the level to the game
	 * @param level			Specifies the level object to be set to the game
	 */
	public void setLevel(LevelImpl level) {
		this.level = level;
	}

	/**
	 * Sets the total score to the game
	 * @param score			Specifies the score to be set to the game
	 */
	public void setTotalScore(int score) {
		this.totalScore = score;
	}

	/**
	 * Sets the name of the current level of the game
	 * @param name			Specifies the name to be set as the name of the current level
	 */
	public void setCurrentLevel(String name) {
		this.currentLevelName = name;
	}

	/**
	 * Gets the number associated to the current level
	 * @param level			Specifies the name of the current level
	 * @return				A number
	 */
	public int getLevelNumber(String level) {
		boolean found = false;
		int index = 0;
		for(int i = 0; i < this.levels.length && !found; i++) {
			if(this.levels[i].equals(level)) {
				index = i;
				found = true;
			}
		}
		return index;
	}
	
	// Accion a realizar cuando se llega al ultimo nivel y se supera.
	public void finishGame() {
		
		int lastIndexLevel = this.levels.length - 1; // Posicion del ultimo nivel (le resto 1 porque en getLevelNumber empieza por 0)
		
		if (this.level.getBoard().getBoxesInGoals().size() == this.level.getBoard().getNumberOfBoxes()
				&& getLevelNumber(getCurrentLevelName()) == lastIndexLevel) {
			
			//TODO: Falta de implementacion
			
		}
		
	}
	
}

