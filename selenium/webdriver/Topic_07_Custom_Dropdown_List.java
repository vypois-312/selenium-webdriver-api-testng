package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Custom_Dropdown_List {
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "5");
		sleepInSecond(2);
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "10");
		sleepInSecond(2);
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "3");
		sleepInSecond(2);
	}

	@Test
	public void TC_02_() {

	}

	@Test
	public void TC_03_() {

	}
	//common
	public void selectItemInCustomDropdown(String parentXpath, String allItemXpath, String expectedValueItem) {
		//Click 1 element (parent Xpath) để cho nó xổ hết các item ra
		driver.findElement(By.xpath(parentXpath)).click();
		//Chờ cho all các item dc load ra
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
		//lưu no lai vô 1 list chua nhung elements
		List <WebElement> allItem= driver.findElements(By.xpath(allItemXpath));
		int allItemsNumber = allItem.size();
		//19 items
		for (int i=0;i<allItemsNumber;i++) {
			String actualValueItem=allItem.get(i).getText();
			if(actualValueItem.equals(expectedValueItem)) {
				allItem.get(i).click();
				break;
			}
		}
	}
	
	public void sleepInSecond(long timeinsecond) {
		try {
			Thread.sleep(timeinsecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
