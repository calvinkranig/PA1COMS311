package pa1;

import java.util.ArrayList;
import java.net.*;

/**
 * Class for Crawling the wikis
 * 
 * @author ckranig, ans66
 *
 */

/**
 * @author pols_ckranig
 *
 */
public class WikiCrawler {
	
	public static final String BASE_URL = "https://en.wikipedia.org";
	
	private String seed;
	private int max;
	String[] topics;
	String output;
	
	/**
	 * 
	 * @param seed : String variable related address of seed URL (within wiki domain)
	 * @param max : maximum number of pages to consider
	 * @param topics : array of strings representing keywords in a topic-list
	 * @param output : string representing the lename where the web graph over discovered pages are
written.
	 */
	public WikiCrawler(String seed, int max, String[] topics, String output) {
		super();
		this.seed = seed;
		this.max = max;
		this.topics = topics;
		this.output = output;
	}
	
	/**
	 * the method takes as input a document representing an entire HTML document. It returns a list of strings consisting of links
from the document. You can assume that the document is HTML from some wiki page. The
method must
(a) extract only relative addresses of wiki links, i.e., only links that are of the form /wiki/XXXX
(b) only extract links that appear after the first occurrence of the html tag <p> (or <P>)
(c) Must not extract any wiki link that contain characters such as \#" or \:"
(d) The order in which the links in the returned array list must be exactly the same order
in which they appear in the document

	 * @param document
	 * @return
	 */
	public ArrayList<String> extractLinks(String document){
		ArrayList<String> links = new ArrayList<String>();
		
		
		return links;
	}
	
	
	/**
	 * crawls/explores the web pages starting from the seed URL. Crawl
the first max number of pages (including the seed page), that contains every keywords in the
Topics list (if Topics list is empty then this condition is vacuously considered true), and are
explored starting from the seed.
(a) if focused is false then explore in a BFS fashion
(b) if focused is true then for every page a, compute the Relevance(T; a), and during
exploration, instead of adding the pages in the FIFO queue,
   -add the pages and their corresponding relevance (to topic) to priority queue. The
	priority of a page is its relevance;
   -extract elements from the queue using extractMax.
After the crawl is done, the edges explored in the crawl method should be written to the
output le.
	 * 
	 * @param focused
	 */
	public void crawl(boolean focused){
		
	}
	
	
}
