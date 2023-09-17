package es.upm.pproject.sokoban.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LevelFileReader{
	
	private LevelFileReader() {
		throw new IllegalStateException("Utility class");
	}
	
	/**
	 * Method readFile
	 * @param path
	 * @param file
	 * @throws InvalidFileFormatExxception if the file does not contain a level name, a board or the dimension of the board
	 * 		   Also if the file name is not Level_N, being N a positive number, and the dimension specified is different from board dimension.
	 * @return LevelFile
	 */
	public static LevelFile readFile(String path, String file) throws InvalidFileFormatException, IOException {
		
		//PRE: The file name must be named "Level_N.txt" being N a positive integer
		if(!file.matches("[Level_]+\\d{1,2}\\.txt")) {
			throw new InvalidFileFormatException("El fichero debe llamarse Level_N siendo N un numero positivo");
		}
		
		String fileAux;
		fileAux = path.concat(file);
		
		try(BufferedReader br = new BufferedReader(new FileReader(fileAux))) {
			String name;
			int nRows;
			int nColumns;
			String [] board;			
			String line;
			
			//PRE: There must be at least one line in the file and this must be the name of the level
			if((name = br.readLine()) == null) {
				throw new InvalidFileFormatException("El fichero debe contener una linea indicando el nombre del nivel");
			}
			
			//PRE: There must be a line with the dimension of the board with the format "nRows nColumns"
			if((line = br.readLine()) == null || line.length() < 3 || line.matches("[0-9]+")) {
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

			board = new String[nRows];
			
			int i = 0;
			boolean correctDimension = true;
			while((line = br.readLine()) != null && correctDimension) {
				if(line.length() == nColumns) {
					board[i] = line;
					i++;
				}
				else {
					correctDimension = false;
				}
			}
			
			//POST: The dimension of the board must be the same as the specified in the second line in the file
			if((!correctDimension) || (i != nRows)) {
				throw new InvalidFileFormatException("La dimension del tablero debe coincidir con el valor de la segunda linea del fichero");
			}
			
			return new LevelFile(name,nRows,nColumns,board);
		}
		
	}
	
}