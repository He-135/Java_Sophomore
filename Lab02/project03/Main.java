package project23;

public class Main {

	public static void main(String[] args) {
		System.out.println("Round\t\tThreadA\t\t\t\tThreadB");
		System.out.println("\tTime\tSelection    Points\t  Time\tSelection    Points");
		
		new Thread(new ClientA(7777, 8888)).start();
		new Thread(new ClientB(8080)).start();
		new Thread(new ServerC(7777, 8888, 8080)).start();

	}

}
