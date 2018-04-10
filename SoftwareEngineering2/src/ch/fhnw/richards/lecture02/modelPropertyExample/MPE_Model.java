package ch.fhnw.richards.lecture02.modelPropertyExample;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * This model simulates external changes to data. It uses a thread that randomly
 * increments or decrements the value.
 * 
 * In order for the value to generate events (when changed), we must use the
 * class javafx.beans.property.SimpleIntegerProperty. An object of this class
 * contains a single integer value, and generate events when the value changes.
 */
public class MPE_Model {
	// final promises that this object will not change
	private final SimpleIntegerProperty value;
	private Simulator sim;

	protected MPE_Model() {
		value = new SimpleIntegerProperty();
		value.setValue(0);
		sim = new Simulator();
		sim.start();
	}
	
	public SimpleIntegerProperty getValueProperty() {
		return value;
	}

	public int getValue() {
		return value.get();
	}

	public int incrementValue() {
		int val = value.get();
		val++;
		value.setValue(val);
		return val;
	}
	
	/**
	 * Called, when we should stop our simulation
	 */
	public void stop() {
		if (sim != null) sim.interrupt();
	}

	/**
	 * We haven't seen threads yet, but a thread is just an independent,
	 * parallel activity. In this case, the thread randomly increments or
	 * decrements our value
	 */
	private class Simulator extends Thread {
		@Override
		public void run() {
			try {
				while (true) {
					int rand = (int) (Math.random() * 5);
					if (rand == 1) // 20% chance of increment
						value.setValue(value.get()+1);
					else if (rand == 2)// 20% chance of decrement
						value.setValue(value.get()-1);
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
			}
		}
	}
}
