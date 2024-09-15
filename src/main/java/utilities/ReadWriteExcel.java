package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import baseClasses.PageBase;

public class ReadWriteExcel extends PageBase {

	// constructor
	public ReadWriteExcel(WebDriver driver) {
		super();
	}

	// this method will read data from various sheets in excel
	public String[] readExcelData(String sheetName) throws IOException {
		File file = new File("./resource/MMT.xlsx");
		FileInputStream fileip = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fileip);
		XSSFSheet sheet = wb.getSheet(sheetName);
		String[] Details = null;

		if (sheetName == "CabsSheet") {
			Details = new String[2];
			XSSFRow row2 = sheet.getRow(1);
			for (int i = 0; i < 2; i++) {
				XSSFCell cell = row2.getCell(i);
				Details[i] = row2.getCell(i).toString();
			}
		} else if (sheetName == "GiftcardsSheet") {
			Details = new String[7];
			XSSFRow row2 = sheet.getRow(1);
			for (int i = 0; i < 7; i++) {
				XSSFCell cell = row2.getCell(i);
				CellType type = cell.getCellType();
				if (type.equals("NUMERIC")) {
					Details[i] = row2.getCell(i).getNumericCellValue() + "";
				} else {
					Details[i] = row2.getCell(i).toString();
				}
			}
		} else if (sheetName == "GiftcardsInvalid") {
			Details = new String[7];
			XSSFRow row2 = sheet.getRow(1);
			for (int i = 0; i < 7; i++) {
				XSSFCell cell = row2.getCell(i);
				CellType type = cell.getCellType();
				if (type.equals("NUMERIC")) {
					Details[i] = row2.getCell(i).getNumericCellValue() + "";
				} else {
					Details[i] = row2.getCell(i).toString();
				}
			}
		} else {
			System.out.println("Sheet does not exist");
		}
		return Details;
	}
}
