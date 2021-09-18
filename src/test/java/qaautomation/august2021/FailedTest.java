package qaautomation.august2021;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import qaautomation.august2021.pages.LoginPage;
import qaautomation.august2021.pages.ProfilePage;

public class FailedTest extends BaseWebTest {
	LoginPage loginPage = new LoginPage(driver, explicitWait);
	ProfilePage profilePage = new ProfilePage(driver, explicitWait);
	
	@Test(testName = "verify login failed", description = "login will failed")
	public void test1() {
    	String username = "fullstackdemo";
		String password = "fullstackdemo";
		
		loginPage.login(username,password);
		String failedUser = profilePage.getProfileText(username) + "failed";
		System.out.println("failed username = " + failedUser);
		assertEquals(failedUser, username + "failed");
	}
}
