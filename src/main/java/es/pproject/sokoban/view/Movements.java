package es.upm.pproject.sokoban.view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Logger;

import javax.swing.JPanel;

import es.upm.pproject.sokoban.controller.MovementsController;
import es.upm.pproject.sokoban.controller.SokobanController;
import es.upm.pproject.sokoban.model.ControlLogger;
import es.upm.pproject.sokoban.model.Game;
import es.upm.pproject.sokoban.model.InvalidBoardElementException;
import es.upm.pproject.sokoban.model.InvalidBoardFormatException;
import es.upm.pproject.sokoban.model.InvalidFileFormatException;
import es.upm.pproject.sokoban.model.OperationsImpl;


public class Movements extends JPanel{
	
	private static final Logger LOGGER = Logger.getLogger("bitacora.view");
	
	
	private static final long serialVersionUID = 4546564798037805998L;
	private MovementsController controller;
	
	private OperationsImpl model;
	private Game game;
	
	private SokobanController controllers;
	private FrameSokoban view;

	
	public Movements (MovementsController controller) throws InvalidFileFormatException, IOException, InvalidBoardElementException, InvalidBoardFormatException {
		
		model = new OperationsImpl();
		game = model.startNewGame();
		controllers = new SokobanController(null,game);
		
		this.controller = controller;
		KeyHandler handler = new KeyHandler();
		view.addKeyListener(handler);
		setFocusable(true);

	}
	
	
	public class KeyHandler extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent e) {
			
			switch(e.getKeyCode()) {
			
			case KeyEvent.VK_LEFT: try {
					controller.moveLeft();
				} catch (InvalidBoardElementException | InvalidBoardFormatException | InvalidFileFormatException
						| IOException e8) {
					LOGGER.log(java.util.logging.Level.FINE, ControlLogger.getStackTrace(e8));
				}
			break;
			
			
			case KeyEvent.VK_RIGHT: try {
					controller.moveRight();
				} catch (InvalidBoardElementException | InvalidBoardFormatException | InvalidFileFormatException
						| IOException e7) {
					LOGGER.log(java.util.logging.Level.FINE, ControlLogger.getStackTrace(e7));
				}
			break;	
			
			
			case KeyEvent.VK_UP: try {
					controller.moveUp();
				} catch (InvalidBoardElementException | InvalidBoardFormatException | InvalidFileFormatException
						| IOException e6) {
					LOGGER.log(java.util.logging.Level.FINE, ControlLogger.getStackTrace(e6));
				}
			break;
			
			
			case KeyEvent.VK_DOWN: try {
					controller.moveDown();
				} catch (InvalidBoardElementException | InvalidBoardFormatException | InvalidFileFormatException
						| IOException e5) {
					LOGGER.log(java.util.logging.Level.FINE, ControlLogger.getStackTrace(e5));
				}
			break;
			
			
			case KeyEvent.VK_A: try {
					controller.moveLeft();
				} catch (InvalidBoardElementException | InvalidBoardFormatException | InvalidFileFormatException
						| IOException e4) {
					LOGGER.log(java.util.logging.Level.FINE, ControlLogger.getStackTrace(e4));
				}
			break;
			
			
			case KeyEvent.VK_D: try {
					controller.moveRight();
				} catch (InvalidBoardElementException | InvalidBoardFormatException | InvalidFileFormatException
						| IOException e3) {
					LOGGER.log(java.util.logging.Level.FINE, ControlLogger.getStackTrace(e3));
				}
			break;
			
			
			case KeyEvent.VK_W: try {
					controller.moveUp();
				} catch (InvalidBoardElementException | InvalidBoardFormatException | InvalidFileFormatException
						| IOException e2) {
					LOGGER.log(java.util.logging.Level.FINE, ControlLogger.getStackTrace(e2));
				}
			break;
			
			
			case KeyEvent.VK_S: try {
					controller.moveDown();
				} catch (InvalidBoardElementException | InvalidBoardFormatException | InvalidFileFormatException
						| IOException e1) {
					LOGGER.log(java.util.logging.Level.FINE, ControlLogger.getStackTrace(e1));
				}
			break;
			
			default:
				break;
			}
			
			
		}
	}

	
	public void setEventListener(MovementsController controller) {
		this.controller = controller;
	}
	
	@Override
	public void repaint() {
		
		controllers = new SokobanController(view, game);
//		view = new FrameSokoban(controllers);
		
		view.loadBoard();
		
	}
}
