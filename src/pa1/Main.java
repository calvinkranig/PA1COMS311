package pa1;



public class Main {

	
	private static final String outputfile = "test.txt";
	
	public static void main(String args[]){
		//String[] topics = {"Calvin", "Bill"};
		String[] topics = new String[0];
		//String page = "space space space j3j3j2 space";
		//System.out.println(PageParser.getPageRelevance(page, topics));
		WikiCrawler c = new WikiCrawler("/wiki/Calvin_and_Hobbes", 200,topics, outputfile);
		c.crawl(false);
	}
}
