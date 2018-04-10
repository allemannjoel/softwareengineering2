package ch.fhnw.richards.lecture03.globalresources.method3;

import java.util.Locale;

// Singleton class - here with almost no functionality
public class ServiceLocator {
	private static ServiceLocator singleton;
	private Locale selectedLocale;

	public static void init() {
		if (singleton == null) {
			singleton = new ServiceLocator();
		}
	}
	
	public static void setSelectedLocale(Locale selectedLocale) {
    singleton.selectedLocale = selectedLocale;
	}
	
	public static Locale getSelectedLocale() {
    return singleton.selectedLocale;
	}
}
