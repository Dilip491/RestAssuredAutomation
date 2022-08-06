package RestAssuredDataDrivenTesting;


import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class XLUtility {

    public static FileInputStream fis;
    public static XSSFWorkbook wb;
    public static XSSFSheet sh;
    public static XSSFRow row;
    public static XSSFCell cell;

    public static int getrowcount(String xlfile,String xlsheet) throws IOException {
        fis=new FileInputStream(xlfile);
        wb=new XSSFWorkbook(fis);
        sh=wb.getSheet(xlsheet);
        int rowcount=sh.getLastRowNum();
        wb.close();
        fis.close();
        return rowcount;
    }

    public static int getcellcount(String xlfile,String xlsheet,int rownum)throws IOException{
        fis=new FileInputStream(xlfile);
        wb=new XSSFWorkbook(fis);
        sh=wb.getSheet(xlsheet);
        row=sh.getRow(rownum);
        int cellcount=row.getLastCellNum();
        wb.close();
        fis.close();
        return cellcount;
    }

    public static String getcelldata(String xlfile,String xlsheet,int rownum,int cellnum)throws IOException{
        fis=new FileInputStream(xlfile);
        wb=new XSSFWorkbook(fis);
        sh=wb.getSheet(xlsheet);
        row=sh.getRow(rownum);
        cell=row.getCell(cellnum);

        DataFormatter formatter=new DataFormatter();
        String celldata=formatter.formatCellValue(cell);
        return celldata;
    }
}
