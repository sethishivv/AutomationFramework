package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

  public static WebDriver driver;
  public static Properties prop = new Properties();
  public static Properties loc = new Properties();
  public static Properties inp = new Properties();
  public static FileReader fr;
  public static FileReader fr1;
  public static FileReader fr2;
  // declare BrowserStack credentails
  public static final String USERNAME = "XXX";
  public static final String AUTOMATE_KEY = "XXX";
  // declare remote URL in a variable
  public static final String URL =
    "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";

  @BeforeTest
  public void setUp() throws IOException {
    if (driver == null) {
      fr =
        new FileReader(
          System.getProperty("user.dir") +
          "/src/test/resources/configfiles/config.properties"
        );
      fr1 =
        new FileReader(
          System.getProperty("user.dir") +
          "/src/test/resources/configfiles/locator.properties"
        );
      fr2 =
        new FileReader(
          System.getProperty("user.dir") +
          "/src/test/resources/configfiles/input.properties"
        );
      prop.load(fr);
      loc.load(fr1);
      inp.load(fr2);
    }
    if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
      WebDriverManager.chromedriver().setup();
      driver = new ChromeDriver();
      driver.manage().window().maximize();
      driver.get(prop.getProperty("url"));
    }
    if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
      WebDriverManager.firefoxdriver().setup();
      driver = new FirefoxDriver();
      driver.manage().window().maximize();
      driver.get(prop.getProperty("url"));
    }
    //Added the BrowserStack Configurations to run the test on Remote Browser
    if (prop.getProperty("browser").equalsIgnoreCase("remote")) {
      ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("140.0");

        HashMap<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("os", "Windows");
        bstackOptions.put("osVersion", "10");
        bstackOptions.put("sessionName", "New Test Session");
        bstackOptions.put("buildName", "BrowserStack Demo");

        options.setCapability("bstack:options", bstackOptions);

      driver = new RemoteWebDriver(new URL(URL), options);
      driver.manage().window().maximize();
      driver.get(prop.getProperty("url"));
    }
  }

  @AfterTest
  public void driverClose() throws InterruptedException {
    Thread.sleep(2000);
    driver.close();
  }
}
