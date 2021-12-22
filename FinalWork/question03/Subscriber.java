package question03;

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
				System.out.println("���գ�\t" + buffer.getMessage());
				syncObject.notifyAll();
			}
			try {
				Thread.sleep(new Random().nextInt(3000));
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
