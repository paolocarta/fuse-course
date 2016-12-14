package lv.jbossfuse.course.flow.services.monthlyreceipt;

import javax.jws.WebService;

import lv.jbossfuse.course.flow.services.monthlyreceipt.model.MonthlyReceipt;
import lv.jbossfuse.course.flow.services.monthlyreceipt.model.MonthlyReceipt.MonthlyReceiptBuilder;

@WebService(name = "SibNskMonthlyReceiptService", serviceName = "SibNskMonthlyReceiptService", 
	portName = "SibNskMonthlyReceiptServicePort",
	endpointInterface = "lv.jbossfuse.course.flow.services.monthlyreceipt.MonthlyReceiptService")
public class SibNskMonthlyReceiptServiceImpl implements MonthlyReceiptService {

	@Override
	public MonthlyReceipt listCurrentMonthReceipts(String userId) {
		return MonthlyReceiptBuilder.with(userId, "04-2016")
				.add("0001", 10.01)
				.add("0002", 15.2)
				.add("003", -5.2)
				.build();
	}
}
