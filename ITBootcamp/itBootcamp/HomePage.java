package itBootcamp;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class HomePage {

	public static final String URL = "https://itbootcamp.rs/?script=cir";
	public static final String URL_CONTACT = "https://itbootcamp.rs/kontakt/";
	public static final String URL_TESTIRANJE = "https://itbootcamp.rs/?s=testiranje";
	public static final String SEARCH_BTN = "open-search";
	public static final String SEARCH_FIELD = "//*[@id=\"masthead\"]/div[2]/div/div/form/div/div/input";
	public static final String CONTACT_BTN = "//*[@id=\"menu-item-4141\"]/a";
	
	public static void clickSearchBtn(WebDriver driver) {
		driver.findElement(By.className(SEARCH_BTN)).click();
	}
	
	public static void inputSearchWord(WebDriver driver, String input) {
		driver.findElement(By.xpath(SEARCH_FIELD)).sendKeys(input);
	}
	
	public static void hitEnter(WebDriver driver) {
		driver.findElement(By.xpath(SEARCH_FIELD)).sendKeys(Keys.ENTER);
	}
	
	public static void clickContactBtn(WebDriver driver) {
		driver.findElement(By.xpath(CONTACT_BTN)).click();
	}
	
	public static void takeScreenShot(WebDriver driver, String fileWithPath) throws Exception {

		TakesScreenshot scrShot = ((TakesScreenshot) driver);

		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);

		File destFile = new File(fileWithPath);

		FileUtils.copyFile(srcFile, destFile);

	}
	
	
	

}
