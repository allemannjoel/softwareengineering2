package ch.fhnw.richards.lecture10_xml.plaintext.client;

import ch.fhnw.richards.lecture10_xml.plaintext.MessageHandler;


public class ClientModel {
	MessageHandler msgHandler;
	
	public void init(String ipAddress, int portNumber) {
		msgHandler = new MessageHandler(ipAddress, portNumber);
	}
    
    public String sayHello() {
    	msgHandler.send("Hello");
        String result = msgHandler.receive();
        return result;
    }
    
    public String sendCustomMessage(String message) {
    	msgHandler.send(message);
        String result = msgHandler.receive();
        return result;
    }
    
    public String sayGoodbye() {
    	msgHandler.send("Goodbye");
        String result = msgHandler.receive();
        msgHandler.close();
        return result;
    }
}
