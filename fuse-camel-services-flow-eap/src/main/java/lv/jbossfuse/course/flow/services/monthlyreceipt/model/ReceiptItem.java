package lv.jbossfuse.course.flow.services.monthlyreceipt.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "service-receipt")
public class ReceiptItem {

	private String serviceCode;
	
	private double balance;
	
	public ReceiptItem() {		
	}

	public ReceiptItem(String serviceCode, double balance) {
		this.serviceCode = serviceCode;
		this.balance = balance;
	}

	@XmlAttribute(name = "service")
	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	@XmlValue
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}	
}
