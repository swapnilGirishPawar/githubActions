package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadExcel {

    public static String[][] getExcelData() {

        String fileLocation = ("/Users/sethuraman/eclipse-workspace/SetMore/excel/Data1.xlsx");
        XSSFWorkbook wbook = null;
        try {
            wbook = new XSSFWorkbook(fileLocation);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        XSSFSheet sheet = wbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
        System.out.println("Inclusive of header: " + physicalNumberOfRows);
        System.out.println("No.of Rows: " + lastRowNum);
        short lastCellNum = sheet.getRow(0).getLastCellNum();
        System.out.println("No.of cells:" + lastCellNum);
        String[][] data = new String[lastRowNum][lastCellNum];
        for (int i = 1; i <= lastRowNum; i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < lastCellNum; j++) {
                XSSFCell cell = row.getCell(j);
                DataFormatter dft = new DataFormatter();
                String value = dft.formatCellValue(cell);
//				String value = cell.getStringCellValue();
                System.out.println(value);
                data[i - 1][j] = value;
            }
        }
        try {
            wbook.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return data;
    }

    public static Sheet file() throws IOException {
        String tempDirectory = CommonUtils.getLocalDirectory();
        String directory = tempDirectory + "/excel/Data1.xlsx";

        FileInputStream fis = new FileInputStream(directory);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        return sheet;
    }

    public static String email() throws IOException {
        Row row = file().getRow(1);
        Cell emailCell = row.getCell(0);
        String email = emailCell.toString();
        return email;
    }

    public static String password() throws IOException {
        Row row = file().getRow(1);
        Cell passwordCell = row.getCell(1);
        String password = passwordCell.toString();
        return password;
    }

}