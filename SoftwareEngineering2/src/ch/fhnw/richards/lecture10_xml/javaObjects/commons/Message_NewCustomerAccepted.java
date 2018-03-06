package ch.fhnw.richards.lecture10_xml.javaObjects.commons;

import java.io.Serializable;

public class Message_NewCustomerAccepted extends Message implements Serializable {
	private static final long serialVersionUID = 1; // This is version 1
    private String name;
    
    public Message_NewCustomerAccepted() {
    	super();
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
	@Override
	public String toString() {
		return super.toString() + ", name='" + this.name + "'";
	}

}
