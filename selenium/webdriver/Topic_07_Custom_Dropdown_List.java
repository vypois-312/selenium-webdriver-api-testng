package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_07_Custom_Dropdown_List {
	WebDriver driver;
	WebDriverWait explicitWait;
	Select select;
	JavascriptExecutor jsExecutor;
	

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		
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
	public void TC_02_Angular() {
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
//		selectItemInCustomDropdown("//ejs-dropdownlist[@id='games']", "//div[@id='games_popup']//li", "Basketball");
//		sleepInSecond(2);
//		selectItemInCustomDropdown("//ejs-dropdownlist[@id='games']", "//div[@id='games_popup']//li", "Hockey");
//		sleepInSecond(2);
//		selectItemInCustomDropdown("//ejs-dropdownlist[@id='games']", "//div[@id='games_popup']//li", "Golf");
//		sleepInSecond(2);
		
		selectItemInCustomDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]", "//div[@id='games_popup']//li", "Hockey");
		sleepInSecond(2);
		selectItemInCustomDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]", "//div[@id='games_popup']//li", "Basketball");
		sleepInSecond(2);
		selectItemInCustomDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]", "//div[@id='games_popup']//li", "Golf");
		sleepInSecond(2);
		
		Assert.assertEquals(getAngularSelectedValueByJS(), "Golf");
		
		
	}

	@Test
	public void TC_03_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInCustomDropdown("//div[@role='listbox']", "//div[@role='listbox']//span[@class='text']", "Elliot Fu");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Elliot Fu']")).isDisplayed());
		
	}
	
	@Test
	public void TC_04_VueJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInCustomDropdown("//div[@role='listbox']", "//div[@role='listbox']//span[@class='text']", "Elliot Fu");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Elliot Fu']")).isDisplayed());
		
	}
	@Test
	public void TC_06_Multiple_Custom_Dropdown() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInCustomDropdown("//div[@role='listbox']", "//div[@role='listbox']//span[@class='text']", "Elliot Fu");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Elliot Fu']")).isDisplayed());
		
	}
	//common
	public void selectMultiItemInEditDropdown(String parentXpath, String allItemXpath, String expectedValueItem) {
		//Click 1 element (parent Xpath) để cho nó xổ hết các item ra
		driver.findElement(By.xpath(parentXpath)).sendKeys(expectedValueItem);;
		sleepInSecond(1);
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
	
	public void selectItemInEditDropdown(String parentXpath, String allItemXpath, String expectedValueItem) {
		//Click 1 element (parent Xpath) để cho nó xổ hết các item ra
		driver.findElement(By.xpath(parentXpath)).sendKeys(expectedValueItem);;
		sleepInSecond(1);
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
	public String getAngularSelectedValueByJS() {
		return (String) jsExecutor.executeScript("return $(\"select[name='games'] option\").text");
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
