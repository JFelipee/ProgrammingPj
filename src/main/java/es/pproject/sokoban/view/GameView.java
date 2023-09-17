package es.upm.pproject.sokoban.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import es.upm.pproject.sokoban.controller.GameController;
import es.upm.pproject.sokoban.model.ControlLogger;
import es.upm.pproject.sokoban.model.InvalidBoardElementException;
import es.upm.pproject.sokoban.model.InvalidBoardFormatException;
import es.upm.pproject.sokoban.model.InvalidFileFormatException;

public class GameView extends JFrame{

	
	private static final long serialVersionUID = -8798613150160203259L;

	private static final Logger LOGGER = Logger.getLogger("bitacora.view");

	private GameController controller; 

	
	public GameView(GameController controller) {
		this.controller = controller; 
		
		setTitle("Sokoban");
		setSize(100,350);
		setLocation(590,300);//400,650
//		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		createFields();
		this.setVisible(true);

	}
	
	
	private void createFields() {
		
		JPanel fields = new JPanel(new GridLayout(1,0));
		
		JPanel buttons = new JPanel(new FlowLayout());
		
		/**
		 * Restart
		 */
		JButton restart = new JButton("Restart");
		restart.addActionListener(event -> {
			try {
				controller.restart();
			} catch (InvalidFileFormatException | IOException | InvalidBoardElementException
					| InvalidBoardFormatException e) {
				LOGGER.log(java.util.logging.Level.FINE, ControlLogger.getStackTrace(e));
			}
		});
		buttons.add(restart);
		
		
		/**
		 * Start New Game
		 */
		JButton startNGame = new JButton("New Game"); 
		
		startNGame.addActionListener(event -> {
			try {
				controller.startNewGame();
			} catch (InvalidFileFormatException | IOException | InvalidBoardElementException
					| InvalidBoardFormatException e) {
				LOGGER.log(java.util.logging.Level.FINE, ControlLogger.getStackTrace(e));
			}
		});
		
		buttons.add(startNGame);
		
		
		/**
		 * Undo
		 */
		JButton undo = new JButton("Undo"); 
		undo.addActionListener(event -> controller.undo());
		buttons.add(undo);
		
		
		/**
		 * Redo
		 */
		JButton redo = new JButton("Redo"); 
		redo.addActionListener(event -> controller.redo());
		buttons.add(redo);
		
		
		/**
		 * Save Game
		 */
		JButton saveGame = new JButton("Save Game"); 
		saveGame.addActionListener(event -> {
			try {
				controller.saveGame();
			} catch (IOException e) {
				LOGGER.log(java.util.logging.Level.FINE, ControlLogger.getStackTrace(e));
			}
		});
		buttons.add(saveGame);
		
		/**
		 * Load Game
		 */
		JButton loadGame = new JButton("Load Game"); 
		loadGame.addActionListener(event -> {
			try {
				controller.loadGame();
			} catch (IOException | InvalidFileFormatException | InvalidBoardElementException
					| InvalidBoardFormatException e) {
				LOGGER.log(java.util.logging.Level.FINE, ControlLogger.getStackTrace(e));
			}
		});
		buttons.add(loadGame);
		
		/**
		 * Pass Level
		 */
		JButton passLevel = new JButton("Pass Level");
		passLevel.addActionListener(event -> {
			try {
				controller.passLevel();
			} catch (InvalidFileFormatException | IOException | InvalidBoardElementException
					| InvalidBoardFormatException e) {
				LOGGER.log(java.util.logging.Level.FINE, ControlLogger.getStackTrace(e));
			}
		});
		buttons.add(passLevel);
		
		
		fields.add(buttons);
		add(fields, BorderLayout.CENTER);
	}
	
		
}
