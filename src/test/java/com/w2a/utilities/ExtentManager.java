package com.w2a.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	private ExtentHtmlReporter htmlReporter;
	private ExtentReports extent;
	
	public ExtentReports getInstance(){
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+
				"\\target\\surefire-reports\\html\\extentreport.html");
		
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Banking test Automation");
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setEncoding("utf-8");
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		//extent.setSystemInfo("Company", "ABC");
		
		return extent;
	}

}
