package ch.fhnw.richards.lecture03.globalresources.method5;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

// Solution 1: Dependency injection
public class MainClass extends JFrame {
    ServiceLocator serviceLocator;

    // Available locales
    private Locale locale_en;
    private Locale locale_de;

    public static GuiceModule guice;
    public static Injector injector;

    public static void main(String[] args) {
        guice = new GuiceModule();
        injector = Guice.createInjector(guice);

        injector.getInstance(MainClass.class);
    }

    @Inject
    protected MainClass(ServiceLocator sl) {
        serviceLocator = sl;
        final JLabel lblLocale;
        final JButton btnSwitch;
        final JButton btnGo;

        // Create possible locales
        locale_en = new Locale("en");
        locale_de = new Locale("de");

        // Set initial locale
        serviceLocator.setSelectedLocale(locale_en);

        this.setLayout(new GridLayout(2, 2));
        this.setTitle(this.getClass().getPackage().getName());
        this.add(new JLabel("Currently selected locale"));

        lblLocale = new JLabel();
        lblLocale.setText(serviceLocator.getSelectedLocale()
                .getDisplayLanguage());
        lblLocale.setPreferredSize(new Dimension(250, 70));
        this.add(lblLocale);

        // Locale switcher
        btnSwitch = new JButton("Switch Locale");
        this.add(btnSwitch);
        btnSwitch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (serviceLocator.getSelectedLocale() == locale_en) {
                    serviceLocator.setSelectedLocale(locale_de);
                } else {
                    serviceLocator.setSelectedLocale(locale_en);
                }
                lblLocale.setText(serviceLocator.getSelectedLocale()
                        .getDisplayLanguage());
            }
        });

        // "Go"
        btnGo = new JButton("Go do stuff...");
        this.add(btnGo);
        btnGo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                do_stuff();
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    // This method represents the program functionality
    // In our case, we just create an instance of MiddleClass,
    // passing it our one resource "selectedLocale"
    private void do_stuff() {
        MiddleClass m = new MiddleClass();
        m.go();
    }
}
