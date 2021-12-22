package question03;

public class Test {
	
	public static void main(String[] args) {
		SyncObject syncObject = new SyncObject();
		Buffer buffer = new Buffer();
		Publisher publisher = new Publisher();
		publisher.setBuffer(buffer);
		publisher.setSyncObject(syncObject);
		Subscriber subscriber = new Subscriber();
		subscriber.setBuffer(buffer);
		subscriber.setSyncObject(syncObject);
		new Thread(publisher).start();
		new Thread(subscriber).start();
		
	}

}

class SyncObject{}