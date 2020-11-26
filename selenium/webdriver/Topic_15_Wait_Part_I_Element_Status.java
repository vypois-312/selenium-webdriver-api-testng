package webdriver;

import java.util.concurrent.TimeUnit;

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

public class Topic_15_Wait_Part_I_Element_Status {
	WebDriver driver;
	WebDriverWait explicitWait;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().window().maximize();
		
	}

	public void TC_01_Visible() {
		driver.get("https://www.facebook.com/");
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']")));
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		//Wait for email visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='email']")));
	}

	@Test
	public void TC_02_Invisible() {
		
	}

	@Test
	public void TC_03_Presence() {
		driver.get("http://live.demoguru99.com/index.php");
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='header-account']//a[text()='My Account']")));
		
	}
	
	@Test
	public void TC_04_Staleness() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		driver.findElement(By.xpath("//button[@id='SubmitCreate']")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='create_account_error']//li")));
		WebElement emailErrorMessage = driver.findElement(By.xpath("//div[@id='create_account_error']//li"));
		driver.navigate().refresh();
		
		//
		explicitWait.until(ExpectedConditions.stalenessOf(emailErrorMessage));
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
