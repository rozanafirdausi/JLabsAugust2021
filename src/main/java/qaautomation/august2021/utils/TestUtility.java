package qaautomation.august2021.utils;

public class TestUtility {
	public static void hardWait(int seconds) {
		try {
			Thread.sleep(1000*seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
