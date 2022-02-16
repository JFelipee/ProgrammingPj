package es.upm.pproject.sokoban.model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

import javax.imageio.ImageIO;


public class Floor extends Moves {
	
	private static final Logger LOGGER = Logger.getLogger("bitacora.model");
	
	/**
	 * @param x
	 * @param y
	 */
	public Floor(int x, int y) {
		
		super(x, y);
		Image imagen;
		try {
			imagen = ImageIO.read(new File("src/main/resources/Floor/Floor5.png"));
			setImage(imagen);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "No se puede ejecutar el programa");
			LOGGER.log(Level.FINE, ControlLogger.getStackTrace(e));
		}
		
	} // constructor
	
	
	public String toString () {
        return "Floor(" + this.getY() + "," + this.getX()+ ")";
    }

} // de Floor
