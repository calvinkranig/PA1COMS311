package pa1;

public class Main {

	
	private static final String outputfile = "C:\\Users\\pols_ckranig\\Documents\\test";
	
	public static void main(String args[]){
		WikiCrawler c = new WikiCrawler("/wiki/A.html", 200, new String[0], outputfile);
		c.crawl(false);
		
		
		
		
		
	}
}
