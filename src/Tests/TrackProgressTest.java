package Tests;


import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
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

public class TrackProgressTest {

    public static String userName = "ravirajkolakalet_FRbOhZ";
    public static String accessKey = "Uoc7pPk229EZAhd5d7wv";
    public static String appId = "bs://1cc1b77d2bafbf42e864de9423f7c6293a5e2184"; // This is the app ID uploaded to BrowserStack

    public AndroidDriver<MobileElement> driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
    	 DesiredCapabilities caps = new DesiredCapabilities();
         caps.setCapability("browserstack.user", userName);
         caps.setCapability("browserstack.key", accessKey);
         
         caps.setCapability("platformName", "android");
         caps.setCapability("platformVersion", "14.0");
         caps.setCapability("deviceName", "Google Pixel 8");
         caps.setCapability("app", appId);
         caps.setCapability("name", "Appium Linda App Nav Test");

         driver = new AndroidDriver<>(new URL("https://hub.browserstack.com/wd/hub"), caps);
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testHeyLindaApp() {
        //  Look for Quick Powerful Meditation, get text, print it and click on it.
        MobileElement meditationElement = driver.findElement(By.xpath("//android.widget.TextView[@text='Quick Powerful Meditation']"));
        String meditationText = meditationElement.getText();
        System.out.println("Meditation Text: " + meditationText);
        meditationElement.click();

        //  Validate Quick Powerful Meditation screen is displayed, get text of it and print it.
        MobileElement meditationScreenElement = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='themed-text' and @text='Quick Powerful Meditation']"));
        String meditationScreenText = meditationScreenElement.getText();
        System.out.println("Meditation Screen Text: " + meditationScreenText);
        Assert.assertEquals(meditationScreenText, "Quick Powerful Meditation");

        //  Click start button to start the meditation.
        MobileElement startButton = driver.findElement(By.xpath("//android.widget.TextView[@text='']"));
        startButton.click();
        
        MobileElement themedTextCounter = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='themed-text' and @text='00:00']"));
		if (themedTextCounter.isDisplayed() && themedTextCounter.getText().matches("\\d{2}:\\d{2}")) {
			System.out.println("Session started and counter clock is running");
		} else {
			System.out.println("Session did NOT start properly");
			return;
		}

					
		// Wait until the Congratulations screen is displayed
		
        WebDriverWait wait = new WebDriverWait(driver, 330); // Adjust the timeout as needed
        WebElement newScreen = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text=\"DONATE\"]")));

        //  Track the progress of left counter and continue to track the progress in a loop until you see Congratulation screen.
        boolean isCongratulationScreenDisplayed = false;
        while (!isCongratulationScreenDisplayed) {
            try {
                MobileElement congratulationElement = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"themed-text\" and @text=\" Congratulations!\"]"));
                if (congratulationElement.isDisplayed()) {
                    isCongratulationScreenDisplayed = true;
                    String congratulationText = congratulationElement.getText();
                    System.out.println("Congratulation Text: " + congratulationText);
                    
                    //  Click on Skip button.
                    MobileElement skipButton = driver.findElement(By.xpath("//android.widget.TextView[@text='SKIP']"));
                    skipButton.click();

                    //  Validate you are on the home page.
                    MobileElement homePageElement = driver.findElement(By.id("//android.widget.TextView[@resource-id=\"themed-text\" and @text=\"POPULAR\"]"));
                    String homePageText = homePageElement.getText();
                    System.out.println("Home Page Text: " + homePageText);
                    Assert.assertEquals(homePageText, "POPULAR");
                }
            }
            
        
catch (Exception e) {}
	
	
}
        }

               

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
