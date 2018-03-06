package ch.fhnw.richards.lecture03.logging;

import java.util.logging.Logger;

public class OtherClass2 {
	public OtherClass2() {
		// Create a logger with the name of this class
		Logger logger = Logger.getLogger(this.getClass().getSimpleName());
		
		logger.fine("Fine message from other class 2");
		logger.info("Info message from other class 2");
		logger.warning("Warning message from other class 2");
	}
}
