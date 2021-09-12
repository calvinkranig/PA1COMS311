ans66
ckranig

The PA1 program cosists of a priority queue, FIFO queue, and a webcrawler that 
utilizes these queues in order to quickly crawl the web and return a list of 
all the edges in a graph of links. The two main methods in this program are 
the WikiCrawler contstructor, which consists of a seed url, a mad number of 
pages to crawl, a list of topics to consider when calculating page relevancy, 
and a file to output to; the other method is crawl which utilizes the seed, 
max, topics, and output to crawl the web and produce a list of edges in a 
graph starting from the the seed url.
