package ch.fhnw.richards.lecture03.globalresources.method4;

import java.util.Locale;

public class MiddleClass {
	private ServiceLocator serviceLocator;

	public MiddleClass(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	// Display an instance of LastClass, which
	// also needs access to our shared resource
	public void go() {
		LastClass lc = new LastClass(serviceLocator);
	}

}