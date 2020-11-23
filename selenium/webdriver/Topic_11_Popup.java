package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Popup {
	WebDriver driver;
	WebDriverWait explicitWait;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	
	}

	
	public void TC_01_Fixed_Popup() {
		driver.get("https://www.zingpoll.com/");
		driver.findElement(By.id("Loginform")).click();
		// wait 1 elelment hien thi
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='loginForm']")));
		Assert.assertTrue(driver.findElement(By.xpath("//form[@id='loginForm']")).isDisplayed());
		driver.findElement(By.xpath("//div[@id='Login']//button[@class='close']")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='loginForm']")));
		Assert.assertFalse(driver.findElement(By.xpath("//form[@id='loginForm']")).isDisplayed());
		
	}

	
	public void TC_02_Fixed_Popup() throws InterruptedException {
		driver.get("https://bni.vn/");
		Thread.sleep(3000);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sgpb-popup-dialog-main-div")));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")).isDisplayed());
		
		driver.findElement(By.xpath("//input[@value='JOIN WITH US']")).click();
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Close']")));
		driver.findElement(By.xpath("//img[@alt='Close']")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")));
		Assert.assertFalse(driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")).isDisplayed());
	}

	
	public void TC_03_Random_Popup_In_Dom() {
		driver.get("https://blog.testproject.io/");
		sleepInSecond(7);
		if(driver.findElement(By.xpath("//div[@class='mailch-wrap rocket-lazyload']")).isDisplayed()) {
			explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='close-mailch']")));
			driver.findElement(By.xpath("//div[@id='close-mailch']")).click();
			sleepInSecond(3);
		}
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id='search-2']//input[@class='search-field']")));
		driver.findElement(By.xpath("//section[@id='search-2']//input[@class='search-field']")).sendKeys("Selenium");
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@id='search-2']//span[@class='glass']")));
		driver.findElement(By.xpath("//section[@id='search-2']//span[@class='glass']")).click();
	}
	
	@Test
	public void TC_04_Random_Popup_Not_In_Dom() {
		driver.get("https://shopee.vn/");
		List<WebElement> popup= driver.findElements(By.xpath("//img[@alt='home_popup_banner']"));
		sleepInSecond(5);
		if(popup.size()>0 && popup.get(0).isDisplayed()) {
			System.out.println("Close popup");
			explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".shopee-popup__close-btn")));
			driver.findElement(By.cssSelector(".shopee-popup__close-btn")).click();
		} else {
			System.out.println("popup khong xuat hien");
		}
		sleepInSecond(2);		
				
	}
	
	public void sleepInSecond(long timeinsecond) {
		try {
			Thread.sleep(timeinsecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
