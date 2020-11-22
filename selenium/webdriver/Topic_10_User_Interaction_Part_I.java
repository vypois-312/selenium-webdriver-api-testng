package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_User_Interaction_Part_I {
	WebDriver driver;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_Hover_Mouse() {
		driver.get("https://tiki.vn/");
		Assert.assertFalse(driver.findElement(By.xpath("//button[text()='Đăng nhập']")).isDisplayed());
		sleepInSecond(3);
		WebElement shortCutAccount = driver
				.findElement(By.xpath("//div[@data-view-id='header_user_shortcut_account']"));
		action.moveToElement(shortCutAccount).perform();
		sleepInSecond(5);
		// verify login button
		Assert.assertTrue(driver.findElement(By.xpath("//button[text()='Đăng nhập']")).isDisplayed());
	}

	public void TC_02_Hover() {
		driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(),
				"We ask for your age only for statistical purposes.");
	}

	@Test
	public void TC_03_Hover_Mouse() {
		driver.get("https://hn.telio.vn/");
		sleepInSecond(5);
		action.moveToElement(
				driver.findElement(By.xpath("//nav[@class='navigation cdz-fix-left']//span[text()='Đồ uống']")))
				.perform();

		action.click(driver.findElement(By.xpath("//nav[@class='navigation cdz-fix-left']//a[text()='Bia']")))
				.perform();
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@id='page-title-heading']/span")).getText(), "BIA");
	}

	@Test
	public void TC_04_Click_And_Hold() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		// tao ra 1 list chua het 12 phan tu
		List<WebElement> allNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		System.out.println("All number = " + allNumber.size());
		//0-11: index
		action.clickAndHold(allNumber.get(0)).moveToElement(allNumber.get(10)).release().perform();
		List<WebElement> allNumberSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(allNumberSelected.size(), 9);
	}
	
	@Test
	public void TC_04_Click_And_Hold_Random() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		// tao ra 1 list chua het 12 phan tu
		List<WebElement> allNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		System.out.println("All number = " + allNumber.size());
		//0-11: index
		
		//Nhan phim Ctrl xuong
		action.keyDown(Keys.CONTROL).perform();
		action.click(allNumber.get(0))
		.click(allNumber.get(2))
		.click(allNumber.get(5))
		.click(allNumber.get(11)).perform();
		
		List<WebElement> allNumberSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(allNumberSelected.size(), 4);
	}

	@Test
	public void TC_05_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='demo' and text()='Hello Automation Guys!']")).isDisplayed());
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
