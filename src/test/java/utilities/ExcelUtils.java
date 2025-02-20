package utilities;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static void main(String[] args) {

		// Create a new workbook (XSSFWorkbook for .xlsx)
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Create a sheet
        Sheet sheet = (Sheet) workbook.createSheet("Sheet1");

        // Create a row (row 0)
        Row row = ((XSSFSheet) sheet).createRow(0);

        // Create cells and set values in row 0
        Cell cell1 = row.createCell(0);
        cell1.setCellValue("Hello");

        Cell cell2 = row.createCell(1);
        cell2.setCellValue("World");

        // Create another row (row 1)
        Row row2 = ((XSSFSheet) sheet).createRow(1);

        // Create cells in row 1
        Cell cell3 = row2.createCell(0);
        cell3.setCellValue("Java");

        Cell cell4 = row2.createCell(1);
        cell4.setCellValue("Excel");

        // Write the workbook to a file
        try (FileOutputStream fileOut = new FileOutputStream("example.xlsx")) {
            workbook.write(fileOut);
            System.out.println("Excel file created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Closing workbook
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
