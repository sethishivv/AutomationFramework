package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class DemoBlaze  {
	public static WebDriver driver;
	
	@Test
	public static void addToCart() {
		driver = new ChromeDriver();
		driver.get("https://hello.com/");
		
	}

}