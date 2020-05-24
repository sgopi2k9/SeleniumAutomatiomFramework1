package com.w2a.utilities;

import org.testng.annotations.DataProvider;
import java.lang.reflect.Method;

import com.w2a.base.TestBase;

public class TestUtil extends TestBase{

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
	
}
