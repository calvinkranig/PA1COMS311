package pa1;

import java.util.ArrayList;
import java.util.LinkedList;


/**
 * @author ckranig, ans66
 *
 */
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
	public void add(String s) {
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
		if(this.isEmpty()){
			return null;
		}
		return queue.removeFirst();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return queue.size() == 0;
	}
	



}
