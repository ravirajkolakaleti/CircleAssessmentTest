package Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class LindaHomePage {
	
	private AndroidDriver<AndroidElement> driver;
	
	public LindaHomePage(AndroidDriver<AndroidElement> driver) {
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath = "//android.widget.TextView[@text='Power of Love']")
	AndroidElement Meditation;
	
	@FindBy(xpath = "//android.widget.TextView[@text='']")
	AndroidElement Home;
	
	@FindBy(xpath = "//android.widget.TextView[@text=\"\"]")
	AndroidElement Stats;
	
	@FindBy(xpath = "//android.widget.TextView[@text=\"Deep Sleep\"]")
	AndroidElement Sleep1;
	
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
