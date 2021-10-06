package browsersetup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageobjects.HomePage;
import utility.CommonMethods;
import utility.Configuration;

public class BrowserSetup extends Configuration {

	protected WebDriver driver;
	protected HomePage home;
	protected CommonMethods common;

	/**
	 * Initialize the browser and URL
	 * 
	 * @param Pass the Browser keyword and Application under test URL from
	 *             Configuration.java class
	 */
	@BeforeTest

	public void beforeTest() throws Exception {
		if (browser.equals("chrome")) {
			System.out.println("browser selected is Chrome");

			ChromeOptions chromeOptions = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(chromeOptions);
			driver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.out.println("browser selected is firefox");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
		driver.get(AUT_URL);
	}

	/**
	 * To close the browser after running all the test cases.
	 */
	@AfterTest
	public void afterTest() {
		driver.close();
	}
}
