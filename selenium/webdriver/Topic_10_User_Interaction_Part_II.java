package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_User_Interaction_Part_II {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	String project_location = System.getProperty("user.dir");
	String JsHelperPath = project_location + "\\dragAndDrop\\drag_and_drop_helper";
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public void TC_01_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		//Verify quit not contain hover
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class, 'context-menu-icon-quit') "+"and not(contains(@class, 'context-menu-hover')) and not(contains(@class, 'context-menu-visible'))]")).isDisplayed());
		
		action.moveToElement(driver.findElement(By.xpath("//li[contains(@class, 'context-menu-icon-quit') "+"and not(contains(@class, 'context-menu-hover')) and not(contains(@class, 'context-menu-visible'))]"))).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class, 'context-menu-icon-quit') "+"and contains(@class, 'context-menu-hover') and contains(@class, 'context-menu-visible')]")).isDisplayed());
		
		//Click to Quit
		driver.findElement(By.xpath("//li[contains(@class, 'context-menu-icon-quit') "+"and contains(@class, 'context-menu-hover') and contains(@class, 'context-menu-visible')]")).click();
		
		//Verify Alert displayed
		Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");
		driver.switchTo().alert().accept();
	}
	@Test
	public void TC_02_Drag_Drop_HTML4() {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
		if (driver.findElement(By.xpath("//button[text()='Accept Cookies']")).isDisplayed()) {
			driver.findElement(By.xpath("//button[text()='Accept Cookies']")).click();
		}
		sleepInSecond(5);
		WebElement SourceCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement TargetCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));
		action.dragAndDrop(SourceCircle, TargetCircle).perform();
		
		Assert.assertEquals(TargetCircle.getText(), "You did great!");
	}

	public void TC_03_Drag_Drop_HTML5_JQUERY() throws IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		String sourceRectangleCss = "#column-a";
		String targetRectangleCss = "column-b";
		
		String jsHelperContent = getJSFileContent(JsHelperPath);
		//Drag A to B
		jsHelperContent = jsHelperContent + "$(\"" + sourceRectangleCss + "\").simulateDragDrop({ dropTarget: \"" + targetRectangleCss + "\"});";
		jsExecutor.executeScript(jsHelperContent);
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
		
		//Drag B to A
		jsExecutor.executeScript(jsHelperContent);
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());
		
	}

	@Test
	public void TC_04_Drag_Drop_HTML5_Position() throws AWTException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		sleepInSecond(2);
		//A to B
		drag_and_drop_html5_by_offset("//div[@id='column-a']", "//div[@id='column-b']");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
		//B to A
		drag_and_drop_html5_by_offset("//div[@id='column-a']", "//div[@id='column-b']");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());
	}
	

	public void sleepInSecond(long timeinsecond) {
		try {
			Thread.sleep(timeinsecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getJSFileContent(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
	
	public void drag_and_drop_html5_by_offset(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
