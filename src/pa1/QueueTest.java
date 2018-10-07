package pa1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QueueTest {

	@Test
	void addTest() {
		
		PriorityQ queue = new PriorityQ();
		queue.add("Hello World", 2);
		assertEquals(queue.returnMax(), "Hello World");
		queue.add("2", 3);
		assertEquals(queue.returnMax(), "2");
		
	}
	
	@Test
	void addTest2(){
		PriorityQ queue = new PriorityQ();
		queue.add("9", 9);
		queue.add("3", 3);
		queue.add("5", 5);
		queue.add("8", 8);
		queue.add("11", 11);
		queue.add("1", 1);
		assertEquals(queue.queueString(), "11, 9, 8, 5, 3, 1, ");
		
	}
	
	@Test
	void extractTest() { // queueString uses extractMax to create the string
		PriorityQ queue = new PriorityQ();
		queue.add("1", 1);
		queue.add("3", 3);
		queue.add("2", 2);
		assertEquals(queue.queueString(), "3, 2, 1, ");
	}
	
	@Test
	void removeTest() {
		PriorityQ queue = new PriorityQ();
		queue.add("9", 9);
		queue.add("3", 3);
		queue.add("5", 5);
		queue.add("8", 8);
		queue.add("11", 11);
		queue.add("1", 1);
		queue.remove(3);
		assertEquals(queue.queueString(), "11, 9, 8, 3, 1, ");
		
	}
	
	@Test
	void priorityArrayTest() {
		
	}

}
