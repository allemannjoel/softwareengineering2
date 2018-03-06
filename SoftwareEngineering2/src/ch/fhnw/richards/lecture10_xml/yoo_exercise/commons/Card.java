package ch.fhnw.richards.lecture10_xml.yoo_exercise.commons;

import org.simpleframework.xml.Element;

public class Card {
	
	@Element
	private double red;
	
	@Element
	private double green;
	
	@Element
	private double blue;
	
	public Card(){
		
	}
	
	private Card(double red, double green, double blue){
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public static Card getRandomCard(){
	    double a = Math.random();
	    double b = Math.random();
	    double c = Math.random();
		return new Card(a, b, c);
	}
	
	public String toString(){
		StringBuffer msg = new StringBuffer();
		msg.append("red:" + this.red + " ");
		msg.append("green:" + this.green + " ");
		msg.append("blue:" + this.blue);
		return msg.toString();
	}

	public double getRed() {
		return red;
	}

	public void setRed(double red) {
		this.red = red;
	}

	public double getGreen() {
		return green;
	}

	public void setGreen(double green) {
		this.green = green;
	}

	public double getBlue() {
		return blue;
	}

	public void setBlue(double blue) {
		this.blue = blue;
	}

}
