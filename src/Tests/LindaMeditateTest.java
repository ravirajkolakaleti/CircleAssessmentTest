package Tests;

import Pages.CongratulationsPage;
import Pages.LindaHomePage;
import Pages.PowerOfLovePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class LindaMeditateTest extends BaseTest {

	private AndroidDriver<MobileElement> driver;
	
	CongratulationsPage cpg = new CongratulationsPage(driver);
	LindaHomePage pg= new LindaHomePage();
	PowerOfLovePage ppg = new PowerOfLovePage(driver);

	@Test
	public void testMeditation() {
		
		pg.Meditation1();
		
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
