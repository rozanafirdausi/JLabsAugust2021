package qaautomation.august2021;

import org.testng.annotations.Test;

import qaautomation.august2021.pages.LoginPage;
import qaautomation.august2021.pages.ProfilePage;

import static org.testng.Assert.assertEquals;


/**
 * Unit test for simple App.
 */
public class AppTest extends BaseWebTest
{
	LoginPage loginPage = new LoginPage(driver, explicitWait);
	ProfilePage profilePage = new ProfilePage(driver, explicitWait);
	
    @Test(testName = "verify login is success", description = "login will be success")
    public void test()
    {
    	String username = "fullstackdemo";
		String password = "fullstackdemo";
		
		loginPage.login(username,password);
		String actualUser = profilePage.getProfileText(username);
		assertEquals(actualUser, username);
        
    }
    
    @Test(testName = "verify login failed", description = "login will failed")
	public void test1() {
    	String username = "fullstackdemo";
		String password = "fullstackdemo";
		
		loginPage.login(username,password);
		String failedUser = profilePage.getProfileText(username) + "failed";
		assertEquals(failedUser, username + "failed");
	}
    
}
