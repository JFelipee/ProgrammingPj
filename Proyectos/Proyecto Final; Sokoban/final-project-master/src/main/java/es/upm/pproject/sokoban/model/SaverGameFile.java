package es.upm.pproject.sokoban.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaverGameFile{

	private SaverGameFile() {
		throw new IllegalStateException("Utility class");
	}
	
	 private static final String SEPARATOR = "line.separator";
	
	 
	 /**
	  * Saves the current state of the game
	  * It creates a file where the data of the game will be saved
	  * The data to be saved are the board's dimension, the board's elements,
	  * The level's score, the level's name and the game's total score
	  * @param game					Specifies the game to be saved
	  * @param path					Specifies the path where the game will be saved
	  * @param file					Specifies the file where the game will be saved
	  * @throws IOException			If there is an error with the path or the file
	  */
	public static void saveGame(Game game, String path, String file) throws IOException {
		Level level = game.getLevel();
		String nameLevel = game.getCurrentLevelName();
		int totalScore = game.getTotalScore();
		//	Path where the file will be created
		String fileAux = "";
		fileAux = path.concat(file);
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileAux))){
			
			//	Writes the name in the file
			bw.write(nameLevel + System.getProperty(SEPARATOR));
			
			//	Writes the board's dimension in the file
			int nRows = level.getBoard().getNrows();
			int nColumns = level.getBoard().getNcolumns();
			bw.write(nRows + " " + nColumns + System.getProperty(SEPARATOR));
			
			//	Writes the disposition of the board's elements in the file
			String [] currentBoard = boardToString(level.getBoard(),level.getBoxesInGoal());
			for(int i = 0; i < currentBoard.length; i++) {
				bw.write(currentBoard[i] + System.getProperty(SEPARATOR));
			}
			
			//	Writes the level's score in the file
			bw.write(level.getScore() + System.getProperty(SEPARATOR));
			
			//	Writes the game's total score in the file
			bw.write(totalScore + System.getProperty(SEPARATOR));
			
		}
	}
	
	
	
	
	/**
	 * Gets the representation of the board's element in strings
	 * @param board
	 * @param boxesInGoals
	 * @return	An array of strings that contains the board's elements
	 */
	private static String[] boardToString(Board board, List<Moves> boxesInGoals) {
		String [] elementsInFile = new String [board.getNrows()];
		Moves [][] boardElements = board.getBoard();
		for(int i = 0; i < board.getNrows(); i++) {
			elementsInFile[i] = "";
			for(int j = 0; j < board.getNcolumns();j++) {
				Moves element = boardElements[i][j];
				
				if(element instanceof Wall) {
					elementsInFile[i] = elementsInFile[i].concat("+");
				}
				else if(element instanceof Floor) {
					elementsInFile[i] = elementsInFile[i].concat(".");
				}
				else if(element instanceof Goal) {
					elementsInFile[i] = elementsInFile[i].concat("*");
				}
				else if(element instanceof WarehouseMan) {
					elementsInFile[i] = elementsInFile[i].concat("W");
				}
				else {
					if(boxesInGoals.contains(element)) {
						elementsInFile[i] = elementsInFile[i].concat("G");
					}
					else {
						elementsInFile[i] = elementsInFile[i].concat("#");
					}
				}
			}
		}
		return elementsInFile;
	}

}
