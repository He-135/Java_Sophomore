package question02;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
	public static void main(String[] args) {
		SyncObject syncObject = new SyncObject();
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(8000);
			for (int i = 0; i < 5; i++) {
				socket = serverSocket.accept();
				MyThread myThread = new MyThread();
				myThread.setSyncObject(syncObject);
				myThread.setSocket(socket);
				Thread thread = new Thread(myThread);
				thread.start();
			}
			
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}


}

class SyncObject{}
class MyThread implements Runnable{
	private SyncObject syncObject = null;
	private Socket socket = null;
	@Override
	public void run() {
		try {
			synchronized (syncObject) {
				ObjectInputStream objectInputStream = new ObjectInputStream(this.socket.getInputStream());
				String string = (String)objectInputStream.readObject();
				
	            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("string.dat", true)); 	
	            objectOutputStream.writeObject(string);
				objectOutputStream.close();
				System.out.print("接收：" + string);
				System.out.println("已写入！");
				
			}
            
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	
	public void setSyncObject(SyncObject syncObject) {
		this.syncObject = syncObject;
	}


	
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

}