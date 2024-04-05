package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseTest;


//https://www.saucedemo.com/
public class SauceDemoCheckout extends BaseTest{
@BeforeTest
	public static void login() {
		
		WebElement userName = driver.findElement(By.name(loc.getProperty("login_username")));
		userName.sendKeys(inp.getProperty("login_username"));
		WebElement password = driver.findElement(By.name(loc.getProperty("login_pass")));
		password.sendKeys(inp.getProperty("login_pass"));
		WebElement login = driver.findElement(By.name(loc.getProperty("login_button")));
		login.click();
		
	}
@Test(priority = 1)
	public static void changeFilter() throws InterruptedException {
		Thread.sleep(2000);
		Select s1 = new Select(driver.findElement(By.className(loc.getProperty("select_filter"))));
		s1.selectByValue(inp.getProperty("filter_value"));
		Thread.sleep(2000);
	}
@Test(priority = 2)
	public static void addItems() throws InterruptedException {
		Thread.sleep(2000);
		WebElement BackPack = driver.findElement(By.name(loc.getProperty("backpack_add")));
		BackPack.click();
		Thread.sleep(2000);
		WebElement Jacket = driver.findElement(By.name(loc.getProperty("jacket_add")));
		Jacket.click();
	}
@Test(priority = 3)
	public static void verifyAddedItem() throws InterruptedException {
	
		WebElement cart = driver.findElement(By.className(loc.getProperty("cart")));
		cart.click();
		Thread.sleep(2000);
		WebElement cart_contents = driver.findElement(By.xpath(loc.getProperty("cart_contents")));
		if (cart_contents.isDisplayed()) {
			System.out.println("The items has been added to the cart successfully");
		}else {
			System.out.println("Fail");
		}
}	@Test(priority = 4)
	public static void checkout () throws InterruptedException {
		WebElement checkout = driver.findElement(By.name(loc.getProperty("checkout")));
		checkout.click();
		WebElement checkout_info = driver.findElement(By.className(loc.getProperty("checkout_info")));
		if (checkout_info.isDisplayed()) {
			WebElement firstName = driver.findElement(By.name(loc.getProperty("firstname")));
			firstName.sendKeys(inp.getProperty("name"));
			WebElement lastName = driver.findElement(By.name(loc.getProperty("lastname")));
			lastName.sendKeys(inp.getProperty("lastName"));
			WebElement postalCode = driver.findElement(By.name(loc.getProperty("postalcode")));
			postalCode.sendKeys(inp.getProperty("postalcode"));
			WebElement continue_button = driver.findElement(By.name(loc.getProperty("continue")));
			continue_button.click();
			Thread.sleep(2000);
			WebElement checkout_summary = driver.findElement(By.id(loc.getProperty("checkout_summary")));
			if (checkout_summary.isDisplayed()) {
				System.out.println("The checkout has been finished successfully, clicking on Finish now");
				Thread.sleep(2000);
				WebElement finish = driver.findElement(By.id(loc.getProperty("finish")));
				finish.click();
				Thread.sleep(2000);
				WebElement checkout_complete = driver.findElement(By.id(loc.getProperty("checkout_complete")));
				if (checkout_complete.isDisplayed()) {
					System.out.println("The order has been placed Successfully");
				}
			}
		}else {
			System.out.println("There's some issue with CheckOut button");
		}
	}
}
