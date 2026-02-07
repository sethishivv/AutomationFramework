package testCases;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;

public class BrowserStackDemo {

    public static final String USERNAME = "XXX";
    public static final String AUTOMATE_KEY = "XXX";

    public static final String URL =
            "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";

    public static void main(String[] args) throws MalformedURLException {

        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("140.0");

        HashMap<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("os", "Windows");
        bstackOptions.put("osVersion", "10");
        bstackOptions.put("sessionName", "Google Test");
        bstackOptions.put("buildName", "BrowserStack Demo");

        options.setCapability("bstack:options", bstackOptions);

        WebDriver driver = new RemoteWebDriver(new URL(URL), options);

        driver.get("https://www.google.com/");
        System.out.println(driver.getTitle());

        driver.quit();
    }
}
