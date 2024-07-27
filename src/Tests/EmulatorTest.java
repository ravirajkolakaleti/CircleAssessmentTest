package Tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class EmulatorTest {

	AndroidDriver<AndroidElement> driver = null;

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

		Thread.sleep(5000);

	}

	@Test

	public void test1() throws InterruptedException {
		// Look for Quick Powerful Meditation, get text, print it and click on it.
		MobileElement meditationElement = driver
				.findElement(By.xpath("//android.widget.TextView[@text='Quick Powerful Meditation']"));
		String meditationText = meditationElement.getText();
		System.out.println("Meditation Text: " + meditationText);
		meditationElement.click();

		Thread.sleep(3000);

		// Validate Quick Powerful Meditation screen is displayed, get text of it and
		// print it.
		MobileElement meditationScreenElement = driver.findElement(By
				.xpath("//android.widget.TextView[@resource-id='themed-text' and @text='Quick Powerful Meditation']"));
		String meditationScreenText = meditationScreenElement.getText();
		System.out.println("Meditation Screen Text: " + meditationScreenText);
		Assert.assertEquals(meditationScreenText, "Quick Powerful Meditation");

		// Click start button to start the meditation.
		MobileElement startButton = driver.findElement(By.xpath("//android.widget.TextView[@text='']"));
		startButton.click();

		MobileElement themedTextCounter = driver
				.findElement(By.xpath("//android.widget.TextView[@resource-id='themed-text' and @text='00:00']"));
		if (themedTextCounter.isDisplayed() && themedTextCounter.getText().matches("\\d{2}:\\d{2}")) {
			System.out.println("Session started and counter clock is running");
		} else {
			System.out.println("Session did NOT start properly");
			return;
		}

		// Wait until the Congratulations screen is displayed

		WebDriverWait wait = new WebDriverWait(driver, 330); // Adjust the timeout as needed
		WebElement newScreen = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text=\"DONATE\"]")));

		// Track the progress of left counter and continue to track the progress in a
		// loop until you see Congratulation screen.
		boolean isCongratulationScreenDisplayed = false;
		while (!isCongratulationScreenDisplayed) {
			try {
				MobileElement congratulationElement = driver.findElement(By.xpath(
						"//android.widget.TextView[@resource-id=\"themed-text\" and @text=\" Congratulations!\"]"));
				if (congratulationElement.isDisplayed()) {
					isCongratulationScreenDisplayed = true;
					String congratulationText = congratulationElement.getText();
					System.out.println("Congratulation Text: " + congratulationText);

					Thread.sleep(3000);

					// Click on Skip button.
					MobileElement skipButton = driver.findElement(By.xpath("//android.widget.TextView[@text='SKIP']"));
					skipButton.click();
					
					Thread.sleep(3000);

					// Validate you are on the home page.
					MobileElement homePageElement = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='themed-text' and @text='POPULAR']"));
					String homePageText = homePageElement.getText();
					System.out.println("Home Page Text: " + homePageText);
					Assert.assertEquals(homePageText, "POPULAR");
				}

			} catch (Exception e) {
				e.printStackTrace();

			}

		}

	}

	@AfterTest
	public void teardown() {

		if (driver != null) {
			driver.quit();
		}

	}

}
