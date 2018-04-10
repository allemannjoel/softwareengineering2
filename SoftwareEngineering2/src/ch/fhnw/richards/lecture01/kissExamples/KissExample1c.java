package ch.fhnw.richards.lecture01.kissExamples;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

/**
 * Part of a GUI with six JButtons performing related tasks
 * 
 * A more object-oriented solution: specialized controls are
 * defined in their own classes. In a real application, the
 * new classes should be in their own files.
 */
public class KissExample1c extends JFrame implements ActionListener {
	private static final int NUM_INDICATORS = 6;
	MyButton[] buttons = new MyButton[NUM_INDICATORS];
	Indicator[] indicators = new Indicator[NUM_INDICATORS];
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new KissExample1a();
			}
		});
	}
	
	public KissExample1c() {
		super();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(NUM_INDICATORS, 2));
		
		for (int i = 1; i <= NUM_INDICATORS; i++) {
			buttons[i-1] = new MyButton(i);
			this.add(buttons[i-1]);
			buttons[i-1].addActionListener(this);
			
			indicators[i-1] = new Indicator(i);
			this.add(indicators[i-1]);
		}
		
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Set all indicators to invisible
		for (Indicator indicator : indicators) indicator.setVisible(false);
		
		// Set the selected indicator to visible
		MyButton btn = (MyButton) e.getSource();
		for (int i = 0; i < NUM_INDICATORS; i++) {
			if (btn == buttons[i]) {
				indicators[i].setVisible(true);
			}
		}	
	}
	
	private static class MyButton extends JButton {
		public MyButton(int id) {
			super();
			setBorder(new BevelBorder(BevelBorder.RAISED));
			setBackground(Color.blue);
			setForeground(Color.white);
			setText("button" + id);
			setPreferredSize(new Dimension(100,30));
			setVisible(true);
		}		
	}
	
	private static class Indicator extends JLabel {
		public Indicator(int id) {
			setVisible(false);
			setForeground(Color.blue);
			setPreferredSize(new Dimension(60,30));
			setHorizontalAlignment(SwingConstants.CENTER);
			setText("icon" + id);
		}
	}
}