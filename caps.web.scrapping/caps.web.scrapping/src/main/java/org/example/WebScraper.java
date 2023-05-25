package org.example;
/**
 *This class helps each invidivual scrapper.
 * It extends thread class and has fields for crawl delay,runThread ad Dao class.
 **/
public class WebScraper extends Thread {
   volatile  boolean runThread;
   int CrawlDelay;
   Dao dao;

    public Dao getDao() {
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    public int getCrawlDelay() {
        return CrawlDelay;
    }

    public void setCrawlDelay(int crawlDelay) {
        CrawlDelay = crawlDelay;
    }
    public void stopThread() {
        runThread = false;
    }

    /**
     * Method that delays the scrapping process.
     */

    public void delayScrapping(){
        try {
            sleep(getCrawlDelay());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



}
