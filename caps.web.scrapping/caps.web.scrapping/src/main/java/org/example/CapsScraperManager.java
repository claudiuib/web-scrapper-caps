package org.example;

import java.util.List;

/**
 * This class is scrap manager.
 * Collects all scrapers into a list -webScraperList.
 * Starts the scraping process.
 */
public class CapsScraperManager {
 List<WebScraper> webScraperList;


    public List<WebScraper> getWebScraperList() {
        return webScraperList;
    }

    public void setWebScraperList(List<WebScraper> webScraperList) {
        this.webScraperList = webScraperList;
    }

    public void startScraping() {
        for (WebScraper webScraper : webScraperList)
            webScraper.start();


  }
}
