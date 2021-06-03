package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home {
	     		
		public static void clickRegBtn(WebDriver driver) {
			driver.findElement(By.linkText("REGISTER")).click();
		}
	

}
