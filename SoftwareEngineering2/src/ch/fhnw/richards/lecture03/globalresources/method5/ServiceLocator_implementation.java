package ch.fhnw.richards.lecture03.globalresources.method5;

import java.util.Locale;

public class ServiceLocator_implementation implements ServiceLocator {
    private Locale selectedLocale;

    public ServiceLocator_implementation() {
    }

    public void setSelectedLocale(Locale selectedLocale) {
        this.selectedLocale = selectedLocale;
    }

    public Locale getSelectedLocale() {
        return selectedLocale;
    }
}
