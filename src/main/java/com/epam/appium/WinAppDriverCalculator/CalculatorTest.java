package com.epam.appium.WinAppDriverCalculator;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class CalculatorTest {

	WebDriver driver;

	@BeforeClass
	public void setUp() throws Exception {		
		File app = new File("F:\\", "ContactManager.apk");
		// i use genymotion emulator: Google Nexus 7 - 4.1.1 - API 16 - 800-1280
		DesiredCapabilities capability = new DesiredCapabilities();		
		capability.setCapability("device", "Android");
		capability.setCapability(CapabilityType.BROWSER_NAME, "");		
		capability.setCapability(CapabilityType.VERSION, "4.1.1");
		capability.setCapability("app", app.getAbsolutePath());		
		capability.setCapability("deviceName", "Moto G");
		capability.setCapability("platformName", "Android");		
		capability.setCapability("app-package", "com.example.android.contactmanager-1");		
		capability.setCapability("app-activity", ".ContactManager");		
		driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capability);
	}

	@Test
	public void testApp() throws MalformedURLException {		
		WebElement addContactButton = driver.findElement(By.className("android.widget.Button"));
		addContactButton.click();		
		List<WebElement> textFields = driver.findElements(By.className("android.widget.EditText"));
		textFields.get(0).sendKeys("Yevhen Hrafov");
		textFields.get(1).sendKeys("+380679829142");
		textFields.get(2).sendKeys("hrafov@gmail.com");		
		driver.findElement(By.className("android.widget.Button")).click();
		// assertions here
		String afterfillingcontact = driver.findElement(By.className("android.widget.Button")).getText();
		Assert.assertEquals("OK",afterfillingcontact);
		driver.quit();
	}	
	
}
