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
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
				for(int i = 0; i < new Random().nextInt(buffer.getSize()); i++) {//接收随机个数个数据					
					System.out.println("接收：\t" + buffer.getMessage(new Random().nextInt(buffer.getSize())));
					syncObject.notifyAll();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
			}
			try {
				Thread.sleep(100);
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
