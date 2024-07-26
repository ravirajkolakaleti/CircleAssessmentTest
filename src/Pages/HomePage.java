package Pages;


import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private AndroidDriver<MobileElement> driver;
    private WebDriverWait wait;

    public HomePage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Deep Sleep']")
    private MobileElement deepSleepText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Power of Love']")
    private MobileElement powerOfLoveText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Yawn and Stretch']")
    private MobileElement yawnAndStretchText;

    public void swipeUp() {
        int startX = driver.manage().window().getSize().width / 2;
        int startY = driver.manage().window().getSize().height * 3 / 4;
        int endY = driver.manage().window().getSize().height / 4;

        new TouchAction<>(driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
    }

    public void swipeDown() {
        int startX = driver.manage().window().getSize().width / 2;
        int startY = driver.manage().window().getSize().height / 4;
        int endY = driver.manage().window().getSize().height * 3 / 4;

        new TouchAction<>(driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
    }

    public void swipeLeft() {
        int startY = driver.manage().window().getSize().height / 2;
        int startX = driver.manage().window().getSize().width * 3 / 4;
        int endX = driver.manage().window().getSize().width / 4;

        new TouchAction<>(driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(endX, startY))
                .release()
                .perform();
    }

    public boolean isDeepSleepTextDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(deepSleepText)).isDisplayed();
    }

    public String getDeepSleepText() {
        return deepSleepText.getText();
    }

    public boolean isPowerOfLoveTextDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(powerOfLoveText)).isDisplayed();
    }

    public String getPowerOfLoveText() {
        return powerOfLoveText.getText();
    }

    public boolean isYawnAndStretchTextDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(yawnAndStretchText)).isDisplayed();
    }

    public String getYawnAndStretchText() {
        return yawnAndStretchText.getText();
    }
}
