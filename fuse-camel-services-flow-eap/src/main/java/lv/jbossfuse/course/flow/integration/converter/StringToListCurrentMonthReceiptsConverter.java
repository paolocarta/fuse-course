package lv.jbossfuse.course.flow.integration.converter;

import org.apache.camel.Converter;

import lv.jbossfuse.course.monthlyreceiptservice.ListCurrentMonthReceipts;

@Converter
public class StringToListCurrentMonthReceiptsConverter {

	@Converter
	public ListCurrentMonthReceipts toListCurrentMonthReceipts(String id) {
		ListCurrentMonthReceipts receipts = new ListCurrentMonthReceipts();
		receipts.setUser(id);
		return receipts;
	}
}
