package ch.fhnw.richards.lecture03.globalresources.method5;

import java.util.Locale;

import com.google.inject.AbstractModule;

public class GuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ServiceLocator.class).to(ServiceLocator_implementation.class);
    }
}
