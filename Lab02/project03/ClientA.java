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
	// ��������Ŀ�ĵصĶ˿�
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
			// TODO �Զ����ɵ� catch ��
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
				//���ʯͷ����������
				buffer = acts[new Random().nextInt(3)].getBytes();
				packet = new DatagramPacket(buffer, 0, buffer.length, inet, toPort);
				socket.send(packet);
				//���ջظ���Ϣ
				buffer = new byte[1024];
				packet = new DatagramPacket(buffer, 0, buffer.length);
				socketReceive.receive(packet);
				String msg = new String(packet.getData(), 0, packet.getLength());
				
				
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}// ���˯0~1000ms��0~1s��
			catch (SocketException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (UnknownHostException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
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
