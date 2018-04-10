package ch.fhnw.richards.lecture10_xml.yoo_exercise.commons;

import java.io.InputStream;
import java.net.Socket;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;


@Root
public class Message {
	
	@Element(required = false)
	private Card card;
	
	@Element(required = false)
	private Position position;
	
	@Element
	private MessageType type;
	
	public Message(@Element(name = "type") MessageType type) {
		this.type = type;
	}
	
	public static Message receive(Socket socket) {
		try{
			Serializer netIn = new Persister();
			InputStream in = socket.getInputStream();
			Message msg = netIn.read(Message.class, in);
			return msg;
		}
		catch(Exception e){
			System.out.println(e.toString());
			return null;
		}
	}
	
	public void setRandomPosition(int xMax, int yMax){
		this.position = Position.getRandomPosition(xMax, yMax);
	}
	
	public void setRandomCard(){
		this.card = Card.getRandomCard();
	}
	

	@Override
	public String toString() {
		StringBuffer msg = new StringBuffer();
		msg.append(this.type.toString());
		msg.append(":");
		if (this.card != null) msg.append(" card='" + this.card + "' ");
		if (this.position != null) msg.append("position='" + this.position + "'");
		return msg.toString();		
	}
	
	public MessageType getType() {
		return type;
	}
	
	public void setType(MessageType type) {
		this.type = type;
	}

	public Card getCard(){
		return this.card;
	}
	
	public Position getPosition(){
		return this.position;
	}
	
}
