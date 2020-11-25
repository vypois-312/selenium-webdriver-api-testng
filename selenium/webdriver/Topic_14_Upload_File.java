package webdriver;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Upload_File {
	WebDriver driver;
	String source_folder = System.getProperty("user.dir");
	String image_name_01 = "Image_01.jpg";
	String image_name_02 = "Image_02.jpg";
	String image_name_03 = "Image_03.jpg";
	String image_01_path = source_folder +"\\uploadFiles\\" + image_name_01;
	String image_02_path = source_folder +"\\uploadFiles\\" + image_name_02;
	String image_03_path = source_folder +"\\uploadFiles\\" + image_name_03;
	
	String chrome_auto_it = source_folder + "\\autoIT\\chromeUploadOneTime.exe";
	String chrome_auto_it_multiple = source_folder + "\\autoIT\\chromeUploadMultiple.exe";
	
	public void TC_01_Sendkey() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		
		WebElement uploadfile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadfile.sendKeys(image_01_path);
		sleepInSecond(1);
		
		uploadfile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadfile.sendKeys(image_01_path);
		sleepInSecond(1);
		
		uploadfile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadfile.sendKeys(image_01_path);
		sleepInSecond(1);
	}

	
	public void TC_02_SendkeyChrome() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		
		WebElement uploadfile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadfile.sendKeys(image_01_path);
		sleepInSecond(1);
		
		uploadfile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadfile.sendKeys(image_01_path);
		sleepInSecond(1);
		
		uploadfile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadfile.sendKeys(image_01_path);
		sleepInSecond(1);
		
	}

	
	public void TC_03_Sendkey_Go_File() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://gofile.io/uploadFiles");
		
		String parentID = driver.getWindowHandle();
		WebElement uploadfile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadfile.sendKeys(image_01_path + "\n" + image_02_path +"\n" + image_03_path);
		sleepInSecond(1);
		
		driver.findElement(By.cssSelector("button#uploadFiles-btnUpload")).click();
		sleepInSecond(20);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Your files have been successfully uploaded']")).isDisplayed());
		driver.findElement(By.cssSelector("a#uploadFiles-uploadRowResult-downloadLink")).click();
		switchToWindowByID(parentID);
		//Verify download icon is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+ image_name_01 +"']/following-sibling::td[@class='text-right']//i[contains(@class,'download')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+ image_name_02 +"']/following-sibling::td[@class='text-right']//i[contains(@class,'download')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+ image_name_03 +"']/following-sibling::td[@class='text-right']//i[contains(@class,'download')]")).isDisplayed());
	}
	
	@Test
	public void TC_04_AutoIT() throws IOException {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.cssSelector(".fileinput-button")).click();
		sleepInSecond(3);
		Runtime.getRuntime().exec(new String[] {chrome_auto_it_multiple, image_01_path, image_02_path});
	}
	
	
	public void TC_05_Java_Robot() throws IOException {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.cssSelector(".fileinput-button")).click();
		sleepInSecond(2);
		
		
	}
	
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
	
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
