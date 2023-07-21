package com.automation.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automation.base.SalesForceBase;
import com.automation.utility.PropertiesUtility;
@Listeners (com.automation.utility.ListenerClass.class)
public class LoginToSalesForce extends SalesForceBase{
	
@Test	 (dataProvider="userAndPasswordDetails")
	public static void ValidLogin2ToSalesForce(String user, String pass) throws InterruptedException {
		waitTillElementVisible(By.id("username"));
		String actualTitle=getPageTitle();
		System.out.println("Actual title = "+actualTitle);
		String expectedTitle="Login | Salesforce";
		AssertJUnit.assertEquals(actualTitle, expectedTitle);
		log.info("Login page loaded") ; 
		exreport.logExtentPass("Login Page loaded.");
		
		String myusername = user;
		
		String mypassword = pass; 
		 
		WebElement usernameE = driver.findElement( By.id("username")) ; 
		enterText(usernameE,myusername , "UserName") ; 
		WebElement passwordE = driver.findElement(By.id("password")) ; 
		enterText(passwordE,mypassword, "Passoword" ) ;
		WebElement loginbtnE = driver.findElement(By.id("Login")) ; 
		clickButton(loginbtnE,"Login Button") ; 
		waitTillElementVisible(By.id("phSearchInput"));
		String actualTitle1=getPageTitle();
		String expectedTitle1="Home Page ~ Salesforce - Developer Edition";
		AssertJUnit.assertEquals(actualTitle1, expectedTitle1);
		exreport.logExtentPass("Login Successful.Logged in to Sales Force Home Page.");
		
		System.out.println("-----------------------------------------------------------------------------") ;
	}

@Test (enabled=true)
public static void loginWithBlankPassword() throws InterruptedException {
	
	waitTillPageLoads("Login | Salesforce");
	waitTillElementVisible(By.id("username"));
	String actualTitle=getPageTitle();
	System.out.println("Actual title = "+actualTitle);
	String expectedTitle="Login | Salesforce";
	AssertJUnit.assertEquals(actualTitle, expectedTitle);
	log.info("Login Page loaded.") ; 
	exreport.logExtentPass("Login Page loaded.");
	PropertiesUtility propertiesUtility = new PropertiesUtility();
	String myusername = propertiesUtility.getPropertyValue("login.valid.userid"); 
	System.out.println("Userid from property file fetched = " + myusername) ; 
	String mypassword = ""; 
	System.out.println("Password from property file fetched = " + mypassword); 
	WebElement usernameE = driver.findElement( By.id("username")) ; 
	enterText(usernameE,myusername , "UserName") ; 
	WebElement passwordE = driver.findElement(By.id("password")) ; 
	enterText(passwordE,mypassword, "Passoword" ) ;
	WebElement loginbtnE = driver.findElement(By.id("Login")) ; 
	clickButton(loginbtnE,"Login Button") ; 
	Thread.sleep(4000) ;
	String expectedError = "Please enter your password." ;
	By actualErrMsg = By.xpath("/html/body/div[1]/div[1]/div/div/div[2]/div[3]/form/div[1]");
	WebElement actualErrMsgE = driver.findElement(actualErrMsg) ;
	
	
	AssertJUnit.assertEquals(actualErrMsgE.getText(), expectedError) ; 
	log.info("Test passed ") ; 
	exreport.logExtentPass("loginWithBlankPassword");
	
	
}
	
	@Test
	public static void ValidLoginToSalesForce() throws InterruptedException {
		waitTillElementVisible(By.id("username"));
		String actualTitle=getPageTitle();
		System.out.println("Actual title = "+actualTitle);
		String expectedTitle="Login | Salesforce";
		AssertJUnit.assertEquals(actualTitle, expectedTitle);
		log.info("Login page loaded") ; 
		exreport.logExtentPass("Login Page loaded.");
		PropertiesUtility propertiesUtility = new PropertiesUtility();
			String myusername = propertiesUtility.getPropertyValue("login.valid.userid");
		//System.out.println("Userid from property file fetched = " + myusername); 
		log.info("Userid from property file fetched = " + myusername) ; 
		String mypassword = propertiesUtility.getPropertyValue("login.valid.password") ; 
		log.info("Password from property file fetched = " + mypassword); 
		WebElement usernameE = driver.findElement( By.id("username")) ; 
		enterText(usernameE,myusername , "UserName") ; 
		WebElement passwordE = driver.findElement(By.id("password")) ; 
		enterText(passwordE,mypassword, "Passoword" ) ;
		WebElement loginbtnE = driver.findElement(By.id("Login")) ; 
		clickButton(loginbtnE,"Login Button") ; 
		waitTillElementVisible(By.id("phSearchInput"));
		String actualTitle1=getPageTitle();
		String expectedTitle1="Home Page ~ Salesforce - Developer Edition";
		AssertJUnit.assertEquals(actualTitle1, expectedTitle1);
		exreport.logExtentPass("Login Successful.Logged in to Sales Force Home Page.");
		System.out.println("-----------------------------------------------------------------------------") ;
	}
	

}
