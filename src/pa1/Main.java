package pa1;



public class Main {

	
	private static final String outputfile = "test.txt";
	
	public static void main(String args[]){
		String[] topics = {"Calvin", "Bill"};
		//String page = "space space space j3j3j2 space";
		//System.out.println(PageParser.getPageRelevance(page, topics));
		WikiCrawler c = new WikiCrawler("/wiki/Calvin_and_Hobbes", 5,topics, outputfile);
		c.crawl(true);
		
		
		
		
	}
}
