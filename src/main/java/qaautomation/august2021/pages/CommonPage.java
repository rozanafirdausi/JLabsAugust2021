package qaautomation.august2021.pages;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonPage extends BasePage{
	public CommonPage(ThreadLocal<WebDriver> driver, ThreadLocal<WebDriverWait> explicitWait) {
		super(driver, explicitWait);
		PageFactory.initElements(driver.get(), this);
	}
	
	public void openUrl(String url) {
		driver.get().get(url);
	}
	
	public void OpenNewTab() {
		driver.get().switchTo().newWindow(WindowType.TAB);
	}
	
	public void OpenNewWindow() {
		driver.get().switchTo().newWindow(WindowType.WINDOW);
	}
	
	public void switchWindow(int index) {
		Set<String> handles = driver.get().getWindowHandles();
		ArrayList<String> tabs = new ArrayList<String>(handles);
		driver.get().switchTo().window(tabs.get(index));
	}
	
	public void runJSCommand(String inputCommand) {
		JavascriptExecutor js = (JavascriptExecutor) driver.get();
		js.executeScript(inputCommand);
	}
	
	public void acceptAlert() {
		driver.get().switchTo().alert().accept();
	}
	
	public void navigateBrowser(String action) {
		if(action.equalsIgnoreCase("forward")) {
			driver.get().navigate().forward();
		}
		else if(action.equalsIgnoreCase("back")) {
			driver.get().navigate().back();
		}
		else if(action.equalsIgnoreCase("refresh")) {
			driver.get().navigate().refresh();
		}
	}
}
