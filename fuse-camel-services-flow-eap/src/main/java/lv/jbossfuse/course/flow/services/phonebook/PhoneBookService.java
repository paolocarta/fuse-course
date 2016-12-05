package lv.jbossfuse.course.flow.services.phonebook;
	
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/phonebook")
public class PhoneBookService {
	
	private PhoneBookRepository repository = new PhoneBookRepository();
	
	@GET
	@Path("/territory/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTerritoryCode(@PathParam("id") String id) {
		try {
			PhoneBookRecord record = repository.get(id);
			return Response.ok()
					.entity(record)
					.build();
		}
		catch (PhoneBookRecordNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND)
					.build();
		}
	}
}
