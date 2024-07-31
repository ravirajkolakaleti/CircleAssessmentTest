package Tests;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

public class LindaNavTest {
    @SuppressWarnings("rawtypes")
	private AndroidDriver driver;

    @SuppressWarnings("rawtypes")
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

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);

        Thread.sleep(5000);
    }

    @Test
    public void testHeyLindaApp() throws InterruptedException {
        // Open the Hey Linda app (Assuming it opens automatically with the driver initialization)

        // Swipe up to get to Sleep Section
        swipeUp();

        // Validate Deep Sleep text is displayed, get Deep Sleep text and print it
        WebElement deepSleepElement = driver.findElement(By.xpath("//android.widget.TextView[@text='Deep Sleep']"));
        Assert.assertTrue(deepSleepElement.isDisplayed(), "Deep Sleep text is not displayed");
        System.out.println("Deep Sleep text: " + deepSleepElement.getText());
        
        deepSleepElement.click();
        
        Thread.sleep(3000);
        
        WebElement deepSleepText = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"themed-text\" and @text=\"Deep Sleep\"]"));
        Assert.assertTrue(deepSleepText.isDisplayed(), "Deep Sleep text is not displayed");
        System.out.println("Deep Sleep Screen is displayed: " + deepSleepText.getText());
        
        Thread.sleep(3000);
        
        WebElement goBack = driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Go back\"]/android.widget.ImageView"));
        goBack.click();
        
        Thread.sleep(3000);
        

        // Swipe down to get to Popular section
        swipeDown();

        // Validate Power of Love text is displayed, get Power of Love text and print it
        WebElement powerOfLoveElement = driver.findElement(By.xpath("//android.widget.TextView[@text='Power of Love']"));
        Assert.assertTrue(powerOfLoveElement.isDisplayed(), "Power of Love text is not displayed");
        System.out.println("Power of Love text: " + powerOfLoveElement.getText());
        
        Thread.sleep(3000);

        // Swipe left on Power of Love to get to Yawn and Stretch text
        swipeLeft(powerOfLoveElement);
        
        WebElement QuickPowerfulElement = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Quick Powerful Meditation\"]"));
        Assert.assertTrue(QuickPowerfulElement.isDisplayed(), "Power of Love text is not displayed");
        System.out.println("Deep Breathing text: " + QuickPowerfulElement.getText());
        
        swipeLeft(QuickPowerfulElement);
        
        Thread.sleep(2000);
      
        
        WebElement DeepBreathingElement = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Deep Breathing\"]"));
        Assert.assertTrue(DeepBreathingElement.isDisplayed(), "Power of Love text is not displayed");
        System.out.println("Deep Breathing text: " + DeepBreathingElement.getText());
      
        swipeLeft(DeepBreathingElement);

        // Get Yawn and Stretch text and print it
        WebElement yawnAndStretchElement = driver.findElement(By.xpath("//android.widget.TextView[@text='Yawn and Stretch']"));
        System.out.println("Yawn and Stretch text: " + yawnAndStretchElement.getText());
        
        yawnAndStretchElement.click();
        
        Thread.sleep(3000);
        
     // Get Yawn and Stretch screen text and print it
        WebElement yawnAndStretchText = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"themed-text\" and @text=\"Yawn and Stretch\"]"));
        System.out.println("Yawn and Stretch screen displays and tile text is : " + yawnAndStretchText.getText());
        
        WebElement goBack1 = driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Go back\"]/android.widget.ImageView"));
        goBack1.click();
        
        Thread.sleep(3000);
        
      
        // Swipe right to get back to Power of Love and get Power of Love text and print it
        swipeRight(yawnAndStretchElement);
        
        Thread.sleep(2000);
        
        swipeRight(DeepBreathingElement);
        
        Thread.sleep(2000);
        
        swipeRight(QuickPowerfulElement);

        
        WebElement powerOfLoveElementAgain = driver.findElement(By.xpath("//android.widget.TextView[@text='Power of Love']"));
        System.out.println("Power of Love text again: " + powerOfLoveElementAgain.getText());
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void swipeUp() {
        int startX = 500;
        int startY = 1500;
        int endX = 500;
        int endY = 300;
        performSwipe(startX, startY, endX, endY);
    }

    private void swipeDown() {
        int startX = 500;
        int startY = 500;
        int endX = 500;
        int endY = 1500;
        performSwipe(startX, startY, endX, endY);
    }

    private void swipeLeft(WebElement element) {
        int startX = element.getLocation().getX() + element.getSize().getWidth();
        int startY = element.getLocation().getY() + element.getSize().getHeight() / 2;
        int endX = element.getLocation().getX();
        performSwipe(startX, startY, endX, startY);
    }

    private void swipeRight(WebElement element) {
        int startX = element.getLocation().getX();
        int startY = element.getLocation().getY() + element.getSize().getHeight() / 2;
        int endX = element.getLocation().getX() + element.getSize().getWidth();
        performSwipe(startX, startY, endX, startY);
    }

    private void performSwipe(int startX, int startY, int endX, int endY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1)
            .addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY))
            .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
            .addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY))
            .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
    }
}
