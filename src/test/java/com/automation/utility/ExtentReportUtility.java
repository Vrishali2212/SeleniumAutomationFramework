package com.automation.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportUtility {
	public static ExtentReports reports ; 
	public static ExtentSparkReporter spark ;
	public static ExtentTest testLogger ; 
	private static ExtentReportUtility ExtentReportObject ; 
	
	private void ExtentReportUtility() {
		
	}
	
	public static ExtentReportUtility getInstance() {
		if (ExtentReportObject== null) {
			ExtentReportObject = new ExtentReportUtility () ; 
		}
		return ExtentReportObject ; 
	}

	public void startExtentReport() {
		reports  = new ExtentReports() ;
		spark = new ExtentSparkReporter("SparkExtentReport.html") ; 
		reports.attachReporter(spark);
		 
		reports.setSystemInfo("Host Name" , "Salesforce") ; 
		reports.setSystemInfo("Environment" , "QA");
		reports.setSystemInfo("User Name", "Vrishali ");
		reports.setSystemInfo("mykey" , "myvalue") ; 
		
		spark.config().setDocumentTitle("Sales force login extent report");
		spark.config().setReportName("Vrishali's Extent Report ");
		spark.config().setTheme(Theme.DARK) ;
		
	}
	
	public void startSingleTestExtentReport(String methodName ) {
		testLogger = reports.createTest(methodName) ; 
	}
	
	public void endSingleTestExtentReport() {
		reports.flush();  
	} 
	public void logExtentInfo(String text) {
		testLogger.log(Status.INFO, text) ; 
	}
	public void logExtentPass(String testcaseName) {
		//testLogger.log(Status.PASS, text ) ; 
		testLogger.log(Status.PASS, MarkupHelper.createLabel(testcaseName+" passed. ", ExtentColor.CYAN)) ;
		}
	public void logExtentFail(String testcaseName) {
		//testLogger.log(Status.FAIL, text ) ; 
		testLogger.log(Status.FAIL, MarkupHelper.createLabel(testcaseName+" failed. ", ExtentColor.RED)) ;
	}
	public void logExtentFailWithException( String testcaseName, Throwable e) {
		//testLogger.log(Status.FAIL, text ) ; 
		testLogger.log(Status.FAIL, MarkupHelper.createLabel(testcaseName+" failed. ", ExtentColor.RED));
		testLogger.log(Status.FAIL, e) ; 
	}
	public void logExtentWarning(String text) {
		testLogger.log(Status.WARNING, text ) ; 
	}
	public void logExtentSkip(String text) {
		testLogger.log(Status.SKIP, text ) ; 
	}
	
 }
