package webdriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropdown {
	WebDriver driver;
	Select select;
	String firstName, lastName, email, password, companyName, date, month, year;
	By genderMaleBy = By.id("gender-male");
	By firstNameBy = By.id("FirstName");
	By lastNameBy = By.id("LastName");
	By emailBy = By.id("Email");
	By companyBy = By.id("Company");
	By dateBy = By.name("DateOfBirthDay");
	By monthBy = By.name("DateOfBirthMonth");
	By yearBy = By.name("DateOfBirthYear");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");

		firstName = "Joe";
		lastName = "Biden";
		email = generateEmail();
		password = "123123";
		companyName = "Anqueda";
		date = "3";
		month = "March";
		year = "1933";
	}

	@Test
	public void TC_01_Register() {
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);

		select = new Select(driver.findElement(dateBy));
		// Verify date of birth dropdpwn is single dropdown
		Assert.assertFalse(select.isMultiple());
		// chon ngay 10
		select.selectByVisibleText(date);
		// verify item selected
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
		// Verify all items in dropdown
		Assert.assertEquals(select.getOptions().size(), 32);

		// Month
		select = new Select(driver.findElement(monthBy));
		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		Assert.assertEquals(select.getOptions().size(), 13);
		// Year
		select = new Select(driver.findElement(yearBy));
		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
//		Assert.assertEquals(select.getOptions().size(), 111);

		driver.findElement(emailBy).sendKeys(email);
		driver.findElement(companyBy).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();

	}

	@Test
	public void TC_02_Multiple() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		select = new Select(driver.findElement(By.name("user_job2")));
		List<String> itemText = new ArrayList<String>();
		itemText.add("Manual");
		itemText.add("Mobile");
		itemText.add("Security");
		itemText.add("Functional UI");
		
		select.selectByVisibleText("Manual");
		select.selectByVisibleText("Mobile");
		select.selectByVisibleText("Security");
		select.selectByVisibleText("Functional UI");
		
		List<WebElement> itemSelected= select.getAllSelectedOptions();
		List<String> itemSelectedText = new ArrayList<String>();
		
		//Java Collection
		
		//Verify 4 items selected
		Assert.assertEquals(itemSelected.size(), 4);
		
		for (WebElement item :itemSelected) {
			itemSelectedText.add(item.getText());
			System.out.println(item.getText());	
		}
		
		Assert.assertTrue(itemSelectedText.contains("Manual"));
		Assert.assertTrue(itemSelectedText.contains("Mobile"));
		Assert.assertTrue(itemSelectedText.contains("Security"));
		Assert.assertTrue(itemSelectedText.contains("Functional UI"));
		
		Assert.assertEquals(itemText, itemSelectedText);
	}

	public String generateEmail() {
		Random rand = new Random();
		return "joe" + rand.nextInt(99999) + "@github.io";
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
