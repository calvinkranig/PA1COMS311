package pa1;

/**
 * 
 * @author ckranig, ans66
 *Class for representing a Priority Queue.
 */
public class PriorityQ {
	
	private Entry[] heapArray;
	private int heapSize;
	
	/**
	 * Creates an empty priority queue.
	 */
	public PriorityQ() {
		heapArray = new Entry[201]; // pretty sure the max # of strings is 200
		heapSize = 0; // new priQ has no size
	}
	
	
	 /**
	 * @return size of the heap
	 */
	public int getSize(){
			return heapSize;
		}
	
	/**
	 * @return if heap is empty
	 */
	public boolean isEmpty(){
		return heapSize == 0;
	}
	
	/**
	 * Adds a String s with priority p to the priority queue.
	 * @param s String to be added to the queue.
	 * @param p Priority of the added String.
	 */
	public void add(String s, int p) {
		Entry e = new Entry(s, p); // encapsulate the string and priority into an Entry
		
		int position = ++heapSize; //start position at the end of the heap
		heapArray[position] = e; // put the new entry at the end of the heap
		
		while(position != 1 && p > heapArray[position/2].getPriority()) { // while the position isn't 1 and the priority of the new entry is greater than its parent
			heapArray[position] = heapArray[position/2]; // move the parent down the heap
			position /= 2; // the position moves to the parent
		}
		heapArray[position] = e; // assign the new entry at the index of the final position
	}
	
	/**
	 * Returns a String whose priority is maximum.
	 * @return String with max priority.
	 */
	public String returnMax() {
		return heapArray[1].getAddress(); // highest priority is at the top of the heap
	}
	
	/**
	 * Returns a String whose priority is maximum and removes it from the priority queue.
	 * @return Maximum, extracted String.
	 */
	public String extractMax() {
		return null;
	}
	
	/**
	 * Removes the element from the priority queue whose array index is i.
	 * @param i Index of element in array to be removed.
	 */
	public void remove(int i) {
		
	}
	
	/**
	 * Decrements the priority of the ith element by k.
	 * @param i Element whose priority is to be decremented.
	 * @param k Amount of decrease for the ith element.
	 */
	public void decrementPriority(int i, int k) {
		
	}
	
	/**
	 * Returns an array B with the following property: B[i] = key(A[i]) for all i in the array A used to implement the priority queue.
	 * @return Priority Array of the priority queue.
	 */
	public int[] priorityArray() {
		return null;
	}
	
	/**
	 * Helper method used for swapping entries
	 * @param e1 First Entry to swap
	 * @param e2 Second Entry to swap
	 */
	private static void swap(Entry e1, Entry e2) {
		Entry temp = e1;
		e1 = e2;
		e2 = temp;
	}
	
	/**
	 * Private Class to encapsulate the address string and int priority into an Entry
	 * @author Alexander Stephens
	 *
	 */
	private class Entry{
		private String address;
		private int priority;
		
		protected Entry(String address, int priority) {
			this.address = address;
			this.priority = priority;
		}
		
		private int getPriority() {
			return priority;
		}
		
		private String getAddress() {
			return address;
		}
	}
}
