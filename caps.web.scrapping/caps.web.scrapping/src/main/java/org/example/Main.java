package org.example;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main class that runs the entire project.
 *
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CapsScraperManager manager = (CapsScraperManager) context.getBean("capsScraperManager");

        manager.startScraping();
    }
}