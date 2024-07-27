package Tests;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;

public class LindaNavTest {
    private AndroidDriver<AndroidElement> driver;
    

    @BeforeClass
    public void setUp() throws Exception {
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
    public void testHeyLindaApp() throws InterruptedException {
        // Open the Hey Linda app (Assuming it opens automatically with the driver initialization)

        // Swipe up to get to Sleep Section
        swipeUp();

        // Validate Deep Sleep text is displayed, get Deep Sleep text and print it
        AndroidElement deepSleepElement = driver.findElementByXPath("//android.widget.TextView[@text='Deep Sleep']");
        Assert.assertTrue(deepSleepElement.isDisplayed(), "Deep Sleep text is not displayed");
        System.out.println("Deep Sleep text: " + deepSleepElement.getText());

        // Swipe down to get to Popular section
        swipeDown();

        // Validate Power of Love text is displayed, get Power of Love text and print it
        AndroidElement powerOfLoveElement = driver.findElementByXPath("//android.widget.TextView[@text='Power of Love']");
        Assert.assertTrue(powerOfLoveElement.isDisplayed(), "Power of Love text is not displayed");
        System.out.println("Power of Love text: " + powerOfLoveElement.getText());

        // Swipe left on Power of Love to get to Yawn and Stretch text
        swipeLeft(powerOfLoveElement);

        // Get Yawn and Stretch text and print it
        AndroidElement yawnAndStretchElement = driver.findElementByXPath("//android.widget.TextView[@text='Yawn and Stretch']");
        System.out.println("Yawn and Stretch text: " + yawnAndStretchElement.getText());

        // Swipe right to get back to Power of Love and get Power of Love text and print it
        swipeRight(yawnAndStretchElement);
        AndroidElement powerOfLoveElementAgain = driver.findElementByXPath("//android.widget.TextView[@text='Power of Love']");
        System.out.println("Power of Love text again: " + powerOfLoveElementAgain.getText());
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void swipeUp() {
        new TouchAction<>(driver)
                .press(PointOption.point(500, 1500))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
                .moveTo(PointOption.point(500, 500))
                .release()
                .perform();
    }

    private void swipeDown() {
        new TouchAction<>(driver)
                .press(PointOption.point(500, 500))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
                .moveTo(PointOption.point(500, 1500))
                .release()
                .perform();
    }

    private void swipeLeft(MobileElement element) {
        int startX = element.getLocation().getX() + element.getSize().getWidth();
        int startY = element.getLocation().getY() + element.getSize().getHeight() / 2;
        int endX = element.getLocation().getX();

        new TouchAction<>(driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
                .moveTo(PointOption.point(endX, startY))
                .release()
                .perform();
    }

    private void swipeRight(MobileElement element) {
        int startX = element.getLocation().getX();
        int startY = element.getLocation().getY() + element.getSize().getHeight() / 2;
        int endX = element.getLocation().getX() + element.getSize().getWidth();

        new TouchAction<>(driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
                .moveTo(PointOption.point(endX, startY))
                .release()
                .perform();
    }
}
