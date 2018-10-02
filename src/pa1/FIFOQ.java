package pa1;

import java.util.ArrayList;
import java.util.LinkedList;


public class FIFOQ implements Queue{
	
	private LinkedList<String> queue;
	
	public FIFOQ() {
		queue = new LinkedList<String>();
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return queue.size();
	}

	@Override
	public void add(String s) throws Exception {
		// TODO Auto-generated method stub
		queue.add(s);
		
	}

	@Override
	public String returnMax() {
		// TODO Auto-generated method stub
		return queue.getFirst();
	}

	@Override
	public String extractMax() {
		// TODO Auto-generated method stub
		
		return queue.removeFirst();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return queue.size() == 0;
	}
	



}
