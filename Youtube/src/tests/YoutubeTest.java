package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import objects.Home;
import objects.Songs;

public class YoutubeTest {

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

	@Test(priority = 1)

	public void searchSongTest() {

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.navigate().to(Home.URL);

		Home.searchInput(driver, "Bon Jovi My life");
		Home.clickSearchButton(driver);

		Songs.selectSong(driver, "//*[@id=\"video-title\"]/yt-formatted-string");

		WebDriverWait wait = new WebDriverWait(driver, 10);

		WebElement skipbtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("ad-text:7")));
		skipbtn.click();
		Songs.skipAdd(driver);

		String currentUrl = driver.getCurrentUrl();
		String expectedUrl = "https://www.youtube.com/watch?v=vx2u5uUu3DE";

		Assert.assertEquals(currentUrl, expectedUrl);

	}

	@Test

	public void recommendedTest() {

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.navigate().to(Home.URL);

		driver.manage().window().maximize();

		List<WebElement> list = driver.findElements(By.cssSelector("#video-title"));

		list.get(2).click();

		try {
			Thread.sleep(8000);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	@Test

	public void searchSongsTest() {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		File f = new File("songlist.xlsx");
		try {
			InputStream in = new FileInputStream(f);
			XSSFWorkbook wb = new XSSFWorkbook(in);
			Sheet sheet = wb.getSheetAt(0);

			SoftAssert sa = new SoftAssert();

			for (int i = 0; i < 3; i++) {
				Row row = sheet.getRow(i);
				Cell c0 = row.getCell(0);
				Cell c1 = row.getCell(1);
				Cell c2 = row.getCell(2);

				String song = c0.toString();
				String path = c1.toString();
				String songUrl = c2.toString();

				driver.navigate().to(Home.URL);
				Home.searchInput(driver, song);
				Home.clickSearchButton(driver);
				Songs.selectSong(driver, path);

				String currentUrl = driver.getCurrentUrl();

				sa.assertEquals(currentUrl, songUrl);

				Cell c3 = row.createCell(3);

				if (currentUrl.equals(songUrl)) {
					c3.setCellValue("pass");
				} else {

					c3.setCellValue("fail");

				}
				OutputStream os = new FileOutputStream(f);
				wb.write(os);

			}

			sa.assertAll();
			wb.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
