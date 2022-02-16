
//movementController

package es.upm.pproject.sokoban.controller;

import java.io.IOException;
import java.util.logging.Logger;

import es.upm.pproject.sokoban.model.ControlLogger;
import es.upm.pproject.sokoban.model.Game;
import es.upm.pproject.sokoban.model.InvalidBoardElementException;
import es.upm.pproject.sokoban.model.InvalidBoardFormatException;
import es.upm.pproject.sokoban.model.InvalidFileFormatException;
import es.upm.pproject.sokoban.model.Level;
import es.upm.pproject.sokoban.view.FrameSokoban;
import es.upm.pproject.sokoban.view.KeyHandler;
import es.upm.pproject.sokoban.view.Movements;

public class MovementsController {
	
	private static final Logger LOGGER = Logger.getLogger("bitacora.controller");
	
	private FrameSokoban view;
	private Level model;
	
//	private Operations models; 
//	private GameView views;
	private  GameController game1;

	
	public MovementsController(FrameSokoban view, Level model) {
		this.view = view;
		this.model = model;
		
//		try {
//			game1.startNewGame();
//		} catch (InvalidFileFormatException | IOException | InvalidBoardElementException
//				| InvalidBoardFormatException e) {
//			LOGGER.log(java.util.logging.Level.FINE, ControlLogger.getStackTrace(e));
//		}
	}
	
	public void setView(FrameSokoban view) {
		this.view = view;
	}


	public void moveUp() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		model.moveUp();
		view.boardChanged();
		
	}


	public void moveDown() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		model.moveDown();
		view.boardChanged();
	}


	public void moveLeft() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		model.moveLeft();
		view.boardChanged();
	}


	public void moveRight() throws InvalidBoardElementException, InvalidBoardFormatException, InvalidFileFormatException, IOException {
		model.moveRight();
		view.boardChanged();
	}
	
	public void undo() {
		model.undo();
		view.boardChanged();
	}
	
	public void redo() {
		model.redo();
		view.boardChanged();
	}
	
	public void getBoard() {
		
	}
	
	public void getScore() {
		
	}
	
	public void setScore(int score) {
		
	}
	
	public void getNumberOfBoxes() {
		
	}
	
	public void getBoxesInGoal() {
		
	}
	
	public void assignGame(Game game) {
		
	}
	
}
