package ${groupId}.restlet;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "resource")
public class MyResource {
	
	private String resource;
	
	private int age;

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
