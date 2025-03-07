package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;

public class ExcelUtils {
    private Workbook workbook;
    private Sheet sheet;

    // Constructor to load the Excel file
    public ExcelUtils(String filePath, String sheetName) {
        try {
            FileInputStream file = new FileInputStream(new File(filePath));
            workbook = WorkbookFactory.create(file);
            sheet = workbook.getSheet(sheetName);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get the total row count
    public int getRowCount() {
        int rowCount = 0;
        for (Row row : sheet) {
            if (row.getCell(0) != null && !row.getCell(0).toString().trim().isEmpty()) {
                rowCount++;
            }
        }
        return rowCount - 1; // Subtract 1 to exclude the header row
    }

	 // Get the total column count (ignoring empty columns)
    public int getColumnCount() {
        Row firstRow = sheet.getRow(0);
        int count = 0;
        if (firstRow != null) {
            for (Cell cell : firstRow) {
                if (!cell.toString().trim().isEmpty()) {
                    count++;
                }
            }
        }
        return count;
    }


    // Get specific cell data as String
    public String getCellData(int rowNumber, int colNumber) {
        Row row = sheet.getRow(rowNumber);
        if (row != null) {
            Cell cell = row.getCell(colNumber);
            if (cell != null) {
                DataFormatter formatter = new DataFormatter();
                return formatter.formatCellValue(cell); // ✅ Returning formatted value
            }
        }
        return ""; // ✅ Return empty string instead of error message
    }

    // Print all Excel data
    public void printExcelData() {
        for (int i = 0; i <= getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                System.out.print(getCellData(i, j) + "\t");
            }
            System.out.println();
        }
    }
}