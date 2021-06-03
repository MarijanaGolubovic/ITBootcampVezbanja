package itBootcamp;


import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class HomePageTests {

	private static WebDriver driver;
	
	
	@BeforeClass

	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@AfterClass
	
	public void closeDriver() {
		driver.quit();
		
	}
	
	
	@Test
	
	public void testHomePgNav() {
		
		driver.get(HomePage.URL);

		driver.manage().window().maximize();

		System.out.println(driver.getTitle());
		driver.navigate().refresh();
		driver.navigate().to(HomePage.URL_CONTACT);
		driver.navigate().back();
		driver.navigate().forward();

		Point p = driver.manage().window().getPosition();
		driver.manage().window().setPosition(p);

		//driver.close();
	}

	@Test

	public void testSearch() throws Exception {
		driver.navigate().to(HomePage.URL);
		HomePage.clickSearchBtn(driver);
		HomePage.inputSearchWord(driver, "testiranje");
		HomePage.hitEnter(driver);

		HomePage.takeScreenShot(driver, "C://Fajl//test.png");
		
		String currentUrl = driver.getCurrentUrl();
		String expectedUrl = HomePage.URL_TESTIRANJE;
		
		Assert.assertEquals(currentUrl,expectedUrl);
		
		
	}

		
	@Test 
	
	public void testContactBtn() {
		
		driver.navigate().to(HomePage.URL);
		HomePage.clickContactBtn(driver);
		
		String currentUrl = driver.getCurrentUrl();
		String expectedUrl = HomePage.URL_CONTACT;
		
		Assert.assertEquals(currentUrl,expectedUrl);
	}

}
