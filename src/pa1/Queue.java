package pa1;

/**
 * @author ckranig, ans66
 *	
 *	Interface to represent any queue used for the crawler
 */
public interface Queue {
	public int getSize();
	public void add(String s, int i);
	public String returnMax();
	public boolean isEmpty();
	public String extractMax();
	
}
