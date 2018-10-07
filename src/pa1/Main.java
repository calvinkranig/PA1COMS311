package pa1;

public class Main {

	
	private static final String outputfile = "test.txt";
	
	public static void main(String args[]){
		WikiCrawler c = new WikiCrawler("/wiki/Physics", 200, new String[0], outputfile);
		c.crawl(false);
		
		
		
		
		
	}
}
