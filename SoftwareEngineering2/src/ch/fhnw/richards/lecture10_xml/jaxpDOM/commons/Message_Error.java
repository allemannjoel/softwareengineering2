package ch.fhnw.richards.lecture10_xml.jaxpDOM.commons;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Message_Error extends Message {
	private static final String ELEMENT_INFO = "info";
	
	private String info;
	
    public Message_Error() {
    	super();
    }

	@Override
	protected void init(Document docIn) {
        Element root = docIn.getDocumentElement();
		
		NodeList tmpElements = root.getElementsByTagName(ELEMENT_INFO);
        if (tmpElements.getLength() > 0) {
            Element info = (Element) tmpElements.item(0);
            this.info = info.getTextContent();
        }
	}    
	
	@Override
	protected void addNodes(Document docIn) {
        Element root = docIn.getDocumentElement();
		
		Element info = docIn.createElement(ELEMENT_INFO);
		info.setTextContent(this.info);
		root.appendChild(info);
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
