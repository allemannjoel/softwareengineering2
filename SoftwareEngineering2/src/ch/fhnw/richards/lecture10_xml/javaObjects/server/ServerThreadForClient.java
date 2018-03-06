package ch.fhnw.richards.lecture10_xml.javaObjects.server;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

import ch.fhnw.richards.lecture10_xml.javaObjects.commons.*;

public class ServerThreadForClient extends Thread {
    private final Logger logger = Logger.getLogger("");
    private Socket clientSocket;

    public ServerThreadForClient(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /**
     * Process messages until the client says "Goodbye"
     */
    @Override
    public void run() {
        logger.info("Request from client " + clientSocket.getInetAddress().toString()
                + " for server " + clientSocket.getLocalAddress().toString());

        try {
			// Read a message from the client
			Message msgIn = Message.receive(clientSocket);
			Message msgOut = processMessage(msgIn);
			msgOut.send(clientSocket);
	    	logger.info("Answered with: " + msgOut.toString());
        } catch (Exception e) {
            logger.severe(e.toString());
        } finally {
            try { if (clientSocket != null) clientSocket.close(); } catch (IOException e) {}
        }
    }
    
    private Message processMessage(Message msgIn) {
		logger.info("Message received from client: "+ msgIn.toString());
		String clientName = msgIn.getClient();		

		Message msgOut = null;
		switch (MessageType.getType(msgIn)) {
		case Hello:
			msgOut = new Message_Hello();
			break;
		case NewCustomer:
			Message_NewCustomer nc_msg = (Message_NewCustomer) msgIn;
			Message_NewCustomerAccepted nca_msg = new Message_NewCustomerAccepted();
			nca_msg.setName(nc_msg.getName());
			msgOut = nca_msg;
			break;
		case Goodbye:
			msgOut = new Message_Goodbye();
			break;
		default:
			msgOut = new Message_Error();
		}
		msgOut.setClient(clientName);
    	return msgOut;
    }
}
