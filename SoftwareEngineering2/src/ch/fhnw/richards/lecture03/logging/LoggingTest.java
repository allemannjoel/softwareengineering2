package ch.fhnw.richards.lecture03.logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingTest {
	// Name of the main logger
	public static final String LOGGER_NAME = LoggingTest.class.getSimpleName();
	private Logger logger = null; // The logger object for this class
	
	public static void main(String[] args) {
		// Separate one-time initialization from the actual work
		LoggingTest programInstance = new LoggingTest();
		programInstance.doStuff();
	}

	private LoggingTest() {
		// Create a logger for this class
		logger = Logger.getLogger(LOGGER_NAME);
		logger.setLevel(Level.INFO); // set the default logger level
		
		// By default there is one handler: the console
		Handler[] defaultHandlers = Logger.getLogger("").getHandlers();
		if (defaultHandlers.length == 1) {
			// Configure the console handler
			defaultHandlers[0].setLevel(Level.FINEST);
		} else {
			throw new RuntimeException("More than one default handler found");
		}
		
		// Add a file handler: put rotating files in the tmp directory
			try {
				// %u is for file-conflicts; this happens if multiple instances
				// of the program are running. %g is the rotating logfile number
				Handler logHandler = new FileHandler("%t/" + LOGGER_NAME + "_%u" + "_%g" + ".log", 1000000, 9);
				logHandler.setLevel(Level.FINE);
				logger.addHandler(logHandler);
			} catch (Exception e) {
				throw new RuntimeException("Unable to initialize log files: " + e.toString());
			}
	}
	
	public void doStuff() {
		
		logger.fine("Fine message from main class");
		logger.info("Info message from main class");
		logger.warning("Warning message from main class");
		
		OtherClass other = new OtherClass();
		OtherClass2 other2 = new OtherClass2();
		OtherClass3 other3 = new OtherClass3();
	}
}
