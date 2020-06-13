package com.w2a.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

public class CustomListener extends TestBase implements ITestListener {

	public void onFinish(ITestContext arg0) {
		extent.flush();
		
	}

	public void onStart(ITestContext arg0) {
		
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult arg0) {
		try {
			TestUtil.captureScreenshot();
			childStep.addScreenCaptureFromPath(TestUtil.screenShotPath);
			childStep.fail(arg0.getName()+" Failed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void onTestSkipped(ITestResult arg0) {
		childStep.skip(arg0.getName()+" Test case skipped");
		//test.log(Status.SKIP, arg0.getName()+" Test case skipped");
		
	}

	public void onTestStart(ITestResult arg0) {
		String className = arg0.getTestClass().getRealClass().getSimpleName();   
		test = extent.createTest(className);
		childStep = test.createNode(arg0.getName());
		System.out.println(arg0.getName()+" "+TestUtil.isTestRunnable(arg0.getName(), excel));
		if(!TestUtil.isTestRunnable(arg0.getName(), excel)){
			throw new SkipException(arg0.getName()+" is not executed because run mode is No");
		}
		
		
	}
	
	public void onTestSuccess(ITestResult arg0) {
			childStep.pass(arg0.getName()+" Passed");
		
	}
	




}
