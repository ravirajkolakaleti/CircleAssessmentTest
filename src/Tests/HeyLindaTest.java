package Tests;




import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.HomePage;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class HeyLindaTest {
    private AndroidDriver<MobileElement> driver;
    private HomePage homePage;

    @BeforeClass
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserstack.user", "ravirajkolakalet_FRbOhZ");
        caps.setCapability("browserstack.key", "Uoc7pPk229EZAhd5d7wv");
        
        caps.setCapability("platformName", "android");
        caps.setCapability("platformVersion", "14.0");
        caps.setCapability("deviceName", "Google Pixel 8");
        caps.setCapability("app", "bs://1cc1b77d2bafbf42e864de9423f7c6293a5e2184");
        caps.setCapability("name", "Appium Linda App Nav Test");

        driver = new AndroidDriver<>(new URL("https://hub.browserstack.com/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        homePage = new HomePage(driver);
    }

    @Test
    public void testMeditationScenario() {
        homePage.swipeUp();
        Assert.assertTrue(homePage.isDeepSleepTextDisplayed(), "Deep Sleep text is NOT displayed");
        System.out.println("Deep Sleep text: " + homePage.getDeepSleepText());

        homePage.swipeDown();
        Assert.assertTrue(homePage.isPowerOfLoveTextDisplayed(), "Power of Love text is NOT displayed");
        System.out.println("Power of Love text: " + homePage.getPowerOfLoveText());

        homePage.swipeLeft();
        Assert.assertTrue(homePage.isYawnAndStretchTextDisplayed(), "Yawn and Stretch text is NOT displayed");
        System.out.println("Yawn and Stretch text: " + homePage.getYawnAndStretchText());
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

