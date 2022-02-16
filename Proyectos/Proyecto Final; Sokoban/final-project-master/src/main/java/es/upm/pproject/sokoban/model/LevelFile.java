package es.upm.pproject.sokoban.model;

public class LevelFile {
	
	private String name;
	private int nRows;
	private int nColumns;
	private String [] boardElems;
	
	/**
	 * @param name
	 * @param nRows
	 * @param nColumns
	 * @param elems
	 */	
	public LevelFile(String name, int nRows, int nColums, String [] elems) {
		this.name = name;
		this.nRows = nRows;
		this.nColumns = nColums;
		this.boardElems = elems;
	}
	
	
	/**
	 * Method: getName
	 * Returns the level name
	 * @return String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Method: getNrows
	 * Returns the number of rows in the board
	 * @return int
	 */
	public int getNrows() {
		return this.nRows;
	}
	
	/**
	 * Method: getNcolumns
	 * Returns the number of columns in the board
	 * @return int
	 */
	public int getNcolumns() {
		return this.nColumns;
	}
	
	/**
	 * Method: getElems
	 * Returns an array with the elements in each row
	 * @return String []
	 */
	public String[] getElems(){
		return this.boardElems;
	}
	
	
	/**
	 * Method: getRow
	 * Returns a row of the board
	 * @return String
	 */
	public String getRow(int row) {
		return this.boardElems[row-1];
	}
	
	
	/**
	 * Method: getColumn
	 * Returns a column of the board
	 * @return String
	 */
	public String getColumn(int column) {
		String [] board = this.boardElems;
		char []columnAux = new char[this.nRows];
		for(int i = 0; i < board.length; i++) {
			columnAux[i] = board[i].charAt(column-1);
		}
		return String.valueOf(columnAux);
	}
	
	/**
	 * Method: toString
	 * Prints the LevelFile object
	 * @return String
	 */
	public String toString() {
		String elems = "";
		for(int i = 0; i < this.getElems().length; i++) {
			elems = elems.concat(this.boardElems[i]);
			elems = elems.concat("\n");
		}
		return "Name: " + this.getName() + "\n" +
				"Dimension: " + this.getNrows() + " x " + this.getNcolumns() + "\n" + elems;
	}
	
}