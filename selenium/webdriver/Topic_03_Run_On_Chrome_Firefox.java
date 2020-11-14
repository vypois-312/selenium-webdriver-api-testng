package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_03_Run_On_Chrome_Firefox {
	WebDriver driver;
	String project_location = System.getProperty("user.dir");

	@Test
	public void TC_01_Run_On_Firefox() {
		// Selenium 2.53.1
		// Firefox 47.0.2
		// No need geckodriver
		driver = new FirefoxDriver();
		System.out.println(driver.toString());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
		driver.quit();
	}

	@Test
	public void TC_02_Run_On_Chrome() {
		// Selenium 2.53.1
		// Chrome latest
		// chromedriver latest
		
		System.setProperty("webdriver.chrome.driver", project_location + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println(driver.toString());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
		driver.quit();
	}

}


