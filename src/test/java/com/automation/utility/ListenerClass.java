package com.automation.utility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerClass implements ITestListener {
	
	private static ExtentReportUtility ExtentReportObj;
	
	public void onStart(ITestContext context) {
		System.out.println(("onStart event appeared "));
		ExtentReportObj = ExtentReportUtility.getInstance();
		ExtentReportObj.startExtentReport();
		System.out.println("Extent report started ");
	}

	public void onTestStart(ITestResult result) {
	System.out.println(("onTestStart event appeared "));
	String methodName = result.getMethod().getMethodName() ; 
	System.out.println("Method name = "+ methodName);
	ExtentReportObj.createNewTest(methodName);
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println(("onTestSuccess event appeared "));
		String methodName = result.getMethod().getMethodName() ;
		System.out.println("Method name = "+ methodName);
		ExtentReportObj.logExtentPass(methodName);
	 
	}

	public void onTestFailure(ITestResult result) {
		System.out.println(("onTestFailure event appeared "));
		ExtentReportObj.logExtentFail(result.getMethod().getMethodName());
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println(("onTestSkipped event appeared "));
		System.out.println(result.getSkipCausedBy());
		//ExtentReportObj.logExtentSkip(result.getSkipCausedBy();
		ExtentReportObj.logExtentSkip(result.getMethod().getMethodName());
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		System.out.println(("onTestFailedWithTimeout event appeared "));
	
		
	}



	public void onFinish(ITestContext context) {
		System.out.println(("onFinish event appeared "));
		ExtentReportObj.endSingleTestExtentReport();
		
	}

}
