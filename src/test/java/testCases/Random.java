package testCases;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Random {
	@Test
	public void brokenLinks() {
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://appwrk.com/");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		for(int i =0; i<links.size();i++) {
			WebElement element = links.get(i);
			String url = element.getAttribute("href");
//			System.out.println(url);
			verifyLink(url);
		}
	}
	
	public void verifyLink(String url) {
		try {
		    	if (url != null && (url.startsWith("tel:") || url.startsWith("javascript:void(0)"))) {
		            System.out.println(url + " - Excluded link (tel or javascript:void(0))");
		            return; 
		    	}
			URL link = new URL(url);
			HttpURLConnection connect = (HttpURLConnection) link.openConnection();
			connect.setConnectTimeout(2000);
			connect.connect();
			if (connect.getResponseCode() != 200) {
		        System.out.println(
		          url + " - " + connect.getResponseMessage() + "is a broken link");
		      } else {
		        System.out.println(url + " - " + connect.getResponseCode());
		      }
			}
		catch (Exception e) {
			
		}

}
}
