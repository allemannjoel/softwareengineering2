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
 * A better version: the repetitive code has been removed and
 * replaced with loops and arrays
 */
public class KissExample1b extends JFrame implements ActionListener {
	private static final int NUM_INDICATORS = 6;
	JButton[] buttons = new JButton[NUM_INDICATORS];
	JLabel[] indicators = new JLabel[NUM_INDICATORS];
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new KissExample1a();
			}
		});
	}
	
	public KissExample1b() {
		super();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(NUM_INDICATORS,2));
		
		for (int i = 1; i <= NUM_INDICATORS; i++) {
			buttons[i-1] = makeButton(i);
			indicators[i-1] = makeIndicator(i);
		}
		
		this.pack();
		this.setVisible(true);
	}
	
	private JButton makeButton(int id) {
		JButton btn = new JButton();
		this.add(btn);
		btn.setBorder(new BevelBorder(BevelBorder.RAISED));
		btn.setBackground(Color.blue);
		btn.setForeground(Color.white);
		btn.setText("button" + id);
		btn.setPreferredSize(new Dimension(100,30));
		btn.setVisible(true);
		btn.addActionListener(this);
		return btn;
	}
	
	private JLabel makeIndicator(int id) {
		JLabel indicator = new JLabel();
		this.add(indicator);
		indicator.setVisible(false);
		indicator.setForeground(Color.blue);
		indicator.setPreferredSize(new Dimension(60,30));
		indicator.setHorizontalAlignment(SwingConstants.CENTER);
		indicator.setText("icon" + id);
		return indicator;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Set all indicators to invisible
		for (JLabel indicator : indicators) indicator.setVisible(false);
		
		// Set the selected indicator to visible
		JButton btn = (JButton) e.getSource();
		for (int i = 0; i < NUM_INDICATORS; i++) {
			if (btn == buttons[i]) {
				indicators[i].setVisible(true);
			}
		}	
	}
}