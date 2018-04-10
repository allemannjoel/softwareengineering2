package ch.fhnw.richards.lecture10_xml.yoo_exercise.commons;

import org.simpleframework.xml.Element;

public class Position {
	
	@Element
	private int xPos;
	
	@Element
	private int yPos;
	
	public Position(){
		
	}
	
	private Position(int x, int y){
		this.setX(x);
		this.setY(y);
	}
	
	public static Position getRandomPosition(int xMax, int yMax){
		int x = (int) (Math.random() * xMax);
		int y = (int) (Math.random() * yMax);
		return new Position(x, y);
	}
	
	public String toString(){
		StringBuffer msg = new StringBuffer();
		msg.append("XPos:" + this.xPos + " ");
		msg.append("YPos:" + this.yPos);
		return msg.toString();
	}

	public int getY() {
		return yPos;
	}

	public void setY(int y) {
		this.yPos = y;
	}

	public int getX() {
		return xPos;
	}

	public void setX(int x) {
		this.xPos = x;
	}
	
	
}
