package ch.fhnw.richards.lecture10_xml.simpleXML.client;

import java.io.IOException;
import java.net.Socket;

import ch.fhnw.richards.lecture10_xml.simpleXML.Message;

public class ClientModel {
	Socket socket;

	public boolean connect(String ipAddress, Integer port) {
		boolean success = false;
		try {
			socket = new Socket(ipAddress, port);
			success = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return success;
	}

	public String sayHello() {
		Message msgOut = new Message(Message.MessageType.Hello);
		String result = null;
		try {
			msgOut.send(socket);
			Message msgIn = Message.receive(socket);
			result = msgIn.toString();
		} catch (Exception e) {
			result = e.toString();
		}
		return result;
	}

	public String sayNewCustomer() {
		Message msgOut = new Message(Message.MessageType.NewCustomer);
		msgOut.setName("Jennifer Jumpingjacks");
		msgOut.setAge(23);
		String result = null;
		try {
			msgOut.send(socket);
			Message msgIn = Message.receive(socket);
			result = msgIn.toString();
		} catch (Exception e) {
			result = e.toString();
		}
		return result;
	}

	public String sayGoodbye() {
		Message msgOut = new Message(Message.MessageType.Goodbye);
		String result = null;
		try {
			msgOut.send(socket);
			Message msgIn = Message.receive(socket);
			result = msgIn.toString();
		} catch (Exception e) {
			result = e.toString();
		}
		try { if (socket != null) socket.close(); } catch (IOException e) {}
		return result;
	}
}
