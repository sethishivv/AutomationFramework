package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.ArrayList;
import java.util.List;

public class PageSpeedInsights {
	public static WebDriver driver;
	
	
    public static void main(String[] args) throws InterruptedException {
    	
    	
    	WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // List of URLs to check
        driver.get("https://appwrk.com/");
        List<String> urls = new ArrayList<String>();
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        for (WebElement link : allLinks) {
            String url = link.getAttribute("href");
            if (url != null && !url.startsWith("tel:") && !url.startsWith("mailto:") && !url.contains("javascript:void(0)") && !url.contains("https://wa.me/") && !url.startsWith("skype:")) {
                urls.add(url);
            }
        }
        
//        for (String url : urls) {
//            System.out.println("URL: " + url);
//        }


        for (String url : urls) {
            driver.get("https://developers.google.com/speed/pagespeed/insights/");
            WebElement inputElement = driver.findElement(By.name("url"));
            inputElement.clear();
            inputElement.sendKeys(url);
            WebElement analyzeButton = driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div[2]/div/div[2]/form/div[2]/button/span"));
            analyzeButton.click();

            // Get and print the PageSpeed Insights data

            Thread.sleep(25000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,400)", "");
            String mobilePerformanceScore = driver.findElement(By.className("lh-gauge__percentage")).getText();
            driver.findElement(By.xpath("//*[@id=\"desktop_tab\"]/span[2]")).click();
            String desktopPerformanceScore = driver.findElement(By.className("lh-gauge__percentage")).getText();
            System.out.println("URL: " + url);
            System.out.println("Mobile Performance Score: " + mobilePerformanceScore);
            System.out.println("Desktop Performance Score: " + desktopPerformanceScore);

            // You can extract and print or store more data as needed
        }

        // Close the Chrome driver
        driver.quit();
    }
}
