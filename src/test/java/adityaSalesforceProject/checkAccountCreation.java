package adityaSalesforceProject;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import reports.ExtentReportManager;
import salesForce.reUsableComponents.SalesForce_SubScripts;

import org.testng.annotations.BeforeTest;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class checkAccountCreation {

	WebDriver driver;
	WebDriverWait wait;
	ExtentReports extent;
	ExtentTest reports;

	String AccountName;
	String PhoneNumber;

	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	String curtimestamp = timeStamp.replaceAll("[^a-zA-Z0-9]", "");

	@BeforeTest
	public void beforeTest() throws InterruptedException {

		// Setup browser properties
		System.setProperty("webdriver.chrome.driver", "/Users/adity/ChromeDriver/chromedriver.exe");
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--incognito");
		System.out.println("Opening Chrome browser");
		driver = new ChromeDriver(opt);
		driver.manage().window().maximize();
		System.out.println("Maximizing Chrome browser");
		
		
		// Explicit wait
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		
		// Report
		extent = ExtentReportManager.getReporter();
		reports = extent.createTest("Account Creation Test");

		
		// URL
		driver.get("https://login.salesforce.com");
		if (driver.findElement(By.xpath("//img[@class='standard_logo']")).getAttribute("alt").equals("Salesforce")) {
			reports.log(Status.PASS, "Navigated to the specified URL");
		} else {
			reports.log(Status.FAIL, "Navigated to a wrong URL");
		}
		Thread.sleep(500);

		
		// Enter Username
		driver.findElement(By.id("username")).sendKeys("aditya.paul.ml@mindful-raccoon-o95wep.com");
		if (driver.findElement(By.id("username")) != null) {
			reports.log(Status.PASS, "Username field displayed - Username entered successfully");
		} else {
			reports.log(Status.FAIL, "Username not entered/Username text field not displayed");
		}
		Thread.sleep(500);

		
		// MarkupHelper.createLabel("",
		// ExtentColor.GREEN),
		// MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build()
		// MarkupHelper.createLabel("",
		// ExtentColor.RED),
		// MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build()

		// Enter Password
		driver.findElement(By.id("password")).sendKeys("Qwerty#1234");
		if (driver.findElement(By.id("password")) != null) {
			reports.log(Status.PASS, "Password field displayed - Password entered successfully");
		} else {
			reports.log(Status.FAIL, "Password not entered/Password text field not displayed");
		}
		Thread.sleep(500);

		// Click login button
		//driver.findElement(By.id("Login")).click();
		
		if (SalesForce_SubScripts.isClicked(driver, driver.findElement(By.id("Login")))) {
			reports.log(Status.PASS, "Login button displayed - Click on Login button");
		} else {
			reports.log(Status.FAIL, "Login button not displayed");
		}
		Thread.sleep(1000);

	}

	@Test
	public void firstTest() {
		try {
			AccountName = "Test Account" + curtimestamp;
			PhoneNumber = "0123456789";

			// Navigate to Sales
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//button[@class='slds-button slds-icon-waffle_container slds-context-bar__button slds-button forceHeaderButton salesforceIdentityAppLauncherHeader']")));
			driver.findElement(By.xpath(
					"//button[@class='slds-button slds-icon-waffle_container slds-context-bar__button slds-button forceHeaderButton salesforceIdentityAppLauncherHeader']"))
					.click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search apps and items...']")));
			driver.findElement(By.xpath("//input[@placeholder='Search apps and items...']")).sendKeys("Sales");
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//a[@data-label='Sales']//b[text()='Sales']")));
			driver.findElement(By.xpath("//a[@data-label='Sales']//b[text()='Sales']")).click();
			Thread.sleep(1000);
			driver.navigate().refresh();

			// Navigate to the Accounts tab
			Thread.sleep(2000);
			WebElement Account = driver.findElement(By.xpath("//a[@title='Accounts']"));
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].click();", Account);

			// Click on the New button to create a new account
			Thread.sleep(2000);
			WebElement NewButton = driver.findElement(By.xpath("//div[@title='New']"));
			JavascriptExecutor js2 = (JavascriptExecutor) driver;
			js2.executeScript("arguments[0].click();", NewButton);

			// Enter account details
			// Account Name
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='Name']")));
			driver.findElement(By.xpath("//input[@name='Name']")).sendKeys(AccountName);

			// Phone Number
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='Phone']")));
			driver.findElement(By.xpath("//input[@name='Phone']")).sendKeys(PhoneNumber);

			// Click the Save button to create the account
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='SaveEdit']")));
			driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();

			// Verify company get created successfully
			Thread.sleep(5000);
			WebElement strvalue = driver
					.findElement(By.xpath("//slot[@name='primaryField']//div//lightning-formatted-text"));
			String expected = AccountName;
			String actual = strvalue.getText();
			if (expected.equals(actual)) {
				System.out.println("Account creation - Passed");
			} else {
				System.out.println("Account creation - Failed");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@AfterTest
	public void afterTest() {
		extent.flush();
		driver.quit();
	}

}
