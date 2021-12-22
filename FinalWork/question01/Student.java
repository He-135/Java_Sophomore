package question01;


public class Student extends Person {
	public Student(String name) {
		super(name);
		
	}

	private static final String status = "sophomore";
	
	@Override
	public String toString() {
		return ("class: Student; name: " + this.getName());
	}
}
