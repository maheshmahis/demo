package mvn_methods;

import java.io.FileInputStream;
import java.util.Calendar;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;

import mvn_driver.DriverScript;

public class Datatable extends DriverScript{
	
	/********************************
	 * Method      : readfromexcel
	 * purpose     : To read data from excel
	 * Author      : Mahesh S
	 * Review By   :
	 * Approved By :
	 * createddate : 2-Jun-2023
	 * Arguments   : String <excelpath>, String <sheetname>, String <logicalname>
	 * return type : Map
	 * 
	 ***************************/
	@Test
	public Map<String, String> readfromexcel( String sheetname, String logicalname){
		Map<String, String> mobj = null;
		FileInputStream fin = null;
		Workbook wb =null;
		Sheet sh = null;
		Row rw1 = null;
		Row rw2 = null;
		Cell c1 = null;
		Cell c2 = null;
		Calendar cal = null;
		String day = null;
		String month = null;
		String year = null;
		int rownum = 0;
		int rownumb = 0;
		String key=null;
		String value = null;
		
		try {
			
			mobj = new HashMap<String, String>();
			fin = new FileInputStream(System.getProperty("user.dir")+"\\configuration\\exceldata.xlsx");
			wb= new XSSFWorkbook(fin);
			sh= wb.getSheet(sheetname);
			
			Assert.assertNotNull(sh,"Sheetname provided is invalid"+sheetname);
			
			rownum = sh.getPhysicalNumberOfRows();
			
			for(int r=0; r<rownum; r++) {
				rw1 = sh.getRow(r);
				c1=rw1.getCell(0);
				
				if(c1.getStringCellValue().trim().equalsIgnoreCase(logicalname)) {
					rownumb = r;
					break;
				}
			}
			
			if(rownum>0) {
				rw1=sh.getRow(0);
				rw2=sh.getRow(rownumb);
				
				for(int c=0;c<rw1.getPhysicalNumberOfCells();c++) {
					c1=rw1.getCell(c);
					key = c1.getStringCellValue();
					
					c2=rw2.getCell(c);
					
					if(c2==null || c2.getCellType()==CellType.BLANK) {
						value="";
					}else if(c2.getCellType()==CellType.STRING) {
						value = c2.getStringCellValue();
					}else if(c2.getCellType()==CellType.BOOLEAN) {
						value= String.valueOf(c2.getBooleanCellValue());
					}else if(c2.getCellType()==CellType.NUMERIC) {
						if(DateUtil.isCellDateFormatted(c2)==true) {
							double dt = c2.getNumericCellValue();
							cal = Calendar.getInstance();
							cal.setTime(DateUtil.getJavaDate(dt));
							
							if(cal.get(Calendar.DAY_OF_MONTH)<10) {
								day = "0"+cal.get(Calendar.DAY_OF_MONTH);
							}else {
								day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
							}
							
							if((cal.get(Calendar.MONTH)+1)<10) {
								month = "0"+ (cal.get(Calendar.MONTH)+1);
							}else {
								month = String.valueOf((cal.get(Calendar.MONTH)+1));
							}
							
							year = String.valueOf(cal.get(Calendar.YEAR));
							
							value = day+"/"+month+"/"+year;
						}else {
							value = String.valueOf(c2.getNumericCellValue());
						}
					}
				
					mobj.put(key, value);
				
				}
				return mobj;
				
			}else {
				System.out.println("Invalid excel file");
				return null;
			}
			
			
		}catch (Exception e) {
			System.out.println("Exception in read from excel"+e);
			return null;
		}finally {
			try {
				fin.close();
				fin=null;
				wb.close();
				wb=null;
				rw1=null;
				rw2=null;
				c1=null;
				c2=null;
				key=null;
				value=null;
				day=null;
				month=null;
				year=null;
			
				
			}catch (Exception e) {
				System.out.println(e);
			}
		}
		
	}

}
