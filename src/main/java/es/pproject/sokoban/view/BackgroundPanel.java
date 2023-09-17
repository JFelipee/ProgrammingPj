package es.upm.pproject.sokoban.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import es.upm.pproject.sokoban.model.ControlLogger;


public class BackgroundPanel extends JPanel {

	private static final long serialVersionUID = 9196647649284915498L;

	private Image imagen;
	
	private static final Logger LOGGER = Logger.getLogger("bitacora.view");

	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		File miimagen = new File("src/main/resources/Floor/Floor4.png");
		try {
			imagen = ImageIO.read(miimagen);
			imagen = imagen.getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
		} catch (IOException e) {
			
			LOGGER.log(Level.SEVERE, "No se puede ejecutar el programa");
			LOGGER.log(Level.FINE, ControlLogger.getStackTrace(e));
			
		}
		g.drawImage(imagen, 0, 0, null);
	}
	
}
