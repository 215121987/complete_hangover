package com.hangover.java.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashifqureshi
 * Date: 08/06/15
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExcelUtil implements Constants {
    
    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");


    public static void write(List entities,  OutputStream outputStream)throws IOException {
        try{
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("");
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            font.setFontHeight(16);
            XSSFRow headerRow = sheet.createRow(0);
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(font);
            List<String> columnNameList = getColumnNames();
            for (int i = 0; i < columnNameList.size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnNameList.get(i));
                cell.setCellStyle(headerCellStyle);
                sheet.autoSizeColumn(i);
            }
            XSSFRow row = sheet.createRow(1);
            Map<String,Object> columnNameValueMap = getColumnValueMap();
            for (int i = 0; i < columnNameList.size(); i++) {
                Cell cell = row.createCell(i);
                Object value = columnNameValueMap.get(columnNameList.get(i));
                if(null!= value){
                    if(value instanceof Double){
                        cell.setCellValue((Double) value);
                    }else{
                        cell.setCellValue(value+"");
                    }
                }
                sheet.autoSizeColumn(i);
            }
            workbook.write(outputStream);
            logger.info("Excel done");
        } catch (Exception e){
            logger.error("Unable to write file \n" + e);
        }finally {
            if (null != outputStream) {
                outputStream.flush();
                outputStream.close();
            }
        }
    }
    

    private static Double getCellValueAsDouble(String value){
        Double dValue=null;
        try{
            dValue = null!=value && !"".equals(value)?Double.parseDouble(value):null;
        }catch (NumberFormatException e){
            logger.error("Unable tp parse excel, Invalid data "+e);
           throw e;
        }
        return dValue;
    }

    private static String getCellValue(Row row, Integer cellIndex) {
        if (null == cellIndex || null == row.getCell(cellIndex))
            return null;
        Cell cell = row.getCell(cellIndex);
        //System.out.println(cell.getCellStyle().getDataFormatString());
        String value = null;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                if (cell.getCellStyle().getDataFormatString().contains("%"))
                    value = Math.round(cell.getNumericCellValue() * 100 *100.0)/100.0+ "";
                else
                    value = Math.round(cell.getNumericCellValue()*100.0)/100.0 + "";
                break;
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                if(null!=value)
                    value=value.trim();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue() + "";
                break;
        }
        return value;
    }


     public static List<String> getColumnNames(){
         return new ArrayList<String>();
     }


     public static Map<String,Object> getColumnValueMap(){
         return new HashMap<String, Object>();
     }

    
    



}
