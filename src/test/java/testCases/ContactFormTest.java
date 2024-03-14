package testCases;

import base.BaseTest;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class ContactFormTest extends BaseTest {

  @Test
  public void contactUs() throws InterruptedException, IOException {
    driver.findElement(By.xpath(loc.getProperty("contact_us"))).click();
    driver
      .findElement(By.xpath(loc.getProperty("name")))
      .sendKeys(inp.getProperty("name"));
    driver
      .findElement(By.xpath(loc.getProperty("email")))
      .sendKeys(inp.getProperty("email"));

    Select sl = new Select(
      driver.findElement(By.xpath(loc.getProperty("pin_code")))
    );
    sl.selectByVisibleText("United States (+ 1)");

    driver
      .findElement(By.xpath(loc.getProperty("phone_number")))
      .sendKeys(inp.getProperty("number"));
    driver
      .findElement(By.xpath(loc.getProperty("website")))
      .sendKeys(inp.getProperty("web"));
    driver
      .findElement(By.xpath(loc.getProperty("help_textbox")))
      .sendKeys(inp.getProperty("text"));
    driver.findElement(By.xpath(loc.getProperty("submit"))).click();
    Thread.sleep(5000);
    WebElement thank = driver.findElement(By.id("thankyou"));

    if (thank.isDisplayed()) {
      System.out.println("Passed");
      File SrcFile =
        ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      Files.copy(
        SrcFile,
        new File(
          System.getProperty("user.dir") +
          "/src/test/resources/Screenshots/Passing.png"
        )
      );
    } else {
      System.out.println("Failed");
    }
  }
}
