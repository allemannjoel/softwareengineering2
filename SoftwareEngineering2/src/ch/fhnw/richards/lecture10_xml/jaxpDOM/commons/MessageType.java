package ch.fhnw.richards.lecture10_xml.jaxpDOM.commons;

/**
 * This class defines all available message types. We provide static functions
 * to map between tye types and the value of the enumeration.
 */
public enum MessageType {
    Hello,
    NewCustomer,
    NewCustomerAccepted,
    Goodbye,
    Error;
	
    public static MessageType parseType(String typeName) {
    	MessageType type = MessageType.Error;
    	for (MessageType value : MessageType.values()) {
    		if (value.toString().equals(typeName)) type = value;
    	}
    	return type;
    }
	
    /**
     * Determine the message type from the actual class of this object
     */
    public static MessageType getType(Message msg) {
    	MessageType type = MessageType.Error;
    	if (msg instanceof Message_Hello) type = Hello;
    	else if (msg instanceof Message_NewCustomer) type = NewCustomer;
    	else if (msg instanceof Message_NewCustomerAccepted) type = NewCustomerAccepted;
    	else if (msg instanceof Message_Goodbye) type = Goodbye;
    	return type;
    }	
	
}