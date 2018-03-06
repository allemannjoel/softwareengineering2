package ch.fhnw.richards.lecture10_xml.camt054;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This class extracts information from a camt.054 (v4) XML document. All
 * elements are inside of <Document><BkToCstmrDbtCdrNtfctn>.
 * 
 * The constructor of this class receives the <BkToCstmrDbtCdrNtfctn> element
 * from the document. All elements referenced below are inside of this element.
 */
public class PaymentsInfo {
	Logger logger = Logger.getLogger("");	
	
	boolean isTest; // from <GrpHdr><AddtlInf> - may indicate TEST data

	// Elements inside of notification <Ntfctn>
	String ID; // from <Id>
	LocalDateTime Timestamp; // from <CreDtTm>
	String AccountIBAN; // credited account, from <Acct><Id>
	String AccountOwner; // account owner, from <Acct><Ownr>

	// Elements inside of <Ntfctn><Ntry>
	String Reference; // <NtryRef>
	BigDecimal Amount; // <Amt>
	boolean isCredit; // <CdtDbtInd> contains "CRDT"
	LocalDate bookingDate; // <BookgDt><Dt>
	LocalDate valueDate; // <ValDt><Dt>
	String additionalInfo; // <AdtlNtryInf>
	
	// Individual entries in: <Ntfctn><Ntry><NtryDtls>
	int numTransactions; // <Btch><NbOfTxs>
	ArrayList<PaymentInfo> payments = new ArrayList<>(); // <TxDtls> one per transaction

	/**
	 * We construct a PaymentsInfo object from the bankToCustomerDebitCreditNotification
	 */
	public PaymentsInfo(Element bankToCustomerDebitCreditNotification) throws Exception {
		// Handle group header
		NodeList tmpList = bankToCustomerDebitCreditNotification.getElementsByTagName(Camt054.GroupHeader);
		assert (tmpList.getLength() == 1);
		Element groupHeader = (Element) tmpList.item(0);
		groupHeaderInfo(groupHeader);

		// Handle notification info
		tmpList = bankToCustomerDebitCreditNotification.getElementsByTagName(Camt054.Notification);
		assert (tmpList.getLength() == 1);
		Element notification = (Element) tmpList.item(0);
		notificationInfo(notification);
		
		// Process individual payments
		tmpList = notification.getElementsByTagName(Camt054.EntryDetails);
		assert (tmpList.getLength() == 1);
		Element entryDetails = (Element) tmpList.item(0);
		individualTransactions(entryDetails);		
	}

	/**
	 * Currently, the only interesting information is whether or not this is
	 * test data. This is test data iff AddtInf contains the string TEST. If
	 * the element is missing entirely, the data is productive (not test)
	 */
	private void groupHeaderInfo(Element groupHeader) {
		isTest = false;
		NodeList tmpList = groupHeader.getElementsByTagName(Camt054.AdditionalInfo);		
		if (tmpList.getLength() == 1) {
			Element additionalInfo = (Element) tmpList.item(0);
			String value = additionalInfo.getTextContent();
			isTest = value.contains("TEST");
		}
		logger.info("Group header: is test data? " + isTest);
	}

	/**
	 * General information about the overall transaction
	 * @throws ParseException 
	 * @throws DOMException 
	 */
	private void notificationInfo(Element notification) throws DOMException, ParseException {
		// Find the ID, CreationDateTime, and Account nodes (direct children)
		NodeList tmpList = notification.getChildNodes();
		Node idNode = null;
		Node creationNode = null;
		Node accountNode = null;
		Node entryNode = null;
		for (int i = 0; i < tmpList.getLength(); i++) {
			Node n = tmpList.item(i);
			if (n.getNodeName().equals(Camt054.ID)) idNode = n;
			if (n.getNodeName().equals(Camt054.CreationDateTime)) creationNode = n;
			if (n.getNodeName().equals(Camt054.Account)) accountNode = n;
			if (n.getNodeName().equals(Camt054.Entry)) entryNode = n;
		}
		assert(idNode != null & creationNode != null & accountNode != null & entryNode != null);
		
		ID = idNode.getTextContent();
		Timestamp = LocalDateTime.parse(creationNode.getTextContent());
		Node acctIdNode =((Element) accountNode).getElementsByTagName(Camt054.ID).item(0);
		AccountIBAN = ((Element) acctIdNode).getElementsByTagName(Camt054.IBAN).item(0).getTextContent();
		Node acctOwnerNode = ((Element) accountNode).getElementsByTagName(Camt054.Owner).item(0);
		AccountOwner = ((Element) acctOwnerNode).getElementsByTagName(Camt054.Name).item(0).getTextContent();
		
		logger.info("ID = " + ID + ", Timestamp = " + Timestamp);
		logger.info("Account IBAN = " + AccountIBAN + ", Owner = " + AccountOwner);
		
		// Elements in the entryNode
		tmpList = entryNode.getChildNodes();
		Node refNode = null;
		Node amountNode = null;
		Node creditNode = null;
		Node bookingDateNode = null;
		Node valueDateNode = null;
		Node additionalInfoNode = null;
		Node entryDetailsNode = null;
		for (int i = 0; i < tmpList.getLength(); i++) {
			Node n = tmpList.item(i);
			if (n.getNodeName().equals(Camt054.EntryReference)) refNode = n;
			if (n.getNodeName().equals(Camt054.Amount)) amountNode = n;
			if (n.getNodeName().equals(Camt054.CreditDebitIndicator)) creditNode = n;
			if (n.getNodeName().equals(Camt054.BookingDate)) bookingDateNode = n;
			if (n.getNodeName().equals(Camt054.ValueDate)) valueDateNode = n;
			if (n.getNodeName().equals(Camt054.AdditionalEntryInfo)) additionalInfoNode = n;
			if (n.getNodeName().equals(Camt054.EntryDetails)) entryDetailsNode = n;
		}
		assert(refNode != null & amountNode != null & creditNode != null
				& bookingDateNode != null & valueDateNode != null
				& additionalInfoNode != null & entryDetailsNode != null);
		
		Reference = refNode.getTextContent();
		isCredit = creditNode.getTextContent().contains("CRDT");
		bookingDate = LocalDate.parse(((Element) bookingDateNode).getElementsByTagName(Camt054.Date).item(0).getTextContent());
		valueDate = LocalDate.parse(((Element) valueDateNode).getElementsByTagName(Camt054.Date).item(0).getTextContent());
		additionalInfo = additionalInfoNode.getTextContent();
		
		logger.info("Reference = '" + Reference + "', Additional info = '" + additionalInfo + "'");
		logger.info("Date: Booking = " + bookingDate + ", value = " + valueDate);
		
		DecimalFormat df = new DecimalFormat();
		df.setParseBigDecimal(true);
		Amount = (BigDecimal) df.parse(amountNode.getTextContent());
		
		logger.info("Total amount = " + Amount);
	}

	/**
	 * Parse all individual transactions. One note also gives us the expected number of transactions, which
	 * we will check at the end.
	 * @throws Exception 
	 */
	private void individualTransactions(Element entryDetails) throws Exception {
		NodeList childNodes = entryDetails.getChildNodes();
		int expectedNumTransactions = -1;
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node childNode = childNodes.item(i);
			
			if (childNode.getNodeName().equals(Camt054.Batch)) {
				// Expected transaction count
				Node nodeNumTransactions = ((Element) childNode).getElementsByTagName(Camt054.NumberOfTransactions).item(0);
				expectedNumTransactions = Integer.parseInt(nodeNumTransactions.getTextContent());
			} else if (childNode.getNodeName().equals(Camt054.TransactionDetails)) {
				payments.add(new PaymentInfo(childNode));
			}
		}
		if (payments.size() != expectedNumTransactions) throw new Exception("Expected " + expectedNumTransactions + " payments, but actually have " + payments.size());
	}
}
