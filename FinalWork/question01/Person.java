package question01;


public class Person {
	private String name;
	private String address;
	private int phoneNum;
	private String email;
	
	public Person(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return ("class: Person; name: " + this.name);
	}

}
