package testCases;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class HoverTest extends BaseTest {

  @Test
  public void hoverEffect() {
    Actions action = new Actions(driver);
    WebElement phone_hover = driver.findElement(
      By.xpath(loc.getProperty("phone_hover"))
    );
    action.moveToElement(phone_hover).build().perform();

    if (prop.getProperty("country").equalsIgnoreCase("us")) {
      WebElement click_number = driver.findElement(
        By.xpath(loc.getProperty("usa"))
      );
      action.moveToElement(click_number);
      action.click().build().perform();
      System.out.println("Clicked on USA");
    } else if (prop.getProperty("country").equalsIgnoreCase("aus")) {
      WebElement click_number = driver.findElement(
        By.xpath(loc.getProperty("aus"))
      );
      action.moveToElement(click_number);
      action.click().build().perform();
      System.out.println("Clicked on Australia");
    } else if (prop.getProperty("country").equalsIgnoreCase("nl")) {
      WebElement click_number = driver.findElement(
        By.xpath(loc.getProperty("nl"))
      );
      action.moveToElement(click_number);
      action.click().build().perform();
      System.out.println("Clicked on NetherLands");
    } else if (prop.getProperty("country").equalsIgnoreCase("ind")) {
      WebElement click_number = driver.findElement(
        By.xpath(loc.getProperty("ind"))
      );
      action.moveToElement(click_number);
      action.click().build().perform();
      System.out.println("Clicked on India");
    } else if (prop.getProperty("country") != null) {
      WebElement click_number = driver.findElement(
        By.xpath(loc.getProperty("drop_enquiry"))
      );
      action.moveToElement(click_number);
      action.click().build().perform();
      WebElement contact_form = driver.findElement(
        By.xpath(loc.getProperty("contact_form"))
      );
      if (contact_form.isDisplayed()) {
        System.out.println("The Contact Form got opened");
      } else {
        System.out.println("The Contatct Form didn't open");
      }
    }
  }
}
