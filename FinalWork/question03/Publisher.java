package question03;

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
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
				int temp = new Random().nextInt(1000);
				buffer.putMessage(temp);
				System.out.println("写入：" + temp);
				syncObject.notifyAll();
			}

			try {
				Thread.sleep(new Random().nextInt(2500));
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
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
