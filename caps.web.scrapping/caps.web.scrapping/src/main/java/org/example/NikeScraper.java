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
 * This class manage the web scrapping process for nike.com.
 * Extends the Web scraper class.
 *
 */
public class NikeScraper extends WebScraper {
    WebDriver driver;


    public void run() {

        runThread = true;
        while (runThread) {

            System.out.println("Nike thread scrapping");
            System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");


            //start new driver for black colour -nike.com

            driver = new ChromeDriver();
            driver.get("https://www.nike.com/gb/w?q=caps");
            delayScrapping();
//            maximize window
            driver.manage().window().maximize();
            //find the cookie element and clicks on accept.
            driver.findElement(By.cssSelector("#hf_cookie_text_cookieAccept")).click();
            Actions actions = new Actions(driver);
            WebElement colourB = driver.findElement(By.xpath("//*[@id=\"wallNavFG2\"]/button[1]/div"));
            delayScrapping();
            actions.moveToElement(colourB).click().perform();
            delayScrapping();
            //scroll down the page 3 times
            for (int i = 0; i < 3; i++) {
                actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
                delayScrapping();
            }
             //list that  store the main  ccs selector
            List<WebElement> listProducts = driver.findElements(By.cssSelector("div[class*='product-card product-grid__card  css-c2ovjx']"));
             //list that save  the WebElements list
            List<ProductWebScrap> nikeWebList = new ArrayList<>();
            //get all the elements and save them
            for (WebElement el : listProducts) {

                WebElement titleNike = el.findElement(By.className("product-card__link-overlay"));
                WebElement detailsNike = el.findElement(By.className("product-card__subtitle"));
                WebElement price = el.findElement(By.className("product-price"));
                WebElement url = el.findElement(By.className("product-card__img-link-overlay"));
                WebElement imgUrl = el.findElement(By.className("product-card__hero-image"));
                WebElement colour = el.findElement(By.xpath("/html/body/div[4]/div/div/div[3]/div[4]/div/div[5]/div[1]/div[1]/div[2]/div/div/div/div/nav/div/div/div[3]/div/div/div/button[1]/span"));
                String priceNike = price.getText().substring(1);
                String urlNike = url.getAttribute("href");
                String imgUrlNike = imgUrl.getAttribute("src");
                String colourN = colour.getText();
                String styleLink = url.getAttribute("href");
                String styleKey = styleLink.substring(styleLink.lastIndexOf("/") + 1);
                String details = detailsNike.getText().replaceAll("'", "");
                ProductWebScrap nike = new ProductWebScrap();
                nike.setTitle(titleNike.getText() + " " + details);
                nike.setPrice(Double.parseDouble(priceNike));
                nike.setUrl(urlNike);
                nike.setImageUrl(imgUrlNike);
                nike.setColour(colourN);
                nike.setStyleKeyId(styleKey);
                nikeWebList.add(nike);


            }
            System.out.println(nikeWebList.size());
            delayScrapping();
            driver.close();


            //send data to Caps class - colour Black

            for (ProductWebScrap nikeScrap : nikeWebList) {
                Caps cap1 = new Caps();
                cap1.setName(nikeScrap.getTitle());
                cap1.setStyle_key(nikeScrap.getStyleKeyId());

                CapsInstance capIn1 = new CapsInstance();
                capIn1.setCaps(cap1);
                capIn1.setColour(nikeScrap.getColour());
                capIn1.setImage_url(nikeScrap.getImageUrl());


                Compare compare1 = new Compare();
                compare1.setUrl(nikeScrap.getUrl());
                compare1.setCapsInstance(capIn1);
                compare1.setPrice(nikeScrap.getPrice());
                try {
                    dao.saveAndMerge(compare1);
                } catch (Exception e) {

                    runThread = false;
                }


            }
            //start new driver for blue colour -nike.com

            driver = new ChromeDriver();

            driver.get("https://www.nike.com/gb/w?q=caps");

            delayScrapping();


            driver.manage().window().maximize();
            delayScrapping();
            driver.findElement(By.cssSelector("#hf_cookie_text_cookieAccept")).click();
            Actions actions2 = new Actions(driver);
            WebElement colourBlue = driver.findElement(By.xpath("//*[@id=\"wallNavFG2\"]/button[2]/div"));
            delayScrapping();
            actions2.moveToElement(colourBlue).click().perform();
            delayScrapping();


            //scroll down the page 3 times
            for (int i = 0; i < 3; i++) {
                actions2.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
                delayScrapping();
            }


            List<WebElement> listProductsBlue = driver.findElements(By.cssSelector("div[class*='product-card product-grid__card  css-c2ovjx']"));


            List<ProductWebScrap> nikeWebListBlue = new ArrayList<>();

            for (WebElement el : listProductsBlue) {

                WebElement titleNike = el.findElement(By.className("product-card__link-overlay"));
                WebElement detailsNike = el.findElement(By.className("product-card__subtitle"));
                WebElement price = el.findElement(By.className("product-price"));
                WebElement url = el.findElement(By.className("product-card__img-link-overlay"));
                WebElement imgUrl = el.findElement(By.className("product-card__hero-image"));
                WebElement colour = el.findElement(By.xpath("/html/body/div[4]/div/div/div[3]/div[4]/div/div[5]/div[1]/div[1]/div[2]/div/div/div/div/nav/div/div/div[3]/div/div/div/button[2]/span"));
                String priceNike = price.getText().substring(1);
                String urlNike = url.getAttribute("href");
                String imgUrlNike = imgUrl.getAttribute("src");
                String colourN = colour.getText();
                String styleLink = url.getAttribute("href");
                String styleKey = styleLink.substring(styleLink.lastIndexOf("/") + 1);
                String details = detailsNike.getText().replaceAll("'", "");
                ProductWebScrap nikeBlue = new ProductWebScrap();
                nikeBlue.setTitle(titleNike.getText());
                nikeBlue.setTitle(titleNike.getText() + " " + details);
                nikeBlue.setPrice(Double.parseDouble(priceNike));
                nikeBlue.setUrl(urlNike);
                nikeBlue.setImageUrl(imgUrlNike);
                nikeBlue.setColour(colourN);
                nikeBlue.setStyleKeyId(styleKey);
                nikeWebListBlue.add(nikeBlue);


            }


            delayScrapping();
            driver.close();


            //send data to Caps object

            for (ProductWebScrap nikeScrapBlue : nikeWebListBlue) {
                Caps cap2 = new Caps();
                cap2.setName(nikeScrapBlue.getTitle());
                cap2.setStyle_key(nikeScrapBlue.getStyleKeyId());

                CapsInstance capIn2 = new CapsInstance();
                capIn2.setCaps(cap2);
                capIn2.setColour(nikeScrapBlue.getColour());
                capIn2.setImage_url(nikeScrapBlue.getImageUrl());


                Compare compare2 = new Compare();
                compare2.setUrl(nikeScrapBlue.getUrl());
                compare2.setCapsInstance(capIn2);
                compare2.setPrice(nikeScrapBlue.getPrice());

                try {
                    //save data to sql database
                    dao.saveAndMerge(compare2);


                } catch (Exception e) {
                    runThread = false;
                }
            }
            //driver for the  caps with  grey colour
            driver = new ChromeDriver();

            driver.get("https://www.nike.com/gb/w?q=caps");

            driver.manage().window().maximize();
            delayScrapping();
            driver.findElement(By.cssSelector("#hf_cookie_text_cookieAccept")).click();
            Actions actions3 = new Actions(driver);
            WebElement colourWhite = driver.findElement(By.xpath("//*[@id=\"wallNavFG2\"]/button[5]/div"));
            delayScrapping();
            actions3.moveToElement(colourWhite).click().perform();
            delayScrapping();
            //scroll down the page 3 times
            for (int i = 0; i < 3; i++) {
                actions3.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
                delayScrapping();
            }


            List<WebElement> listProductsGrey = driver.findElements(By.cssSelector("div[class*='product-card product-grid__card  css-c2ovjx']"));


            List<ProductWebScrap> nikeWebListGrey = new ArrayList<>();

            for (WebElement el : listProductsGrey) {

                WebElement titleNike = el.findElement(By.className("product-card__link-overlay"));
                WebElement detailsNike = el.findElement(By.className("product-card__subtitle"));
                WebElement price = el.findElement(By.className("product-price"));
                WebElement url = el.findElement(By.className("product-card__img-link-overlay"));
                WebElement imgUrl = el.findElement(By.className("product-card__hero-image"));
                WebElement colour = el.findElement(By.xpath("/html/body/div[4]/div/div/div[3]/div[4]/div/div[5]/div[1]/div[1]/div[2]/div/div/div/div/nav/div/div/div[3]/div/div/div/button[5]/span"));
                String priceNike = price.getText().substring(1);
                String urlNike = url.getAttribute("href");
                String imgUrlNike = imgUrl.getAttribute("src");
                String colourN = colour.getText();
                String styleLink = url.getAttribute("href");
                String styleKey = styleLink.substring(styleLink.lastIndexOf("/") + 1);
                String details = detailsNike.getText().replaceAll("'", "");

                ProductWebScrap nikeGrey = new ProductWebScrap();
                nikeGrey.setTitle(titleNike.getText() + " " + details);
                nikeGrey.setPrice(Double.parseDouble(priceNike));
                nikeGrey.setUrl(urlNike);
                nikeGrey.setImageUrl(imgUrlNike);
                nikeGrey.setColour(colourN);
                nikeGrey.setStyleKeyId(styleKey);
                nikeWebListGrey.add(nikeGrey);


            }

            delayScrapping();
            driver.close();
            driver.quit();

            //send data to Caps object

            for (ProductWebScrap nikeScrapGrey : nikeWebListGrey) {
                Caps cap3 = new Caps();
                cap3.setName(nikeScrapGrey.getTitle());
                cap3.setStyle_key(nikeScrapGrey.getStyleKeyId());

                CapsInstance capIn3 = new CapsInstance();
                capIn3.setCaps(cap3);
                capIn3.setColour(nikeScrapGrey.getColour());
                capIn3.setImage_url(nikeScrapGrey.getImageUrl());


                Compare compare3 = new Compare();
                compare3.setUrl(nikeScrapGrey.getUrl());
                compare3.setCapsInstance(capIn3);
                compare3.setPrice(nikeScrapGrey.getPrice());

                try {
                    //save data to sql database
                    dao.saveAndMerge(compare3);


                } catch (Exception e) {
                    runThread = false;
                }
            }
            stopThread();

        }

    }


}
