package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Frame_Iframe_Window {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	public void TC_01_Iframe() {
		driver.get("https://kyna.vn/");
		sleepInSecond(2);
		//switch to facebook iframe
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fanpage ']//iframe")));
		String facebookLike= driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
		Assert.assertEquals(facebookLike, "169K likes");
		
		// switch to default content
		driver.switchTo().defaultContent();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='live-search-bar']")).isDisplayed());
		
		//Switch to webchat iframe
		driver.switchTo().frame(driver.findElement(By.cssSelector("#cs_chat_iframe")));
		Assert.assertTrue(driver.findElement(By.cssSelector(".scrollable_inside")).isDisplayed());
		
		driver.findElement(By.cssSelector(".input_name")).sendKeys("vypois");
		driver.findElement(By.cssSelector(".input_phone")).sendKeys("045678231");
		driver.findElement(By.cssSelector("#serviceSelect")).click();
		driver.findElement(By.xpath("//select[@id='serviceSelect']/option[text()='HỖ TRỢ KỸ THUẬT']")).click();
		driver.findElement(By.tagName("textarea")).sendKeys("fgfgdsdfbghh");
		driver.findElement(By.cssSelector(".submit")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector(".floater")).isDisplayed());
		// switch to default content
		driver.switchTo().defaultContent();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='live-search-bar']")).isDisplayed());
		
		driver.findElement(By.id("live-search-bar")).sendKeys("Java");
		driver.findElement(By.cssSelector(".search-button")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='menu-heading']/h1")).getText(), "'Java'");
		
	}

	@Test
	public void TC_02_Windows() {
		driver.get("https://kyna.vn/");
		// lay ra id tai tab co driver dang dung
		String parentID = driver.getWindowHandle();
		System.out.println(parentID);
		
		clickToElementByJS("//img[@alt='VietnamWorks']");
		sleepInSecond(4);
		
		switchToWindowByID(parentID);
		String childID = driver.getWindowHandle();
		Assert.assertEquals(driver.getTitle(), "Tuyển dụng, việc làm, tìm việc làm nhanh mới nhất | VietnamWorks");
		
		//switch ve tab parent
		switchToWindowByID(childID);
		Assert.assertEquals(driver.getCurrentUrl(), "https://kyna.vn/");
		
		//Click to "Vietnamwork Learning" link at pre-footer
		clickToElementByJS("//img[@alt='VietnamWorks Learning']");
		sleepInSecond(4);
		switchToWindowByTitle("Trang chủ | Vietnamworks Learning for Enterprise");
		Assert.assertTrue(driver.findElement(By.cssSelector(".ga_login_header")).isDisplayed());
		closeAllWindowsWithoutParent(parentID);
		
	}

	@Test
	public void TC_03_LoginFormDisplayed() {
		
	}
	
	@Test
	public void TC_04_LoginFormDisplayed() {
		
	}

	public void clickToElementByJS (String locator) {
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath(locator)));
	}
	
	public void switchToWindowByID(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for(String runWindow : allWindows) {
			if(!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}
	
	public void switchToWindowByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for(String runWindow : allWindows) {
			driver.switchTo().window(runWindow);
			String currentWin = driver.getTitle();
			if(currentWin.equals(title)) {
				break;
			}
		}
	}
	public boolean closeAllWindowsWithoutParent(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for(String runWindow : allWindows) {
			if(!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
		if(driver.getWindowHandles().size()==1) {
			return true;
		} else {
			return false;
		}
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
