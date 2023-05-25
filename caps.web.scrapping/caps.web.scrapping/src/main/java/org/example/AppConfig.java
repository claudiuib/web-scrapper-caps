package org.example;



import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
/**
 *This class manage the entire application.

 **/
@Configuration
public class AppConfig {

    SessionFactory sessionFactory;

    @Bean
    public CapsScraperManager capsScraperManager() {
        CapsScraperManager capsScraperManager = new CapsScraperManager();

        //list of scraper manager
        List<WebScraper> scraperList = new ArrayList();
        scraperList.add(nikeScraper());
        scraperList.add(footShopScraper());
        scraperList.add(sportDirectScraper());
        capsScraperManager.setWebScraperList(scraperList);


        return capsScraperManager;
    }

    @Bean
    public WebScraper sportDirectScraper() {
        WebScraper  scraper3 = new SportDirectScraper();
        scraper3.setDao(Dao());
        scraper3.setCrawlDelay(3000);
        return scraper3;
    }
    @Bean
    public WebScraper footShopScraper() {
        WebScraper  scraper2 = new FootShopScraper();
        scraper2.setDao(Dao());
        scraper2.setCrawlDelay(3000);
        return scraper2;
    }
    @Bean
    public WebScraper nikeScraper() {
        WebScraper  scraper1 = new NikeScraper();
        scraper1.setDao(Dao());
        scraper1.setCrawlDelay(3000);
        return scraper1;
    }
    @Bean
    public Dao Dao(){
        Dao Dao = new Dao();
        Dao.setSessionFactory(sessionFactory());
        return Dao;
    }



    @Bean

    public SessionFactory sessionFactory() {
        if (sessionFactory == null) {
            try {
                //Create a builder for the standard service registry
                StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();

                //Load configuration from hibernate configuration file.
                //Here we are using a configuration file that specifies Java annotations.
                standardServiceRegistryBuilder.configure("hibernate.cfg.xml");

                //Create the registry that will be used to build the session factory
                StandardServiceRegistry registry = standardServiceRegistryBuilder.build();
                try {
                    //Create the session factory - this is the goal of the init method.
                    sessionFactory = (SessionFactory) new MetadataSources(registry).buildMetadata().buildSessionFactory();
                } catch (Exception e) {
                    /* The registry would be destroyed by the SessionFactory,
                        but we had trouble building the SessionFactory, so destroy it manually */
                    System.err.println("Session Factory build failed.");
                    e.printStackTrace();
                    StandardServiceRegistryBuilder.destroy(registry);
                }

                //Ouput result
                System.out.println("Session factory built.");

            } catch (Throwable ex) {
                // Make sure you log the exception, as it might be swallowed
                System.err.println("SessionFactory creation failed." + ex);
            }
        }
        return  sessionFactory;
    }
}




