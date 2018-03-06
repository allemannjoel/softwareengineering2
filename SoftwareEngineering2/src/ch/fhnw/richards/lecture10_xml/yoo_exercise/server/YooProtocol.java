package ch.fhnw.richards.lecture10_xml.yoo_exercise.server;

import ch.fhnw.richards.lecture10_xml.yoo_exercise.commons.Message;
import ch.fhnw.richards.lecture10_xml.yoo_exercise.commons.MessageType;

public class YooProtocol {

    ServerThread s;
	
	public YooProtocol(ServerThread s){
		this.s = s;
	}
	
	public void processInput(Message m){
		if(m.getType() == MessageType.YOO){
			for(ClientThread c: s.state.keySet()){
				Message message = s.state.get(c);
				message.setRandomCard();
				message.setRandomPosition(1000, 600);
				s.state.put(c, message);
			}
		}
	}

}
