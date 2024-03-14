package testCases;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserStackDemo {

  // declare BrowserStack credentails as environment variables
  public static final String USERNAME = "stevens_IwtlEx";
  public static final String AUTOMATE_KEY = "5B8s2YdZzdKgisRa9NMr";
  // declare remote URL in a variable
  public static final String URL =
    "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";

  public static void main(String[] args) throws MalformedURLException {
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability("os", "Windows");
    caps.setCapability("os_version", "11");
    caps.setCapability("browser", "chrome");
    caps.setCapability("browser_version", "110");
    caps.setCapability("name", "Steven");

    WebDriver driver = new RemoteWebDriver(new URL(URL), caps);

    driver.get("https://www.google.com/");
    System.out.println(driver.getTitle());
    driver.close();
  }
}
