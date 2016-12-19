package lv.jbossfuse.course.flow.services.phonebook;

public enum Territory {

	SIBNSK("SIB.NSK"),
	SIBKEM("SIB.KEM"),
	SIBKRN("SIB.KRN");
	
	String code;
	
	private Territory(String code) {
		this.code = code;
	}
	
	public String code() {
		return code;
	}
}
