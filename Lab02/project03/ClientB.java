package project23;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;

public class ClientB extends Thread{//TCP
	private int timeB;
	Socket socket = null;
	OutputStream os = null;
	InputStream is = null;
	ByteArrayOutputStream baos = null;
	// 发送数据目的地的端口
	private int Port;
	String[] acts = {"rock", "paper", "sciss"};
	
	public ClientB(int Port) {
		this.Port = Port;		
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			try {
				//发送sleep时间
				socket = new Socket("127.0.0.1", Port);
				os = socket.getOutputStream();
				timeB = new Random().nextInt(1000);
				Thread.sleep(timeB);
				String msgString = Integer.toString(timeB);
				os.write(msgString.getBytes());
				socket.shutdownOutput();
				//发送石头...
				socket = new Socket("127.0.0.1", Port);
				os = socket.getOutputStream();
				os.write(acts[new Random().nextInt(3)].getBytes());
				socket.shutdownOutput();
				//接收回复消息
				is = socket.getInputStream();
				baos = new ByteArrayOutputStream();
		        int len=0;
		        byte[] buffer = new byte[1024];
		        while ((len=is.read(buffer))!=-1){
		            baos.write(buffer,0,len);
		        }
			} catch (IOException | InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
	}
}




