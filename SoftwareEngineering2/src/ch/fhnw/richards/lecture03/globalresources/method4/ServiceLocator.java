package ch.fhnw.richards.lecture03.globalresources.method4;

import java.util.Locale;

// Simple class - here with almost no functionality
public class ServiceLocator {
	private Locale selectedLocale;

	public void setSelectedLocale(Locale selectedLocale) {
    this.selectedLocale = selectedLocale;
	}
	
	public Locale getSelectedLocale() {
    return this.selectedLocale;
	}
}
