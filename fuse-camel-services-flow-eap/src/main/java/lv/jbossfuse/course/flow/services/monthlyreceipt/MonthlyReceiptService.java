package lv.jbossfuse.course.flow.services.monthlyreceipt;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import lv.jbossfuse.course.flow.services.monthlyreceipt.model.MonthlyReceipt;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface MonthlyReceiptService {

	@WebMethod
	public @WebResult(name = "monthly-receipt") MonthlyReceipt listCurrentMonthReceipts(
			@WebParam(name = "user") String userId);
}
