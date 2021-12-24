package question04;

import java.util.Random;

public class Publisher implements Runnable {
	Buffer buffer = null;
	SyncObject syncObject = null;

	@Override
	public void run() {
		while (true) {
			synchronized (syncObject) {
				while (buffer.isFull()) {
					try {
						syncObject.wait();
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}
				for(int i = 0; i < new Random().nextInt(5 - buffer.getSize()); i++) {//�������������				
					int temp = new Random().nextInt(1000);
					buffer.putMessage(temp);
					System.out.println("д�룺" + temp);
					syncObject.notifyAll();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			
		}

	}

	public void setBuffer(Buffer buffer) {
		this.buffer = buffer;
	}

	public void setSyncObject(SyncObject syncObject) {
		this.syncObject = syncObject;
	}

}
