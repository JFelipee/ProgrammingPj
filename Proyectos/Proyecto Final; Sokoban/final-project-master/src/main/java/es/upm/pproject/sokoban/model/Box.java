package es.upm.pproject.sokoban.model;


import java.awt.Image;
import java.io.File;
import java.io.IOException;

import java.util.logging.Logger;
import java.util.logging.Level;

import javax.imageio.ImageIO;

/**
 * 
 * @author Samuel López Delgado
 * @author Jose Antonio Márquez
 * @author Jason Taco Paredes
 * @version 1.0
 */
public class Box extends Moves {
	
	private static final Logger LOGGER = Logger.getLogger("bitacora.model");
	
	/**
	 * Constructor Box
	 * @param x
	 * @param y
	 * 
	 * @throws IOException
	 * @throws SecurityException 
	 */
	public Box(int x , int y) {
		super (x,y);
		Image imagen;
		try {
			imagen = ImageIO.read(new File("src/main/resources/Box/Box1.png"));
			setImage(imagen);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "No se puede ejecutar el programa: ");
			LOGGER.log(Level.FINE, ControlLogger.getStackTrace(e));
		}
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void move (int x, int y) {
		int xb = this.getX() + x;
		int yb = this.getY() + y; 
		this.setX(xb);
		this.setY(yb);
	}
	
	
	public String toString () {
        return "Box(" + this.getY() + "," + this.getX()+ ")";
    }
	
	
	
}
