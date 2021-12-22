package project23;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;

public class ClientA extends Thread {//UDP
	private int timeA;
	DatagramSocket socket = null;	
	DatagramSocket socketReceive = null;
	// 发送数据目的地的端口
	private int toPort;
	private int fromPort;
	String[] acts = {"rock", "paper", "sciss"};
	
	public static void main(String[] args) {
		
	}

	public ClientA(int toPort, int fromPort) {
		this.toPort = toPort;
		this.fromPort = fromPort;
		try {
			socketReceive = new DatagramSocket(fromPort);
		} catch (SocketException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	@Override
	public void run() {		
		for (int i = 0; i < 3; i++) {
			
			
			try {
				socket = new DatagramSocket();
		        InetAddress inet = InetAddress.getLocalHost();
				timeA = new Random().nextInt(1000);
				Thread.sleep(timeA);
				String msgString = Integer.toString(timeA);
				byte[] buffer = msgString.getBytes();
				DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length, inet, toPort);
				socket.send(packet);
				//随机石头、剪刀、布
				buffer = acts[new Random().nextInt(3)].getBytes();
				packet = new DatagramPacket(buffer, 0, buffer.length, inet, toPort);
				socket.send(packet);
				//接收回复信息
				buffer = new byte[1024];
				packet = new DatagramPacket(buffer, 0, buffer.length);
				socketReceive.receive(packet);
				String msg = new String(packet.getData(), 0, packet.getLength());
				
				
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}// 随机睡0~1000ms（0~1s）
			catch (SocketException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (UnknownHostException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
							
		}
		if(socket != null) {
			socket.close();
		}
		if(socketReceive != null) {
			socketReceive.close();
		}
	}
}
