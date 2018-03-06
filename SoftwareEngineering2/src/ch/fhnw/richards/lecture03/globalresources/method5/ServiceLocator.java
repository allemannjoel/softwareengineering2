package ch.fhnw.richards.lecture03.globalresources.method5;

import java.util.Locale;

public interface ServiceLocator {
    public void setSelectedLocale(Locale selectedLocale);

    public Locale getSelectedLocale();
}
