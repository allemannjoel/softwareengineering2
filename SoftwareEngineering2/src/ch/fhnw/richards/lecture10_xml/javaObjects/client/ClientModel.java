package ch.fhnw.richards.lecture10_xml.javaObjects.client;

import java.io.IOException;
import java.net.Socket;

import ch.fhnw.richards.lecture10_xml.javaObjects.commons.*;

public class ClientModel {
    String ipAddress;
    Integer port;

	public void init(String ipAddress, Integer port) {
	    this.ipAddress = ipAddress;
	    this.port = port;
	}

	private Socket connect() {
	    Socket socket = null;
        try {
            socket = new Socket(ipAddress, port);
        } catch (Exception e) {
            // TODO Auto-generated catch block
        }
	    return socket;
	}
	
	public String sayHello(String clientName) {
        String result = null;
	    Socket socket = connect();
	    if (socket != null) {
    		Message msgOut = new Message_Hello();
    		msgOut.setClient(clientName);
    		try {
    			msgOut.send(socket);
    			Message msgIn = Message.receive(socket);
    			result = msgIn.toString();
    		} catch (Exception e) {
    			result = e.toString();
    		}
            try { if (socket != null) socket.close(); } catch (IOException e) {}
	    }
		return result;
	}

	public String sayNewClient(String clientName) {
        String result = null;
        Socket socket = connect();
        if (socket != null) {
    		Message_NewCustomer msgOut = new Message_NewCustomer();
    		msgOut.setClient(clientName);
    		msgOut.setName("Jennifer Jumpingjacks");
    		msgOut.setAge(23);
    		try {
    			msgOut.send(socket);
    			Message msgIn = Message.receive(socket);
    			result = msgIn.toString();
    		} catch (Exception e) {
    			result = e.toString();
    		}
            try { if (socket != null) socket.close(); } catch (IOException e) {}
        }
		return result;
	}

	public String sayGoodbye(String clientName) {
        String result = null;
        Socket socket = connect();
        if (socket != null) {
    		Message msgOut = new Message_Goodbye();
    		msgOut.setClient(clientName);
    		try {
    			msgOut.send(socket);
    			Message msgIn = Message.receive(socket);
    			result = msgIn.toString();
    		} catch (Exception e) {
    			result = e.toString();
    		}
    		try { if (socket != null) socket.close(); } catch (IOException e) {}
        }
		return result;
	}
}
