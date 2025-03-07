package utilities;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
    @DataProvider(name = "LoginData")
    public static Object[][] getExcelData() {
        String filePath = "/Users/thekumar/eclipse-workspace/Kapruka/testData/Kapruka Login Data.xlsx"; // Path to your Excel file
        String sheetName = "Sheet 1"; // Sheet name
        ExcelUtils excel = new ExcelUtils(filePath, sheetName);

        int rowCount = excel.getRowCount();
        int colCount = excel.getColumnCount();

        Object[][] data = new Object[rowCount][colCount];

        // Fetch data from Excel (excluding header row)
        for (int i = 1; i <= rowCount; i++) { // Start from row index 1 (skip header)
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = excel.getCellData(i, j).trim(); // ✅ Store in 2D array (trim unnecessary spaces)
            }
        }
        return data;
    }
}
