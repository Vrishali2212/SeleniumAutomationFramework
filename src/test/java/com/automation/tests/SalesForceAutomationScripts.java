package com.automation.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automation.base.SalesForceBase;
import com.automation.utility.PropertiesUtility;

import io.github.bonigarcia.wdm.WebDriverManager;
@Listeners (com.automation.utility.ListenerClass.class)
public class SalesForceAutomationScripts extends SalesForceBase{
	
	public static final String LOGINPAGETITLE = "Login | Salesforce" ; 
	public static final String HOMEPAGETITLE = "Home Page ~ Salesforce - Developer Edition" ; 

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
	public static void rememberMe( ) throws InterruptedException  {
		// TAB: SDFC Login |  test case 3  | Testname: Check RemeberMe - 3
		//waitTillPageLoads("Login | Salesforce");
		waitTillElementVisible(By.id("username"));
		
		String actualTitle=getPageTitle();
		System.out.println("Actual title = "+actualTitle);
		String expectedTitle="Login | Salesforce";
		AssertJUnit.assertEquals(actualTitle, expectedTitle);
		log.info("Login page loaded") ; 
		exreport.logExtentPass("Login Page loaded.");

		PropertiesUtility propertiesUtility = new PropertiesUtility();
		String myusername = propertiesUtility.getPropertyValue("login.valid.userid"); 
		System.out.println("Userid from property file fetched = " + myusername) ; 
		String mypassword = propertiesUtility.getPropertyValue("login.valid.password");
		System.out.println("Password from property file fetched = " + mypassword); 
		WebElement usernameE = driver.findElement( By.id("username")) ; 
		enterText(usernameE,myusername , "UserName") ; 
		WebElement passwordE = driver.findElement(By.id("password")) ; 
		enterText(passwordE,mypassword, "Passoword" ) ;
		
		
		WebElement rememberMe = driver.findElement(By.xpath("//label[text()='Remember me']"));
		clickCheckBox(rememberMe) ; 
		log.info("Remember ME check box selected.") ;
		
		
		WebElement loginbtnE = driver.findElement(By.id("Login")) ; 
		clickButton(loginbtnE,"Login Button") ; 

		
		waitTillElementVisible(By.id("phSearchInput"));
		String actualTitle1=getPageTitle();
		String expectedTitle1="Home Page ~ Salesforce - Developer Edition";
		AssertJUnit.assertEquals(actualTitle1, expectedTitle1);
		exreport.logExtentPass("Login Successful.Logged in to Sales Force Home Page.");
		
		// On the Salesforce home page
		WebElement profileE = driver.findElement(By.id("userNavLabel")) ;
		profileE.click();
		WebElement logoutE = driver.findElement(By.xpath("//a[text()='Logout']"));
		logoutE.click();
		log.info("Logout clicked..");
		Thread.sleep(5000);
		
		AssertJUnit.assertEquals("Login | Salesforce", getPageTitle()) ; 
		log.info("Logout successful.") ; 
		exreport.logExtentPass("Logout succesful. ") ;
		Thread.sleep(2000);
		
		//boolean usernamePresent = false ;
		WebElement rememberMe2 = driver.findElement(By.xpath("//input[@name='rememberUn']"));
		if (rememberMe2.isDisplayed()) {
			if (rememberMe2.isSelected()) {
				System.out.println("Checkbox is already selected. ");
				//System.out.println("PASS >> Test case  passed");
				log.info("PASS >> Test case  passed") ; 
				exreport.logExtentPass("RememberMe checkbox remain selected, after logout. ") ; 
				
			}else {
				System.out.println("-- Checkbox is NOT selected. ");
				log.info("-- Test case  failed.");	
				exreport.logExtentFail("RememberMe checkbox is NOT selected, after logout. Expected result is -  checkbox selected . ") ; 
			}
			
		}else {
			log.error("Checkbox not displayed. ");	
		}
		
		WebElement username =  driver.findElement( By.id("username")) ; 
		String actualUsernameValue = username.getAttribute("value") ;
		String expectedUsernameValue = myusername ; 
		AssertJUnit.assertEquals(actualUsernameValue, expectedUsernameValue) ; 
		log.info("PASS >> Username present in username text box. Test case passed .") ;
		exreport.logExtentPass("PASS >> Username present in username text box. Test case passed .") ;
		
	}
	
	@Test
	public static void forgotPasswordA() throws InterruptedException {
		
		waitTillElementVisible(By.id("username"));
		AssertJUnit.assertEquals(getPageTitle(),LOGINPAGETITLE );
		exreport.logExtentPass("Login page loaded");
		
		
		WebElement forgotPasswordE = driver.findElement(By.linkText("Forgot Your Password?")) ; 
		forgotPasswordE.click();
		log.info("Forgot password link clicked. ") ; 
		Thread.sleep(5000);
		String expectedTitle ="Forgot Your Password | Salesforce" ;
		AssertJUnit.assertEquals(expectedTitle, getPageTitle());
		log.info("Forgot password page opened.") ; 
		exreport.logExtentPass("Forgot password page opened.");
		
		WebElement usernameTextBoxE = driver.findElement(By.xpath("//input[@id='un']"));
		PropertiesUtility propertiesUtility = new PropertiesUtility();
		String myusername = propertiesUtility.getPropertyValue("login.valid.userid");
		enterText(usernameTextBoxE, myusername, "UserName") ; 
		WebElement continuebtnE = driver.findElement(By.id("continue")) ; 
		clickButton(continuebtnE,"Continue") ; 
		
		Thread.sleep(2000);
		String expectedTitle2 = "Check Your Email | Salesforce" ; 
		AssertJUnit.assertEquals(getPageTitle(), expectedTitle2) ; 
		log.info("ASS : Password reset message page is displayed. An email associated with the <username> account is sent.");
		exreport.logExtentPass("ASS : Password reset message page is displayed. An email associated with the <username> account is sent") ; 
		
		
	}
	@Test
	public static void forgotPassword_B() {
		AssertJUnit.assertEquals(getPageTitle(), LOGINPAGETITLE);
		exreport.logExtentPass("Login page loaded");
		
			 
			String invalidusername ="123" ;
			String invalidpassword="221331";
			
			WebElement usernameE = driver.findElement( By.id("username")) ; 
			enterText(usernameE,invalidusername , "UserName") ; 
			WebElement passwordE = driver.findElement(By.id("password")) ; 
			enterText(passwordE,invalidpassword, "Passoword" ) ;
			WebElement loginbtnE = driver.findElement(By.id("Login")) ; 
			clickButton(loginbtnE,"Login Button") ; 
			
			String actualErrorText =  driver.findElement(By.id("error")).getText();
			String expectedErrorText = "Please check your username and password. If you still can't log in, contact your Salesforce administrator." ;
			println(expectedErrorText);
			AssertJUnit.assertEquals(actualErrorText, expectedErrorText);
			log.info("PASS: ") ; 
			exreport.logExtentPass("PASS>> Expected error message appeared after entering invalid username and invalid password.");
		
		
	}
	@Test
	public static void userMenuDropDownTC05() throws InterruptedException {
		
		boolean teststatus = false ; 
		
		loginToSalesForce();
		
		
		WebElement idE= driver.findElement(By.id("userNavLabel"));
		String actualProfileName = idE.getText();
		//String expectedProfileName = myusername;
		String expectedProfileName="vrish ABCDE" ; 
		println("Profile name displayed = "+actualProfileName) ; 
		AssertJUnit.assertEquals(expectedProfileName, actualProfileName);
		log.info("Profile name displayed = "+actualProfileName) ; 
		exreport.logExtentPass("Profile name displayed = "+actualProfileName);
		
		
		ArrayList<String> expectedProfileMenus = new ArrayList<String > () ;
		expectedProfileMenus.add("My Profile");
		expectedProfileMenus.add("My Settings");
		expectedProfileMenus.add("Developer Console");
		expectedProfileMenus.add("Switch to Lightning Experience");
		expectedProfileMenus.add("Logout");
		
		List<WebElement> profileMenulist = driver.findElements(By.xpath("//div[@id='userNav-menuItems']/a")) ; 
		int menusize = profileMenulist.size() ; 
		if (menusize == 5 ) {
			println(">> PASS : NO of items in the profile menu = "+menusize )  ;
			exreport.logExtentPass(">> PASS : NO of items in the profile menu = "+menusize )  ;
			log.info(">> PASS : NO of items in the profile menu = "+menusize )  ;
			
		}
		log.info("List size"+profileMenulist.size()) ;
		
		
		ArrayList<String> actulProfileMenus = new ArrayList<String > () ;
		
		for (int i=0; i<profileMenulist.size(); i++) {
			println(profileMenulist.get(i));
			println(profileMenulist.get(i).getAttribute("textContent")) ; 
			println(profileMenulist.get(i).getText()) ; 
			println(profileMenulist.get(i).getTagName()) ; 
			println(profileMenulist.get(i).getAttribute("title")) ;
			
			String acutalMenu = profileMenulist.get(i).getAttribute("textContent");
			String expectedMenu = expectedProfileMenus.get(i) ;
			
			actulProfileMenus.add(acutalMenu) ; 
			println(profileMenulist.get(i).getClass());	
			AssertJUnit.assertEquals(expectedMenu, acutalMenu) ; 
			log.info(">> PASS FOR menu no. "+i) ;
			log.info("Expected menu item  = "+expectedMenu) ;
			log.info("Actual menu item =" +acutalMenu );
			exreport.logExtentPass("PASS FOR menu " +acutalMenu ) ; 
		}
		exreport.logExtentPass("Passed : Expected menu under userprofilename displayed.");
		
	}
	public static void changeLastName() {
		// Step 3 : Click on edit profile button to edit contact information
				WebElement editE = driver.findElement(By.xpath("//div[@class='contactInfo profileSection']//img[@title='Edit Profile']")) ; 
				editE.click();
				log.info("Edit profile pen image clicked") ; 
				driver.switchTo().frame("contactInfoContentId");
				log.info("Switched to Edit Profile frame") ; 
				WebElement AboutE = driver.findElement(By.xpath("//html/body//li[@id='aboutTab']/a")) ;
				System.out.println("Located About webelement.") ;
				AboutE.click();
				log.info("Clicked About tab in Edit Profile frame") ; 
				String newlastname="ABCDE" ; 
				
			//	driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(newlastname);
				enterText(driver.findElement(By.xpath("//input[@id='lastName']")), newlastname , "LastName" ) ;
				log.info("Last name, entered. in text area in About tab ") ; 
				WebElement saveallE= driver.findElement(By.xpath("//div[@class='net-buttons zen-mtl']/input[@value='Save All']")) ; 
				saveallE.click();
				log.info("Save all button clicked") ;
				driver.switchTo().defaultContent();
				log.info("Driver switched to main window") ; 
				WebElement idE1= driver.findElement(By.id("userNavLabel"));
				String newid = idE1.getText();
				System.out.println("Updated profile id = " +newid);
				if (newid.contains(newlastname)) {
					log.info(">> PASS: New id,"+newid+", contains new last name, "+newlastname) ;
					exreport.logExtentPass("Last name updated on profile id on my profile page.") ; 
				} else {
					log.warn(">> FAIL: New id,"+newid+", does NOT contains new last name, "+newlastname);
				}
				
	}
	public static void postMessageonProfilePage() throws InterruptedException {
		// Step 5 : Click on Post link
				driver.findElement(By.xpath("//*[@id=\"publisherAttachTextPost\"]/span[1]")).click();
				log.info("Clicked on the post tab") ; 
				int frames = driver.findElements(By.tagName("iframe")).size();
				System.out.println("no. of iframes = "+frames);
			 	// switching to iframe 
				driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']")));
				log.info("Switched to iframe") ; 
				
				WebElement textareaE = driver.findElement(By.xpath("//html/body")) ;
				System.out.println("Successful : located textarea for writing the post");
				 
				String post ="my new post1111111111";
				//textareaE.sendKeys(post) ; 
				Thread.sleep(10000);
				//waitTillElementIsClickable(By.xpath("//html/body")) ;
				if (textareaE.isDisplayed()) {
					
					textareaE.click();
					textareaE.clear();
					textareaE.sendKeys(post) ; 
					log.info(textareaE + " entered.");
					} 
					else {
						log.error("-- "+textareaE + " is not displayed.");
					}
				log.info("Entered text "+post+" in the text area") ; 
				Thread.sleep(3000);
				driver.switchTo().defaultContent();
			
				log.info("Driver switched back to main window") ; 
				Thread.sleep(5000);
				driver.findElement(By.xpath("//input[@id='publishersharebutton']")).click();
				log.info("Share button clicked") ; 
				Thread.sleep(7000);
				System.out.println(" reached here Vrish");
				WebElement mypostE = driver.findElement(By.xpath("//div[@class='cxfeeditem feeditem'][1]//p")) ; 
				String mypost = mypostE.getText();
				log.info("Post "+mypost+" is seen on the page.") ; 
				AssertJUnit.assertEquals(mypost, post) ; 
				exreport.logExtentPass("Successfully, shared the post on  my profile page.") ; 
	}
	public static void uploadFile() throws AWTException, InterruptedException {
		Thread.sleep(5000);
		WebElement fileE = driver.findElement(By.xpath("//*[@id='publisherAttachContentPost']/span[1]")) ;
				Thread.sleep(3000);											
		fileE.click();
		log.info("File link clicked. ") ; 
		
		Thread.sleep(5000);
		WebElement buttonE = driver.findElement(By.xpath("//*[@id='chatterUploadFileAction']"));
		waitTillElementIsClickable(By.xpath("//*[@id='chatterUploadFileAction']"));
		buttonE.click();
		log.info("Upload a file from your computer, button clicked") ; 
		
		WebElement chooseFileE =  driver.findElement(By.xpath("//*[@id='chatterFile']"));
		String filepath = "/Users/vrish/Downloads/player2.png";
	/*	Actions action = new Actions(driver) ; 
		action.moveToElement(chooseFileE) ; 
		action.click(chooseFileE).build().perform();
		action.keyDown(Keys.TAB) ;
		action.keyUp(Keys.TAB) ; 
		Thread.sleep(2000);
		action.keyDown(Keys.TAB) ;
		action.keyUp(Keys.TAB) ; 
		Thread.sleep(2000);
		action.keyDown(Keys.TAB) ;
		action.keyUp(Keys.TAB) ; 
		Thread.sleep(2000);
		action.keyDown(Keys.TAB) ;
		action.keyUp(Keys.TAB) ; 
		Thread.sleep(2000);
		action.keyDown(Keys.TAB) ;
		action.keyUp(Keys.TAB) ; 
		
		log.info("chooseFileE button clicked") ;     */
		chooseFileE.sendKeys(filepath) ;
		log.info("File added") ;
		
		Thread.sleep(10000);
		//WebElement shareE=driver.findElement(By.xpath("//input[@id='publishersharebutton']"));
		//shareE.click();
		//log.info("Share button clicked"); 		
	//	System.out.println("Check if file is added on the home screen, manually." ) ; 
		//Thread.sleep(2000);
		
		
		
		// verification if file is added 
		//WebElement e = driver.findElement(By.xpath("//*[@id=\"title_492550878\"]")) ;
		//String file = e.getText();
		//if (filepath.contains(file)) {
		//log.info("File shared on my profile page. ") ; 
		//	exreport.logExtentPass("File uploaded and shared on my profile page") ;
		//}
		
		//log.info("Choose a File, button clicked") ; 
		
	}
	
	public static void uploadProfilePhoto() throws InterruptedException {
		Thread.sleep(5000);
		
		WebElement photo = driver.findElement(By.xpath("//div[@id='photoSection']")) ;
		Actions action = new Actions(driver) ; 
		action.moveToElement(photo).build().perform() ;
		log.info("Mouse moved to photo") ;
		
		
		driver.findElement(By.xpath("//*[@id='uploadLink']")).click();
		log.info("Update link clicked") ; 
		WebElement frame = driver.findElement(By.xpath("//iframe[@id='uploadPhotoContentId']"));
		driver.switchTo().frame(frame);
		log.info("Switched to new frame") ; 
		
		WebElement browse = driver.findElement(By.xpath("//input[@id='j_id0:uploadFileForm:uploadInputFile']"));
		String filepath = "/Users/vrish/Downloads/player2.png";
		browse.sendKeys(filepath) ; 
	
		//click save button
		Thread.sleep(6000);
		
		
		waitTillElementIsClickable(By.xpath("//div[@class='uploadButtonPanel']/input[@value='Save'][1]"));
		
		WebElement savebutton = driver.findElement(By.xpath("//div[@class='uploadButtonPanel']/input[@value='Save'][1]"));
		
		savebutton.click();
		log.info("Savve button clicked") ; 
		
		driver.switchTo().defaultContent();
		log.info("Profile photo uploaded. check that.");
		
		
		//driver.findElement(By.id("j_id0:uploadFileForm:save")).click();
		//log.info("save button clicked.");
		
		
		
	}
	@Test
	public static void UserMenuDropDownTC06() throws InterruptedException, AWTException {
		loginToSalesForce();
		log.info("Login to SalesForce successful") ; 
		//Select user menu for <username> drop down[TC01]
		WebElement idE= driver.findElement(By.id("userNavLabel"));
		idE.click();
		log.info("username clicked."); 
		driver.findElement(By.xpath("//a[@title='My Profile']")).click();
		log.info("Clicked myprofile") ;
		String expectedTitle = "User: vrish xxyyzz ~ Salesforce - Developer Edition" ; 
		String actualTitle=driver.getTitle();
	//	Assert.assertEquals(actualTitle,expectedTitle) ; 
		exreport.logExtentPass("Success step 2 : User Profile page is displayed") ; 
	
		// step 3  and 4
		//changeLastName();
		// step 5
		//postMessageonProfilePage();
		//step 6 
	//	uploadFile();
		//step7
		
		uploadProfilePhoto();
		
	
	}
	@Test
	public static void UserMenuDropDownTC07() throws InterruptedException  {
		loginToSalesForce();
		log.info("Login to SalesForce successful") ; 
		//Select user menu for <username> drop down[TC01]
		WebElement idE= driver.findElement(By.id("userNavLabel"));
		idE.click();
		log.info("username clicked."); 
		driver.findElement(By.xpath("//a[@title='My Settings']")).click();
		log.info("Clicked My Settings") ;
		String expectedTitle = "Hello, vrish ABCDE! ~ Salesforce - Developer Edition" ; 
		String actualTitle=driver.getTitle();
	//	Assert.assertEquals(actualTitle,expectedTitle) ; 
		exreport.logExtentPass("Success step 2 : Setting page is displayed") ; 
		
		WebElement personal = driver.findElement(By.xpath("//div[@id='AutoNumber5']//span[@id='PersonalInfo_font']"));
		personal.click();
		log.info("Personal link clicked") ;
		
		driver.findElement(By.xpath("//div[@id='PersonalInfo_child']//a[@id='LoginHistory_font']")).click();
		driver.findElement(By.xpath("//div[@class='pShowMore']/a")).click();
		
		WebElement displaylayout = driver.findElement(By.xpath("//div[@id='AutoNumber5']//span[@id='DisplayAndLayout_font']"));
		displaylayout.click();
		
		WebElement customize = driver.findElement(By.xpath("//div[@id='DisplayAndLayout']//a[@id='CustomizeTabs_font']")) ; 
		customize.click();
		log.info("Customize my tabs clicked.") ; 
		
		Select customapp = new Select ( driver.findElement(By.xpath("//select[@id='p4']"))) ; 
		customapp.selectByVisibleText("Salesforce Chatter") ; 
		log.info("Salesforce Chatter selected.") ;

		WebElement reports = driver.findElement(By.xpath("//select[@id='duel_select_0']/option[@value='report']")) ; 
		reports.click();
		log.info("Clicked report");
		driver.findElement(By.xpath("//a[@id='duel_select_0_right']")).click();
		log.info("Clicked Add button");
		driver.findElement(By.xpath("//td[@id='bottomButtonRow']/input[@title='Save']")).click();
		
		log.info("Save button clicked")  ;
		
		WebElement email = driver.findElement(By.xpath("//div[@id='AutoNumber5']//span[@id='EmailSetup_font']"));
		email.click();
		
		
		driver.findElement(By.xpath("//div[@id='EmailSetup']//a[@id='EmailSettings_font']")).click();
		log.info("Email settings link clicked.") ;
		
		WebElement emailname = driver.findElement(By.xpath("//input[@id='sender_name']")) ; 
		enterText(emailname, "vrishali")  ; 
		
		WebElement emailaddress = driver.findElement(By.xpath("//input[@id='sender_email']")) ;
		enterText(emailaddress, "vrishali@gmail.com") ;
		
		WebElement bccradio = driver.findElement(By.xpath("//input[@id='auto_bcc1']"))  ; 
		selectRadioButton(bccradio);
		
		WebElement save = driver.findElement(By.xpath("//input[@title='Save']"));
		save.click();
		log.info("save button clicked") ;
		
		driver.switchTo().alert().accept();
		log.info("Alert accepted") ;
		
		Thread.sleep(10000);
		WebElement calender = driver.findElement(By.xpath("//div[@id='AutoNumber5']//span[@id='CalendarAndReminders_font']"));
		calender.click();
		log.info("Calendar and  reminder link clicked.") ;
		
		driver.findElement(By.xpath("//div[@id='CalendarAndReminders_child']//a[@id='Reminders_font']")).click();
		log.info("Activity reminder link clicked.") ;
		
		driver.findElement(By.xpath("//input[@id='testbtn']")).click();
		
		String mainwindow = driver.getWindowHandle();
		Set <String > handles = driver.getWindowHandles() ;
		int windows = handles.size();
		int count=0;
		while (windows == 1) {
			handles = driver.getWindowHandles() ;
			windows = handles.size();
			count ++ ; 
			System.out.println("Count = " + count);
			
		}
		System.out.println("Found more than 1 windows ");
		Iterator<String> it = handles.iterator();
		String child ="";
		while(it.hasNext()) {
			String c = it.next();
			if (! c.equals(mainwindow)) {
				child = c;
				
			}
		}
		driver.switchTo().window(child) ; 
		log.info("Switched to child window. ") ; 
		
		String heading = driver.findElement(By.xpath("//div[@class='subject']")).getText();
		log.info("New window created : " + heading) ;
		driver.close();
		driver.switchTo().window(mainwindow);
		log.info(">> PASSED. ");
		
		
		
		
		
		
	}
	
	
	@Test
public static void UserMenuDropDownTC08() throws InterruptedException {
		loginToSalesForce();
		WebElement idE= driver.findElement(By.id("userNavLabel"));
		idE.click();
		driver.findElement(By.xpath("//a[@title='Developer Console (New Window)']")).click();
		Set<String> windows  = driver.getWindowHandles();
		System.out.println("no of window s; " + windows.size());
		String mainwindow = driver.getWindowHandle();
		String childwindow="";
		for (String w:windows ) {
			if (! w.equals(mainwindow)) {
				childwindow = w ; 
			}
		}
 		
		driver.switchTo().window(childwindow) ;
		exreport.logExtentPass("Developer console window opened") ; 
		Thread.sleep(2000) ;
		driver.close();
		driver.switchTo().window(mainwindow) ; 
		
		exreport.logExtentPass("Developer console window closed.") ; 
	}
	@Test
	public static void UserMenuDropDownTC09() {
		loginToSalesForce();
	
		WebElement idE= driver.findElement(By.id("userNavLabel"));
		idE.click();
		driver.findElement(By.xpath("//a[@title='Logout']")).click();
		exreport.logExtentPass("Logout successful.") ;
		
		
	}
	
	
	public static void openAccountsTab() throws InterruptedException {
		loginToSalesForce();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//li[@id='Account_Tab']")).click();
		Thread.sleep(3000);
		AssertJUnit.assertEquals(driver.getTitle(), "Accounts: Home ~ Salesforce - Developer Edition") ; 
		log.info("Accounts tab opened") ; 
		exreport.logExtentInfo("Accounts tab opened") ; 
		
	}
	@Test

	public static void CreateAccountTC10() throws InterruptedException {
		openAccountsTab();
		
	Thread.sleep(5000);
		
	//waitTillElementIsClickable(By.xpath("//input[@name='new']"));
		driver.findElement(By.xpath("//input[@name='new']")).click();
		String expectedaccname = "Vrishali New Account";
		driver.findElement(By.xpath("//input[@id='acc2']")).sendKeys(expectedaccname) ; 
		
		Select type = new Select(driver.findElement(By.xpath("//select[@id='acc6']"))) ;
		type.selectByVisibleText("Technology Partner") ; 
		
		Select custprio = new Select(driver.findElement(By.xpath("//select[@id='00NDp00000CUOsc']")));
		custprio.selectByVisibleText("High");
		
		driver.findElement(By.xpath("//div[@class='pbBottomButtons']//input[@name='save']")).click();
		
		String Actualaccname = driver.findElement(By.xpath("//div[@id='contactHeaderRow']//div[@class='textBlock']//h2")).getText();
		AssertJUnit.assertEquals(expectedaccname, Actualaccname) ; 
		exreport.logExtentPass("New account created" ) ; 
		
	}
	@Test
	public static void CreateAccountTC11() throws InterruptedException {
		openAccountsTab();
		Thread.sleep(3000);
		By linkb = By.xpath("//span[@class='fFooter']/a[2]");
		Thread.sleep(5000);
		driver.findElement(linkb).click();
		exreport.logExtentPass("Create New View link clicked.") ; 
		String viewname="ViewNameFriday11" ; 
		driver.findElement(By.xpath("//input[@id='fname']")).sendKeys(viewname);
		WebElement uview = driver.findElement(By.xpath("//input[@id='devname']")) ; 
		uview.clear();
		uview.sendKeys("UniqueViewNameFriday11");
		
		driver.findElement(By.xpath("//div[@class='pbHeader']//input[@name='save']")).click();
		
		Select view = new Select(driver.findElement(By.xpath("//select[@name='fcf']")))  ; 
		String currentview = view.getFirstSelectedOption().getText() ; 
		AssertJUnit.assertEquals(currentview, viewname) ; 
		exreport.logExtentPass("Given View name added and selected. ") ; 
	
	
	}
	
	public static void closeLighteningDialog() throws InterruptedException {
Thread.sleep(10000);
		
		//driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='sessionserver']")));
		WebElement nothanks = driver.findElement(By.xpath("//input[@id='lexNoThanks']"));
		nothanks.click();
		log.info("No thanks button clicked") ;
		Thread.sleep(3000);
	WebElement sendE = driver.findElement(By.xpath("//div[@id='tryLexDialog']//input[@id='lexSubmit']")) ; 
	sendE.click();
		log.info("Send button closed. Lightening Dialogue box closed. ") ; 
	}
	public static void closeChildWindow() {
		String mainwindow = driver.getWindowHandle();
		Set <String > handles = driver.getWindowHandles() ;
		int windows = handles.size();
		int count=0;
		while (windows == 1) {
			handles = driver.getWindowHandles() ;
			windows = handles.size();
			count ++ ; 
			System.out.println("Count = " + count);
			
		}
		System.out.println("Found more than 1 windows ");
		Iterator<String> it = handles.iterator();
		String child ="";
		while(it.hasNext()) {
			String c = it.next();
			if (! c.equals(mainwindow)) {
				child = c;
				
				
				
			}
		}
		driver.switchTo().window(child) ; 
		log.info("Switched to child window. ") ; 
		driver.close();
		log.info("Closed child window.") ; 
		driver.switchTo().window(mainwindow) ; 
		log.info("Switched back to the main window");
	}
	@Test
	public static void CreateAccountTC12() throws InterruptedException {
		openAccountsTab();
		Thread.sleep(3000);
		
		closeLighteningDialog();
		
		
		Select views = new Select(driver.findElement(By.xpath("//select[@name='fcf']"))) ; 
		String viewname = "View22NEWNEWNEW" ; 
		
		views.selectByVisibleText(viewname) ;
		log.info("View name "+viewname+" selected from dropdown.");
		WebElement editE = driver.findElement(By.xpath("//div[@class='filterOverview']//a[contains(text(),'Edit')]"));
		editE.click();
		log.info("Edit link clicked") ; 
		Thread.sleep(3000);
		log.info("Edit link clicked.") ; 
		//String newviewname =  viewname+"NEW" ; 
		String newviewname =  viewname;
		WebElement viewnameE = driver.findElement(By.xpath("//input[@name='fname']"));
		viewnameE.clear();
		viewnameE.sendKeys(newviewname) ; 
		
		Select field = new Select(driver.findElement(By.xpath("//select[@id='fcol1']"))) ;
		field.selectByVisibleText("Account Name") ;
		
		Select operator = new Select(driver.findElement(By.xpath("//select[@id='fop1']")));
		operator.selectByVisibleText("contains") ;
		enterText(driver.findElement(By.xpath("//input[@id='fval1']")), "vrish") ; 
		
		
		driver.findElement(By.xpath("//div[@class='pbBottomButtons']//input[@name='save' and @value=' Save ']")).click();
		log.info("save button clicked") ; 
		Thread.sleep(10000) ;
		
	}
	
	@Test
	
	public static void createAccountsTC13() throws InterruptedException {
		openAccountsTab();
		Thread.sleep(5000);
		closeLighteningDialog();
		
		WebElement merge = driver.findElement(By.xpath("//div[@class='toolsContentRight']//a[contains(text(),'Merge Accounts')]"));
		merge.click();
		log.info("Merge link clicked") ;
		
		WebElement text = driver.findElement(By.xpath("//input[@id='srch']")) ; 
		enterText(text, "Code Ninja") ; 
		log.info("Entered the text ") ;
		
		WebElement findaccounts = driver.findElement(By.xpath("//input[@name='srchbutton']"));
		findaccounts.click();
		log.info("Find Accounts button is clicked");
		
		List<WebElement> checkboxes = driver.findElements(By.xpath("//table[@class='list']//th/input[@name='cid']"));
		System.out.println("No. of checkboxes = "+checkboxes.size());
		Iterator <WebElement> it = checkboxes.iterator();
		while(it.hasNext()) {
			WebElement checkbox = it.next();
			if (checkbox.isSelected()) {
				checkbox.click();
			}
		}
		log.info("Unchecked all the checkboxes in the list.");
		Thread.sleep(3000);

		clickCheckBox(checkboxes.get(0));
		clickCheckBox(checkboxes.get(1));
		log.info("First 2 checkboxes clicked.");
		Thread.sleep(3000);
		
		WebElement next = driver.findElement(By.xpath("//div[@class='pbTopButtons']//input[@class='btn' and @name='goNext']"));
		next.click();
		log.info("next button clicked");
		
		WebElement mergeE = driver.findElement(By.xpath("//div[@class='pbTopButtons']//input[@name='save']"));
		mergeE.click();
		log.info("merge button clicked") ;
		
		
			driver.switchTo().alert().accept();
		System.out.println("Alert accepted.");
		
	}
	// Getting error in test case 14  script.
	@Test
	
	public static void CreateAccount14() throws InterruptedException {
		openAccountsTab();
		Thread.sleep(5000);
		closeLighteningDialog();
		WebElement reports = driver.findElement(By.xpath(("//div[@class='toolsContentLeft']//a[contains(text(),'Accounts with last activity > 30 days')]")));
		reports.click();
		log.info("Accounts with last activity > 30 days, clicked.");
		AssertJUnit.assertEquals(driver.getTitle(), "Unsaved Report ~ Salesforce - Developer Edition");
		log.info("Unsaved Report page opened");
		
		WebElement datefield=driver.findElement(By.xpath("//input[@id='ext-gen20']"));
		Select datafield = new Select ( datefield);
		datafield.selectByVisibleText("Created Date") ; 
		log.info("Created date selcted from drop down .");
		
	}
	
	
	public static void gotoOpportunitiesPage() throws InterruptedException {
		WebElement opportunitiesE = driver.findElement(By.xpath("//li[@id='Opportunity_Tab']/a")) ; 
		Thread.sleep(3000);
		
		opportunitiesE.click();
		log.info("Opportunities tab clicked") ; 
		
		Thread.sleep(4000);
		String pageTitle=driver.getTitle();
		String expectedTitle = "Opportunities ~ Salesforce - Developer Edition";
		//Assert.assertEquals(expectedTitle, pageTitle);
		exreport.logExtentPass("Opportunities home page loaded.") ;
	
	}
	
	
	
	@Test	
	public static void CreateOptyTC15() throws InterruptedException {
		loginToSalesForce();
		gotoOpportunitiesPage();
		Thread.sleep(5000);
		// Verification pending.
		List<String> exp = new ArrayList<String> () ;
		exp.add("All Opportunitie") ;
		exp.add("Closing Next Month") ;
		exp.add("Closing This Month") ;
		exp.add("My Opportunities") ;
		exp.add("New This Week") ;
		exp.add("Recently Viewed Opportunities") ;
		exp.add("Won") ;
		
		Select opp = new Select(driver.findElement(By.id("fcf"))) ;
		List<WebElement> alloptions = opp.getOptions() ; 
		Iterator <WebElement> it = alloptions.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().getText());
		}
		
	}
	
	
	
	@Test
	public static void CreateOptyTC16() throws InterruptedException {
		//step 1 : login to salesforce home page
		loginToSalesForce();
		//step 2 : Open opportunities tab 
		gotoOpportunitiesPage();
		Thread.sleep(5000);
		WebElement newE = driver.findElement(By.xpath("//form[@id='hotlist']//td[2]/input"));
		newE.click();
		
		String myoppname = "My Selenium Opportunity";
		WebElement oppName = driver.findElement(By.xpath("//input[@id='opp3']")) ; 
		enterText(oppName,myoppname);
		WebElement accName = driver.findElement(By.xpath("//input[@id='opp4']")) ; 
		accName.sendKeys("abcd") ; 
		enterText(accName,"Education&Learning");
 		WebElement clsDate = driver.findElement(By.xpath("//input[@id='opp9']")) ;  
 		enterText(clsDate,"6/15/2023");
 		WebElement stage = driver.findElement(By.xpath("//select[@id='opp11']"));
 		Select stageselect = new Select(stage) ; 
 		stageselect.selectByVisibleText("Prospecting");
		WebElement probability = driver.findElement(By.xpath("//input[@id='opp12']")) ; 
		enterText(probability,"20");
		WebElement leadsource = driver.findElement(By.xpath("//select[@id='opp6']")) ;
		Select leadsourceselect = new Select(leadsource);
		leadsourceselect.selectByVisibleText("Web");
		WebElement pricamp  = driver.findElement(By.xpath("//input[@id='opp17']")) ;  
		enterText(pricamp,"");
		log.info("Data entered on the new opportunity page. ");
		WebElement saveE = driver.findElement(By.xpath("//td[@id='bottomButtonRow']/input[@name='save']")) ; 
		saveE.click();
		log.info("Save button clicked") ; 
		
		Thread.sleep(5000);
		String oname = driver.findElement(By.xpath("//div[@class='content']//h2")).getText();
		AssertJUnit.assertEquals(oname, myoppname) ; 
		log.info("New Opportunity page is displayed with Opportunity details.") ; 
		exreport.logExtentPass("New Opportunity page is displayed with Opportunity details.") ;
		
		
	}
	
	@Test
	public static void CreateOptyTC17() throws InterruptedException {
		//step 1 : login to salesforce home page
		loginToSalesForce();
		//step 2 : Open opportunities tab 
		gotoOpportunitiesPage();
		Thread.sleep(5000);
		
		//Opportunity Pipeline
		WebElement pipelineE = driver.findElement(By.xpath("//div[@class='lbBody']//a[text()='Opportunity Pipeline']")) ; 
		pipelineE.click();
		log.info("Opportunity Pipeline clicked. ");
		Thread.sleep(5000);
		String pagetitle="Opportunity Pipeline ~ Salesforce - Developer Edition" ;
		AssertJUnit.assertEquals(driver.getTitle(), pagetitle);
		log.info("Opportunity pipelines page loaded. ") ; 
		exreport.logExtentPass("Report Page with the Opportunities that are pipelined will be displayed.") ;
		
}
	@Test
	public static void CreateOptyTC18() throws InterruptedException {
		//step 1 : login to salesforce home page
		loginToSalesForce();
		//step 2 : Open opportunities tab 
		gotoOpportunitiesPage();
		Thread.sleep(5000);
		
		
	//	driver.switchTo().alert().dismiss();
	//	System.out.println("Alert dismissed.");
		
	/*	
		String mainwindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles() ; 
		System.out.println("No. of windows = " +handles.size());
		Iterator<String> it =handles.iterator();
		String child  = "";
		while (it.hasNext()) {
			String s = it.next();
			if (! s.equals(mainwindow)) {
				child = s ;
			}
		}
		driver.switchTo().window(child) ; 
		System.out.println("Switched to the child window");
		driver.close();
		System.out.println("Closed the child window");
		driver.switchTo().defaultContent();
		System.out.println("Switched to the main window");
		*/
		
		
		Thread.sleep(3000);
		
		WebElement stuckE = driver.findElement(By.xpath("//div[@class='lbBody']//a[text()='Stuck Opportunities']")) ; 
		stuckE.click();
		log.info("Stuck opportunities clicked. ");
		//Stuck Opportunities ~ Salesforce - Developer Edition
		Thread.sleep(5000);
		
		String pagetitle="Stuck Opportunities ~ Salesforce - Developer Edition" ;
		AssertJUnit.assertEquals(driver.getTitle(), pagetitle);
		log.info("Stuck opportunities page loaded. ") ; 
		exreport.logExtentPass("Report Page with the Opportunities that are Stuck will be displayed.") ;

	}
	
	@Test
	public static void CreateOptyTC19() throws InterruptedException {
		//step 1 : login to salesforce home page
		loginToSalesForce();
		//step 2 : Open opportunities tab 
		gotoOpportunitiesPage();
		Thread.sleep(5000);
		
		// Quarterly summary
		WebElement intervalE = driver.findElement(By.xpath("//select[@id='quarter_q']")) ; 
		Select intervalS = new Select(intervalE);
		intervalS.selectByVisibleText("Previous FQ");
		WebElement includeE = driver.findElement(By.xpath("//select[@id='open']")) ;
		Select includeS = new Select(includeE) ; 
		includeS.selectByVisibleText("Open Opportunities") ; 
		WebElement runReportE  = driver.findElement(By.xpath("//table[@class='opportunitySummary']//input[@name='go']")) ;
		runReportE.click();
		log.info("Run Report button clicked") ;
		Thread.sleep(3000);
		String expPageTitle="Opportunity Report ~ Salesforce - Developer Edition";
		AssertJUnit.assertEquals(driver.getTitle(), expPageTitle) ; 
		log.info("Report Page with the Opportunities that satisfies the search criteria  displayed.");
		exreport.logExtentPass("Report Page with the Opportunities that satisfies the search criteria displayed.") ; 
		
	}
		public static void gotoLeadsPage() throws InterruptedException {
		WebElement leadE = driver.findElement(By.xpath("//li[@id='Lead_Tab']/a")) ; 
		Thread.sleep(3000);
		
		leadE.click();
		log.info("Leads tab clicked") ; 
		
		Thread.sleep(4000);
		String pageTitle=driver.getTitle();
		String expectedTitle = "Leads: Home ~ Salesforce - Developer Edition";
		AssertJUnit.assertEquals(expectedTitle, pageTitle);
		
	}
		@Test		
		public static void LeadsTC20() throws InterruptedException {
			loginToSalesForce();
			Thread.sleep(3000);
			gotoLeadsPage();
			log.info("Leads home page loaded.") ; 
			exreport.logExtentPass("Leads home page loaded.") ;
		}
		
	@Test
		public static void LeadsTC21() throws InterruptedException {
			loginToSalesForce();
			Thread.sleep(3000);
			gotoLeadsPage();
			log.info("Leads home page loaded.") ; 
			exreport.logExtentPass("Leads home page loaded.") ;
			
			// click on dropdown
			Thread.sleep(3000);
			WebElement viewE= driver.findElement(By.xpath("//select[@id='fcf']"));
			Select viewS = new Select(viewE);
			List<WebElement> viewoptions =  viewS.getOptions(); 
			Iterator<WebElement> it = viewoptions.iterator();
			while(it.hasNext()) {
				System.out.println(it.next().getText());
			}
		
		}
	@Test
	
	public static void LeadsTC22() throws InterruptedException {
		loginToSalesForce();
		Thread.sleep(3000);
		gotoLeadsPage();
		log.info("Leads home page loaded.") ; 
		exreport.logExtentPass("Leads home page loaded.") ;
		
		// click on dropdown
		Thread.sleep(3000);
		
		WebElement viewE= driver.findElement(By.xpath("//select[@id='fcf']"));
		Select viewS = new Select(viewE);
		WebElement selecteditem1 = viewS.getFirstSelectedOption();
		String selecteddefault = selecteditem1.getText() ; 
		log.info("Selected by default: " + selecteddefault) ; 
		viewS.selectByVisibleText("Recently Viewed Leads") ; 
		Thread.sleep(3000);
		WebElement profileE = driver.findElement(By.id("userNavLabel")) ;
		profileE.click();
		WebElement logoutE = driver.findElement(By.xpath("//a[text()='Logout']"));
		logoutE.click();
		log.info("Logout link clicked..");
		Thread.sleep(5000);
		
		AssertJUnit.assertEquals(driver.getTitle(), "Login | Salesforce") ;
		log.info("Logout sucessful") ; 
		
		Thread.sleep(2000); 
		loginToSalesForce();
		Thread.sleep(3000);
		
		gotoLeadsPage();
		log.info("Leads home page loaded.") ; 
		exreport.logExtentPass("Leads home page loaded.") ;
		WebElement view= driver.findElement(By.xpath("//select[@id='fcf']"));
		Select view1 = new Select(view);
		WebElement selecteditem = view1.getFirstSelectedOption();
		String selected = selecteditem.getText() ; 
		log.info("Selected option = " + selected) ; 
		
		WebElement goE = driver.findElement(By.xpath("//select[@id='fcf']//following::input[@title='Go!']")) ; 
		goE.click();
		log.info("Go button clicked") ;
		
		Thread.sleep(3000);
		if (selecteddefault.equals(selected)) {
			log.info("PASS: Test case passed . ") ; 
		}else {
			log.info("FAIL : Test case failed. ");
		}
	}
	@Test
	
	public static void LeadsTC23() throws InterruptedException {
		loginToSalesForce();
		Thread.sleep(3000);
		gotoLeadsPage();
		log.info("Leads home page loaded.") ; 
		exreport.logExtentPass("Leads home page loaded.") ;
		
		// click on dropdown
		Thread.sleep(3000);
		
		WebElement viewE= driver.findElement(By.xpath("//select[@id='fcf']"));
		Select viewS = new Select(viewE);
		//WebElement selecteditem1 = viewS.getFirstSelectedOption();
		//String selecteddefault = selecteditem1.getText() ; 
		//log.info("Selected by default: " + selecteddefault) ; 
		viewS.selectByVisibleText("Today's Leads") ; 
		log.info("Selected Today's Leads from the dropdown") ; 
		
		//click Go button 
		Thread.sleep(3000);
		
		WebElement goE = driver.findElement(By.xpath("//select[@id='fcf']//following::input[@title='Go!']")) ; 
		goE.click();
		log.info("Go button clicked") ;
		
		Thread.sleep(3000);
		
		String pageTitle=driver.getTitle();
		String expectedTitle = "Leads ~ Salesforce - Developer Edition";
		AssertJUnit.assertEquals(expectedTitle, pageTitle);
		

		Select view1 = new Select(driver.findElement(By.xpath("//select[@id='00BDp000008EzCf_listSelect']")));
		String selected = view1.getFirstSelectedOption().getText();
		
		log.info("Selected option = " + selected) ; 
		

		AssertJUnit.assertEquals(selected, "Today's Leads") ; 
		
		log.info("Today's Leads page loaded ") ; 
		exreport.logExtentPass("Today's Leads page loaded ")  ; 
		
	}
	
	@Test
	
	public static void LeadsTC24() throws InterruptedException {
		loginToSalesForce();
		Thread.sleep(3000);
		gotoLeadsPage();
		log.info("Leads home page loaded.") ; 
		exreport.logExtentPass("Leads home page loaded.") ;
		
		// click on dropdown
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@name='new']")).click();
		log.info("New button clicked") ; 
		Thread.sleep(3000);
		AssertJUnit.assertEquals(driver.getTitle(), "Lead Edit: New Lead ~ Salesforce - Developer Edition") ; 
		log.info("New Lead creation page  opened  . ") ; 
		exreport.logExtentPass("New Lead creation page  opened  . ");
		String lastname="ABCD";
		driver.findElement(By.xpath("//input[@id='name_lastlea2']")).sendKeys("ABCD") ;
		driver.findElement(By.xpath("//input[@id='lea3']")).sendKeys("ABCDE") ; 
		driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@name='save']")).click();
		
		Thread.sleep(4000);
		String pagetitle = "Lead: "+lastname+" ~ Salesforce - Developer Edition" ; 
		AssertJUnit.assertEquals(driver.getTitle(), pagetitle) ; 
		log.info("Newly created page with the new view is opened.") ; 
		
		String viewlabel = driver.findElement(By.xpath("//div[@id='contactHeaderRow']//h2")).getText();
		
		AssertJUnit.assertEquals(viewlabel, lastname);
		log.info("PASS >> new lead should be saved and the newly created lead view page should be opened") ;
		exreport.logExtentPass("new lead should be saved and the newly created lead view page should be opened");
			
	}
	
	public static void goToContactsPage() throws InterruptedException {
		WebElement contactE = driver.findElement(By.xpath("//li[@id='Contact_Tab']/a")) ; 
		Thread.sleep(3000);
		
		contactE.click();
		log.info("Contacts tab clicked") ; 
		
		Thread.sleep(4000);
		String pageTitle=driver.getTitle();
		String expectedTitle = "Contacts: Home ~ Salesforce - Developer Edition";
		AssertJUnit.assertEquals(expectedTitle, pageTitle);
	}

	
	@Test	
	public static void ContactsTC25() throws InterruptedException {
		loginToSalesForce();
		Thread.sleep(3000);
		goToContactsPage();
		
		//clicking new button 
		driver.findElement(By.xpath("//td[@class='pbButton']/input[@name='new']")).click();
		log.info("New button clicked") ; 
		Thread.sleep(5000);
		AssertJUnit.assertEquals(driver.getTitle(), "Contact Edit: New Contact ~ Salesforce - Developer Edition") ; 
		log.info("New Contact home page  displayed") ; 
		exreport.logExtentPass("New Contact home page  displayed") ; 
		String lastname = "MylastnameB" ; 
		driver.findElement(By.xpath("//input[@id='name_lastcon2']")).sendKeys(lastname) ; 
		driver.findElement(By.xpath("//input[@id='con4']")).sendKeys("Burlington Textiles Corp of America") ; 
		
		driver.findElement(By.xpath("//td[@id='bottomButtonRow']/input[@name='save']")).click();
		log.info("Save button clicked") ; 
		Thread.sleep(3000);
		
		String expectedTitle = "Contact: "+lastname+" ~ Salesforce - Developer Edition" ; 
		
		String displayedLastname = driver.findElement(By.xpath("//div[@class='textBlock']/h2")).getText();
		AssertJUnit.assertEquals(displayedLastname, lastname) ; 
		log.info("New contact created") ; 
		exreport.logExtentPass("New contact created");
		
}
	@Test	
	public static void ContactsTC26() throws InterruptedException {
		loginToSalesForce();
		Thread.sleep(3000);
		exreport.logExtentPass("SalesForce application Launced");
		goToContactsPage();
		exreport.logExtentPass("Contact Home page displayed");
		
		// //div[@id='00BDp000008VbgP_filterLinks']/a[text()='Create New View'] 
		// view name : //input[@id='fname']
		//unique view : //input[@id='devname']
		// save button : //div[@class='pbHeader']//input[@name='save']
		// page title : Contacts ~ Salesforce - Developer Edition
		// select view dropdown : //select[@id='00BDp000008VbgZ_listSelect']
		
		driver.findElement(By.xpath("//span[@class='bFilter']//a[text()='Create New View']")).click();
		log.info("create new view clicked");
		Thread.sleep(4000);
		AssertJUnit.assertEquals(driver.getTitle(), "Contacts: Create New View ~ Salesforce - Developer Edition") ;
		log.info("Create new view page opened");
		String viewname = "MyNewViewFriday";
		driver.findElement(By.xpath("//input[@id='fname']")).sendKeys(viewname);
		WebElement uniqueview = driver.findElement(By.xpath("//input[@id='devname']"));
		uniqueview.clear()  ; 
		uniqueview.sendKeys("MyUniqueviewFridayyy") ; 
		
		//save button
		driver.findElement(By.xpath("//div[@class='pbHeader']//input[@name='save']")).click();
		log.info("Save button clicked") ; 
		Thread.sleep(3000);
		AssertJUnit.assertEquals(driver.getTitle(), "Contacts ~ Salesforce - Developer Edition")  ;
		Select viewdropdown = new Select(driver.findElement(By.xpath("//select[@name='fcf']"))) ; 
		String newviewname = viewdropdown.getFirstSelectedOption().getText();
		AssertJUnit.assertEquals(newviewname, viewname) ;
		
		exreport.logExtentPass("Contacts Home page is opened. Created View name is displayed in drop down in that page by defalut. ") ; 
		}
	@Test	
	public static void ContactsTC27() throws InterruptedException {
		loginToSalesForce();
		Thread.sleep(3000);
		exreport.logExtentPass("SalesForce application Launced");
		goToContactsPage();
		exreport.logExtentPass("Contact Home page displayed");
		
		Select viewdropdown = new Select(driver.findElement(By.xpath("//select[@id='hotlist_mode']"))) ;
		viewdropdown.selectByVisibleText("Recently Created") ; 
		log.info(" Recently Created , selected from the view dropdown.");
		Thread.sleep(3000);
		String viewname=viewdropdown.getFirstSelectedOption().getText();
		AssertJUnit.assertEquals("Recently Created", viewname)  ;
		exreport.logExtentPass("Recently Created view is displayed");
		
	}
		
	@Test	
	public static void ContactsTC28() throws InterruptedException {
		loginToSalesForce();
		Thread.sleep(3000);
		exreport.logExtentPass("SalesForce application Launced");
		goToContactsPage();
		exreport.logExtentPass("Contact Home page displayed");
		
		Select viewdropdown = new Select(driver.findElement(By.xpath("//select[@name='fcf']"))) ;
		viewdropdown.selectByVisibleText("My Contacts") ; 
		log.info("My Contacts selected from the view dropdown.");
		Thread.sleep(3000);
		String viewname=viewdropdown.getFirstSelectedOption().getText();
		AssertJUnit.assertEquals("My Contacts", viewname)  ;
		exreport.logExtentPass("My Contacts view is displayed");
		
	}
	
	@Test	
	public static void ContactsTC29() throws InterruptedException {
		loginToSalesForce();
		Thread.sleep(3000);
		exreport.logExtentPass("SalesForce application Launced");
		goToContactsPage();
		exreport.logExtentPass("Contact Home page displayed");
		Thread.sleep(3000);
		//click on 1st contact 
		WebElement contact = driver.findElement(By.xpath("//div[@class='pbBody']//tr[2]//th/a")) ; 
		String contactname = contact.getText();
		contact.click();
		log.info("Contact name clicked") ; 
		String expectedTitle = "Contact: "+contactname+" ~ Salesforce - Developer Edition";
		Thread.sleep(3000);
		AssertJUnit.assertEquals(driver.getTitle(), expectedTitle) ; 
		exreport.logExtentPass("Contact Page related to "+contactname+", displayed ");
		
		
	}
	
	@Test	
	public static void ContactsTC30() throws InterruptedException {
		loginToSalesForce();
		Thread.sleep(3000);
		exreport.logExtentPass("SalesForce application Launced");
		goToContactsPage();
		exreport.logExtentPass("Contact Home page displayed");
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//span[@class='bFilter']//a[text()='Create New View']")).click();
		log.info("create new view clicked");
		Thread.sleep(4000);
		AssertJUnit.assertEquals(driver.getTitle(), "Contacts: Create New View ~ Salesforce - Developer Edition") ;
		log.info("Create new view page opened");
		
		WebElement uniqueview = driver.findElement(By.xpath("//input[@id='devname']"));
		uniqueview.clear()  ; 
		uniqueview.sendKeys("EFGH") ; 
		
		//save button
		driver.findElement(By.xpath("//div[@class='pbHeader']//input[@name='save']")).click();
		log.info("Save button clicked") ; 
		Thread.sleep(3000);
		
		
		WebElement error = driver.findElement(By.xpath("//td[@class='dataCol']//input[@id='fname']//following-sibling::div")) ; 
		String errormessage = error.getText();
		log.info("Error message printted - "+errormessage);
		AssertJUnit.assertEquals("Error: You must enter a value", errormessage) ; 
		
	}
	
	@Test	
	public static void ContactsTC31() throws InterruptedException {
		loginToSalesForce();
		Thread.sleep(3000);
		exreport.logExtentPass("SalesForce application Launced");
		goToContactsPage();
		exreport.logExtentPass("Contact Home page displayed");
		
		// //div[@id='00BDp000008VbgP_filterLinks']/a[text()='Create New View'] 
		// view name : //input[@id='fname']
		//unique view : //input[@id='devname']
		// save button : //div[@class='pbHeader']//input[@name='save']
		// page title : Contacts ~ Salesforce - Developer Edition
		// select view dropdown : //select[@id='00BDp000008VbgZ_listSelect']
		
		driver.findElement(By.xpath("//span[@class='bFilter']//a[text()='Create New View']")).click();
		log.info("create new view clicked");
		Thread.sleep(4000);
		AssertJUnit.assertEquals(driver.getTitle(), "Contacts: Create New View ~ Salesforce - Developer Edition") ;
		log.info("Create new view page opened");
		String viewname = "ABCDEFG";
		driver.findElement(By.xpath("//input[@id='fname']")).sendKeys(viewname);
		WebElement uniqueview = driver.findElement(By.xpath("//input[@id='devname']"));
		uniqueview.clear()  ; 
		uniqueview.sendKeys("EFGH") ; 
		
		//cancel button 
		driver.findElement(By.xpath("//div[@class='pbHeader']//input[@name='cancel']")).click();
		log.info("cancel button clicked") ; 
		Thread.sleep(3000);
		AssertJUnit.assertEquals(driver.getTitle(), "Contacts: Home ~ Salesforce - Developer Edition")  ;
		Select viewdropdown = new Select(driver.findElement(By.xpath("//select[@name='fcf']"))) ; 
		String newviewname = viewdropdown.getFirstSelectedOption().getText();
		Assert.assertNotEquals(newviewname, viewname) ;
		
		exreport.logExtentPass("Contacts Home page is displayed and the View ABCD should not be created.") ; 
		
		
		
	}
	@Test
	
	public static void ContactsTC32() throws InterruptedException {
		loginToSalesForce();
		Thread.sleep(3000);
		exreport.logExtentPass("SalesForce application Launced");
		goToContactsPage();
		exreport.logExtentPass("Contact Home page displayed");
		
	//new button click
		driver.findElement(By.xpath("//input[@name='new']")).click();
		log.info("new button clicked") ; 
		
		Thread.sleep(3000);
		AssertJUnit.assertEquals(driver.getTitle(), "Contact Edit: New Contact ~ Salesforce - Developer Edition") ; 
		log.info("Contact Edit: New Contact ~ Salesforce - Developer Edition") ; 
		exreport.logExtentPass("Contact Edit: New Contact ~ Salesforce - Developer Edition") ; 
		
		driver.findElement(By.xpath("//input[@name='name_lastcon2']")).sendKeys("Indian") ; 
		driver.findElement(By.xpath("//input[@id='con4']")).sendKeys("Global Media") ;
		
		driver.findElement(By.xpath("//td[@id='topButtonRow']//input[@name='save_new']")).click();
		log.info("Save & New button clicked") ;
		
		Thread.sleep(2000);
		AssertJUnit.assertEquals(driver.getTitle(), "Contact Edit: New Contact ~ Salesforce - Developer Edition");
		exreport.logExtentPass("New contact is created. Contact Edit: New Contact Page is dispalyed") ;
		
	}
	
	
		
		
}
