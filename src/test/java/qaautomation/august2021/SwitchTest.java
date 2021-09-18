package qaautomation.august2021;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import qaautomation.august2021.pages.CommonPage;
import qaautomation.august2021.utils.JavascriptSnippets;
import qaautomation.august2021.utils.TestUtility;

public class SwitchTest extends BaseWebTest {
	CommonPage commonPage = new CommonPage(driver, explicitWait);
	
	@Test
	public void testSwitching() {
		commonPage.OpenNewTab();
		commonPage.switchWindow(1);
		commonPage.openUrl("https://facebook.com");
		
		String script = JavascriptSnippets.alertScript;
		
		commonPage.runJSCommand(script);
		TestUtility.hardWait(2);
		commonPage.acceptAlert();
		TestUtility.hardWait(2);
	}
	
	@Test
	public void testSwitchingWindow() {
		commonPage.OpenNewWindow();
		commonPage.switchWindow(1);
		commonPage.openUrl("https://facebook.com");
	}
	
	@Test
	public void testForwardBackRefresh() {
		commonPage.openUrl("https://facebook.com");
		commonPage.navigateBrowser("back");
		TestUtility.hardWait(2);
		commonPage.navigateBrowser("forward");
		TestUtility.hardWait(2);
		commonPage.navigateBrowser("refresh");
	}
	
	@Test
	public void testYopmail() {
		String bodyMail = "this is email body"; 
		commonPage.openUrl("https://yopmail.com");
		explicitWait.get().until(ExpectedConditions.elementToBeClickable(By.id("login")));
		driver.get().findElement(By.id("login")).sendKeys("sample");
		explicitWait.get().until(ExpectedConditions.elementToBeClickable(By.id("refreshbut")));
		driver.get().findElement(By.id("refreshbut")).click();
		driver.get().findElement(By.id("newmail")).click();
		driver.get().switchTo().frame(2);
		driver.get().findElement(By.id("msgto")).click();
		driver.get().findElement(By.id("msgto")).sendKeys("automationtest");
		driver.get().findElement(By.id("msgsubject")).click();
		driver.get().findElement(By.id("msgsubject")).sendKeys("subject");
		driver.get().findElement(By.id("msgbody")).click();
		driver.get().findElement(By.id("msgbody")).sendKeys(bodyMail);
		driver.get().findElement(By.id("msgsend")).click();
		TestUtility.hardWait(3);
		commonPage.navigateBrowser("back");
		TestUtility.hardWait(3);
		explicitWait.get().until(ExpectedConditions.elementToBeClickable(By.id("login")));
		driver.get().findElement(By.id("login")).clear();
		driver.get().findElement(By.id("login")).sendKeys("automationtest");
		explicitWait.get().until(ExpectedConditions.elementToBeClickable(By.id("refreshbut")));
		driver.get().findElement(By.id("refreshbut")).click();
		TestUtility.hardWait(3);
		driver.get().switchTo().frame(2);
		String resultBodyMail = driver.get().findElement(By.id("mail")).getText();
		System.out.println(driver.get().findElement(By.id("mail")).getText());
		assertEquals(bodyMail, resultBodyMail);
	}
	
	@Test
	public void testMakeMyTrip() {
		commonPage.OpenNewWindow();
		commonPage.switchWindow(1);
		commonPage.openUrl("https://www.makemytrip.com");
		TestUtility.hardWait(3);
		driver.get().findElement(By.id("root")).click();
		TestUtility.hardWait(3);
		explicitWait.get().until(ExpectedConditions.elementToBeClickable(By.id("fromCity")));
		driver.get().findElement(By.id("fromCity")).click();
		TestUtility.hardWait(5);
		driver.get().findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[1]/div[2]/div[1]/div[1]/div[1]/div/div/div/input")).click();
		driver.get().findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[1]/div[2]/div[1]/div[1]/div[1]/div/div/div/input")).sendKeys("jakarta");
		TestUtility.hardWait(5);
		driver.get().findElement(By.id("react-autowhatever-1-section-0-item-0")).click();
		TestUtility.hardWait(5);
		driver.get().findElement(By.id("root")).click();
		TestUtility.hardWait(5);
		driver.get().findElement(By.xpath("//*[text()='31']")).click();
		driver.get().findElement(By.xpath("//*[text()='Search']")).click();
		TestUtility.hardWait(5);
		String resultPrice = driver.get().findElement(By.xpath("//*[@id=\"listing-id\"]/div/div[2]/div/div[1]/div[1]/div[1]/div[2]/div/div/p")).getText();
		String finalPrice = resultPrice.replace("₹ ","");
		System.out.println(finalPrice);
		TestUtility.hardWait(5);
	}
	
}
