package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class PowerOfLovePage {
	private AndroidDriver<AndroidElement> driver;
	
	public PowerOfLovePage(AndroidDriver<AndroidElement> driver) {
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath= "//android.widget.TextView[@resource-id=\"themed-text\" and @text='Power of Love']")
	
	AndroidElement PageText;
	
	@FindBy(xpath= "//android.widget.TextView[@resource-id=\"themed-text\" and @text='00:00']")
	
	AndroidElement LeftCounter;
	
	@FindBy(xpath= "//android.widget.TextView[@text='']")
	AndroidElement BackSkip;
	
	@FindBy(xpath= "//android.widget.TextView[@text='']")
	AndroidElement StartButton;
	
	@FindBy(xpath= "//android.widget.TextView[@text='']")
	
	AndroidElement ForwardSkip;
	
	@FindBy(xpath= "//android.widget.TextView[@resource-id=\"themed-text\" and @text='02:15']")
	AndroidElement RightCounter;
	
	
	
	public void PageTitle() {
		
		AndroidElement powerOfLovePage = driver
				.findElementByXPath("//android.widget.TextView[@text='Power of Love']");
		if (powerOfLovePage.isDisplayed()) {
			System.out.println("Power of Love page is displayed");
		} else {
			System.out.println("Power of Love page is NOT displayed");
			return;
		}
		
	}
	
	public void LeftCounter() {
		
		AndroidElement themedTextCounter = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='themed-text' and @text='00:00']"));
		if (themedTextCounter.isDisplayed() && themedTextCounter.getText().matches("\\d{2}:\\d{2}")) {
			System.out.println("Session started and counter clock is running");
		} else {
			System.out.println("Session did NOT start properly");
			return;
		}
		
	}
	
	public void BackSkipClick() {
		BackSkip.click();
	}
	
	public void StartButtonClick() {
		StartButton.click();
	}
	
	public void ForwardSkipClick() {
		
		ForwardSkip.click();
	}
	
	public void RightCounter() {
		
		RightCounter.click();
	}

}
