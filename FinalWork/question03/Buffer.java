package question03;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
	private Queue<Integer>messages = new LinkedList<Integer>();
	private boolean empty = true;
	private boolean full =  false;
	private final static int maxSize = 5;
	
	public void putMessage(int mes) {
		messages.add(mes);
		empty = false;
		if(messages.size() == maxSize) {
			full = true;
		}
	}
	
	public int getMessage() {
		int res = messages.remove();
		full = false;
		if (messages.isEmpty()) {
			empty = true;
		}
		return res;
	}

	
	public boolean isEmpty() {
		return empty;
	}

	
	public boolean isFull() {
		return full;
	}
	public int getSize() {
		return messages.size();
	}
}
