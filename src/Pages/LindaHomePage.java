package Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class LindaHomePage {
	
	private AndroidDriver<MobileElement> driver;
	
	public void HomLindaHomePageePage(AndroidDriver<MobileElement> driver) {
		
	this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath = "//android.widget.TextView[@text='Power of Love']")
	MobileElement Meditation;
	
	@FindBy(xpath = "//android.widget.TextView[@text='']")
	MobileElement Home;
	
	@FindBy(xpath = "//android.widget.TextView[@text=\"\"]")
	MobileElement Stats;
	
	@FindBy(xpath = "//android.widget.TextView[@text=\"Deep Sleep\"]")
	MobileElement Sleep1;
	
	public void Meditation1() {
		
		String Str = driver.findElementByXPath("//android.widget.TextView[@text='Power of Love']").getText();

		System.out.println("The Text appears under the image is " + Str);

		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Meditation.click();
	}
	
	public void HomeClick() {
		
		Home.click();
	}
	
	public void StatsClick() {
		
		Stats.click();
		
	}
	public void Sleep1Click() {
		
		Sleep1.click();
	}

}
