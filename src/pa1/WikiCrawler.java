package pa1;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

/**
 * Class for Crawling the wikis
 * 
 * @author ckranig, ans66
 *
 */
public class WikiCrawler {
	
	private static class Page{
		private String url;
		private String pagehtml;
		private Page(String url, String pagehtml){
			this.url = url;
			this.pagehtml = pagehtml;
		}
		String getPageHtml(){
			return pagehtml;
		}
	}
	
	/**
	 * 
	 * 
	 * @author ckranig, ans66
	 *
	 */
	private static class PageParser {
		
		private static boolean validString(String s, HashSet<String> links, String page){
			if(s.equals(page)||links.contains(s) ||s.contains("#")|| s.contains(":")){
				return false;
			}
			return true;
		}
		
		private static ArrayList<String> extractLinks(String document, String pageurl){
			ArrayList<String> links = new ArrayList<String>();
			HashSet<String> hlinks = new HashSet<String>();
			//have to look and see if we need to look for <P> as well
			int start = document.indexOf("<p>");
			if(start<0){
				return links;
			}
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
		
		private static int getPageRelevance(String page, String[] topics){
			int count = 0;
			for(int i = 0; i<topics.length; i++){
				int start = page.indexOf("<p>");
				int tmp = page.indexOf(topics[i], start);
				//if topic does not exist on page page has a relevance of -1 since we only look at pages that contain all topics
				if(tmp ==-1){
					return -1;
				}
				while(tmp != -1){
					count ++;
					start = tmp;
					tmp = page.indexOf(topics[i], start+1);
				}
			}
			return count;
		}
		
		private static boolean containsTopics(String page, String[] topics){
			for(String topic: topics){
				if(!page.contains(topic)){
					return false;
				}
			}
			
			return true;
		}
		
		
		
		/**
		 * Method for getting html of a given page
		 * 
		 * check to see if example in programming assignment works better
		 * 
		 * @param urlString: url of page
		 * @return html string of entire page
		 */
		private static String getPage(String urlString){
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
				//e.printStackTrace();
				return "";
			}
			
			
			
			
			
		}
		
		

	}
	
	public static final String BASE_URL = "https://en.wikipedia.org";
	private Set<String> visitedpages;
	private Queue pagesToVisit;
	private String seed;
	private int max;
	private String[] topics;
	private String output;
	
	/**
	 * 
	 * @param seed : String variable related address of seed URL (within wiki domain)
	 * @param max : maximum number of pages to consider
	 * @param topics : array of strings representing keywords in a topic-list
	 * @param output : string representing the filename where the web graph over discovered pages are
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
		
		if(this.topics.length<1){
			unfocusedcrawlhelper();
		}
		else{
			if(!PageParser.containsTopics(PageParser.getPage(BASE_URL + seed), this.topics)){
				return;
			}
			if(focused){
				focusedCrawlHelper();
			}
			else{
			unfocusedCrawlHelperWithTopics();
			}
		}
	}
	
	
	private void unfocusedCrawlHelperWithTopics(){
		int pagesvisited = 0;
		this.pagesToVisit = new FIFOQ();
		this.pagesToVisit.add(seed, 0);
		String nexturl = null;
		HashSet<String> prioritied = new HashSet<String>();
		prioritied.add(seed);
		
		try{
			FileWriter fw = new FileWriter(output, false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			out.println(this.max);
			
			while(visitedpages.size()<this.max&& (nexturl = this.nextURL())!=null){
				String webpage = PageParser.getPage(BASE_URL + nexturl);
				pagesvisited++;
				if(pagesvisited >= 20){
					Thread.sleep(3000);
					pagesvisited = 0;
				}
				ArrayList<String> links = PageParser.extractLinks(webpage, nexturl);
				//write edges to output
				writeToOutput(links, out, nexturl);
				//Add edges to edges to visit
				for(String link:links){
					if(!prioritied.contains(link)){
						prioritied.add(link);
						if(PageParser.containsTopics(PageParser.getPage(BASE_URL + link), this.topics)){
							this.pagesToVisit.add(link, 0);
						}
						pagesvisited++;
						if(pagesvisited >= 20){
							Thread.sleep(3000);
							pagesvisited = 0;
						}	
					}
				}
				
				
				
			}
			out.close();
			bw.close();
			fw.close();
			}catch(IOException | InterruptedException e){
				System.out.println(e.getMessage());
			}
	}
	
	private void focusedCrawlHelper(){
		
		int pagesvisited = 0;
		this.pagesToVisit = new PriorityQ();
		this.pagesToVisit.add(seed, 0);
		HashSet<String> prioritied = new HashSet<String>();
		prioritied.add(seed);
		
		String nexturl = null;
		
		try{
			FileWriter fw = new FileWriter(output, false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			out.println(this.max);
			
			while(visitedpages.size()<this.max&& (nexturl = this.nextURL())!=null){
				String webpage = PageParser.getPage(BASE_URL + nexturl);
				pagesvisited++;
				if(pagesvisited >= 20){
					Thread.sleep(3000);
					pagesvisited = 0;
				}
				ArrayList<String> links = PageParser.extractLinks(webpage, nexturl);
				//write edges to output
				writeToOutput(links, out, nexturl);
				//Add edges to edges to visit
				for(String link:links){
					if(!prioritied.contains(link)){//check to see if link was already prioritized
						prioritied.add(link);//add link to list of prioritized pages
						String page = PageParser.getPage(BASE_URL + link);
						
						int priority = PageParser.getPageRelevance(page , this.topics);
						pagesvisited++;
						if(pagesvisited >= 20){
							Thread.sleep(3000);
							pagesvisited = 0;
						}
						if(priority > -1){
							this.pagesToVisit.add(link, priority);
						}
					}
				}
				
				
				
			}
			out.close();
			bw.close();
			fw.close();
			}catch(IOException | InterruptedException e){
				System.out.println(e.getMessage());
			}
	}
	
	private void unfocusedcrawlhelper(){
		int pagesvisited = 0;
		this.pagesToVisit = new FIFOQ();
		this.pagesToVisit.add(seed, 0);
		String nexturl;
		
		try{
		FileWriter fw = new FileWriter(output, false);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter out = new PrintWriter(bw);
		out.println(this.max);
		while(visitedpages.size()<this.max&& (nexturl = this.nextURL())!= null){
			 
			String webpage = PageParser.getPage(BASE_URL + nexturl);
			pagesvisited++;
			
			ArrayList<String> links = PageParser.extractLinks(webpage, nexturl);
			//write edges to output
			writeToOutput(links, out, nexturl);
			//Add edges to edges to visit
			for(String link:links){
				this.pagesToVisit.add(link, 0);
			}
			
			
			if(pagesvisited >= 20){
				Thread.sleep(3000);
				pagesvisited = 0;
			}
			
			
		}
		out.close();
		bw.close();
		fw.close();
		}catch(IOException | InterruptedException e){
			System.out.println(e.getMessage());
		}
		
		
	}
	
	
	
	
	private void writeToOutput(ArrayList<String> links, PrintWriter out, String currentURL){
		for(String link: links){
			out.println(currentURL + " " + link );
		}
	}
	
	
	
	/**
	 * 
	 * TO DO Still need to look at exact url path for each entry
	 * 
	 * This method assumes that there are pages to visit
	 * 
	 * @return the next url to crawl and add next url to visited pages
	 */
	private String nextURL(){
		if(this.pagesToVisit.isEmpty()){
			return null;
		}
		
		String nextURL = this.pagesToVisit.extractMax();
		//extract max and see if it was already visited
		while(this.visitedpages.contains(nextURL)){
			if(pagesToVisit.isEmpty()){
				return null;
			}
			nextURL = pagesToVisit.extractMax();
		}
		this.visitedpages.add(nextURL);
		return nextURL;
	}
	
	
}
