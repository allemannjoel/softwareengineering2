package ch.fhnw.richards.lecture10_xml.javaObjects.commons;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

/**
 * A simple example showing how to encapsulate messages in a class. This class sends and receives
 * some simple data via sockets. The data is formatted in XML.
 * 
 * Each message is uniquely identified with an ID and a timestamp. This can be useful, for example,
 * if you want to keep a log of messages.
 */
public abstract class Message implements Serializable {
	private static final long serialVersionUID = 1; // This is version 1 of the message class
	
	// Data included in a message
    private long id;
    private long timestamp;
    private String client;

    // Generator for a unique message ID
    private static long messageID = 0;

    /**
     * Increment the global messageID
     * 
     * @return the next valid ID
     */
    private static long nextMessageID() {
        return messageID++;
    }

    /**
     * Constructor to create a new message
     * 
     * @param type What type of message to construct
     */
    protected Message() {
        this.id = -1;
    }
    
    /**
     * Send this message, as a serialized object, over the given socket
     * 
     * @param s The socket to use when sending the message
     */
    public void send(Socket s) {
    	// Set the message id before sending (if not already done)
    	if (this.id == -1) this.id = nextMessageID();
    	
    	// Set the timestamp
    	this.timestamp = System.currentTimeMillis();
    	
        try { // Ignore IO errors
        	ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            out.writeObject(this);
            out.flush();
            s.shutdownOutput(); // ends output without closing socket
        } catch (Exception e) {
        }
    }    

    /**
     * Factory method to construct a message-object from data received via socket
     * 
     * @param socket The socket to read from
     * @return the new message object, or null in case of an error
     * @throws Exception
     */
    public static Message receive(Socket socket) throws Exception {
    	ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
    	Message msg = (Message) in.readObject();
        return msg;
    }
    
    @Override
    public String toString() {
		return "Message type " + this.getClass().getSimpleName() + ": ID = " + this.id + ", Timestamp=" + this.timestamp
				+ ", Client='" + this.client + "'";
    }

    // --- Getters and Setters ---
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}
}
