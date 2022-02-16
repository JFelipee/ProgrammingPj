package es.upm.pproject.sokoban.view;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;


import es.upm.pproject.sokoban.model.Moves;
import es.upm.pproject.sokoban.model.Wall;
import es.upm.pproject.sokoban.model.Box;
import es.upm.pproject.sokoban.model.ControlLogger;
import es.upm.pproject.sokoban.model.Floor;
import es.upm.pproject.sokoban.model.Goal;


public class ObjectPanel extends JPanel {
	
	private static final Logger LOGGER = Logger.getLogger("bitacora.view");
	
	private Image imagen;
	private String type;
	
	public ObjectPanel(Moves element) {
		imagen = element.getImage();
		if(element instanceof Wall) {
			type = "Wall";
		}
		else if(element instanceof Box) {
			type = "Box";
		}
		else if(element instanceof Floor) {
			type = "Floor";
		}
		else if(element instanceof Goal) {
			type = "Goal";
		}
		else {
			type = "WarehouseMan";
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(this.type.equals("Box") || this.type.equals("Goal") || this.type.equals("WarehouseMan")){
			try {
			File fotoFondo = new File("src/main/resources/Floor/Floor5.png");
			Image fondo = ImageIO.read(fotoFondo);
			fondo = fondo.getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
			g.drawImage(fondo, 0, 0, null);
			
			} catch (IOException e) {
				
				LOGGER.log(Level.SEVERE, "No se puede ejecutar el programa");
				LOGGER.log(Level.FINE, ControlLogger.getStackTrace(e));
				
			}
		}
		imagen = imagen.getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
		g.drawImage(imagen, 0, 0, null);

	}
	
}
