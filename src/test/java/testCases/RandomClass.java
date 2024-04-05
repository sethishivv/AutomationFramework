package testCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import base.BaseTest;

public class RandomClass extends BaseTest {
@Test
	public void allLinks () {
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		  for (WebElement link : allLinks) {
	            String url = link.getAttribute("href");
	            if (url != null) {
	                System.out.println(',' + url);
	            }
	        }
	}	
	
}
