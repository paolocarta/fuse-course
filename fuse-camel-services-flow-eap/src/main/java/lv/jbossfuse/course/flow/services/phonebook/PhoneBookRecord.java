package lv.jbossfuse.course.flow.services.phonebook;

public class PhoneBookRecord {

	private String id;
	
	private String title;
	
	private Territory territory;

	public PhoneBookRecord() {		
	}
	
	public PhoneBookRecord(String id, String title, Territory territory) {
		this.id = id;
		this.title = title;
		this.territory = territory;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Territory getTerritory() {
		return territory;
	}

	public void setTerritory(Territory territory) {
		this.territory = territory;
	}	
}
