package lv.jbossfuse.course.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface MyCourseEndpoint {
	
	@WebMethod
	@WebResult String getHelloWorldAsString(@WebParam(name = "name") String name);
}
