package ch.fhnw.richards.lecture03.logging;

import java.util.logging.Logger;

public class OtherClass {
	private Logger logger;
	
	public OtherClass() {
		// Get a reference to the main logger object
		logger = Logger.getLogger(LoggingTest.LOGGER_NAME);
		
		logger.fine("Fine message from other class");
		logger.info("Info message from other class");
		logger.warning("Warning message from other class");
	}
}
