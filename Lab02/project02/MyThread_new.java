package project22;

import java.util.Random;

public class MyThread_new {

	public static void main(String[] args) {
		SyncObject syncObject = new SyncObject();
		MyThread_new myThread = new MyThread_new();
		System.out.println("Round\t\tThreadA\t\t\t\tThreadB");
		System.out.println("\tTime\tCharacter    Points\t  Time\tCharacter    Points");
		ThreadA threadA = new ThreadA();
		ThreadB threadB = new ThreadB();
		ThreadC threadC = new ThreadC();
		threadA.setThreadC(threadC);
		threadB.setThreadC(threadC);
		threadC.setThreadA(threadA);
		threadC.setThreadB(threadB);
		threadA.setSyncObject(syncObject);
		threadB.setSyncObject(syncObject);
		threadC.setSyncObject(syncObject);

		Thread thread = new Thread(threadC);
		thread.start();
		threadA.start();
		threadB.start();

	}

}

class SyncObject {
}// 同步对象

class ThreadC implements Runnable {
	SyncObject syncObject = null;
	private boolean ACreate = false;
	private boolean BCreate = false;
	private char cha;
	private char chb;
	private int timeA;
	private int timeB;
	private ThreadA threadA = null;
	private ThreadB threadB = null;
	private int totalA = 0;
	private int totalB = 0;
	private int pointA = 0;
	private int pointB = 0;

	@Override
	public void run() {
		synchronized (syncObject) {
			for (int i = 0; i < 3; i++) {

				while (!(ACreate && BCreate)) {
					try {
						syncObject.wait();
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
				if (cha > chb) {
					pointA = 2;
					pointB = 0;
					totalA += 2;
				} else if (cha < chb) {
					pointA = 0;
					pointB = 2;
					totalB += 2;
				} else {
					pointA = 1;
					pointB = 1;
					totalA += 1;
					totalB += 1;
				}
				System.out.println((i + 1) + "\t" + timeA + "\t" + cha + "\t     " + pointA + "\t\t  " + timeB + "\t"
						+ chb + "\t     " + pointB);

				ACreate = false;
				BCreate = false;
				threadA.setACreate(false);
				threadB.setBCreate(false);
				syncObject.notifyAll();
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
		}
	}

	public void setACreate(boolean aCreate) {
		ACreate = aCreate;
	}

	public void setBCreate(boolean bCreate) {
		BCreate = bCreate;
	}

	public void setCha(char cha) {
		this.cha = cha;
	}

	public void setChb(char chb) {
		this.chb = chb;
	}

	public void setTimeA(int timeA) {
		this.timeA = timeA;
	}

	public void setTimeB(int timeB) {
		this.timeB = timeB;
	}

	public void setThreadA(ThreadA threadA) {
		this.threadA = threadA;
	}

	public void setThreadB(ThreadB threadB) {
		this.threadB = threadB;
	}

	public void setSyncObject(SyncObject syncObject) {
		this.syncObject = syncObject;
	}
}

class ThreadA extends Thread {
	SyncObject syncObject = null;
	private boolean ACreate = false;
	private char cha;
	private int timeA;
	private ThreadC threadC = null;

	@Override
	public void run() {
		synchronized (syncObject) {
			for (int i = 0; i < 3; i++) {

				while (ACreate) {
					try {
						syncObject.wait();
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
				timeA = new Random().nextInt(1000);
				threadC.setTimeA(timeA);
				try {
					Thread.sleep(timeA);// 随机睡0~1000ms（0~1s）
					cha = (char) ('A' + Math.random() * ('Z' - 'A' + 1));
					threadC.setCha(cha);
					ACreate = true;
					threadC.setACreate(true);
					syncObject.notifyAll();
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}
	}

	public void setACreate(boolean aCreate) {
		ACreate = aCreate;
	}

	public void setThreadC(ThreadC threadC) {
		this.threadC = threadC;
	}

	public void setSyncObject(SyncObject syncObject) {
		this.syncObject = syncObject;
	}
}

class ThreadB extends Thread {
	SyncObject syncObject = null;
	private boolean BCreate = false;
	private char chb;
	private int timeB;
	private ThreadC threadC = null;

	@Override
	public void run() {
		synchronized (syncObject) {
			for (int i = 0; i < 3; i++) {

				while (BCreate) {
					try {
						syncObject.wait();
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
				timeB = new Random().nextInt(1000);
				threadC.setTimeB(timeB);
				try {
					Thread.sleep(timeB);// 随机睡0~1000ms（0~1s）
					chb = (char) ('A' + Math.random() * ('Z' - 'A' + 1));
					threadC.setChb(chb);
					BCreate = true;
					threadC.setBCreate(true);
					syncObject.notifyAll();
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}
	}

	public void setBCreate(boolean bCreate) {
		BCreate = bCreate;
	}

	public void setThreadC(ThreadC threadC) {
		this.threadC = threadC;
	}

	public void setSyncObject(SyncObject syncObject) {
		this.syncObject = syncObject;
	}
}
