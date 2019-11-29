package classSrc;
import jxl.*;
import jxl.read.biff.BiffException;
import java.io.*;
public class ReadExcel {

    public static void getSheet(String path) throws BiffException, IOException {
    	   Workbook book = Workbook.getWorkbook(new File(path));
           Sheet sheet = book.getSheet(0);         
           int rows = sheet.getRows();
           int cols = sheet.getColumns();
           
           System.out.println("col：" + cols);
           System.out.println("row:" + rows);
           System.out.println("----------------------------");  
           /*
           result = new String[rows];
           int i=0;

           for(i=1;i<rows;i++)
           {
        	   //getCell(x,y)   y row, x column 
               result[i] = new String(sheet.getCell(0,i).getContents()+sheet.getCell(12,i).getContents());
           }
           */
    	
    } 
}

