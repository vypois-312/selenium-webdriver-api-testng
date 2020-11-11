package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_CSS_Part_II_FindElement {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/");
	}

	@Test
	public void TC_01_Login_Empty_Email_And_Password() {
		//Click link My accoumt
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		// leave blank username & password
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		// click login button
		driver.findElement(By.name("send")).click();
		//verify error message "This is a required field"
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		
	}

	@Test
	public void TC_02_Login_Invalid_Email() {
		//Click link My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		// Enter invalid email 123434234@1234.1234 and valid password 123456
		driver.findElement(By.id("email")).sendKeys("123434234@1234.1234");
		driver.findElement(By.id("pass")).sendKeys("123456");
		// Click login button 
		driver.findElement(By.name("send")).click();
		// Verify error message "Please enter a valid email address. For example johndoe@domain.com."
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
		
	}
	
	@Test
	public void TC_03_Login_Invalid_Password() {
		//Click link My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		// Enter valid email dam@gmail.com and invalid password 123
		driver.findElement(By.id("email")).sendKeys("dam@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		driver.findElement(By.name("send")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test
	public void TC_04_Login_Incorrect_Email_And_Valid_Password() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("autotest435435@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.name("send")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
	}
	
	@Test
	public void TC_05_Login_Valid_Email_And_Incorrect_Password() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("dam@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("654321");
		driver.findElement(By.name("send")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
	}

	@AfterClass
	public void afterClass() {
		
	
	}
}
