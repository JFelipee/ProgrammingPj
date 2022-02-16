package es.upm.pproject.sokoban.model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

import javax.imageio.ImageIO;




/**
 * Class WarehouseMan.
 * @author Samuel López Delgado
 * @author Jose Antonio Márquez
 * @author Jason Taco Paredes
 * @version 1.0
 */
public class WarehouseMan extends Moves {
	
	private static final Logger LOGGER = Logger.getLogger("bitacora.model");

	
	/**
	 * @param x
	 * @param y
	 */
	public WarehouseMan(int x, int y) {
		
		super(x, y);
		Image imagen;
		try {
			imagen = ImageIO.read(new File("src/main/resources/WarehouseMan/WarehouseMan2.png"));
			setImage(imagen);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "No se puede ejecutar el programa");
			LOGGER.log(Level.FINE, ControlLogger.getStackTrace(e));
		}
		
	} // Constructor
	
	// ----------------------------------------
	
	/**
	 * @param x
	 * @param y
	 */
	public void move (int x, int y) {
		
		int moveX;
		int moveY;
		
		moveX = this.getX() + x;
		moveY = this.getY() + y;
		
		setX(moveX);
		setY(moveY);
		
	} // de move
	
	
	public String toString () {
        return "WarehouseMan(" + this.getY() + "," + this.getX()+ ")";
    }

} // de WarehouseMan
