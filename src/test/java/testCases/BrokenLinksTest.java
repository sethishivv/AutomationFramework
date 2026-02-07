package testCases;

import base.BaseTest;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class BrokenLinksTest extends BaseTest {

  @Test
  public void brokenLinks() throws InterruptedException {
    List<WebElement> links = driver.findElements(By.tagName("a"));
    System.out.println("Total number of Links are :- " + links.size());
    for (int i = 0; i < links.size(); i++) {
      WebElement element = links.get(i);
      String url = element.getAttribute("href");
     System.out.println(url); 
    }
  }

  public void verifyLink(String urlLink) {
    try {
    	if (urlLink != null && (urlLink.startsWith("tel:") || urlLink.startsWith("javascript:void(0)"))) {
            System.out.println(urlLink + " - Excluded link (tel or javascript:void(0))");
            return;
        }
      URL link = new URL(urlLink);
      HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
      httpConn.setConnectTimeout(2000);
      httpConn.connect();
      if (httpConn.getResponseCode() == 404) {
        System.out.println(
          urlLink + " - " + httpConn.getResponseMessage() + "is a broken link"
        );
      } else {
        System.out.println(urlLink + " - " + httpConn.getResponseCode());
      }
    } catch (Exception e) {}
  }
}
