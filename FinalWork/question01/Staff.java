package question01;


public class Staff extends Employee {
	public Staff(String name) {
		super(name);
	}

	private String title;
	
	@Override
	public String toString() {
		return ("class: Staff; name: " + this.getName());
	}

}
