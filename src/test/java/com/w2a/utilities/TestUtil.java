package com.w2a.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import com.w2a.base.TestBase;

public class TestUtil extends TestBase{
	
	public static String screenShotFileName;
	public static String screenShotPath;

	/*
	 * Method to read the data from excel
	 * 
	 */
	@DataProvider(name="dp")
	public Object[][] getData(Method m){
		String sheetName = m.getName();
		System.out.println(sheetName);
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		Object[][] data = new Object[rows-1][cols];
		for(int i=1;i<rows;i++){
			for(int j=0;j<cols;j++){
				data[i-1][j] = excel.getData(sheetName, i, j);
			}
		}
		
		return data;
		
	}
	
	/*
	 * Method to capture screenshot of the webpage
	 */
	public static void captureScreenshot(){
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		String s= d.toString();
		s = s.substring(4, 19);
		s = s.replace(" ","_").replace(":","-");
		s = s+System.currentTimeMillis();
		screenShotFileName = s+".jpg";
		System.out.println(screenShotFileName);
		screenShotPath = System.getProperty("user.dir")+"\\src\\test\\resources\\screenshots\\"+screenShotFileName;
		System.out.println(screenShotPath);
		try {
			FileUtils.copyFile(srcFile, new File(screenShotPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
