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


public class SalesForceBase_v1 {
	public static  WebDriver driver =null ;
	protected static Logger log;
	protected static ExtentReportUtility extentreportutility ; 

	@BeforeSuite
	public static void beforeSuit() {
		log  =	LogManager.getLogger(SalesForceBase.class.getName())  ;
		System.out.println("logger initialized.");
		log.info("------------------ @beforeSuit started ---------------------------------") ;
		System.out.println("------------------ @beforeSuit started ---------------------------------") ;
		extentreportutility  = ExtentReportUtility.getInstance();
	}
	
	@BeforeTest
	public static void beforeTest() {
		System.out.println("------------------ @beforeTest started ---------------------------------") ;
		
		//System.out.println("Inside @BeforeTest : set up logger done");
	}
	
	@BeforeMethod
	@Parameters("browserName")
	public static void beforeMethod( @Optional("Firefox")   String browName) {
		System.out.println("------------------ @beforeMethod started ---------------------------------") ;
		SalesForceAutomationScripts.launchBrowser(browName ) ;
		PropertiesUtility property_utility = new PropertiesUtility();
		String url = property_utility.getPropertyValue("url");
		driver.get(url) ;   // to load website
		log.info("URL: "+url +" launched.") ; 
	//	extentreportutility.logExtentInfo(url);
//		extentreportutility.logExtentInfo("URL Loaded : " +url);	
	}
	
	@AfterMethod
	public static void afterMethod() {
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
		/*if (bname.equals("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		} else if (bname.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		} */
		log.info("Browser "+bname+" launched. ") ; 
		//Implicit wait
		//driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ; 
	}
	
/*	public static void addTestHeader(String tabname, int testNum, String testName) {
		System.out.println("----------------------------------------");
		System.out.println(tabname+": Test Case No. "+testNum);
		System.out.println("Description: "+testName);
		System.out.println("----------------------------------------");
	} */
	/* public static void printResult(String result) {
		if (result.equalsIgnoreCase("pass")) {
			println(">>>>>>>>> TEST CASE PASSED ");
			System.out.println("----------------------------------------");
		} else {
			println("-------- TEST CASE FAILED. ") ;
			System.out.println("----------------------------------------");
		}    
	}   */
	public static void println(Object obj) {
		log.info(obj);
	}
	public static void print(Object obj) {
		System.out.print(obj);
	}
	
	public static String getPageTitle() {
		//String pageTitle =  driver.getTitle() ; 
		//log.info("Page Title : " + pageTitle) ; 
		return driver.getTitle();
	}
	
	/* public static boolean checkIfTitleMatches(String expectedTitle) {
		String actualTitle = driver.getTitle();
		if (actualTitle.equals(expectedTitle)) {
			System.out.println("Successfully opened page with title : "+expectedTitle);
			return true;
		}
		else {
			System.out.println("-- Failed to open page with title : "+expectedTitle);
			System.out.println("Expected title = " +expectedTitle );
			System.out.println("Actual title = "+actualTitle);
			return false;
		}
	}   */
	public static void waitTillElementVisible(By locator) {
		log.info("Waiting for page element to be visible. ");
		Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class) ;
		fwait.until(ExpectedConditions.visibilityOfElementLocated(locator)) ; 
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
	
	/* public static void loginToSalesForce(String myusername, String mypassword) {
		
		driver.get("https://login.salesforce.com/") ;   // to load website
		if (checkIfTitleMatches("Login | Salesforce")  ) {
			System.out.println("PASS : SI No 1");
		}
		WebElement usernameE = driver.findElement( By.id("username")) ; 
		enterText(usernameE,myusername , "UserName") ; 
		WebElement passwordE = driver.findElement(By.id("password")) ; 
		enterText(passwordE,mypassword, "Passoword" ) ;
		WebElement loginbtnE = driver.findElement(By.id("Login")) ; 
		clickButton(loginbtnE,"Login Button") ; 
		
		//Thread.sleep(5000);
		if(checkIfTitleMatches("Home Page ~ Salesforce - Developer Edition") ) {
			log.info("Login to Sales Force Successful");
			
		}else {
			log.error("Error in login to Sales force ") ; 
		}
		

	} 
public static void loginToSalesForce() {
		
		driver.get("https://login.salesforce.com/") ;   // to load website
		if (checkIfTitleMatches("Login | Salesforce")  ) {
			log.info("PASS : SI No 1");
		}
		String myusername="vrish1302@tekarch.com" ;
		String mypassword="vrishali1234" ;
		WebElement usernameE = driver.findElement( By.id("username")) ; 
		enterText(usernameE,myusername , "UserName") ; 
		WebElement passwordE = driver.findElement(By.id("password")) ; 
		enterText(passwordE,mypassword, "Passoword" ) ;
		WebElement loginbtnE = driver.findElement(By.id("Login")) ; 
		clickButton(loginbtnE,"Login Button") ; 
		
		//Thread.sleep(5000);
		if(checkIfTitleMatches("Home Page ~ Salesforce - Developer Edition") ) {
			log.info("Login to Sales Force Successful");
			
		}else {
			log.error("Error in login to Sales force ") ;
		}
		

	} */

}
