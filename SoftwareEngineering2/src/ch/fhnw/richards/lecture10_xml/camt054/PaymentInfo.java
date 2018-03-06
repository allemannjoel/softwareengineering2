package ch.fhnw.richards.lecture10_xml.camt054;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.logging.Logger;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

/**
 * Imported from ESR payment info (camt.054 format). In the element
 * <Document><BkToCstmrDbtCdrNtfctn><Ntfctn><Ntry><NtryDtls> each payment is
 * contained in a subelement <TxDtls>
 */
public class PaymentInfo {
	private String esrNumber; // <RmtInf><Strd><CdtrRefInf><Ref>
	private boolean isReject; // <RmtInf><Strd><AddtlRmtInf>
	private BigDecimal amount; // <Amt>

	public PaymentInfo(Node transaction) throws Exception {
		// Find the Amount and RemittanceInfo nodes (direct children)
		NodeList tmpList = transaction.getChildNodes();
		Node amountNode = null;
		Node remInfNode = null;
		for (int i = 0; i < tmpList.getLength(); i++) {
			Node n = tmpList.item(i);
			if (n.getNodeName().equals(Camt054.Amount))
				amountNode = n;
			if (n.getNodeName().equals(Camt054.RemittanceInfo))
				remInfNode = n;
		}
		assert (amountNode != null & remInfNode != null);

		DecimalFormat df = new DecimalFormat();
		df.setParseBigDecimal(true);
		amount = (BigDecimal) df.parse(amountNode.getTextContent());

		Node stNode = ((Element) remInfNode).getElementsByTagName(Camt054.Structured).item(0);
		tmpList = stNode.getChildNodes();
		Node additionalInfoNode = null;
		Node creditorRefInfoNode = null;
		for (int i = 0; i < tmpList.getLength(); i++) {
			Node n = tmpList.item(i);
			if (n.getNodeName().equals(Camt054.AdditionalRemittanceInfo))
				additionalInfoNode = n;
			if (n.getNodeName().equals(Camt054.CreditorReferenceInfo))
				creditorRefInfoNode = n;
		}
		assert (additionalInfoNode != null & creditorRefInfoNode != null);

		String rejectionInfo = additionalInfoNode.getTextContent();
		isReject = !rejectionInfo.contains("0");

		Node esrNode = ((Element) creditorRefInfoNode).getElementsByTagName(Camt054.Reference).item(0);
		esrNumber = esrNode.getTextContent();

		parseEsrNumber(esrNumber);
		
		Logger logger = Logger.getLogger("");
		logger.info("--> ESR " + esrNumber + ", Payment " + amount);
	}

	private void parseEsrNumber(String esrNumber) throws Exception {
		assert (esrNumber.length() == 27);
		String controlDigit = esrNumber.substring(26);

		if (!ControlDigit(esrNumber.substring(0, 26)).equals(controlDigit)) {
			throw new Exception("Invalid ESR control digit");
		}
	}

	private String ControlDigit(String idigits) {

		// The 10-place vector
		int[] arrayB = new int[10];
		arrayB[0] = 0;
		arrayB[1] = 9;
		arrayB[2] = 4;
		arrayB[3] = 6;
		arrayB[4] = 8;
		arrayB[5] = 2;
		arrayB[6] = 7;
		arrayB[7] = 1;
		arrayB[8] = 3;
		arrayB[9] = 5;

		int carry = 0;
		String digitChar = "";
		int digit = 0;
		for (int i = 0; i < idigits.length(); i++) {
			digitChar = idigits.substring(i, i + 1);
			digit = Integer.parseInt(digitChar);
			carry = arrayB[((carry + digit) % 10)];
		}
		carry = (10 - carry) % 10;
		return Integer.toString(carry);
	}

}
