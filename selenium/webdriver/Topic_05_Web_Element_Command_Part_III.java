package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Element_Command_Part_III {
	WebDriver driver;
	By EmailTextboxBy = By.id("mail");
	By educationTextAreaBy = By.id("edu");
	By ageUnder18Radio = By.id("under_18");
	By jobRole01Dropdown = By.id("job1");
	By developmentCheckbox =By.id("development");
	By slider_01 = By.id("slider-1");
	By javaLanguageCheckbox = By.id("java");
	
	By PasswordTextboxBy = By.id("password");
	By disabledRadioBy = By.id("radio-disabled");
	By jobRole_03Dropdown = By.id("job3");
	By interestCheckbox = By.id("check-disbaled");
	By slider_02 = By.id("slider-2");
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://automationfc.github.io/basic-form/index.html");
	}

	@Test
	public void TC_01_Element_Displayed() throws InterruptedException {
		//Email textbox
		if (driver.findElement(By.id("mail")).isDisplayed()) {
			System.out.println("Element is displayed");
			driver.findElement(By.id("mail")).sendKeys("Automation Testing");
		} else {
			System.out.println("Element is not displayed");
		}
		Thread.sleep(3000);
		
		//Education textarea
		if (driver.findElement(By.id("edu")).isDisplayed()) {
			System.out.println("Element is displayed");
			driver.findElement(By.id("edu")).sendKeys("Automation Testing");
		} else {
			System.out.println("Element is not displayed");
		}
		Thread.sleep(3000);
		
		//Age under 18  
		if (driver.findElement(By.id("under_18")).isDisplayed()) {
			System.out.println("Element is displayed");
			driver.findElement(By.id("under_18")).click();
		} else {
			System.out.println("Element is not displayed");
		}
		Thread.sleep(3000);
		
		
	}
	
	@Test
	public void TC_02_Element_Displayed() {
		driver.navigate().refresh();
		if(isElementDisplayed(EmailTextboxBy)) {
			driver.findElement(EmailTextboxBy).sendKeys("Automation Testing");
		}
		if(isElementDisplayed(educationTextAreaBy)) {
			driver.findElement(educationTextAreaBy).sendKeys("Automation Testing");
		}
		if(isElementDisplayed(ageUnder18Radio)) {
			driver.findElement(ageUnder18Radio).click();
		}
		
	}
	
	public void TC_03_Element_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.navigate().refresh();
		
		Assert.assertTrue(isElementEnabled(EmailTextboxBy));
		Assert.assertTrue(isElementEnabled(educationTextAreaBy));
		Assert.assertTrue(isElementEnabled(ageUnder18Radio));
		Assert.assertTrue(isElementEnabled(jobRole01Dropdown));
		
		Assert.assertFalse(isElementEnabled(PasswordTextboxBy));
		Assert.assertFalse(isElementEnabled(disabledRadioBy));
		Assert.assertFalse(isElementEnabled(jobRole_03Dropdown));
		Assert.assertFalse(isElementEnabled(slider_02));
	}
	
	public void TC_04_Element_Selected() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.navigate().refresh();
		
		// verify age under 18 and java checkbox deselected
		Assert.assertFalse(isElementSelected(ageUnder18Radio));
		Assert.assertFalse(isElementSelected(javaLanguageCheckbox));
		
		driver.findElement(ageUnder18Radio).click();
		driver.findElement(javaLanguageCheckbox).click();
		Thread.sleep(3000);
		Assert.assertTrue(isElementSelected(ageUnder18Radio));
		Assert.assertTrue(isElementSelected(javaLanguageCheckbox));
	}
	
	@Test
	public void TC_05_Validate_Register_Form() throws InterruptedException {
		driver.get("https://login.mailchimp.com/signup/");	
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("new_username")).sendKeys("automationtest");
		
		//Verify Sign up button disabled
		Assert.assertFalse(isElementEnabled(By.id("create-account")));
		
		driver.findElement(By.id("new_password")).sendKeys("auto");
		Thread.sleep(2000);
		Assert.assertFalse(isElementEnabled(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		
		// Special char
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("auto$#@");
		Thread.sleep(2000);
		Assert.assertFalse(isElementEnabled(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='special-char completed' and text()='One special character']")));
		
		// Uppercase
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("Auto$#@");
		Thread.sleep(2000);
		Assert.assertFalse(isElementEnabled(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='special-char completed' and text()='One special character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
		
		
		//8 Char
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("Automation$#@");
		Thread.sleep(2000);
		Assert.assertFalse(isElementEnabled(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='special-char completed' and text()='One special character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")));
		
		//Number
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("Aut$1");
		Thread.sleep(2000);
		Assert.assertFalse(isElementEnabled(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='special-char completed' and text()='One special character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='number-char completed' and text()='One number']")));
		
		//Full
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("Automation$#@123");
		Thread.sleep(2000);
		Assert.assertTrue(isElementEnabled(By.id("create-account")));
		Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
		Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='special-char completed' and text()='One special character']")));
		Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
		Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")));
		
		Assert.assertTrue(isElementDisplayed(By.xpath("//h4[text()=\"Your password is secure and you're all set!\"]")));
		
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
	public boolean isElementDisplayed(By by) {
		if (driver.findElement(by).isDisplayed()) {
			System.out.println("Element is displayed");
			return true;
		} else {
			System.out.println("Element is undisplayed");
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
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
