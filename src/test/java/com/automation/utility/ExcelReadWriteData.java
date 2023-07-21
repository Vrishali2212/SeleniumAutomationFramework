package com.automation.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelReadWriteData {
	public ArrayList<ArrayList> readSheetDataFromXLFile(String sheetName ) throws InvalidFormatException, IOException  {
		File file ; 
		//XSSFWorkbook workbook ; 
		
		file = new File (Constants.EXCEL_FILE) ;
		ArrayList<ArrayList > xlrows = new ArrayList<ArrayList> ()  ;
		XSSFWorkbook workbook = new XSSFWorkbook (file) ; 
		XSSFSheet sheet = workbook.getSheet(sheetName) ;
		Iterator<Row> rows = sheet.rowIterator();
		 
		 while (rows.hasNext()) {
			 XSSFRow row  = (XSSFRow) rows.next() ;
			 Iterator<Cell> cells =  row.cellIterator() ;
			 ArrayList xlcells = new ArrayList() ;
			 while(cells.hasNext()) { 
				 XSSFCell cell = (XSSFCell) cells.next();
				if ( cell.getCellType() == CellType.STRING)  {
					String str = cell.getStringCellValue();
				// System.out.print( str  +"\t")  ; 
				 xlcells.add(str);
				}
				else if  ( cell.getCellType() == CellType.NUMERIC)  {
					int n = (int)cell.getNumericCellValue();
					//System.out.print(n+ "\t");
					xlcells.add(n ) ;
				}
			 }
			// System.out.println();
			 xlrows.add(xlcells) ;
		 } // while ends 
		// System.out.println(xlrows);
		 workbook.close();
		 return xlrows;
		 
	} // function ends 
	
	public  ArrayList getEmpRecord(int rownum)  throws InvalidFormatException, IOException{
		//POIExcelReaderWriterUtility poireader = new POIExcelReaderWriterUtility();
		ArrayList<ArrayList> list = readSheetDataFromXLFile("Data");
		
		return list.get(rownum) ; 
	}
	
	
	public ArrayList<ArrayList> getSheetData(String sheetname) throws InvalidFormatException, IOException {
		ArrayList<ArrayList > xlrows = new ArrayList<ArrayList> ()  ;
		File file = new File(Constants.EXCEL_FILE);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet =workbook.getSheet(sheetname) ; 
		Iterator<Row> it_rows = sheet.rowIterator();
		while (it_rows.hasNext()) {
			XSSFRow row = (XSSFRow) it_rows.next();
			Iterator<Cell> it_cells = row.cellIterator();
			 ArrayList xlcells = new ArrayList() ;
			while (it_cells.hasNext()) {
				XSSFCell cell = (XSSFCell) it_cells.next();
				if (cell.getCellType() == CellType.STRING) {
					String data = cell.getStringCellValue();
					xlcells.add(data);
				}
			}
			xlrows.add(xlcells);
		}
		 workbook.close();
		 return xlrows;
	}
	
	public Object[][] getSheetDataIn2DArray(String sheetname) throws InvalidFormatException, IOException {
		
		ArrayList<ArrayList > xlrows = getSheetData(sheetname);
		
		String[][] logindata = new String[xlrows.size()][];
		for (int i = 0; i < xlrows.size(); i++) {
		    ArrayList<String> row = xlrows.get(i);
		    logindata[i] = row.toArray(new String[row.size()]);
		}
		return logindata;
	}
	public static void main(String [] args) {
		ExcelReadWriteData e = new ExcelReadWriteData();
		try {
			Object[][] logindetails= e.getSheetDataIn2DArray("valid_logindata");
			int rows = logindetails.length;
			System.out.println("Excel rows = "+rows);
			for (int i=0;i<rows;i++) {
				int cols = logindetails[i].length;
				for (int j=0;j<cols;j++) {
					//System.out.println("Cols = " + cols);
					System.out.println(logindetails[i][j]);
				
				}
			}
			
		} catch (InvalidFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
