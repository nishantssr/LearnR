package com.imaginea.dc.rss;

import java.net.URL;
import java.util.Iterator;

import org.junit.Test;

import com.imaginea.dc.entities.NewsArticle;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class ReaderTest {

	@Test
	public void fetchRSSFeedsTest() throws Exception {
		
		String hindu = "http://www.thehindu.com/news/international/?service=rss";
		String bbcWorld = "http://feeds.bbci.co.uk/news/world/rss.xml";
		String googleRss = "https://news.google.com/news/feeds?pz=1&cf=all&ned=in&hl=en&output=rss";
		
		URL url = new URL(hindu);
		XmlReader reader = null;

		try {

			reader = new XmlReader(url);
			SyndFeed feed = new SyndFeedInput().build(reader);
			System.out.println("Feed Title: " + feed.getAuthor());

			SyndEntry entry;
			SyndContent content;
			
			NewsArticle article;
			for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
				entry = (SyndEntry) i.next();
				content = entry.getDescription();
				
				article = new NewsArticle();
				article.setTitle(entry.getTitle());
				
				
				System.out.println("-------------------------------------------------------------");
				System.out.println("Title : " + entry.getTitle());
				
				
				System.out.println("Description : " + content.getValue());
				
				System.out.println("URI : " + entry.getUri());
				System.out.println("Contents : " + entry.getContents());
				System.out.println("Date : " + entry.getPublishedDate());
				
				
				
			}
			
		} finally {
			if (reader != null)
				reader.close();
		}

	}
	
	
	
	
	
	
	
	

}
