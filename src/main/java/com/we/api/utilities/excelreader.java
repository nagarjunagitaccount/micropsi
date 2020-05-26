package com.we.api.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class excelreader {
	 private static String s = "DataSheet1";
	 private static String Pathfortestdata = "./src/main/resources/We_API_TDATA.xlsx";
	 private static Map<String, String> map = new HashMap<String, String>();

	 public  String get(String testcasename,String columnname) throws IOException {
	 FileInputStream fis;
	 int k =0;
	 try {
	 fis = new FileInputStream(Pathfortestdata);
	 HSSFWorkbook wb = new HSSFWorkbook(fis);
	 HSSFSheet ws = wb.getSheet(s);
	 int rows = ws.getPhysicalNumberOfRows();
	 for(int i=0;i<rows;i++){
	 int cols = ws.getRow(0).getPhysicalNumberOfCells();
	 for (int j = 0; j < cols; j++) { 
	 if(ws.getRow(i).getCell(j,Row.CREATE_NULL_AS_BLANK).toString().replace(".0", "").
	 equalsIgnoreCase(columnname)){ 
	 k=j;
	 }
	 map.put(ws.getRow(i).getCell(0,Row.CREATE_NULL_AS_BLANK).toString().replace(".0",""),
	 ws.getRow(i).getCell(k,Row.CREATE_NULL_AS_BLANK).toString().replace(".0", "")); 
	 }
	 } 
	 }catch (Exception e){e.printStackTrace(); } 
	 return map.get(testcasename);
	 } 
	 



}
