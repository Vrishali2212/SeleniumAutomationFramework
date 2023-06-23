package com.automation.base;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.automation.tests.SalesForceAutomationScripts;
import com.automation.utility.ExtentReportUtility;
import com.automation.utility.PropertiesUtility;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;


	


public class SalesForceBase {
	public static  WebDriver driver =null ;
	protected static Logger log;
	protected static ExtentReportUtility exreport ; 

	@BeforeSuite
	public static void beforeSuit() {
		log  =	LogManager.getLogger(SalesForceBase.class.getName())  ;
		System.out.println("logger initialized.");
		log.info("------------------ @beforeSuit started ---------------------------------") ;
		System.out.println("------------------ @beforeSuit started ---------------------------------") ;
		
	}
	
	@BeforeTest
	public static void beforeTest() {
		System.out.println("------------------ @beforeTest started ---------------------------------") ;
	}
	
	@BeforeMethod
	@Parameters("browserName")
	public static void beforeMethod( @Optional("Firefox")   String browName) {
		System.out.println("------------------ @beforeMethod started ---------------------------------") ;
		log.info("------------------ @beforeMethod started ---------------------------------") ;
		SalesForceAutomationScripts.launchBrowser(browName ) ;
		PropertiesUtility property_utility = new PropertiesUtility();
		String url = property_utility.getPropertyValue("url");
		driver.get(url) ;   // to load website
		log.info("URL: "+url +" launched.") ; 
		exreport  = ExtentReportUtility.getInstance();
		//exreport.logExtentInfo("URL Loaded : " +url);	
	}
	
	@AfterMethod
	public static void afterMethod() throws InterruptedException {
		Thread.sleep(10000) ;
		driver.close() ; 
		System.out.println("Inside @afterMethod");
		log.info("Driver closed.");
	}
	
	public static void launchBrowser(String bname) {
		switch(bname) 
		{
			case "Firefox" : WebDriverManager.firefoxdriver().setup();
							driver = new FirefoxDriver();
							driver.manage().window().maximize();
							break;
			case "chrome" : WebDriverManager.chromedriver().setup();
							driver = new ChromeDriver();
							driver.manage().window().maximize();
							break;	
		} 
		log.info("Browser "+bname+" launched. ") ; 
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ; 
	}
	public static void println(Object obj) {
		log.info(obj);
	}
	public static void print(Object obj) {
		System.out.print(obj);
	}
	
	public static void loginToSalesForce() {
		waitTillElementVisible(By.id("username"));
		String actualTitle=getPageTitle();
		//System.out.println("Actual title = "+actualTitle);
		String expectedTitle="Login | Salesforce";
		Assert.assertEquals(actualTitle, expectedTitle);
		log.info("Login page loaded") ; 
		exreport.logExtentPass("Login Page loaded.");
		PropertiesUtility propertiesUtility = new PropertiesUtility();
		String myusername = propertiesUtility.getPropertyValue("login.valid.userid");
		//System.out.println("Userid from property file fetched = " + myusername); 
		//log.info("Userid from property file fetched = " + myusername) ; 
		String mypassword = propertiesUtility.getPropertyValue("login.valid.password") ; 
		//log.info("Password from property file fetched = " + mypassword); 
		WebElement usernameE = driver.findElement( By.id("username")) ; 
		enterText(usernameE,myusername , "UserName") ; 
		WebElement passwordE = driver.findElement(By.id("password")) ; 
		enterText(passwordE,mypassword, "Passoword" ) ;
		WebElement loginbtnE = driver.findElement(By.id("Login")) ; 
		clickButton(loginbtnE,"Login Button") ; 
		waitTillElementVisible(By.id("phSearchInput"));
		String actualTitle1=getPageTitle();
		String expectedTitle1="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actualTitle1, expectedTitle1);
		log.info("Login Successful.Logged in to Sales Force Home Page.") ; 
		exreport.logExtentPass("Login Successful.Logged in to Sales Force Home Page.");
		
	}
	public static String getPageTitle() {
		//String pageTitle =  driver.getTitle() ; 
		//log.info("Page Title : " + pageTitle) ; 
		return driver.getTitle();
	}
	
	public static void clickCheckBox(WebElement checkboxElement) {
		if (checkboxElement.isDisplayed()) {
			if (checkboxElement.isSelected()) {
				System.out.println("Checkbox is already selected. ");
				
			}
			else {
				System.out.println("Clicking the checkbox");
				checkboxElement.click();
				log.info("Checked the checkbox") ; 
				
			}
		}else {
			System.out.println("Checkbox not displayed. ");
		}
		
	}
	
	public static void selectRadioButton(WebElement radiobutton ) {
		if (radiobutton.isSelected()) {
			log.info("Radio button is already selected") ; 
		} else {
			radiobutton.click(); 
			log.info("Radio button selected.")  ; 
		}
	}
	
	public static void waitTillElementVisible(By locator) {
		log.info("Waiting for page element to be visible. ");
		Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class) ;
		fwait.until(ExpectedConditions.visibilityOfElementLocated(locator)) ; 
				}
	public static void waitTillElementIsClickable(By by) {
		log.info("Waiting for page element to be clickable.. ");
		Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class) ;
		fwait.until(ExpectedConditions.elementToBeClickable(by)) ; 
				}
	

	
	
	public static void waitTillPageLoads(String pageTitle) {
		log.info("waitTillPageLoads::Waiting for page with title - "+pageTitle+" to be loaded. ");
		Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class) ;
		fwait.until(ExpectedConditions.titleContains(pageTitle)) ; 
	}
	
	public static void enterText(WebElement element, String value, String elementname) {
		if (element.isDisplayed()) {
			element.clear();
			element.sendKeys(value) ; 
			log.info(elementname + " entered.");
			} 
			else {
				log.error("-- "+elementname + " is not displayed.");
			}
	}
	public static void enterText(WebElement element, String value) {
		if (element.isDisplayed()) {
			element.clear();
			element.sendKeys(value) ; 
			log.info("Entered data in the webelement.");
			} 
			else {
				log.error("Element is not displayed.");
			}
	}
	
	public static void clickButton(WebElement element, String elementname) 
	{
		if (element.isDisplayed()) {
			element.click(); 
			log.info(elementname + " clicked ");
			}
			else {
				log.error("-- "+elementname+"  not displayed.");
			}
	}
}
