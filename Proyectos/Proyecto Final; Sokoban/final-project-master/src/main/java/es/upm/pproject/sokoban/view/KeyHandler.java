package es.upm.pproject.sokoban.view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Logger;

import es.upm.pproject.sokoban.controller.MovementsController;
import es.upm.pproject.sokoban.model.ControlLogger;
import es.upm.pproject.sokoban.model.InvalidBoardElementException;
import es.upm.pproject.sokoban.model.InvalidBoardFormatException;
import es.upm.pproject.sokoban.model.InvalidFileFormatException;

public class KeyHandler extends KeyAdapter{
	
	private static final Logger LOGGER = Logger.getLogger("bitacora.view");
	private MovementsController controller;
	
	public KeyHandler(MovementsController controller){
		this.controller = controller;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		switch(e.getKeyCode()) {
		
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			try {
				controller.moveLeft();
			} catch (InvalidBoardElementException | InvalidBoardFormatException | InvalidFileFormatException
					| IOException e1) {
				LOGGER.log(java.util.logging.Level.FINE, ControlLogger.getStackTrace(e1));
			}
		break;
		
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT: 
			try {
				controller.moveRight();
			} catch (InvalidBoardElementException | InvalidBoardFormatException | InvalidFileFormatException
					| IOException e1) {
				LOGGER.log(java.util.logging.Level.FINE, ControlLogger.getStackTrace(e1));
			}
			break;
		case KeyEvent.VK_W:	
		case KeyEvent.VK_UP: 
			try {
				controller.moveUp();
			} catch (InvalidBoardElementException | InvalidBoardFormatException | InvalidFileFormatException
					| IOException e6) {
				LOGGER.log(java.util.logging.Level.FINE, ControlLogger.getStackTrace(e6));
			}
			break;
	
	case KeyEvent.VK_S:
	case KeyEvent.VK_DOWN: 
			try {
				controller.moveDown();
			} catch (InvalidBoardElementException | InvalidBoardFormatException | InvalidFileFormatException
					| IOException e5) {
				LOGGER.log(java.util.logging.Level.FINE, ControlLogger.getStackTrace(e5));
			}
			break;
	default:
			break;
		}
		
	}
	
}