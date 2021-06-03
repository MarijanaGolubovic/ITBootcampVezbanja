package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Songs {
	
	//public static final String SONG_URL = "https://www.youtube.com/results?search_query=Bon+Jovi+My+life";
	//public static final String SONG_TITLE = "//*[@id=\"video-title\"]/yt-formatted-string";
	
	
	public static void selectSong(WebDriver driver, String songXPath) {
		driver.findElement(By.xpath(songXPath)).click();
	}
	
	
	public static void skipAdd(WebDriver driver) {
		driver.findElement(By.id("ad-text:7")).click();
	}

}

//
