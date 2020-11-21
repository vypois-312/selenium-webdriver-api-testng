package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Button_Radio_Checkbox {
	WebDriver driver;
	By loginButton = By.cssSelector(".fhs-btn-login");
	By loginUsername = By.cssSelector("#login_username");
	By loginPassword = By.cssSelector("#login_password");
	By summerRadio = By.xpath("//input[@value='Summer']");
	By checkedCheckbox = By.xpath("//span[contains(text(), 'Checked')]/preceding-sibling::div/input");
	By indeterminateCheckbox = By.xpath("(//span[contains(text(), 'Indeterminate')]/preceding-sibling::div/input)[1]");
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
		// Disabled field
		Assert.assertFalse(isElementEnabled(loginButton));
		driver.findElement(loginUsername).sendKeys("dam@gmail.com");
		driver.findElement(loginPassword).sendKeys("123456");
		
		sleepInSecond(2);
		
		//Enabled field
		Assert.assertTrue(isElementEnabled(loginButton));
		driver.navigate().refresh();
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
		removeDisabledAttributeByJS(loginButton);
		sleepInSecond(5);
		
		//enabled field
		Assert.assertTrue(isElementEnabled(loginButton));
		driver.findElement(loginButton).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
	}
	
	
	public void TC_02_Checkbox_Select_All() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		//Select All
		List<WebElement> checkboxes=driver.findElements(By.xpath("//input[@type='checkbox']"));
		for (WebElement checkbox : checkboxes) {
			checkToCheckboxOrRadio(checkbox);
			sleepInSecond(1);
		}
		
		//Verify
		for (WebElement checkbox : checkboxes) {
			Assert.assertTrue(isElementSelected(checkbox));
		}
		
		//Deselect all
		for (WebElement checkbox : checkboxes) {
			uncheckToCheckbox(checkbox);
			sleepInSecond(1);
		}
		
	}

	@Test
	public void TC_03_Checkbox_Default() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		sleepInSecond(2);
		checkToCheckboxOrRadio(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")));
		sleepInSecond(1);
		Assert.assertTrue(isElementSelected(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"))));
		uncheckToCheckbox(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")));
		Assert.assertFalse(isElementSelected(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"))));
		
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		sleepInSecond(2);
		checkToCheckboxOrRadio(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")));
		sleepInSecond(1);
		Assert.assertTrue(isElementSelected(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"))));
		uncheckToCheckbox(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")));
		Assert.assertTrue(isElementSelected(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"))));
		
	}
	
	@Test
	public void TC_04_Checkbox_Custom() {
		driver.get("https://material.angular.io/components/radio/examples");
		
		//Case 03
		//Click - div - visible - pass
		
		//Verify - Input - Pass
//		checkToCheckboxOrRadio(driver.findElement(By.xpath("//input[@value='Summer']/preceding-sibling::div[@class='mat-radio-outer-circle']")));
//		Assert.assertTrue(isElementSelected(By.xpath("//input[@value='Summer']")));
		
		//Case 04
		clickToElementByJS(driver.findElement(summerRadio));
		Assert.assertTrue(isElementSelected(driver.findElement(summerRadio)));
		
		driver.get("https://material.angular.io/components/checkbox/examples");
		clickToElementByJS(driver.findElement(checkedCheckbox));
		clickToElementByJS(driver.findElement(indeterminateCheckbox));
		sleepInSecond(2);
		
		Assert.assertTrue(isElementSelected(driver.findElement(checkedCheckbox)));
		Assert.assertTrue(isElementSelected(driver.findElement(indeterminateCheckbox)));
		
		clickToElementByJS(driver.findElement(checkedCheckbox));
		clickToElementByJS(driver.findElement(indeterminateCheckbox));
		sleepInSecond(2);
		
		Assert.assertFalse(isElementSelected(driver.findElement(checkedCheckbox)));
		Assert.assertFalse(isElementSelected(driver.findElement(indeterminateCheckbox)));
	}
	public boolean isElementEnabled(By by) {
		if (driver.findElement(by).isEnabled()) {
			System.out.println("Element is enabled");
			return true;
		} else {
			System.out.println("Element is disabled");
			return false;
		}
	}
	
	public boolean isElementSelected(By by) {
		if (driver.findElement(by).isSelected()) {
			System.out.println("Element is selected");
			return true;
		} else {
			System.out.println("Element is de-selected");
			return false;
		}
	}
	
	public boolean isElementSelected(WebElement element) {
		if (element.isSelected()) {
			System.out.println("Element is selected");
			return true;
		} else {
			System.out.println("Element is de-selected");
			return false;
		}
	}
	
	public void sleepInSecond(long timeinsecond) {
		try {
			Thread.sleep(timeinsecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
	
	public void checkToCheckboxOrRadio(WebElement element) {
		if(!element.isSelected()) {
			element.click();
		}
	}
	public void uncheckToCheckbox(WebElement element) {
		if(element.isSelected()) {
			element.click();
		}
	}
	
	public void removeDisabledAttributeByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", element);
	}
	
	public void clickToElementByJS(WebElement element) {
		jsExecutor.executeScript("arguments[0].click()", element);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
