package Tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class BaseTest {
	
	AndroidDriver<AndroidElement> driver = null;
	
	
	@BeforeTest
	public void setup() throws MalformedURLException {
		
		try {
		
			// Set the desired capabilities
			DesiredCapabilities caps = new DesiredCapabilities();

			caps.setCapability("browserstack.user", "ravirajkolakalet_FRbOhZ");
			caps.setCapability("browserstack.key", "Uoc7pPk229EZAhd5d7wv");

			caps.setCapability("platformName", "android");
			caps.setCapability("platformVersion", "14.0");
			caps.setCapability("deviceName", "Google Pixel 8");
			caps.setCapability("app", "bs://1cc1b77d2bafbf42e864de9423f7c6293a5e2184");

			caps.setCapability("name", "Appium Linda App Test");

			driver = new AndroidDriver<AndroidElement>(new URL("https://hub.browserstack.com/wd/hub"), caps);
	
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	
	
	@AfterTest
	public void teardown() {
		
		if (driver != null) {
			driver.quit();
	}

	}
}
