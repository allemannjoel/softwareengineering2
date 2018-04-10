package ch.fhnw.richards.lecture03.globalresources.method4;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Locale;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class LastClass extends JDialog {
	private ServiceLocator serviceLocator;
	
	public LastClass(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
		
		this.setLayout(new GridLayout(1,1));
		JLabel lblLocale =new JLabel(serviceLocator.getSelectedLocale().getDisplayLanguage());
		lblLocale.setPreferredSize(new Dimension(200,50));
		this.add(lblLocale);
		
		this.setModal(true);
		this.setTitle("LastClass Dialog");
		this.setLocale(serviceLocator.getSelectedLocale());
		
		this.pack();
		this.setVisible(true);
	}

}

