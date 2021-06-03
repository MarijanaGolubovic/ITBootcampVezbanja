package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Home {
	
	public static final String URL = "https://www.youtube.com/";
	
	private static final String SEARCH_ID = "search";
	
	private static final String SEARCHBTN_ID = "search-icon-legacy";
	
	
	
	public static void searchInput(WebDriver driver, String input) {
		WebElement we = driver.findElement(By.id(SEARCH_ID));
		we.sendKeys(input);
	}
	
	public static void clickSearchButton(WebDriver driver) {
		driver.findElement(By.id(SEARCHBTN_ID)).click();
	}
	
}
