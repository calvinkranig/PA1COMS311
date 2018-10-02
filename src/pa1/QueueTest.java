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

}
