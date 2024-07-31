package Tests;

import Pages.CongratulationsPage;
import Pages.LindaHomePage;
import Pages.PowerOfLovePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class LindaMeditateTest extends BaseTest {

	private AndroidDriver<AndroidElement> driver;
	
	CongratulationsPage cpg = new CongratulationsPage(driver);
	LindaHomePage homepage = new LindaHomePage(driver);
	PowerOfLovePage ppg = new PowerOfLovePage(driver);
	
	
	@Test
	public void testMeditation() {
		
		homepage.Meditation1();
		
		ppg.PageTitle();
		
		ppg.StartButtonClick();
		
		ppg.LeftCounter();
		
		
		cpg.DonateText();
		
		WebElement congratulationScreen = driver.findElement(By.xpath("//android.widget.TextView[@text=\"DONATE\"]"));
		if (congratulationScreen.isDisplayed()) {
			String Text = congratulationScreen.getText();
			System.out.println("Congratulations screen displayed with button text: " + Text);
		} else {
			System.out.println("Congratulations screen is NOT displayed");
			return;
		}
		
		cpg.SkipClick();
		
		System.out.println("Sucessfully executed the meditation test scenario");
		

	}

}
