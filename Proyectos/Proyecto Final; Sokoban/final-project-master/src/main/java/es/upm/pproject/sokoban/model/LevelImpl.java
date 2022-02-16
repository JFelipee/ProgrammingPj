package es.upm.pproject.sokoban.model;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LevelImpl implements Level {
	
	private Board board;
	private Moves player;
	private int score;
	private int nBoxes;
	private boolean playerInGoal = false;	
	private List <Moves> boxesInGoals;	
	private boolean undoneAction;	
	private Deque <LevelImpl> undoStack;
	private Deque <LevelImpl> redoStack;
	private Game game;

	/**
	 * The LevelImpl object constructor
	 * This is the main constructor used to create new levels
	 * @param levelfile			Specifies the level file used to create the level
	 * @param newLevel			Specifies if it is a new level or copied
	 * @throws InvalidBoardElementException		If there is a element in the board which is not allowed
	 * @throws InvalidBoardFormatException		If the board does not fulfill the requirements
	 */
	public LevelImpl(LevelFile levelfile, boolean newLevel) throws InvalidBoardElementException, InvalidBoardFormatException {
		this.board = new Board(levelfile.getNrows(), levelfile.getNcolumns(),levelfile.getElems(), newLevel);
		getWarehouseMan();
		this.score = 0;
		this.nBoxes = this.board.getNumberOfBoxes();
		this.playerInGoal = false;
		this.boxesInGoals = board.getBoxesInGoals();
		this.undoStack = new ArrayDeque<>();
		this.undoneAction = false;
		this.redoStack = new ArrayDeque<>();	
		this.game = null;
	}
	
	/**
	 * The LevelImpl object constructor
	 * This constructor is used when the level is loaded from a previous game
	 * @param board					Specifies the board to be used in the level
	 * @param score					Specifies the score to be set to the level
	 * @param boxesInGoals			Specifies the list of boxes that are in goals
	 */
	public LevelImpl (Board board, int score, List <Moves> boxesInGoals) {
		this.board = new Board(board.getNrows(), board.getNcolumns(), board.getBoard());
		getWarehouseMan();
		this.score = score;
		this.boxesInGoals = new ArrayList<>(boxesInGoals);
		this.undoStack = new ArrayDeque<>();
		this.redoStack = new ArrayDeque<>();
		this.game = null;
	}
		

	
	/**
	 * Moves the warehouse man in the board
	 * @param row					Specifies which row the player is in
	 * @param column				Specifies which column the player is in
	 * @param diffRow				Specifies how many steps the player is going to move towards left or right
	 * @param diffColumn			Specifies how many steps the player is going to move up or down
	 * @param nextElement			Specifies the element that is in where the player wants to move
	 * @throws InvalidBoardElementException				If there is a element in the board which is not allowed
	 */
	private void movePlayer(int row, int column, int diffRow, int diffColumn, Moves nextElement) 
			throws InvalidBoardElementException {
		board.setElement(row + diffRow, column + diffColumn, 'W');
		board.setElement(row, column, '.');
		if(playerInGoal) {
			board.setElement(row, column, '*');
			playerInGoal = false;
		}
		if(nextElement instanceof Goal) {
			playerInGoal = true;
		}
		score++;
		undoneAction = false;
		checkRedoConditions();
	}
	
	
	
	/**
	 * Moves the warehouse man up in the board
	 * @throws InvalidBoardElementException			If the board contains an element not allowed
	 * @throws InvalidBoardFormatException			If the board does not fulfill the requirements
	 * @throws InvalidFileFormatException			If the level file does not fulfill the requirements
	 * @throws IOException							If the program cannot access to the path or the file
	 */
	public void moveUp() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		int row = player.getY();
		int column = player.getX();
		Moves swapElement = board.getElem(row - 1, column);
		if(!(swapElement instanceof Wall)) {
			Moves swapElement2 = this.board.getElem(row - 2, column);
			if(ableToMoveBox(swapElement, swapElement2)) {
				undoStack.push(new LevelImpl(this.board, this.score, this.boxesInGoals));	
				this.board.setElement(row - 2, column, '#');
				if(swapElement2 instanceof Goal) {
					boxesInGoals.add(this.board.getElem(row - 2, column));
				}
				movePlayer(row, column, -1, 0, swapElement);
				getWarehouseMan();
			}
			else if(!(swapElement instanceof Box)) {
				undoStack.push(new LevelImpl(this.board, this.score, this.boxesInGoals));	
				movePlayer(row, column, -1, 0, swapElement);
				getWarehouseMan();
			}
		}
	}
	
	
	/**
	 * Moves the warehouse man down in the board
	 * @throws InvalidBoardElementException			If the board contains an element not allowed
	 * @throws InvalidBoardFormatException			If the board does not fulfill the requirements
	 * @throws InvalidFileFormatException			If the level file does not fulfill the requirements
	 * @throws IOException							If the program cannot access to the path or the file
	 */
	public void moveDown() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		int row = player.getY();
		int column = player.getX();
		Moves swapElement = board.getElem(row + 1, column);
		if(!(swapElement instanceof Wall)) {
			Moves swapElement2 = this.board.getElem(row + 2, column);
			if(ableToMoveBox(swapElement, swapElement2)) {
				undoStack.push(new LevelImpl(this.board, this.score, this.boxesInGoals));	
				this.board.setElement(row + 2, column, '#');
				if(swapElement2 instanceof Goal) {
					boxesInGoals.add(this.board.getElem(row + 2, column));
				}
				movePlayer(row, column, 1, 0, swapElement);
				getWarehouseMan();
			}
			else if(!(swapElement instanceof Box)) {
				undoStack.push(new LevelImpl(this.board, this.score, this.boxesInGoals));	
				movePlayer(row, column, 1, 0, swapElement);
				getWarehouseMan();
			}
		}
	}
	
	
	/**
	 * Moves the warehouse man left in the board
	 * @throws InvalidBoardElementException			If the board contains an element not allowed
	 * @throws InvalidBoardFormatException			If the board does not fulfill the requirements
	 * @throws InvalidFileFormatException			If the level file does not fulfill the requirements
	 * @throws IOException							If the program cannot access to the path or the file
	 */
	public void moveLeft() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		int row = player.getY();
		int column = player.getX();
		Moves swapElement = board.getElem(row, column -1);
		if(!(swapElement instanceof Wall)) {
			Moves swapElement2 = this.board.getElem(row, column - 2);
			if(ableToMoveBox(swapElement, swapElement2)) {
				undoStack.push(new LevelImpl(this.board, this.score, this.boxesInGoals));	
				this.board.setElement(row, column - 2, '#');
				if(swapElement2 instanceof Goal) {
					boxesInGoals.add(this.board.getElem(row, column - 2));
				}
				movePlayer(row, column, 0, -1, swapElement);
				getWarehouseMan();
			}
			else if(!(swapElement instanceof Box)) {
				undoStack.push(new LevelImpl(this.board, this.score, this.boxesInGoals));	
				movePlayer(row, column, 0, -1, swapElement);
				getWarehouseMan();
			}
		}
	}
	
	
	/**
	 * Moves the warehouse man right in the board
	 * @throws InvalidBoardElementException			If the board contains an element not allowed
	 * @throws InvalidBoardFormatException			If the board does not fulfill the requirements
	 * @throws InvalidFileFormatException			If the level file does not fulfill the requirements
	 * @throws IOException							If the program cannot access to the path or the file
	 */
	public void moveRight() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		int row = player.getY();
		int column = player.getX();
		Moves swapElement = board.getElem(row, column + 1);
		if(!(swapElement instanceof Wall)) {
			Moves swapElement2 = this.board.getElem(row, column + 2);
			if(ableToMoveBox(swapElement, swapElement2)) {
				undoStack.push(new LevelImpl(this.board, this.score, this.boxesInGoals));	
				this.board.setElement(row, column + 2, '#');
				if(swapElement2 instanceof Goal) {
					boxesInGoals.add(this.board.getElem(row, column + 2));
				}
				movePlayer(row, column, 0, 1, swapElement);
				getWarehouseMan();
			}
			else if(!(swapElement instanceof Box)) {
				undoStack.push(new LevelImpl(this.board, this.score, this.boxesInGoals));	
				movePlayer(row, column, 0, 1, swapElement);
				getWarehouseMan();
			}
		}
	}
	
	
	
	/**
	 * Undo the previous action done
	 */
	public void undo() {
		LevelImpl undoneLevel = undoStack.pop();
		redoStack.push(new LevelImpl(this.board, this.score, this.boxesInGoals));
		undoneAction = true;
		this.board = undoneLevel.board;
		this.score = undoneLevel.score;
		this.boxesInGoals = undoneLevel.boxesInGoals;
		this.player = undoneLevel.player;
	}
	
	
	/**
	 * Redo the previous action undone
	 */
	public void redo() {
		LevelImpl redoneLevel = redoStack.pop();
		undoStack.push(new LevelImpl(redoneLevel.board,redoneLevel.score, redoneLevel.boxesInGoals));
		this.board = redoneLevel.board;
		this.score = redoneLevel.score;
		this.boxesInGoals = redoneLevel.boxesInGoals;
		this.player = redoneLevel.player;
	}
	
	
	/**
	 * Checks if it is possible to redo an action
	 * If it is possible to undo but it is decided to move instead of redo
	 * The stack to redo actions is restarted.
	 */
	private void checkRedoConditions() {
		if(undoneAction)
			redoStack = new ArrayDeque<>();
	}


	/**
	 * Gets the board of the level
	 * @return A Board object
	 */
	public Board getBoard() {
		return this.board;
	}
	
	/**
	 * Search the position of the warehouse man in the board
	 */
	private void getWarehouseMan() {
		for(int i = 1; i < this.board.getNrows(); i++) {
			Moves[] row = this.board.getRow(i);
			for(int j = 0; j < row.length; j++) {
				if(row[j] instanceof WarehouseMan) {
					this.player = row[j];
				}
			}
		}
	}
	
	/**
	 * Gets the score accumulated in the level
	 * @return	A number
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * Sets the score of the level
	 * @param score			Specifies the score to be set
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	
	/**
	 * Sets the element of a position of the board
	 * @param row					Specifies the row where the element will be set
	 * @param column				Specifies the column where the element will be set
	 * @param element				Specifies the element to be set in the board
	 * @throws InvalidBoardElementException			If there is an element in the board that is not allowed
	 */
	public void setBoardElement(int row, int column, char element) throws InvalidBoardElementException {
		this.board.setElement(row, column, element);
		getWarehouseMan();
	}
	
	
	/**
	 * Checks if a box is in a goal
	 * @param box		Specifies the box to be checked whether it is in a goal
	 * @return			true or false
	 */
	public boolean isBoxInGoal(Moves box) {
		return this.boxesInGoals.contains(box);
	}
	
	/**
	 * Checks whether the warehouse man can move a box
	 * @param elem				Specifies the element next to the warehouse man
	 * @param elemNextToBox		Specifies the element next to a box
	 * @return					true or false
	 */
	public boolean ableToMoveBox(Moves elem, Moves elemNextToBox) {
		return (elem instanceof Box) && !isBoxInGoal(elem) && !(elemNextToBox instanceof Wall)
				&& !(elemNextToBox instanceof Box);
	}
	
	
	/**
	 * Gets the list of boxes which are in goals
	 * @return 	A list of Moves objects
	 */
	public List<Moves> getBoxesInGoal(){
		return new ArrayList <> (this.boxesInGoals);
	}
	
	
	/**
	 * Gets the number of boxes in the board
	 * @return	A number
	 */
	public int getNumberOfBoxes() {
		return this.board.getNumberOfBoxes();
	}
	
	/**
	 * Assigns the game associated with this level
	 * @param game			Specifies the game to be assigned
	 */
	public void assignGame(Game game) {
		this.game = game;
	}
	
	/**
	 * Checks if the level has been finished
	 * @throws InvalidFileFormatException			If the level file format does not fulfill the requirements
	 * @throws IOException							If the path or the file cannot be accessed
	 * @throws InvalidBoardElementException			If there is an element in the board which is not allowed
	 * @throws InvalidBoardFormatException			If the board does not fulfill the requirements
	 */
	@SuppressWarnings("unused")
	private void checkFinishedLevel() throws InvalidFileFormatException, IOException, InvalidBoardElementException,
				InvalidBoardFormatException {
		if(this.boxesInGoals.size() == this.nBoxes) {
			this.game.nextLevel();
		}
	}
	
	
	
	
}

