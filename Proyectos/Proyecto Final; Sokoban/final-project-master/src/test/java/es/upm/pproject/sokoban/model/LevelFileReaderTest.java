package es.upm.pproject.sokoban.model;


import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LevelFileReaderTest {
	
	private LevelFile level;
	private String file;
	private String path;
	
	@BeforeEach
	void initializePath() {
		path = "src/test/resources/LevelFileReader/";
	}
	
	@Test
	void constructor() throws Exception {
		  Constructor constructor = LevelFileReader.class.getDeclaredConstructor();
	      assertTrue(Modifier.isPrivate(constructor.getModifiers()));
	      constructor.setAccessible(true);
	      assertThrows(InvocationTargetException.class,() -> constructor.newInstance());
	}
	

	@Test
	@DisplayName("Test the read of a correct format file level")
	void readCorrectLevelFile() throws InvalidFileFormatException, IOException {
		String [] board = {"+++++","+.*.+","+.#.+","+W..+","+++++"};
		level = new LevelFile("Initial level",5,5,board);
		
		file = "Level_1.txt";
		
		assertEquals(level.toString(),LevelFileReader.readFile(path, file).toString());
	}
	
	@Test
	@DisplayName("Test the read of file that it doesnt exist")
	void readNonExistentFileName() throws InvalidFileFormatException, IOException {		
		file = "Lel_1.txt";
		
		assertThrows(IOException.class,()-> LevelFileReader.readFile(path, file));
	}
	
	@Test
	@DisplayName("Test the read of file with an incorrect name")
	void readIncorrectFileName() throws InvalidFileFormatException, IOException {
		
		file = "Levjl.txt";	
		assertThrows(InvalidFileFormatException.class,()-> LevelFileReader.readFile(path, file));
	}
	
	
	@Test
	@DisplayName("Test the read of an empty file")
	void readEmptyFile() throws InvalidFileFormatException, IOException {
		
		file = "Level_2.txt";		
		assertThrows(InvalidFileFormatException.class,()-> LevelFileReader.readFile(path, file));
	}
	
	
	@Test
	@DisplayName("Test the read of file with only the name of the level")
	void readFileWithOnlyName() throws InvalidFileFormatException, IOException {
		
		file = "Level_3.txt";		
		assertThrows(InvalidFileFormatException.class,()-> LevelFileReader.readFile(path, file));
	}
	
	@Test
	@DisplayName("Test the read of file with only the name and the dimension of the level")
	void readFileWithoutBoardElements() throws InvalidFileFormatException, IOException {
		
		file = "Level_4.txt";		
		assertThrows(InvalidFileFormatException.class,()-> LevelFileReader.readFile(path, file));
	}
	
	@Test
	@DisplayName("Test the read of file with only one dimension")
	void readFileIncorrectDimensionFormat() throws InvalidFileFormatException, IOException {
		
		file = "Level_7.txt";		
		assertThrows(InvalidFileFormatException.class,()-> LevelFileReader.readFile(path, file));
	}
	
	@Test
	@DisplayName("Test the read of file with letters instead of number for dimension")
	void readFileIncorrectDimensionFormat2() throws InvalidFileFormatException, IOException {
		
		file = "Level_8.txt";		
		assertThrows(InvalidFileFormatException.class,()-> LevelFileReader.readFile(path, file));
	}
	
	@Test
	@DisplayName("Test the read of file with a size of board different from the dimension")
	void readFileWithIncorrectBoardElements() throws InvalidFileFormatException, IOException {
		
		file = "Level_5.txt";		
		assertThrows(InvalidFileFormatException.class,()-> LevelFileReader.readFile(path, file));
	}
	
	@Test
	@DisplayName("Test the read of file with a size of board different from the dimension")
	void readFileWithIncorrectBoardColumns() throws InvalidFileFormatException, IOException {
		
		file = "Level_6.txt";		
		assertThrows(InvalidFileFormatException.class,()-> LevelFileReader.readFile(path, file));
	}
	
	
	

}
