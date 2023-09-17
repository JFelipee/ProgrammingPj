package es.upm.pproject.sokoban.model;

import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;

public class ControlLogger {

	// Logger que se va a utilizar para esta clase
	private static final Logger LOGGER = Logger.getLogger("bitacora");

	public ControlLogger() {
		throw new UnsupportedOperationException();
	} // Constructor

	// --------------------------------------------

	public static void main(String[] args) {

		try {

			Handler consoleHandler = new ConsoleHandler(); // Pra que se muestre en consola
			Handler fileHandler = new FileHandler("src//main//resources//Loggers//LOGGER", false); // Para que se muestre en un fichero
			SimpleFormatter simpleFormatter = new SimpleFormatter();

			fileHandler.setFormatter(simpleFormatter); // Para que escriba en formato simple y no en xml

			LOGGER.addHandler(fileHandler); // Se le añade el handler del fichero
			LOGGER.addHandler(consoleHandler); // Se le añade el handler de la consola

			// Salta a todos los niveles(warnings, errores graves...)
			fileHandler.setLevel(Level.ALL);
			consoleHandler.setLevel(Level.ALL);

			// Mensaje de inicio en el logger (llamdo bitacora)
			LOGGER.log(Level.INFO, "##### Inicio de Logger #####");

		} catch (IOException | SecurityException e) {
			
			LOGGER.log(Level.SEVERE, ControlLogger.getStackTrace(e));
			
		}

	} // de control
	
	// Para convertir el error en un String y poder escribirlo en el logger
	public static String getStackTrace(Exception exception) {
		
		StringWriter sW = new StringWriter();
		PrintWriter pW = new PrintWriter(sW);
		exception.printStackTrace(pW);
		
		return sW.toString();
		
	}

} // de ControlLogger
