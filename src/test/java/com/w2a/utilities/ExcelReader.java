package com.w2a.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	public String path;
	public String sheetName;
	public FileInputStream fis = null;
	public FileOutputStream fos = null;
	public XSSFWorkbook wb;
	public XSSFSheet ws;
	public XSSFCell cell;
	
	public ExcelReader(String path) {
		try{
			this.path = path;
			fis = new FileInputStream(this.path);
			wb = new XSSFWorkbook(fis);
			ws = wb.getSheetAt(0);
			fis.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public int getRowCount(String sheetName){
		this.sheetName = sheetName;
		ws = wb.getSheet(this.sheetName);
		int rowCount = ws.getPhysicalNumberOfRows();
		return rowCount;
	}
	
	public int getColumnCount(String sheetName){
		this.sheetName = sheetName;
		int colCount = ws.getRow(getRowCount(this.sheetName)-1).getPhysicalNumberOfCells();
		return colCount;
	}

	public String getData(String sheetName,int row,int column){
		this.sheetName = sheetName;
		cell = ws.getRow(row).getCell(column);
		DataFormatter df = new DataFormatter();
		String value = df.formatCellValue(cell);
		return value;
		
	}
}
