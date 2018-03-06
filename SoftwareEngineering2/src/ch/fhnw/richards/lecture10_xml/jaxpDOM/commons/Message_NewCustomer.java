package ch.fhnw.richards.lecture10_xml.jaxpDOM.commons;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Message_NewCustomer extends Message {
    private static final String ELEMENT_NAME = "name";
	private static final String ELEMENT_AGE = "age";

    private String name;
    private Integer age;
    
    public Message_NewCustomer() {
    	super();
    }

	/**
	 * Fill in attributes specific to this message type
	 */
	@Override
	protected void init(Document docIn) {
        Element root = docIn.getDocumentElement();
		
		NodeList tmpElements = root.getElementsByTagName(ELEMENT_NAME);
        if (tmpElements.getLength() > 0) {
            Element name = (Element) tmpElements.item(0);
            this.name = name.getTextContent();
        }
		
		tmpElements = root.getElementsByTagName(ELEMENT_AGE);
        if (tmpElements.getLength() > 0) {
            Element age = (Element) tmpElements.item(0);
            this.age = Integer.parseInt(age.getTextContent());
        }
	}
	
	@Override
	protected void addNodes(Document docIn) {
        Element root = docIn.getDocumentElement();
		
		Element name = docIn.createElement(ELEMENT_NAME);
		name.setTextContent(this.name);
		root.appendChild(name);
		
		Element age = docIn.createElement(ELEMENT_AGE);
		age.setTextContent(Integer.toString(this.age));
		root.appendChild(age);
	}
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
