package es.upm.pproject.sokoban.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
	
	private Moves [][] elements;
	private int nRows;
	private int nColumns;
	private int nBoxes = 0;
	private int nGoals = 0;
	private int nWarehouseMan = 0;
	private boolean isAnewLevel;
	private List <Moves> boxesInGoals;
	
	
	/**
	 * The constructor of this board object
	 * The row and the column arguments must be a positive number greater than zero
	 * The elements in the elems argument must be a wall, a box, a goal, floor, or a warehouse man
	 * @param row								Specifies the number of rows that the board will have
	 * @param column							Specifies the number of columns that the board will have
	 * @param elems								Specifies the elements that the board will have
	 * @param newLevel							Specifies if the board is of a new level or not
	 * @throws InvalidBoardElementException 	If the element is not a box, a wall, a goal, floor or a warehouse man
	 * @throws InvalidBoardFormatException 
	 */
	public Board(int nRows, int nColumns, String [] elems, boolean newLevel) throws InvalidBoardElementException, InvalidBoardFormatException {
		this.nRows = nRows;
		this.nColumns = nColumns;
		this.elements = new Moves [nRows][nColumns];
		this.isAnewLevel = newLevel;
		this.boxesInGoals = new ArrayList<>();
		for(int i = 0; i < elems.length; i++) {
			setRow(i+1,elems[i]);
		}
		if(isAnewLevel) {
			checkConditions();
		}
	}
	
	/**
	 * The constructor used when a board is created copying another
	 * The row and the column arguments must be a positive number greater than zero
	 * The elements in the board argument must be a wall, a box, a goal, a floor or a warehouse man
	 * @param nRows								Specifies the number of rows that the board will have
	 * @param nColumns							Specifies the number of columns that the board will have
	 * @param board								Specifies the elements that the board will have
	 */
	public Board(int nRows, int nColumns, Moves [][] board) {
		this.nRows = nRows;
		this.nColumns = nColumns;
		this.elements = new Moves [nRows][nColumns];
		copyElements(board);
	}
	
	
	/**
	 * Check if the conditions to create a board are fulfilled
	 * @throws InvalidBoardFormatException		If the requirements are not fulfilled.
	 */
	public void checkConditions() throws InvalidBoardFormatException {
		if(nWarehouseMan != 1) {
			throw new InvalidBoardFormatException("There must be at least and only one warehouse man");
		}
		if(nBoxes < 1 || nGoals < 1) {
			throw new InvalidBoardFormatException("There must be be at least one box and one goal");
		}
		if(nBoxes != nGoals) {
			throw new InvalidBoardFormatException("There must be the same number of boxes as goals");
		}
	}
	

	/**
	 * Sets the elements of a row on the board.
	 * The row argument and the length of the
	 * string argument must be in the board dimension range
	 * @param row								Specifies in which row the elements will be set
	 * @param elements							Specifies the string of elements to be set in the row
	 * @throws InvalidBoardElementException 	If the element is not a box, a wall, a goal, floor or a warehouse man
	 * @throws InvalidBoardFormatException 
	 */
	public void setRow (int row, String elements) throws InvalidBoardElementException {
		char element;
		for(int i = 0; i < elements.length(); i++) {
			element = elements.charAt(i);
			setElement(row,i+1,element);
		}
	}
	
	
	/**
	 * Sets the elements of a column on the board.
	 * The column argument and the length of the
	 * string argument must be in the board dimension range
	 * @param column							Specifies in which column will be set the elements
	 * @param elements							Specifies the string of elements to be set in the column
	 * @throws InvalidBoardElementException 	If the element is not a box, a wall, a goal, floor or a warehouse man
	 * @throws InvalidBoardFormatException 
	 */
	public void setColumn (int column, String elements) throws InvalidBoardElementException {
		char element;
		for(int i = 0; i < elements.length(); i++) {
			element = elements.charAt(i);
			setElement(i+1,column,element);
		}
	}
	
	
	/**
	 * Sets an element on any place of the board
	 * The column and the row arguments and the length
	 * of the string argument must be in the board dimension range
	 * @param row								Specifies in which row the element will be set
	 * @param column							Specifies in which column the element will be set
	 * @param element							Specifies the element to be set on the board
	 * @throws InvalidBoardElementException 	If the element is not a box, a wall, a goal, floor or a warehouse man
	 * @throws InvalidBoardFormatException 
	 */
	public void setElement(int row, int column, char element) throws InvalidBoardElementException {
		switch(element) {
		case '+': this.elements[row-1][column-1] = new Wall(column,row);
				break;
				
		case '.': this.elements[row-1][column-1] = new Floor(column,row);
				break;
				
		case 'W': this.elements[row-1][column-1] = new WarehouseMan(column,row);
				  nWarehouseMan++;
				  break;
				
		case '*': this.elements[row-1][column-1] = new Goal(column,row);
				  nGoals++;
				break;
				
		case '#': this.elements[row-1][column-1] = new Box(column,row);
				  nBoxes++;
				break;
		case 'G': if(isAnewLevel) {
					throw new InvalidBoardElementException("Board element not recognized");
				  }
				  this.elements[row-1][column-1] = new Box(column,row);
				  this.boxesInGoals.add(new Box(column,row));
				  nBoxes++;
				  nGoals++;
				  break;
				
		default: throw new InvalidBoardElementException("Board element not recognized");
		}
	}
	
	
	
	/**
	 * Gets the elements in a row of the board
	 * The row argument must be in the board dimension range
	 * @param row	Specifies the row to be returned
	 * @return 		The elements in the specified row
	 */
	public Moves[] getRow(int row) {
		return this.elements[row-1];
	}
	
	
	/**
	 * Gets the elements in a column of the board
	 * The column argument must be in the board dimension range
	 * @param column	Specifies the column to be returned
	 * @return 			The elements in the specified column
	 */
	public Moves[] getColumn(int column) {
		Moves [] columnBoard = new Moves[this.nRows];
		for(int i = 0; i < this.nRows; i++) {
			columnBoard[i] = this.elements[i][column-1];
		}
		return columnBoard;
	}
	
	
	/**
	 * Gets an element on the board
	 * The column must be in the board dimension range
	 * @param column	Specifies the column of the element
	 * @param row		Specifies the row of the element
	 * @return 			The element in the specified row and column
	 */
	public Moves getElem(int row, int column) {
		return this.elements[row-1][column-1];
	}
	
	
	/**
	 * Gets all the elements on the board
	 * The column must be in the board dimension range
	 * @return 	A matrix with all of the elements on the board
	 */
	public Moves[][] getBoard(){
		return this.elements;
	}
	
	
	/**
	 * Gets the number of rows of the board
	 * @return 	A number
	 */
	public int getNrows() {
		return this.nRows;
	}
	
	
	/**
	 * Gets the number of columns of the board
	 * @return 	A number
	 */
	public int getNcolumns() {
		return this.nColumns;
	}
	
	
	/**
	 * Represents the data of this object
	 * @return 	the string format of this object
	 */
	public String toString() {
		String result = "";
		for(Moves[] fila : this.elements) {
			int i = 0;
			for(Moves elem : fila) {
				result = result.concat(elem.toString());
				if(i != fila.length -1) {					
					result = result.concat(", ");
				}
				i++;
			}
			result = result.concat("\n");
		}
		return result;
	}
	
	/**
	 * Gets the number of boxes that the board has
	 * @return	A number
	 */
	public int getNumberOfBoxes() {
		return this.nBoxes;
	}
	
	
	/**
	 * Sets the number of boxes that the board will have
	 * The nBoxes argument must be a number greater than zero
	 * @param nBoxes		Specifies the number of boxes that the board will have
	 */
	public void setNumberOfBoxes(int nBoxes) {
		this.nBoxes = nBoxes;
	}
	
	/**
	 * Sets the number of goals that the board will have
	 * The nGoals argument must be a number greater than zero
	 * @param nGoals		Specifies the number of goals that the board will have
	 */
	public void setNumberOfGoals(int nGoals) {
		this.nGoals = nGoals;
	}
	
	/**
	 * Gets the list of boxes that are in a goal in the board
	 * @return		A list
	 */
	public List<Moves> getBoxesInGoals(){
		return this.boxesInGoals;
	}
	
	
	/**
	 * Copy the elements from a board to another that is being created
	 * @param board			Specifies the board that will be copied
	 */
	public void copyElements(Moves[][] board) {
		for(int i = 0; i < this.nRows; i++) {
			for(int j = 0; j < this.nColumns; j++) {
				Moves elemToCopy = board[i][j];
				if(elemToCopy instanceof Wall) {
					this.elements[i][j] = new Wall(elemToCopy.getX(),elemToCopy.getY());
				}
				else if(elemToCopy instanceof Box) {
					this.elements[i][j] = new Box(elemToCopy.getX(),elemToCopy.getY());
				}
				else if(elemToCopy instanceof Goal) {
					this.elements[i][j] = new Goal(elemToCopy.getX(),elemToCopy.getY());
				}
				else if(elemToCopy instanceof Floor) {
					this.elements[i][j] = new Floor(elemToCopy.getX(),elemToCopy.getY());
				}
				else {
					this.elements[i][j] = new WarehouseMan(elemToCopy.getX(),elemToCopy.getY());
				}
			}
		}
		
	}
	
	

}
