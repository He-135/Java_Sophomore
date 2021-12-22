package question02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadFile {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream objectInputStream = new ObjectInputStream(
				new FileInputStream("string.dat"));
		String string = (String)objectInputStream.readObject();
		System.out.print(string);
		string = (String)objectInputStream.readObject();
		System.out.print(string);
	}

}
