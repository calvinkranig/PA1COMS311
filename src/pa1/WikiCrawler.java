package pa1;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

/**
 * Class for Crawling the wikis
 * 
 * @author ckranig, ans66
 *
 */
public class WikiCrawler {
	
	public static class PageParser {
		
		private static boolean validString(String s, HashSet<String> links, String page){
			if(s.equals(page)||links.contains(s) ||s.contains("#")|| s.contains(":")){
				return false;
			}
			return true;
		}
		
		public static ArrayList<String> extractLinks(String document, String pageurl){
			ArrayList<String> links = new ArrayList<String>();
			HashSet<String> hlinks = new HashSet<String>();
			//have to look and see if we need to look for <P> as well
			int start = document.indexOf("<p>");
			int tmp = document.indexOf("/wiki/", start);
			
			while(tmp != -1){
				start = document.indexOf("\"", tmp);
				String substring = document.substring(tmp, start);
				if(validString(substring,hlinks, pageurl)){
					links.add(substring);
					hlinks.add(substring);
				}
				tmp = document.indexOf("/wiki/", start);
			}
			return links;
			
		}
		
		
		
		/**
		 * Method for getting html of a given page
		 * 
		 * check to see if example in programming assignment works better
		 * 
		 * @param urlString: url of page
		 * @return html string of entire page
		 */
		public static String getPage(String urlString){
			try {
				URL url = new URL(urlString);
				URLConnection conn = url.openConnection();
				BufferedReader breader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
				String inputline = "";
				StringBuilder output = new StringBuilder();
				while((inputline = breader.readLine())!= null){
					output.append(inputline);
				}
				return output.toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			return null;
		}
		
		

	}
	
	public static final String BASE_URL = "https://en.wikipedia.org";
	private Set<String> visitedpages;
	private Queue pagesToVisit;
	private Set<String> pagesToVisitSet;
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

This is use for specification purposes only. Real extract links looks to see if link is already been extracted, 
and that it does not refer to the current page.
	 * @param document
	 * @return list of links
	 */
	public ArrayList<String> extractLinks(String document){
		ArrayList<String> links = new ArrayList<String>();
		int start = document.indexOf("<p>");
		int tmp = document.indexOf("/wiki/", start);
		
		while(tmp != -1){
			start = document.indexOf("\"", tmp);
			String substring = document.substring(tmp, start);
			if(!substring.contains(":")||!substring.contains("#")){
				links.add(substring);
			}
			tmp = document.indexOf("/wiki/", start);
		}
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
		visitedpages = new HashSet<String>();
		pagesToVisitSet = new HashSet<String>();
		if(focused){
			focusedcrawlhelper();
		}
		else{
			unfocusedcrawlhelper();
		}
	}
	
	public void unfocusedcrawlhelper(){
		int pagesvisited = 0;
		while(visitedpages.size()<200){
			if(pagesvisited >= 30){
				//rest
			}
			String url = BASE_URL + this.nextURL();
			String webpage = PageParser.getPage(url);
			
		}
		//need to use fifo queue
	}
	
	public void focusedcrawlhelper(){
		
	}
	
	
	
	/**
	 * 
	 * TO DO Still need to look at exact url path for each entry
	 * 
	 * This method assumes that all of the pages in the pages to visit are not duplicates and do not exist in visited pages
	 * @return the next url to crawl
	 */
	private String nextURL(){
		String nextURL = this.BASE_URL;
		
		if(this.pagesToVisit.isEmpty()){
			return null;
		}
		
		nextURL = this.pagesToVisit.extractMax();
		this.visitedpages.add(nextURL);
		return nextURL;
		
		
	}
	
	
}
