package project23;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerC extends Thread {

		
	DatagramSocket socket=null;//����д����
	DatagramSocket socketReceive=null;//���ڽ�������
	
	Socket socketTCP = null;
	ServerSocket serverSocket = null;//���ڽ���TCP����
    //���շ��Լ����ڵĶ˿�
    private int fromPort;	
	private int toPort;
	private int port;
	
	private int pointA;	
	private int pointB;
	private int totalA = 0;
	private int totalB = 0;
	
	private String timeA;
	private String timeB;
	
	private String selectionA;
	private String selectionB;
	
	
	ByteArrayOutputStream baos = null;
	InputStream is = null;
	OutputStream os = null;
	
	public ServerC(int fromPort, int toPort, int port) {
			this.fromPort = fromPort;
			this.toPort = toPort;
			this.port = port;
			try {
				socketReceive=new DatagramSocket(fromPort);
				serverSocket = new ServerSocket(port);
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
		
	        try {
	        	//����ClientA(UDP)
	        	byte[] buffer = new byte[1024 * 8];
	        	DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);
				//����sleepʱ��
	        	socketReceive.receive(packet);
				timeA = new String(packet.getData(), 0, packet.getLength());
				
				//����ʯͷ...
				socketReceive.receive(packet);
				selectionA = new String(packet.getData(), 0, packet.getLength());
				
				//���ͻء����յ���
				socket = new DatagramSocket();
		        InetAddress inet = InetAddress.getLocalHost();
		        String reString = "���յ�";
		        buffer = reString.getBytes();
				packet = new DatagramPacket(buffer, 0, buffer.length, inet, toPort);
				socket.send(packet);
				
				//����ClientB(TCP)
				socketTCP = serverSocket.accept();
	            is= socketTCP.getInputStream();
	            baos = new ByteArrayOutputStream();
	            int len=0;
	            buffer = new byte[1024];
	            while ((len=is.read(buffer))!=-1){
	                baos.write(buffer,0,len);
	            }
	            timeB = baos.toString();
	            	            
	            socketTCP = serverSocket.accept();
	            is= socketTCP.getInputStream();
	            baos = new ByteArrayOutputStream();
	            len = 0;
	            buffer = new byte[1024];
	            while ((len=is.read(buffer))!=-1){
	                baos.write(buffer,0,len);
	            }
	            selectionB = baos.toString();	           
	            
	            os = socketTCP.getOutputStream();
	            os.write("�ѽ���".getBytes());
	            socketTCP.shutdownOutput();
	            
	            
	            //�Ƚ� �÷�
	            if (selectionA.equals(selectionB)) {
					pointA = 1;
					pointB = 1;
					totalA += 1;
					totalB += 1;
				}
	            else if (selectionA.equals("rock") && selectionB.equals("paper")) {
					pointA = 0;
					pointB = 2;
					totalA += 0;
					totalB += 2;
				}
	            else if (selectionA.equals("rock") && selectionB.equals("sciss")) {
					pointA = 2;
					pointB = 0;
					totalA += 2;
					totalB += 0;
				}
	            else if (selectionA.equals("paper") && selectionB.equals("sciss")) {
					pointA = 0;
					pointB = 2;
					totalA += 0;
					totalB += 2;
				}
	            else if (selectionA.equals("paper") && selectionB.equals("rock")) {
					pointA = 2;
					pointB = 0;
					totalA += 2;
					totalB += 0;
				}
	            else if (selectionA.equals("sciss") && selectionB.equals("rock")) {
					pointA = 0;
					pointB = 2;
					totalA += 0;
					totalB += 2;
				}
	            else if (selectionA.equals("sciss") && selectionB.equals("paper")) {
					pointA = 2;
					pointB = 0;
					totalA += 2;
					totalB += 0;
				}
	            
	            System.out.println((i + 1) + "\t" + timeA + "\t" + selectionA + "\t     " + pointA + "\t\t  " + timeB + "\t"
						+ selectionB + "\t     " + pointB);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
		System.out.println();
		System.out.println("Total points: A: " + totalA + "\tB: " + totalB);
		if (totalA > totalB) {
			System.out.println("Winner: A!!");
		}
		else if (totalA < totalB) {
			System.out.println("Winner: B!!");
		}
		else {
			System.out.println("Winner: None");
		}
		
		
        if(socket != null) {
			socket.close();
		}
        if (serverSocket != null) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
        if (socketTCP != null) {
        	try {
				socketTCP.close();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
        if (is != null) {
        	try {
				is.close();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
        if (baos != null) {
        	try {
				baos.close();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
        if (os != null) {
			try {
				os.close();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
        }
	}
}
