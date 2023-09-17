package es.upm.pproject.sokoban.model;

import java.io.IOException;
import java.util.List;

public interface Level {

	/**
	 * Moves the warehouse man up in the board
	 * @throws InvalidBoardElementException			If there is a element in the board which is not allowed
	 * @throws InvalidBoardFormatException			If the board does not fulfill the requirements
	 * @throws InvalidFileFormatException			If the level file format does not fulfill the requirements
	 * @throws IOException							If 
	 */
	public void moveUp() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException;
	
	/**
	 * Moves the warehouse man down in the board
	 * @throws InvalidBoardElementException			If the board contains an element not allowed
	 * @throws InvalidBoardFormatException			If the board does not fulfill the requirements
	 * @throws InvalidFileFormatException			If the level file does not fulfill the requirements
	 * @throws IOException							If the program cannot access to the path or the file
	 */
	public void moveDown() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException;
	
	/**
	 * Moves the warehouse man left in the board
	 * @throws InvalidBoardElementException			If the board contains an element not allowed
	 * @throws InvalidBoardFormatException			If the board does not fulfill the requirements
	 * @throws InvalidFileFormatException			If the level file does not fulfill the requirements
	 * @throws IOException							If the program cannot access to the path or the file
	 */
	public void moveLeft() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException;
	
	/**
	 * Moves the warehouse man right in the board
	 * @throws InvalidBoardElementException			If the board contains an element not allowed
	 * @throws InvalidBoardFormatException			If the board does not fulfill the requirements
	 * @throws InvalidFileFormatException			If the level file does not fulfill the requirements
	 * @throws IOException							If the program cannot access to the path or the file
	 */
	public void moveRight() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException;
	
	/**
	 * Undo the previous action done
	 */
	public void undo();
	
	/**
	 * Redo the previous action undone
	 */
	public void redo();
	
	/**
	 * Gets the board of the level
	 * @return A Board object
	 */
	public Board getBoard();
	
	/**
	 * Gets the score accumulated in the level
	 * @return	A number
	 */
	public int getScore();
	
	/**
	 * Sets the score of the level
	 * @param score			Specifies the score to be set
	 */
	public void setScore(int score);
	
	/**
	 * Gets the number of boxes in the board
	 * @return	A number
	 */
	public int getNumberOfBoxes();
	
	/**
	 * Gets the list of boxes which are in goals
	 * @return 	A list of Moves objects
	 */
	public List <Moves> getBoxesInGoal();
	
	/**
	 * Assigns the game associated with this level
	 * @param game			Specifies the game to be assigned
	 */
	public void assignGame(Game game);
	
}
