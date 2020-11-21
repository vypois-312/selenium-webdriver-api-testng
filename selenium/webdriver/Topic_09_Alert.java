package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Alert {
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;
	By resultText = By.xpath("//p[@id='result']");
	String username = "admin";
	String password = "admin";
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver,30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		explicitWait.until(ExpectedConditions.alertIsPresent());
		
		//Switch vao alert
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		sleepInSecond(3);
		alert.accept();
		Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked an alert successfully");
		
	}

	@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		explicitWait.until(ExpectedConditions.alertIsPresent());
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		sleepInSecond(3);
		alert.dismiss();
		Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked: Cancel");
	}

	@Test
	public void TC_03_Prompt_Alert() {
		String LoginValue ="Automation testing";
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert = driver.switchTo().alert();
		alert.sendKeys(LoginValue);
		sleepInSecond(2);
		alert.accept();
		Assert.assertEquals(driver.findElement(resultText).getText(), "You entered: " + LoginValue);
		
	}
	
	@Test
	public void TC_04_Authentication_Alert() {
		//http://the-internet.herokuapp.com/basic_auth
		driver.get("http://"+username+":"+password+"@the-internet.herokuapp.com/basic_auth");
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}

	@Test
	public void TC_05_Authentication_Alert() {
		driver.get("http://the-internet.herokuapp.com/");
		String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		System.out.println(basicAuthenLink);
		driver.get(getAuthenticationUrl(basicAuthenLink, username, password));
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	
	public void sleepInSecond(long timeinsecond) {
		try {
			Thread.sleep(timeinsecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
	
	public String getAuthenticationUrl(String oldUrl, String username, String password) {
		String newUrl = null;
		String urlValue[]= oldUrl.split("//");
		newUrl = urlValue[0] + "//" + username + ":" + password + "@" + urlValue[1];
		return newUrl;
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
