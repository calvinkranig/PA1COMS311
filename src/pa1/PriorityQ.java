package pa1;

import java.util.ArrayList;

/**
 * 
 * @author ckranig, ans66
 *Class for representing a Priority Queue. All methods with an index argument need to keep in mind that the heapArray starts at 1.
 */
public class PriorityQ implements Queue{
	
	private ArrayList<Entry> heapArray;
	private int heapSize;
	
	/**
	 * Creates an empty priority queue.
	 */
	public PriorityQ() {
		heapArray = new ArrayList<Entry>(); // pretty sure the max # of strings is 200
		heapArray.add(null);
		heapSize = 0; // new priQ has no size
	}
	
	
	 /**
	 * Accessor for getting the size of the heap
	 * @return size of the heap
	 */
	public int getSize(){
			return heapSize;
		}
	

	
	/**
	 * Adds a String s with priority p to the priority queue.
	 * @param s String to be added to the queue.
	 * @param p Priority of the added String.
	 */
	public void add(String s, int p) {
		Entry e = new Entry(s, p); // encapsulate the string and priority into an Entry
		
		int position = ++heapSize; //start position at the end of the heap
		heapArray.add(position, e); // put the new entry at the end of the heap
		
		while(position != 1 && p > heapArray.get(position / 2).getPriority()) { // while the position isn't 1 and the priority of the new entry is greater than its parent
			heapArray.set(position, heapArray.get(position / 2)); // move the parent down the heap
			position /= 2; // the position moves to the parent
		}
		heapArray.set(position, e); // assign the new entry at the index of the final position
	}
	
	/**
	 * Returns a String whose priority is maximum.
	 * @return String with max priority.
	 */
	public String returnMax() {
		if(this.isEmpty()){
			return null;
		}
		return heapArray.get(1).getAddress(); // highest priority is at the top of the heap
	}
	/*
	public String returnMaxDocument(){
		if(this.isEmpty()){
			return null;
		}
		return heapArray.get(1).getDocument();
	}*/
	
	/**
	 * Returns a String whose priority is maximum and removes it from the priority queue.
	 * @return Maximum, extracted String.
	 */
	public String extractMax() {	
		if(this.isEmpty()){
			return null;
		}
		String max = this.returnMax(); // save string for the return
		swap(1, this.getSize()); // swap the first and last elements in the heap
		heapSize--; // decrement heap
		
		this.heapify(1); // heapify top element of heap
		
		return max; // return old max
	}
	
	/**
	 * Removes the element from the priority queue whose array index is i.
	 * @param i Index of element in array to be removed.
	 */
	public void remove(int i) {
		
		swap(i, this.getSize()); // swap removed element with last element
		heapSize--; // decrement heap
		this.heapify(i); // heapify swapped element
	}
	
	/**
	 * Decrements the priority of the ith element by k.
	 * @param i Element whose priority is to be decremented.
	 * @param k Amount of decrease for the ith element.
	 */
	public void decrementPriority(int i, int k) {
		//String s = heapArray.get(i).getAddress(); // copy address of entry to be decremented
		//int p = heapArray.get(i).getPriority() - k; // new priority
	
		//remove(i); // remove entry with old priority
		//add(s, p); // add same entry with updated priority
		
		heapArray.get(i).priority -= k; // decrement priority
		heapify(i); // heapify with new priority
		
	}
	
	/**
	 * Returns an array B with the following property: B[i] = key(A[i]) for all i in the array A used to implement the priority queue.
	 * @return Priority Array of the priority queue.
	 */
	public int[] priorityArray() {
		
		int[] priArr = new int[heapSize + 1]; // create array for the priorities
		for(int i = 1; i <= heapSize; ++i) { // for all Entries in the heapArray, enter their priorities into the priArr
			priArr[i] = heapArray.get(i).getPriority();
		}
		return priArr;
	}
	
	/**
	 * Returns value(A[i]), where A is the array used to represent the priority queue
	 * @param i index of Entry that holds the returned value
	 * @return value of specified Entry
	 */
	public String getValue(int i) {
		return heapArray.get(i).getAddress();
	}
	
	/**
	 * Returns key(A[i]), where A is the array used to represent the priority queue
	 * @param i index of Entry that holds the returned key
	 * @return key of specified Entry
	 */
	public int getKey(int i) {
		return heapArray.get(i).getPriority();
	}
	
	/**
	 * Returns true if and only if the queue is empty
	 * @return whether the queue is empty
	 */
	public boolean isEmpty() {
		return heapSize == 0;
	}
	
	/**
	 * Helper method to maintain heap properties after a remove.
	 * @param i Index at which to begin heapify.
	 */
	private void heapify(int i) {

		int largest = i;
		int left = 2*i;
		int right = 2*i + 1;
		
		if((left <= heapSize) && (heapArray.get(largest).getPriority() < heapArray.get(left).getPriority())) {
			largest = left;
		}
		
		if((right <= heapSize) && (heapArray.get(largest).getPriority() < heapArray.get(right).getPriority())) {
			largest = right;
		}
		
		if (largest != i) {
			swap(i, largest);
			heapify(largest);
		}
	}
	
	/**
	 * Helper method used for swapping entries
	 * @param i index of first Entry to swap
	 * @param j index of second Entry to swap
	 */
	private void swap(int i, int j) {
		Entry temp = heapArray.get(i);
		heapArray.set(i, heapArray.get(j));
		heapArray.set(j, temp);
	}
	
	/**
	 * Private Class to encapsulate the address string and int priority into an Entry
	 * @author ans66, ckranig
	 *
	 */
	protected class Entry{
		private String address;
		private int priority;
		
		private Entry(String address, int priority) {
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

	/*
	@Override
	public void add(String s,){
		System.out.println("Need a Precedence");
	}*/

	/**
	 * Helper method to create a string with all of the strings in the queue. Ordered by priority. Used for testing.
	 * @return String of comma separated string values from the queue.
	 */
	protected String queueString() {
		String s = "";
		int n = heapSize;
		for(int i = 0; i < n; ++i) {
			s += this.extractMax() + ", ";
		}
		return s;
	}





}
