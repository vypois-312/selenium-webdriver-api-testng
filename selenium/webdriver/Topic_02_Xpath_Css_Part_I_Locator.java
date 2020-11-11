package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Part_I_Locator {
	WebDriver driver;
	Select select;
	@Test
	public void TC_01() {
	// 1 - mo trinh duyet firefox
	  driver= new FirefoxDriver();
	// 2 - nhap vao app facebook
	  driver.get("https://m.facebook.com/");
	// 3 - nhap email textbox
	//Action: nhap/chon/hover...
	  driver.findElement(By.id("m_login_email")).sendKeys("vypois@gmail.com");
	//name
	  driver.findElement(By.name("pass")).sendKeys("123456");
	//Tagname
	  int linknumber = driver.findElements(By.tagName("a")).size();
	  System.out.println("Link_number = "+ linknumber);
	  
	// Link Text (link -a)
	  driver.findElement(By.linkText("English (UK)")).click();
	  
	// Partial link text (link -a)
	  driver.findElement(By.partialLinkText("Việt")).click();
	  
	//CSS
	  driver.findElement(By.cssSelector("#m_login_email")).sendKeys("vypois@gmail.com");
	  driver.findElement(By.cssSelector("input[id='m_login_email']")).clear();
	  
	//Xpath
	  driver.findElement(By.xpath("//input[@id='m_login_password']")).sendKeys("123456");
	  driver.findElement(By.xpath("//input[@id='m_login_password']")).clear();
	//Xpath text
	  driver.findElement(By.xpath("//a[text()='English (UK)']")).click();
  }
  
}
