package project2;

public class TestTriangle {

	public static void main(String[] args) {
		try {
			Triangle triangle1 = new Triangle(1, 1.5, 1);
			triangle1.setColor("yellow");
			triangle1.setFilled(true);
			System.out.println(triangle1.toString() + " " + triangle1.getArea() + " "
					+ triangle1.getPerimeter() + " " + triangle1.getColor() + " " + triangle1.isFilled());
			Triangle triangle2 = new Triangle(-1, 0, 1);
		} catch (IllegalSideException e) {
			
			System.out.println(e.getMessage());
		}
		

	}

}
