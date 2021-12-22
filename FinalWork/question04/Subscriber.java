package question04;

import java.util.Random;

public class Subscriber implements Runnable {
	Buffer buffer = null;
	SyncObject syncObject = null;

	@Override
	public void run() {

		while (true) {
			synchronized (syncObject) {
				while (buffer.isEmpty()) {
					try {
						syncObject.wait();
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}
				for(int i = 0; i < new Random().nextInt(buffer.getSize()); i++) {//�����������������					
					System.out.println("���գ�\t" + buffer.getMessage(new Random().nextInt(buffer.getSize())));
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
