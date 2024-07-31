package Tests;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class NewTestLindaApp {

    public AndroidDriver<AndroidElement> driver;

    @BeforeClass
    public void setUp() throws MalformedURLException, InterruptedException {
        // Set the desired capabilities
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("deviceName", "emulator-5554");
        caps.setCapability("platformVersion", "8.1");
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");

        caps.setCapability("appPackage", "com.meditation.heylinda");
        caps.setCapability("appActivity", "com.meditation.heylinda.MainActivity");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723"), caps);

        Thread.sleep(5000);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testHeyLindaApp() {
        // Look for Quick Powerful Meditation, get text, print it and click on it.
        MobileElement meditationElement = driver
                .findElement(By.xpath("//android.widget.TextView[@text='Quick Powerful Meditation']"));
        String meditationText = meditationElement.getText();
        System.out.println("Meditation Text: " + meditationText);
        meditationElement.click();

        // Validate Quick Powerful Meditation screen is displayed, get text of it and print it.
        MobileElement meditationScreenElement = driver.findElement(By
                .xpath("//android.widget.TextView[@resource-id='themed-text' and @text='Quick Powerful Meditation']"));
        String meditationScreenText = meditationScreenElement.getText();
        System.out.println("Meditation Screen Text: " + meditationScreenText);
        Assert.assertEquals(meditationScreenText, "Quick Powerful Meditation");

        // Click start button to start the meditation.
        MobileElement startButton = driver.findElement(By.xpath("//android.widget.TextView[@text='']"));
        startButton.click();

        // Check if the counter clock has started
        MobileElement themedTextCounter = driver
                .findElement(By.xpath("//android.widget.TextView[@resource-id='themed-text' and @index='0']"));
        if (themedTextCounter.isDisplayed() && themedTextCounter.getText().matches("\\d{2}:\\d{2}")) {
            System.out.println("Session started and counter clock is running");
        } else {
            System.out.println("Session did NOT start properly");
            return;
        }

        boolean isSingleDigitRemaining = false;

        while (!isSingleDigitRemaining) {
            try {
                // Calculate and print the time difference every 30 seconds
                Thread.sleep(40000); // Wait for 30 seconds

                // Fetch and calculate the time difference
                MobileElement leftCounter = driver
                        .findElement(By.xpath("//android.widget.TextView[@resource-id='themed-text' and @index='0']"));
                MobileElement rightCounter = driver
                        .findElement(By.xpath("//android.widget.TextView[@resource-id='themed-text' and @index='4']"));

                String leftTime = leftCounter.getText();
                String rightTime = rightCounter.getText();

                // Assuming the format is "MM:SS"
                int leftMinutes = Integer.parseInt(leftTime.split(":")[0]);
                int leftSeconds = Integer.parseInt(leftTime.split(":")[1]);
                int rightMinutes = Integer.parseInt(rightTime.split(":")[0]);
                int rightSeconds = Integer.parseInt(rightTime.split(":")[1]);

                int leftTotalSeconds = leftMinutes * 60 + leftSeconds;
                int rightTotalSeconds = rightMinutes * 60 + rightSeconds;
                int remainingTime = rightTotalSeconds - leftTotalSeconds;

                System.out.println("Remaining Time: " + remainingTime + " seconds");

                // Check if remaining time is in single digits
                if (remainingTime < 10) {
                    isSingleDigitRemaining = true;
                    break;
                }

            } catch (Exception e) {
                // Handle any exceptions if needed
                System.err.println("Exception occurred during time tracking: " + e.getMessage());
            }
        }

        // Wait until the Congratulations screen is displayed
        WebDriverWait wait = new WebDriverWait(driver, 330); // Adjust the timeout as needed
        WebElement congratulationsElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text=\"DONATE\"]")));

        // Proceed to click on Skip button and validate the home page
        if (congratulationsElement.isDisplayed()) {
            try {
                MobileElement skipButton = driver.findElement(By.xpath("//android.widget.TextView[@text='SKIP']"));
                skipButton.click();

                // Validate you are on the home page.
                WebDriverWait homePageWait = new WebDriverWait(driver, 30);
                WebElement homePageElement = homePageWait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//android.widget.TextView[@resource-id='themed-text' and @text='POPULAR']")));
                String homePageText = homePageElement.getText();
                System.out.println("Home Page Text: " + homePageText);
                Assert.assertEquals(homePageText, "POPULAR");
            } catch (Exception e) {
                System.err.println("Exception while handling Skip button: " + e.getMessage());
            }
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
