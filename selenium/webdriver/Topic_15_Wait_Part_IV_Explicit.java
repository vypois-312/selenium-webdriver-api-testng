package webdriver;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Wait_Part_IV_Explicit {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
	WebElement dateSelected;
	String today ="Thursday, December 10, 2020";
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Alert_Presence() {
		driver.get("http://demo.guru99.com/v4/index.php");
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.name("btnLogin")));
		driver.findElement(By.name("btnLogin")).click();
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		System.out.println(alert.getText());
		alert.accept();
	}

	@Test
	public void TC_02_Find_Elements() {
		driver.get("http://juliemr.github.io/protractor-demo/");
		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("5");
		driver.findElement(By.id("gobutton")).click();
		WebElement resultText= explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='10']")));
		Assert.assertTrue(resultText.isDisplayed());
		
	}

	@Test
	public void TC_03_Invisible() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
	}
	
	@Test
	public void TC_04_Ajax_Loading() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));
		
		dateSelected = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
		Assert.assertEquals(dateSelected.getText(), "No Selected Dates to display.");
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@title='"+today +"']"))).click();;
		// wait for loading icon disappear
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
		
		//DOM sau khi clcik
		dateSelected = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
		Assert.assertEquals(dateSelected.getText(), "Thursday, December 10, 2020");
		
	}
	
	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
