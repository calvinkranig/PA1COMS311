package pa1;

public interface Queue {
	public int getSize();
	public void add(String s) throws Exception;
	public String returnMax();
	public boolean isEmpty();
	public String extractMax();
	
}
