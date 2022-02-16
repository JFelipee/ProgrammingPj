package es.upm.pproject.sokoban.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoaderGameFile {

	private LoaderGameFile() {
		throw new IllegalStateException("Utility class");
	}

	private static final String PATTERN = "[0-9]+";
	
	
	
	/**
	 * Load a game which has been previously saved
	 * @param path					Specifies the path where the game file is saved
	 * @param file					Specifies the game file to be loaded
	 * @return						A Game object
	 * @throws IOException							If the path or the file cannot be accessed
	 * @throws InvalidFileFormatException			If the file does not fulfill the requirements
	 * @throws InvalidBoardElementException			If the board contains an element not allowed
	 * @throws InvalidBoardFormatException			If the board does not fulfill the requirements
	 */
	public static Game loadGame(String path, String file) throws  IOException, 
				InvalidFileFormatException, InvalidBoardElementException, InvalidBoardFormatException {
		
		String fileAux;
		fileAux = path.concat(file);
		String name;
		int levelScore;
		int gameScore;
		int nRows;
		int nColumns;
		String [] board;
		try(BufferedReader br = new BufferedReader(new FileReader(fileAux))){
			
			//	Read the name of the level
			String line;			
			if((line = br.readLine()) == null || !line.matches("[Level_]+\\d{1,2}\\.txt")) {
				throw new InvalidFileFormatException("El fichero debe contener una linea indicando un nombre de nivel correcto");
			}
			
			name = line;
			
			//	Read the dimension of the board
			if((line = br.readLine()) == null || line.length() < 3 || line.matches(PATTERN)) {
				throw new InvalidFileFormatException("El fichero debe contener una linea indicando la dimension del tablero");
			}
			String [] dimension = line.split(" ");
			try {
				nRows = Integer.parseInt(dimension[0]);
				nColumns = Integer.parseInt(dimension[1]);
			}
			catch(NumberFormatException e) {
				throw new InvalidFileFormatException("El fichero debe contener una linea indicando la dimension del tablero");
			}
			
			//	Read the elements' representation of the board
			board = new String [nRows];
			int i = 0;
			while((line = br.readLine()) != null && i < nRows) {
				if(!line.matches("[+.W#*G]") && (line.length() == nColumns)) {
					board[i] = line;
					i++;
				}
				else {
					throw new InvalidFileFormatException("The board format is incorrect");
				}
			}
			
			//	Read the current score got in the level
			if(line  == null || !line.matches(PATTERN)) {
				throw new InvalidFileFormatException("The file must contain the score of the level");
			}
			levelScore = Integer.parseInt(line);
			
			//	Read the current total score got in the game
			if((line = br.readLine()) == null || !line.matches(PATTERN)) {
				throw new InvalidFileFormatException("The file must contain the total score of the game");
			}
			gameScore = Integer.parseInt(line);
		}
		
		//	Create a new game with the data read from the file
		Board boardGame = new Board(nRows,nColumns,board,false);
		Game game = new Game(path, boardGame,levelScore);
		game.setCurrentLevel(name);
		game.setTotalScore(gameScore);
		return game;
	}
	
	
}
