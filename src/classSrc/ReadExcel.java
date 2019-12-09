package classSrc;
import jxl.*;
import jxl.read.biff.BiffException;
import java.io.*;
import java.util.Vector;
public class ReadExcel {

    public static Vector<Vector<Object>> getSheet(String path) throws BiffException, IOException {
    	   Workbook book = Workbook.getWorkbook(new File(path));
           Sheet sheet = book.getSheet(0);         
           int rows = sheet.getRows();
           int cols = sheet.getColumns();
           
           System.out.println("col：" + cols);
           System.out.println("row:" + rows);
           System.out.println("----------------------------"); 
           Vector<Vector<Object>> data = new Vector<>();
           int i = 0;
           for(i=1;i<rows;i++){
        	   Vector<Object> sample = new Vector<>();
        	   sample.add(sheet.getCell(1,i).getContents());
        	   sample.add(sheet.getCell(0,i).getContents());
        	   sample.add(sheet.getCell(2,i).getContents());
        	   data.add(sample);
           }
           return data;
    	
    } 
}

