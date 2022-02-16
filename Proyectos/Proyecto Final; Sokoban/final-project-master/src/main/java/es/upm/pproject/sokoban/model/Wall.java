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

public class Wall extends Moves{
	
	private static final Logger LOGGER = Logger.getLogger("bitacora.model");


	/**
	 * @param x
	 * @param y
	 * @see Wall
	 */
	public Wall(int x, int y) {
		super(x,y);
		Image imagen;
		try {
			imagen = ImageIO.read(new File("src/main/resources/Wall/Wall2.png"));
			setImage(imagen);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "No se puede ejecutar el programa");
			LOGGER.log(Level.FINE, ControlLogger.getStackTrace(e));
		}
	}
	
	
	public String toString () {
        return "Wall(" + this.getY() + "," + this.getX()+ ")";
    }
	
}

