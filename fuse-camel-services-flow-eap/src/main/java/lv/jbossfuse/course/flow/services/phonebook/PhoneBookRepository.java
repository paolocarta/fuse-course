package lv.jbossfuse.course.flow.services.phonebook;

import java.util.HashMap;
import java.util.Map;

public class PhoneBookRepository {

	private static Map<String, PhoneBookRecord> cache = new HashMap<>();
	
	static {
		cache.put("ni10105", new PhoneBookRecord("ni10105", "Roga i kopita, inc.", Territory.SIBNSK));
		cache.put("ki10105", new PhoneBookRecord("ki10105", "Melkoprom", Territory.SIBKRN));
	}
	
	public PhoneBookRecord get(String id) throws PhoneBookRecordNotFoundException {
		PhoneBookRecord record = cache.get(id);
		if (record == null)
			throw new PhoneBookRecordNotFoundException(id);
		return record;
	}
}
