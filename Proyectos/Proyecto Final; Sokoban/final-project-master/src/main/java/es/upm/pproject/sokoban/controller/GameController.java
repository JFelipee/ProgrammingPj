package es.upm.pproject.sokoban.controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.upm.pproject.sokoban.model.ControlLogger;
import es.upm.pproject.sokoban.model.Game;
import es.upm.pproject.sokoban.model.InvalidBoardElementException;
import es.upm.pproject.sokoban.model.InvalidBoardFormatException;
import es.upm.pproject.sokoban.model.InvalidFileFormatException;
import es.upm.pproject.sokoban.model.Operations;
import es.upm.pproject.sokoban.model.OperationsImpl;
import es.upm.pproject.sokoban.view.FrameSokoban;
import es.upm.pproject.sokoban.view.GameView;
import es.upm.pproject.sokoban.view.KeyHandler;

public class GameController {
	
	private Operations model; 
	private GameView view;
	
	private Game game;
	private String path;
	private String file;
	private FrameSokoban view1;
	private MovementsController movements;
	private KeyHandler keyHandler;
	
	
	private static final Logger LOGGER = Logger.getLogger("bitacora.controller");
	private static final String PATH = "src/main/resources/Level/";
	
	
	public GameController(GameView view, Operations model) {
		this.view = view;
		this.model = model;	
	}
	
	
    public void restart() throws InvalidFileFormatException, IOException, InvalidBoardElementException, InvalidBoardFormatException
    {
    	
    	if (game!=null) {
    			view1.dispose();
				model.restartLevel(game);
				SokobanController controller = new SokobanController(null,game);
				this.movements = new MovementsController(null,game.getLevel());
				this.keyHandler = new KeyHandler(movements);
				view1 = new FrameSokoban(controller,keyHandler);
				controller.setView(view1);
				this.movements.setView(view1);
    	}
    	
    	else {
    		/**
    		 * No hace nada a menos que empieze la partida
    		 */
    	}
    }
    
    
    public void startNewGame() throws InvalidFileFormatException, IOException, InvalidBoardElementException, InvalidBoardFormatException {
    
    	if (view1!=null) {
    		view1.dispose();
    	}
    	model = new OperationsImpl();
		game = model.startNewGame();
		SokobanController controller = new SokobanController(null,game);
		this.movements = new MovementsController(null,game.getLevel());
		this.keyHandler = new KeyHandler(movements);
		view1 = new FrameSokoban(controller, this.keyHandler);
		controller.setView(view1);
		this.movements.setView(view1);
    }
     
    public void undo() {

    	if (game !=null) {
    		try{
    			model.undo(game);
    			view1.boardChanged();
    		}
    		catch(NoSuchElementException e) {
    			LOGGER.log(Level.FINE, ControlLogger.getStackTrace(e));		
    		}
    	}
    	else {
    		/**
    		 * No se puede deshacer un movimiento sino existe una partida en marcha
    		 */
    	}

    }
    
    public void redo() {
    	
    	if (game != null) {
    		try{
    			model.redo(game);
    			view1.boardChanged();
    		}
    		catch(NoSuchElementException e) {
    			LOGGER.log(Level.FINE, ControlLogger.getStackTrace(e));		
    		}
    	}
    	
    	else {
    		/**
    		 * No se puede rehacer un movimiento sino existe una partida en marcha
    		 */
    	}
    	
	}
    
    public void saveGame() throws IOException {
 
    		model = new OperationsImpl();
    		file = "LevelGuardado.txt";
    		model.saveGame(game, PATH, file);
    	
    }
    
    
    public void loadGame() throws IOException, InvalidFileFormatException, InvalidBoardElementException, InvalidBoardFormatException {
    	path = PATH;
    	file = "LevelGuardado.txt";
    	if ( path!= null && file !=null) {
    		if(game != null) {
    			view1.dispose();
    		}
    		model = new OperationsImpl();
    		game = model.loadGame(path, file);
    		SokobanController controller = new SokobanController(null,game);
    		this.movements = new MovementsController(null,game.getLevel());
    		this.keyHandler = new KeyHandler(movements);
    		view1 = new FrameSokoban(controller, this.keyHandler);
    		controller.setView(view1);
    		this.movements.setView(view1);
    	}
    	
    	else {
    		/**
    		 * No se puede cargar una partida sino existe una partida guarda anteriormente(path,file)
    		 */
    	}
    	
    }
    
    public void passLevel() throws InvalidFileFormatException, IOException, InvalidBoardElementException, InvalidBoardFormatException {
    	
    	if (game!=null) {
    	view1.dispose();
    	model = new OperationsImpl();
		model.passLevel(game);
		SokobanController controller = new SokobanController(null,game);
		
		this.movements = new MovementsController(null,game.getLevel());
		this.keyHandler = new KeyHandler(movements);
		
		view1 = new FrameSokoban(controller,this.keyHandler);
		controller.setView(view1);
		this.movements.setView(view1);
    	}
    	else {
    		/**
    		 * No se puede pasar al siguiente nivel sino existe una partida guardada anteriormente
    		 */
    	}
    	
    }
    
    public void setView(GameView view) {
    	
    	this.view = view;
    	
    } 
    
    public void setView1(FrameSokoban view) {
    	this.view1 = view;
    }
}
