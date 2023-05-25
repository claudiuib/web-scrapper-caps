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
 * This class manage the web scrapping process for fthp.co.uk.
 * Extends the Web scraper class.
 *
 */
public class FootShopScraper extends WebScraper {

    WebDriver driver;

    public void run() {


        runThread = true;

        while (runThread) {
            System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");


//            /**
//             * This code creates a new Chrome Driver and navigates to specified website
//             *
//             */
            driver = new ChromeDriver();
            driver.get("https://www.ftshp.co.uk/en-uk/29-caps/brand-nike/sale-no_sale/colour-black/orderby-price/orderway-asc");
            delayScrapping();
            driver.manage().window().maximize();
            delayScrapping();
            driver.findElement(By.cssSelector("button[class*='Button_button_37ztF CookieConsent_button_oCHSw']")).click();
            delayScrapping();
            Actions actions = new Actions(driver);
            for (int i = 0; i < 3; i++) {
                actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
                delayScrapping();
            }


            List<WebElement> footShopB = driver.findElements(By.cssSelector("div[class*='Products_product_1JtLQ']"));
            List<ProductWebScrap> footSListB = new ArrayList<>();
            for (WebElement ft : footShopB) {
                WebElement name = ft.findElement(By.className("Product_name_1Go7D"));
                WebElement price = ft.findElement(By.className("ProductPrice_price_J4pAM"));
                WebElement url = ft.findElement(By.className("Product_text_2wCMF"));
                WebElement imgLink = ft.findElement(By.className("LazyImage_image_3wH1D"));
                WebElement colour = ft.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div[1]/div[1]/div[2]"));
                WebElement styleKey = ft.findElement(By.cssSelector("meta[itemprop*='sku']"));


                String nameB = name.getText().replace("'s", "");
                String priceB = price.getText().replace("£", "");
                String link = url.getAttribute("href");
                String imageLink = imgLink.getAttribute("src");
                String colourB = colour.getText().replace("Colour: ", "");
                String styleKeB = styleKey.getAttribute("content");

                ProductWebScrap prods = new ProductWebScrap();
                prods.setTitle(nameB);
                prods.setPrice(Double.parseDouble(priceB));
                prods.setUrl(link);
                prods.setImageUrl(imageLink);
                prods.setColour(colourB);
                prods.setStyleKeyId(styleKeB);
                footSListB.add(prods);


            }

            delayScrapping();
            driver.close();

            //send data to Caps class - colour Black

            for (ProductWebScrap footScrap : footSListB) {
                Caps capF1 = new Caps();
                capF1.setName(footScrap.getTitle());
                capF1.setStyle_key(footScrap.getStyleKeyId());

                CapsInstance capFs1 = new CapsInstance();
                capFs1.setCaps(capF1);
                capFs1.setColour(footScrap.getColour());
                capFs1.setImage_url(footScrap.getImageUrl());


                Compare compareF = new Compare();
                compareF.setUrl(footScrap.getUrl());
                compareF.setCapsInstance(capFs1);
                compareF.setPrice(footScrap.getPrice());
                try {
                    dao.saveAndMerge(compareF);
                } catch (Exception e) {

                    runThread = false;
                }


            }

            delayScrapping();


            // new driver for colour blue

            driver = new ChromeDriver();
            driver.get("https://www.ftshp.co.uk/en-uk/29-caps/brand-nike/sale-no_sale/colour-blue/orderby-price/orderway-asc");
            //maximize window
            driver.manage().window().maximize();
            try {
                sleep(getCrawlDelay());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);

            }
            driver.findElement(By.cssSelector("button[class*='Button_button_37ztF CookieConsent_button_oCHSw']")).click();
            try {
                sleep(getCrawlDelay());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);

            }
            //action class to scroll down  the website
            Actions actions2 = new Actions(driver);
            for (int i = 0; i < 3; i++) {
                actions2.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
                delayScrapping();
            }

            //get the Webelemets and save them

            List<WebElement> footShopBlue = driver.findElements(By.cssSelector("div[class*='Products_product_1JtLQ']"));
            List<ProductWebScrap> footSListBlue = new ArrayList<>();
            for (WebElement fst : footShopBlue) {
                WebElement name = fst.findElement(By.className("Product_name_1Go7D"));
                WebElement price = fst.findElement(By.className("ProductPrice_price_J4pAM"));
                WebElement url = fst.findElement(By.className("Product_text_2wCMF"));
                WebElement imgLinkBlue = fst.findElement(By.className("LazyImage_image_3wH1D"));
                WebElement colour = fst.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div[1]/div[1]/div[2]"));
                WebElement styleKey = fst.findElement(By.cssSelector("meta[itemprop*='sku']"));


                String nameBlue = name.getText().replace("'s", "");
                String priceBlue = price.getText().replace("£", "");
                String link = url.getAttribute("href");
                String imageLink = imgLinkBlue.getAttribute("src");
                String colourBlue = colour.getText().replace("Colour: ", "");
                String styleKeBlue = styleKey.getAttribute("content");

                ProductWebScrap prods = new ProductWebScrap();
                prods.setTitle(nameBlue);
                prods.setPrice(Double.parseDouble(priceBlue));
                prods.setUrl(link);
                prods.setImageUrl(imageLink);
                prods.setColour(colourBlue);
                prods.setStyleKeyId(styleKeBlue);
                footSListBlue.add(prods);


            }
            delayScrapping();
            driver.close();
            driver.quit();
            //send data to Caps class - colour Blue

            for (ProductWebScrap footScrapBlue : footSListBlue) {
                Caps capF2 = new Caps();
                capF2.setName(footScrapBlue.getTitle());
                capF2.setStyle_key(footScrapBlue.getStyleKeyId());

                CapsInstance capFs2 = new CapsInstance();
                capFs2.setCaps(capF2);
                capFs2.setColour(footScrapBlue.getColour());
                capFs2.setImage_url(footScrapBlue.getImageUrl());


                Compare compareF2 = new Compare();
                compareF2.setUrl(footScrapBlue.getUrl());
                compareF2.setCapsInstance(capFs2);
                compareF2.setPrice(footScrapBlue.getPrice());
                try {
                    dao.saveAndMerge(compareF2);
                } catch (Exception e) {

                    runThread = false;
                }


            }

            stopThread();

        }

    }

}
