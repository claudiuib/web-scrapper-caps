package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
/**
 * This class manage the web scrapping process for sportdirect.com.
 * Extends the Web scraper class.
 *
 */
public class SportDirectScraper extends WebScraper {


    WebDriver driver;


    public void run() {
        runThread = true;
        while (runThread) {

            System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");

            driver = new ChromeDriver();
            driver.get("https://www.sportsdirect.com/searchresults?descriptionfilter=nike%20caps#dcp=1&dppp=120&OrderBy=rank&Filter=CATG%5EHeadwear%7CACOL%5EBlack%2CBlue%2CCream");
//            maximize window
            driver.manage().window().maximize();
            delayScrapping();
            //find the cookie element and clicks on accept.
            driver.findElement(By.id("onetrust-accept-btn-handler")).click();
            delayScrapping();
            //action class that helps to scroll up and down the website
            Actions actions = new Actions(driver);
            actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
            delayScrapping();
            actions.keyUp(Keys.CONTROL).sendKeys(Keys.HOME).perform();
            delayScrapping();


//            get all the Webelements and save them
            List<WebElement> sportDirectListB = driver.findElements(By.cssSelector("li[li-brand*='Nike']"));
            List<ProductWebScrap> sportDListB = new ArrayList<>();
            for (WebElement sd : sportDirectListB) {
                WebElement imageUrl = sd.findElement(By.xpath(".//a[@class=\"ProductImageList\"]"));
                String title = sd.getAttribute("li-brand") + " " + sd.getAttribute("li-name");
                String price = sd.getAttribute("li-price");
                String url = "https://www.sportsdirect.com/" + sd.getAttribute("li-url");
                String imgLink = imageUrl.getAttribute("href");
                String colour = sd.getAttribute("li-variant").replace("Navy", "Blue");
                ;
                String styleKey = sd.getAttribute("data-insights-object-id");

                ProductWebScrap prod = new ProductWebScrap();
                prod.setTitle(title);
                prod.setPrice(Double.parseDouble(price));
                prod.setUrl(url);
                prod.setImageUrl(imgLink);
                prod.setColour(colour);
                prod.setStyleKeyId(styleKey);
                sportDListB.add(prod);

            }



            delayScrapping();
            driver.close();
            driver.quit();

            //send data to Caps class - colour Black

            for (ProductWebScrap sportD : sportDListB) {
                Caps capS = new Caps();
                capS.setName(sportD.getTitle());
                capS.setStyle_key(sportD.getStyleKeyId());

                CapsInstance capInS1 = new CapsInstance();
                capInS1.setCaps(capS);
                capInS1.setColour(sportD.getColour());
                capInS1.setImage_url(sportD.getImageUrl());


                Compare compareS1 = new Compare();
                compareS1.setUrl(sportD.getUrl());
                compareS1.setCapsInstance(capInS1);
                compareS1.setPrice(sportD.getPrice());
                try {
                    dao.saveAndMerge(compareS1);
                } catch (Exception e) {

                    runThread = false;
                }


            }
            stopThread();


        }

    }


}
