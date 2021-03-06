package project2;

public class IllegalSideException extends Exception{
	private double side1;
	private double side2;
	private double side3;
	
	public IllegalSideException() {
		System.out.println("Illegal Sides");
	}

	
	public IllegalSideException(double side1, double side2, double side3) {
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
	}
	
	@Override
	public String getMessage() {
		return "Illegal Sides: side1: " + side1 +", side2: "+ side2 +", side3: " + side3;		
	}
	
	
	

}
