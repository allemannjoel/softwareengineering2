package ch.fhnw.richards.lecture10_xml.camt054;

import java.io.File;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Camt054_Model {
	
	PaymentsInfo paymentsInfo;

	public void importFile(File xmlFile) {
    	// Parse the XMl document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        Document xmlDocument = builder.parse(xmlFile);
	        
	        // Root element of the document
	        Element root = xmlDocument.getDocumentElement();
	        assert(root.getTagName().equals(Camt054.Root));
	        
	        // Bank-to-customer notification
	        NodeList tmpList = root.getElementsByTagName(Camt054.BankToCustomerDebitCreditNotification);
	        assert(tmpList.getLength() == 1);
	        Element bankToCustomerDebitCreditNotification = (Element) tmpList.item(0);
	
			paymentsInfo = new PaymentsInfo(bankToCustomerDebitCreditNotification);
			
			Logger.getLogger("").info("Successfully processed " + paymentsInfo.payments.size() + " payments");
        } catch (Exception e) {
        	System.out.println(e);
        }
	}
}
