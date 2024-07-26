

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class HeyLindaMeditateTest {
	AndroidDriver<AndroidElement> driver = null;
	
	@BeforeTest
	public void setup() {

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
		
		@Test
		public void  MeditateTest() throws InterruptedException {
			
			String String = driver.findElementByXPath("//android.widget.TextView[@text='Power of Love']").getText();

			System.out.println("The Text appears under the image is " + String);

			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Look for Power of Love
			MobileElement powerOfLove = driver.findElementByXPath("//android.widget.TextView[@text='Power of Love']");
			powerOfLove.click();

			// Validate Power of Love page is displayed
			MobileElement powerOfLovePage = driver
					.findElementByXPath("//android.widget.TextView[@text='Power of Love']");
			if (powerOfLovePage.isDisplayed()) {
				System.out.println("Power of Love page is displayed");
			} else {
				System.out.println("Power of Love page is NOT displayed");
				return;
			}

			// Click on Start button
			MobileElement startButton = driver.findElement(By.xpath("//android.widget.TextView[@text='']"));
			startButton.click();

			// Verify if the session started by verifying the themed text counter clock is
			// running
			MobileElement themedTextCounter = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='themed-text' and @text='00:00']"));
			if (themedTextCounter.isDisplayed() && themedTextCounter.getText().matches("\\d{2}:\\d{2}")) {
				System.out.println("Session started and counter clock is running");
			} else {
				System.out.println("Session did NOT start properly");
				return;
			}

						
			// Wait until the Congratulations screen is displayed
			
            WebDriverWait wait = new WebDriverWait(driver, 150); // Adjust the timeout as needed
            WebElement newScreen = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text=\"DONATE\"]")));
      //      String buttonText = newScreen.getText();
       //     System.out.println("Congratulations screen displayed with button text: " + buttonText);
            
            Thread.sleep(3000); 

			// Validate the Congratulations screen is displayed and capture the button text
			WebElement congratulationScreen = driver.findElement(By.xpath("//android.widget.TextView[@text=\"DONATE\"]"));
			if (congratulationScreen.isDisplayed()) {
				String Text = congratulationScreen.getText();
				System.out.println("Congratulations screen displayed with button text: " + Text);
			} else {
				System.out.println("Congratulations screen is NOT displayed");
				return;
			}

			// Click on Skip button
			MobileElement skipButton = driver.findElement(By.xpath("//android.widget.TextView[@text='SKIP']"));
			skipButton.click();
			
			System.out.println("Sucessfully executed the meditation test scenario");

		
		
	}
		
		public void close() {
			if (driver != null) {
				driver.quit();
			}
		}
	}

