package ch.fhnw.richards.lecture10_xml.javaObjects.commons;

import java.io.Serializable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Message_Error extends Message implements Serializable {
	private static final long serialVersionUID = 1; // This is version 1
	private static final String ELEMENT_INFO = "info";
	
	private String info;
	
    public Message_Error() {
    	super();
    }

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
