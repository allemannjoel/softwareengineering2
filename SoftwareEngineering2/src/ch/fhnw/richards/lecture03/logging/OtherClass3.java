package ch.fhnw.richards.lecture03.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class OtherClass3 {
	public OtherClass3() {
		// Create a logger "below" the logger of the main class
		Logger logger = Logger.getLogger(LoggingTest.LOGGER_NAME + "."
				+ this.getClass().getSimpleName());
		logger.setLevel(Level.INFO);

		logger.fine("Fine message from inheriting logger");
		logger.info("Info message from inheriting logger");
		logger.warning("Warning message inheriting logger");
	}
}
