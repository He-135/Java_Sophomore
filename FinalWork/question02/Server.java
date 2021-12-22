package question02;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
	private InputStream is = null;
	private ByteArrayOutputStream baos = null;
	@Override
	public void run() {
		try {
			synchronized (syncObject) {
				is = this.socket.getInputStream();
				baos = new ByteArrayOutputStream();
	            int len=0;
	            byte[] buffer = new byte[1024];
	            while ((len=is.read(buffer))!=-1){
	                baos.write(buffer,0,len);
	            }
	            String str = baos.toString();
	            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
						new FileOutputStream("string.dat", true));
	            byte[] buf = str.getBytes();
	            objectOutputStream.writeObject(buf.toString());
				objectOutputStream.close();
				System.out.print("接收：" + baos.toString());
				System.out.println("已写入！");
			}
            
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
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