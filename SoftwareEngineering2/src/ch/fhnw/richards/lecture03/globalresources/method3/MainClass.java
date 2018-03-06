package ch.fhnw.richards.lecture03.globalresources.method3;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

// Solution 3: singleton resource locator
public class MainClass extends JFrame {
	// Available locales
	private Locale locale_en;
	private Locale locale_de;
	
	public static void main(String[] args) {
		// Initialize static resources in the main method
		ServiceLocator.init();
		new MainClass();
	}

	private MainClass() {
		final JLabel lblLocale;
		final JButton btnSwitch;
		final JButton btnGo;

		// Create possible locales
		locale_en = new Locale("en");
		locale_de = new Locale("de");
		
		// Set initial locale
		ServiceLocator.setSelectedLocale(locale_en);
		
		this.setLayout(new GridLayout(2,2));
		this.setTitle(this.getClass().getPackage().getName());
		this.add(new JLabel("Currently selected locale"));
		
		lblLocale = new JLabel();
		lblLocale.setText(ServiceLocator.getSelectedLocale().getDisplayLanguage());
		lblLocale.setPreferredSize(new Dimension(250,70));
		this.add(lblLocale);
		
		// Locale switcher
		btnSwitch = new JButton("Switch Locale");
		this.add(btnSwitch);
		btnSwitch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (ServiceLocator.getSelectedLocale() == locale_en) {
					ServiceLocator.setSelectedLocale(locale_de);
				} else {
					ServiceLocator.setSelectedLocale(locale_en);
				}
				lblLocale.setText(ServiceLocator.getSelectedLocale().getDisplayLanguage());
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
