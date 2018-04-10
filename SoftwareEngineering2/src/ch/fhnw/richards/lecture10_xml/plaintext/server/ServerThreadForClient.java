package ch.fhnw.richards.lecture10_xml.plaintext.server;

import java.net.Socket;
import java.util.logging.Logger;

import ch.fhnw.richards.lecture10_xml.plaintext.MessageHandler;

public class ServerThreadForClient extends Thread {
    private final Logger logger = Logger.getLogger("");
    private Socket clientSocket;

    public ServerThreadForClient(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /**
     * We process single-line messages, similar to the XML messages used in other examples
     */
    @Override
    public void run() {
        boolean goodbye = false;
        
        logger.info("Request from client " + clientSocket.getInetAddress().toString()
                + " for server " + clientSocket.getLocalAddress().toString());

        MessageHandler msgHandler = new MessageHandler(clientSocket);

        while (!goodbye) {
            // Read request from client
            String messageIn = msgHandler.receive();

            if (messageIn == null) {
                // Network connection closed or interrupted
                goodbye = true;
            } else {
                logger.info("Message received: " + messageIn);
                String msgOut = processMessage(messageIn);
                msgHandler.send(msgOut);
                logger.info("Message answered: " + msgOut + "\n");

                
                if (msgOut.equals("Goodbye")) goodbye = true;
            }
        }
        msgHandler.close();
    }
    
    private String processMessage(String msgIn) {
    	String msgOut = "";
    	if (msgIn.charAt(0) == 'h' || msgIn.charAt(0) == 'H')
    		msgOut = "Hi, how are you? ";
    	else if (msgIn.charAt(0) == 'g' || msgIn.charAt(0) == 'G') {
    		msgOut = "Goodbye";
    	} else {
    		msgOut = "Say what?";
    	}
        return msgOut;
    }
}
