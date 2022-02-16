package es.upm.pproject.sokoban.view;

import es.upm.pproject.sokoban.controller.SokobanController;
import es.upm.pproject.sokoban.model.Board;
import es.upm.pproject.sokoban.model.Box;
import es.upm.pproject.sokoban.model.ControlLogger;
import es.upm.pproject.sokoban.model.Moves;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameSokoban extends JFrame{
	
	private static final long serialVersionUID = 1L;

	private SokobanController controller;
	private KeyHandler handler;
	private static final Logger LOGGER = Logger.getLogger("bitacora.view");
	
	public FrameSokoban(SokobanController controller, KeyHandler keyHandler) {
		this.controller = controller;
		this.handler = keyHandler;
		setTitle("Sokoban");
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		loadBoard();
		setFocusable(true);
		addKeyListener(this.handler);
		this.setVisible(true);
		
	}
	
	public void loadBoard() {
		
		List<Moves> boxesInGoals = this.controller.getGame().getLevel().getBoxesInGoal();
		Board board = this.controller.getBoard();
		
		setSize(70 * board.getNrows() + 2, 70 * board.getNcolumns() + 2);
		JPanel boardWindow = new JPanel (new GridLayout(board.getNrows() + 2,board.getNcolumns() + 2));
		
		int nRows = board.getNrows() + 2;
		int nColumns = board.getNcolumns() + 2;
		for(int row = 0; row < nRows; row++) {		
			for(int column = 0; column < nColumns; column++) {
				if((row > 0 && row < nRows - 1) && (column > 0 && column < nColumns -1)) {
					
					Moves element = board.getElem(row, column);
					if ((element instanceof Box) && boxesInGoals != null &&  boxesInGoals.contains(element)) {				
						Image im;	
						try {						
							im = ImageIO.read(new File("src//main//resources//Box//Box2.PNG"));
							element.setImage(im);							
						} catch (IOException | NullPointerException e ) {						
							LOGGER.log(Level.FINE, ControlLogger.getStackTrace(e));						
						}					
					}
					ObjectPanel elementPanel = new ObjectPanel(element);
					boardWindow.add(elementPanel);
					
				}
				
				else {
					BackgroundPanel bgpanel = new BackgroundPanel();
					boardWindow.add(bgpanel);
				}
			}
		}		
		add(boardWindow);
	}
	
	public void boardChanged() {
		loadBoard();
		this.setVisible(true);
	}
	
	public SokobanController getController() {
		return this.controller;
	}
	
	public KeyHandler getHandler() {
		return this.handler;
	}
	
	
}