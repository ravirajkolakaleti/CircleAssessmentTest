package Utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class BasicTestDevice {
	
	public AndroidDriver<AndroidElement> driver = null;

	@BeforeTest
	public void Appsetup() throws InterruptedException, MalformedURLException {
		
		

		// Set the desired capabilities
		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("deviceName", "emulator-5554");
		caps.setCapability("platformVersion", "8.1");
		caps.setCapability("platformName", "Android");
		caps.setCapability("automationName", "UiAutomator2");
		
		caps.setCapability("appPackage", "com.meditation.heylinda");
		caps.setCapability("appActivity", "com.meditation.heylinda.MainActivity");

		// com.meditation.heylinda.MainActivity - Hey linda challenge
		//

		driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723"), caps);
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		

	}
	@AfterTest
	public void teardown() {
		
		if (driver != null) {
			driver.quit();
			
	}

	}
}



