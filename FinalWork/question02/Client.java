package question02;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		Socket socket = null;
		OutputStream os = null;
		try {
			socket = new Socket("127.0.0.1", 8000);
			os = socket.getOutputStream();
			os.write("Hello World!\n".getBytes());
			socket.shutdownOutput();
		} catch (UnknownHostException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
	}

}
