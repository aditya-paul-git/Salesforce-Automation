package salesForce.reUsableComponents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SalesForce_SubScripts {
	public static boolean isClicked(WebDriver driver, WebElement element)
	{ 
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	        wait.until(ExpectedConditions.elementToBeClickable(element));
	        element.click();
	        return true;
	    } catch(Exception e){
	        return false;
	    }
	}
}
 