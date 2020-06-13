package com.w2a.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentManager;


public class TestBase {
	
	/*
	 * Class to initialize the following
	 * WebDriver-done
	 * Properties-done
	 * Logs-done
	 * ExtentReports-done
	 * DB
	 * Excel
	 * Mail
	 */
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = LogManager.getLogger(TestBase.class.getName());
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\TestData.xlsx");
	public static WebDriverWait wait;
	public static ExtentReports extent ;
	public static ExtentTest test;
	public static ExtentTest childStep;
	public static ExtentManager e = new ExtentManager();
	public static List stepResults = new ArrayList<String>();
	public static String testResult;
	
	@BeforeSuite
	public void setUp(){
		
		if(driver==null){
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
				config.load(fis);
				log.info("Config file loaded successfully");
			} catch (Exception e) {
				e.printStackTrace();
				log.error("### Error in loading config file ###"+e.getStackTrace());
			}
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
				OR.load(fis);
				log.info("Object Repository config file loaded successfully");
			} catch (Exception e) {
				e.printStackTrace();
				log.error("### Error in loading Object repository config file ###"+e.getStackTrace());
			}
			
			if(config.getProperty("browser").equalsIgnoreCase("ie")){
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			else if(config.getProperty("browser").equalsIgnoreCase("chrome")){
				ChromeOptions chromeOptions= new ChromeOptions();
				chromeOptions.setBinary("C:\\Users\\Elcot\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver(chromeOptions);
				log.info("Chrome browser launched successfully");
			}
			else{
				System.out.println("Unsupported browser");
			}
			
			driver.get(config.getProperty("testsiteurl"));
			try {
				Thread.sleep(60);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(
					Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);
			
			extent = e.getInstance();
			
		}
		
	}
	
	/*
	 * Method to check whether the element is present or not
	 */
	public boolean isElementPresent(String locator,String locatorType){
		WebElement e = getElement(locator, locatorType);
		if(e!=null){
			return true;
		}
		else{
			return false;
		}
	}
	
	public WebElement getElement(String locator,String locatorType){
		WebElement e;
		try {
			if(locatorType.equalsIgnoreCase("id")){
				e = driver.findElement(By.id(locator));
				log.info("Element found with locator: "+locator+" and locatorType: "+locatorType);
				return e;
			}
			else if(locatorType.equalsIgnoreCase("name")){
				e = driver.findElement(By.name(locator));
				log.info("Element found with locator: "+locator+" and locatorType: "+locatorType);
				return e;
			}
			else if(locatorType.equalsIgnoreCase("classname")){
				e = driver.findElement(By.className(locator));
				log.info("Element found with locator: "+locator+" and locatorType: "+locatorType);
				return e;
			}
			else if(locatorType.equalsIgnoreCase("css")){
				e = driver.findElement(By.cssSelector(locator));
				log.info("Element found with locator: "+locator+" and locatorType: "+locatorType);
				return e;
			}
			else if(locatorType.equalsIgnoreCase("xpath")){
				e = driver.findElement(By.xpath(locator));
				log.info("Element found with locator: "+locator+" and locatorType: "+locatorType);
				return e;
			}
			else if(locatorType.equalsIgnoreCase("linktext")){
				e = driver.findElement(By.linkText(locator));
				log.info("Element found with locator: "+locator+" and locatorType: "+locatorType);
				return e;
			}
			else if(locatorType.equalsIgnoreCase("partiallinktext")){
				e = driver.findElement(By.partialLinkText(locator));
				log.info("Element found with locator: "+locator+" and locatorType: "+locatorType);
				return e;
			}
			else if(locatorType.equalsIgnoreCase("tagname")){
				e = driver.findElement(By.tagName(locator));
				log.info("Element found with locator: "+locator+" and locatorType: "+locatorType);
				return e;
			}
			else{
				log.error("### Element not found with locator: " +locator+" and locatorType: "+locatorType+" ###");
				return null;
			}
		} catch (Exception e1) {
			log.error("### Element not found with locator: " +locator+" and locatorType: "+locatorType+" ###"+e1.getMessage());
			return null;
		}
	}
	
	public void clickElement(String locator,String locatorType){
		WebElement e = getElement(locator, locatorType);
		try {
			e.click();
			log.info("Element: "+locator+" clicked successfully");
		} catch (Exception e1) {
			log.error("### Unable to click element with locator: "+locator+" and locator type:"+locatorType+e1.getMessage());
		}
	}
	
	public void typeText(String locator,String locatorType,String textToType){
		WebElement e = getElement(locator, locatorType);
		try {
			e.sendKeys(textToType);
			log.info("Typed text: "+textToType+" in element: "+locator+" successfully");
		} catch (Exception e1) {
			log.error("### Unable to type into element with locator: "+locator+" and locator type:"+locatorType+e1.getMessage());
		}
	}
	
	public void verifyEquals(String expected,String actual,String validationString){
		if(expected.equals(actual)){
			stepResults.add("Pass");
			childStep.pass(validationString+" Passed");
		}
		else{
			stepResults.add("Fail");
			childStep.fail(validationString+" Failed");
		}
			
	}
	
	public void verifyIsTrue(boolean result,String validationString){
		if(result){
			stepResults.add("Pass");
			childStep.pass(validationString+" Passed");
		}
			
		else{
			stepResults.add("Fail");
			childStep.pass(validationString+" Failed");
		}
			
	}
	
	public void verifyIsFalse(boolean result,String validationString){
		if(!result){
			stepResults.add("Pass");
			childStep.pass(validationString+" Passed");
		}
			
		else{
			stepResults.add("Fail");
			childStep.pass(validationString+" Failed");
		}
	}
	
	public void verifyAll(){
		if(stepResults.contains("Fail")){
			testResult = "Fail";
			stepResults.clear();
		}
			
		else{
			testResult = "Pass";
			stepResults.clear();
		}
			
	}
	
	@AfterSuite
	public void tearDown(){
		
		if(driver!=null){
			driver.close();
		}
		
	}

}
