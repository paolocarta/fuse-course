package lv.jbossfuse.course.flow.services.monthlyreceipt.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "monthly-receipt")
public class MonthlyReceipt {

	private String userId;
	
	private String monthCode;
	
	private List<ReceiptItem> receipts;

	@XmlElement(name = "service-receipts")
	public List<ReceiptItem> getReceipts() {
		return receipts;
	}
	
	public void setReceipts(List<ReceiptItem> receipts) {
		this.receipts = receipts;
	}	
	
	@XmlElement(name = "user")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@XmlElement(name = "month-code")
	public String getMonthCode() {
		return monthCode;
	}

	public void setMonthCode(String monthCode) {
		this.monthCode = monthCode;
	}

	public static class MonthlyReceiptBuilder {
		private List<ReceiptItem> receipts = new ArrayList<>();
		
		private String userId;
		
		private String monthCode;
		
		private MonthlyReceiptBuilder(String userId, String monthCode) {
			this.userId = userId;
			this.monthCode = monthCode;
		}
		
		public static MonthlyReceiptBuilder with(String userId, String monthCode) {
			return new MonthlyReceiptBuilder(userId, monthCode);
		}
		
		public MonthlyReceiptBuilder add(String serviceCode, double balance) {
			receipts.add(new ReceiptItem(serviceCode, balance));
			return this;
		}
		
		public MonthlyReceipt build() {
			MonthlyReceipt receipt = new MonthlyReceipt();
			receipt.receipts = receipts;
			receipt.userId = userId;
			receipt.monthCode = monthCode;
			return receipt;
		}
	}
}
