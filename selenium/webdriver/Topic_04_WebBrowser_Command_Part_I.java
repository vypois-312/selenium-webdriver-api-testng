package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_WebBrowser_Command_Part_I {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
	}

	@Test
	public void TC_01_Web_Browser() {
		// Apply cho tab/window
		driver.close();
		
		//Apply cho browser
		driver.quit();
		
		//Mở ra 1 web app (Url)
		driver.get("https://www.facebook.com/");
		
		//Các hàm mà tương tác lên trình duyệt/element -> kiểu trả về là void
		// Các hàm lấy ra dữ liệu thì sẽ có kiểu trả về chứa dữ liệu đó (string/int/boolean)
		
		//Lấy ra URL của page hien tai
		String loginPageURL=driver.getCurrentUrl();
		Assert.assertEquals(loginPageURL, "https://www.facebook.com/");
		
		//lay ra html code cua page hien tai
		driver.getPageSource();
		// lay ra title cua page hien tai
		driver.getTitle();
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		
		//lay ra GUID cua tab hien tai
		driver.getWindowHandle();
		//Lay ra GUID cua all tabs/windows
		driver.getWindowHandles();
		//Chờ cho element dc load ra thanh cong trong vong 15s
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		//Chờ cho page dc load thanh cong
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		
		//JS executor
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		
		//Fullscreen
		driver.manage().window().fullscreen();
		driver.manage().window().maximize();
		
		//Back ve page trc
		driver.navigate().back();
		
		// Forward toi page ke tiep
		driver.navigate().forward();
		// tai lai trang
		driver.navigate().refresh();
		// giong driver.get()
		driver.navigate().to("https://www.facebook.com/");// suport tot hon cho back/forward - luu history
		
		//Alert/ Iframe(frame)/ Window(tab)
		driver.switchTo().alert();
		driver.switchTo().frame("");
		driver.switchTo().window("");
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}


