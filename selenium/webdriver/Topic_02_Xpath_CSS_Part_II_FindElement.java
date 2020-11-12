package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.Random;
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
	Random rand;
	String emailAddress, firstName, lastName;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		rand = new Random();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/index.php");
		
		emailAddress = "autotest" + rand.nextInt(999999) + "@automation.vn";
		firstName = "Automation";
		lastName = "Testing";
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
	
	@Test
	public void TC_06_Register_To_System() {
		//Click My Account link
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		//Click create an account button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		//Fill inf in all required field
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("confirmation")).sendKeys("123456");
		
		//Click Register button
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		// Verify message "Thank you for registering with Main Website Store."
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
		
		String contactInfor = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div[@class='box-title']/following-sibling::div[@class='box-content']")).getText();
		Assert.assertTrue(contactInfor.contains(firstName));
		Assert.assertTrue(contactInfor.contains(lastName));
		Assert.assertTrue(contactInfor.contains(emailAddress));
		
		//Log out
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		// Verify logo
//		Assert.assertTrue(driver.findElement(By.cssSelector("img[src$='logo.pnj']")).isDisplayed());
		
	}
	
	@Test
	public void TC_07_Login_Valid_Email_And_Password() {
		//Click My account link
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		// Login
		driver.findElement(By.id("email")).sendKeys(emailAddress);
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.name("send")).click();
		//Verify welcome message include Firstname/Lastname
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(), "Hello, "+firstName+" "+lastName+"!");
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}