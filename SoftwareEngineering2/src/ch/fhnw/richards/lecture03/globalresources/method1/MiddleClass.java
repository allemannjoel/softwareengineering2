package ch.fhnw.richards.lecture03.globalresources.method1;

import java.util.Locale;

public class MiddleClass {
	private Locale selectedLocale;

	public MiddleClass(Locale selectedLocale) {
		this.selectedLocale = selectedLocale;
	}

	// Display an instance of LastClass, which
	// also needs access to our shared resource
	public void go() {
		LastClass lc = new LastClass(selectedLocale);
	}

}
