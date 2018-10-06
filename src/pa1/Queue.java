package pa1;

/**
 * @author ckranig, ans66
 *
 */
public interface Queue {
	public int getSize();
	public void add(String s, int i);
	public String returnMax();
	public boolean isEmpty();
	public String extractMax();
	
}
