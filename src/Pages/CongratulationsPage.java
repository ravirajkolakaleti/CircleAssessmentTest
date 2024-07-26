package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class CongratulationsPage {
	
	private AndroidDriver<MobileElement> driver;
	
	
	public CongratulationsPage(AndroidDriver<MobileElement> driver) {
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath= "//android.widget.TextView[@resource-id=\"themed-text\" and @text='Congratulations!']")
	
	MobileElement CongratulationsText;
	
	@FindBy(xpath="//android.widget.TextView[@text='DONATE']")
	
	MobileElement DonateText;
	
	@FindBy(xpath= "//android.widget.TextView[@text='DONATE']")
	
	MobileElement DonateButton;
	
	@FindBy(xpath= "//android.widget.TextView[@text='SKIP']")
	
	MobileElement SkipButton;
	
	public void CongratsText() {
		
		CongratulationsText.click();
	}
	
	public void DonateText() {
		
		WebDriverWait wait = new WebDriverWait(driver, 150); // Adjust the timeout as needed
        WebElement newScreen = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text=\"DONATE\"]")));
	}
	
	public void DonateClick() {
		
		DonateButton.click();
	}
	
	public void SkipClick() {
		
		SkipButton.click();
	}

}
