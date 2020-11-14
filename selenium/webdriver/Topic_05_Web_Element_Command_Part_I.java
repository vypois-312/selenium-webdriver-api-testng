package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Element_Command_Part_I {
	WebDriver driver;
	WebElement element;
	List<WebElement> elements;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Web_Element_Command() {
		// khai bao bien
		WebElement emailTextBox = driver.findElement(By.xpath("//input[@id='email']"));
		emailTextBox.clear();
		emailTextBox.sendKeys("vypois@gmail.com");
		emailTextBox.isDisplayed();
		
		// coa du lieu trong element
		element.clear();
		element.sendKeys("fdf");
		// cho phep tim 1 element
		
		element = driver.findElement(By.xpath(""));
		// cho phep tim nhieu element
		elements =  driver.findElements(By.xpath(""));
		
		WebElement searchTextbox = driver.findElement(By.xpath("//input[@id='search']"));
		searchTextbox.getAttribute("placeholder");
		
		//GUI: font/size/color/location/position/...
		WebElement subscribeButton = driver.findElement(By.xpath("//button[@title='Subscribe']"));
		subscribeButton.getCssValue("background");
		subscribeButton.getCssValue("font-family");
		subscribeButton.getCssValue("font-size");
		subscribeButton.getCssValue("text-align");
		
		// check element is disabled
		Assert.assertFalse(element.isEnabled());
		
		// duoc chon hay chua: checkbox/radio
		// dropdown (item) -> thu vien rieng = class rieng = select(selenium)
		Assert.assertTrue(element.isSelected());
		
	}

	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}


