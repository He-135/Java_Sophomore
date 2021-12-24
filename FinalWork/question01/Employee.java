package question01;

public class Employee extends Person {
	public Employee(String name) {
		super(name);
	}
	private String office;
	private int salary;
	
	@Override
	public String toString() {
		return ("class: Employee; name: " + this.getName());
	}

}



