package ch.fhnw.richards.lecture10_xml.jaxpDOM.commons;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Message_NewCustomerAccepted extends Message {
    private static final String ELEMENT_NAME = "name";

    private String name;
    
    public Message_NewCustomerAccepted() {
    	super();
    }

	@Override
	protected void init(Document docIn) {
        Element root = docIn.getDocumentElement();
		
		NodeList tmpElements = root.getElementsByTagName(ELEMENT_NAME);
        if (tmpElements.getLength() > 0) {
            Element name = (Element) tmpElements.item(0);
            this.name = name.getTextContent();
        }
	} 
	
	@Override
	protected void addNodes(Document docIn) {
        Element root = docIn.getDocumentElement();
		
		Element name = docIn.createElement(ELEMENT_NAME);
		name.setTextContent(this.name);
		root.appendChild(name);
	}
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
