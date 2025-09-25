package utils;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;


import java.io.*;
import java.util.*;

public class XlsReader {
    public String path;
    public FileInputStream fis = null;
    public FileOutputStream fileOut = null;
    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;
    private XSSFRow row = null;
    private XSSFCell cell = null;
    static XlsReader reader;
    public static propertyFileReader property = new propertyFileReader();
    static String projectRoot = System.getProperty("user.dir");
    public static String file_path =  projectRoot + "/" +property.getProperty("gui-config", "READWRITEDATAFILE_PATH");;

    public XlsReader(String path) {

        this.path = path;
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            fis.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the data from a specific row in a sheet, with each cell's data stored in a Map.
     * The map is populated with column names as keys and their corresponding cell values from the specified row as values.
     *
     * @param sheetName - The name of the sheet from which to retrieve data.
     * @param rowNum - The row number to retrieve data from (1-based index).
     * @return - A Map containing column names as keys and their corresponding cell values as values.
     */    public Map<String, String> getRowDataByColumn(String sheetName, int rowNum) {
        Map<String, String> rowData = new HashMap<>();
        String[] columnNames = getColumnNames(sheetName).toArray(new String[0]);
        for (String column : columnNames) {
            rowData.put(column, getCellData(sheetName, column, rowNum));
        }
        return rowData;
    }

    /**
     * Retrieves the number of rows present in the specified sheet.
     *
     * @param sheetName - The name of the sheet to count rows in.
     * @return - The number of rows in the sheet.
     */
    public int getRowCount(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1)
            return 0;
        else {
            sheet = workbook.getSheetAt(index);
            int number = sheet.getLastRowNum() + 1;
            return number;
        }

    }

    /**
     * Retrieves the data from a specific cell in the sheet based on the column name and row number.
     * The method checks the cell type and returns the appropriate value as a string, handling date and numeric types.
     *
     * @param sheetName - The name of the sheet containing the cell.
     * @param colName - The name of the column where the data is located.
     * @param rowNum - The row number of the cell (1-based index).
     * @return - The value from the specified cell as a string, or an error message if the cell is not found.
     */
    public String getCellData(String sheetName, String colName, int rowNum) {
        try {
            if (rowNum <= 0)
                return "";

            int index = workbook.getSheetIndex(sheetName);
            int col_Num = -1;
            if (index == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                //System.out.println(row.getCell(i).getStringCellValue().trim());
                if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
                    col_Num = i;
            }
            if (col_Num == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                return "";
            cell = row.getCell(col_Num);

            if (cell == null)
                return "";
            //System.out.println(cell.getCellType());
            if (cell.getCellType() == Cell.CELL_TYPE_STRING)
                return cell.getStringCellValue();
            else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

                String cellText = String.valueOf(cell.getNumericCellValue());
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // format in form of M/D/YY
                    double d = cell.getNumericCellValue();

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(HSSFDateUtil.getJavaDate(d));
                    cellText =
                            (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
                    cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
                            cal.get(Calendar.MONTH) + 1 + "/" +
                            cellText;

                    //System.out.println(cellText);

                }


                return cellText;
            } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());

        } catch (Exception e) {

            e.printStackTrace();
            return "row " + rowNum + " or column " + colName + " does not exist in xls";
        }
    }



    /**
     * Retrieves the data from a specific cell in the sheet based on the column index and row number.
     * The method checks the cell type and returns the appropriate value as a string, handling date and numeric types.
     *
     * @param sheetName - The name of the sheet containing the cell.
     * @param colNum - The column index (0-based) of the data.
     * @param rowNum - The row number of the cell (1-based index).
     * @return - The value from the specified cell as a string, or an error message if the cell is not found.
     */
    public String getCellData(String sheetName, int colNum, int rowNum) {
        try {
            if (rowNum <= 0)
                return "";

            int index = workbook.getSheetIndex(sheetName);

            if (index == -1)
                return "";


            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                return "";
            cell = row.getCell(colNum);
            if (cell == null)
                return "";

            if (cell.getCellType() == Cell.CELL_TYPE_STRING)
                return cell.getStringCellValue();
            else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

                String cellText = String.valueOf(cell.getNumericCellValue());
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // format in form of M/D/YY
                    double d = cell.getNumericCellValue();

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(HSSFDateUtil.getJavaDate(d));
                    cellText =
                            (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
                    cellText = cal.get(Calendar.MONTH) + 1 + "/" +
                            cal.get(Calendar.DAY_OF_MONTH) + "/" +
                            cellText;

                    System.out.println(cellText);

                }


                return cellText;
            } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());
        } catch (Exception e) {

            e.printStackTrace();
            return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
        }
    }


    /**
     * Sets the value of a specific cell in the sheet based on the column name and row number.
     * If the column or row does not exist, the method will return false.
     *
     * @param sheetName - The name of the sheet containing the cell.
     * @param colName - The column name where the data is to be set.
     * @param rowNum - The row number of the cell (1-based index).
     * @param data - The value to set in the specified cell.
     * @return - True if the data was set successfully, false if an error occurred.
     */
    public boolean setCellData(String sheetName, String colName, int rowNum, String data) {
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);

            if (rowNum <= 0)
                return false;

            int index = workbook.getSheetIndex(sheetName);
            int colNum = -1;
            if (index == -1)
                return false;


            sheet = workbook.getSheetAt(index);


            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                //System.out.println(row.getCell(i).getStringCellValue().trim());
                if (row.getCell(i).getStringCellValue().trim().equals(colName))
                    colNum = i;
            }
            if (colNum == -1)
                return false;

            sheet.autoSizeColumn(colNum);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                row = sheet.createRow(rowNum - 1);

            cell = row.getCell(colNum);
            if (cell == null)
                cell = row.createCell(colNum);

            // cell style
            //CellStyle cs = workbook.createCellStyle();
            //cs.setWrapText(true);
            //cell.setCellStyle(cs);
            cell.setCellValue(data);

            fileOut = new FileOutputStream(path);

            workbook.write(fileOut);

            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Sets the value of a specific cell in the sheet based on the column name, row number, and sets a hyperlink in the cell.
     * If the column or row does not exist, the method will return false.
     *
     * @param sheetName - The name of the sheet containing the cell.
     * @param colName - The column name where the data is to be set.
     * @param rowNum - The row number of the cell (1-based index).
     * @param data - The value to set in the specified cell.
     * @param url - The URL to associate as a hyperlink in the cell.
     * @return - True if the data and hyperlink were set successfully, false if an error occurred.
     */
    public boolean setCellData(String sheetName, String colName, int rowNum, String data, String url) {
        //System.out.println("setCellData setCellData******************");
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);

            if (rowNum <= 0)
                return false;

            int index = workbook.getSheetIndex(sheetName);
            int colNum = -1;
            if (index == -1)
                return false;


            sheet = workbook.getSheetAt(index);
            //System.out.println("A");
            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                //System.out.println(row.getCell(i).getStringCellValue().trim());
                if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName))
                    colNum = i;
            }

            if (colNum == -1)
                return false;
            sheet.autoSizeColumn(colNum); //ashish
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                row = sheet.createRow(rowNum - 1);

            cell = row.getCell(colNum);
            if (cell == null)
                cell = row.createCell(colNum);

            cell.setCellValue(data);
            XSSFCreationHelper createHelper = workbook.getCreationHelper();

            //cell style for hyperlinks
            //by default hypelrinks are blue and underlined
            CellStyle hlink_style = workbook.createCellStyle();
            XSSFFont hlink_font = workbook.createFont();
            hlink_font.setUnderline(XSSFFont.U_SINGLE);
            hlink_font.setColor(IndexedColors.BLUE.getIndex());
            hlink_style.setFont(hlink_font);
            //hlink_style.setWrapText(true);

            XSSFHyperlink link = createHelper.createHyperlink(XSSFHyperlink.LINK_FILE);
            link.setAddress(url);
            cell.setHyperlink(link);
            cell.setCellStyle(hlink_style);

            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);

            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * Creates a new sheet in the workbook with the specified name.
     *
     * @param sheetname - The name of the sheet to create.
     * @return - True if the sheet was created successfully, false if an error occurred.
     */
    public boolean addSheet(String sheetname) {

        FileOutputStream fileOut;
        try {
            workbook.createSheet(sheetname);
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Removes a sheet from the workbook with the specified name.
     * Returns false if the sheet does not exist.
     *
     * @param sheetName - The name of the sheet to remove.
     * @return - True if the sheet was removed successfully, false if the sheet does not exist.
     */
    public boolean removeSheet(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1)
            return false;

        FileOutputStream fileOut;
        try {
            workbook.removeSheetAt(index);
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Adds a new column to the specified sheet with the given column name.
     *
     * @param sheetName - The name of the sheet to add the column to.
     * @param colName - The name of the new column to add.
     * @return - True if the column was added successfully, false if an error occurred.
     */
    public boolean addColumn(String sheetName, String colName) {
        //System.out.println("**************addColumn*********************");

        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            int index = workbook.getSheetIndex(sheetName);
            if (index == -1)
                return false;

            XSSFCellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            sheet = workbook.getSheetAt(index);

            row = sheet.getRow(0);
            if (row == null)
                row = sheet.createRow(0);

            //cell = row.getCell();
            //if (cell == null)
            //System.out.println(row.getLastCellNum());
            if (row.getLastCellNum() == -1)
                cell = row.createCell(0);
            else
                cell = row.createCell(row.getLastCellNum());

            cell.setCellValue(colName);
            cell.setCellStyle(style);

            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;


    }

    /**
     * Removes a column from the specified sheet at the given column index.
     * All data in the column will be deleted.
     *
     * @param sheetName - The name of the sheet containing the column.
     * @param colNum - The column index (0-based) of the column to remove.
     * @return - True if the column was removed successfully, false if an error occurred.
     */
    public boolean removeColumn(String sheetName, int colNum) {
        try {
            if (!isSheetExist(sheetName))
                return false;
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            XSSFCellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
            XSSFCreationHelper createHelper = workbook.getCreationHelper();
            style.setFillPattern(HSSFCellStyle.NO_FILL);


            for (int i = 0; i < getRowCount(sheetName); i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    cell = row.getCell(colNum);
                    if (cell != null) {
                        cell.setCellStyle(style);
                        row.removeCell(cell);
                    }
                }
            }
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    /**
     * Checks whether a sheet with the specified name exists in the workbook.
     *
     * @param sheetName - The name of the sheet to check for existence.
     * @return - True if the sheet exists, false if it does not.
     */
    public boolean isSheetExist(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1) {
            index = workbook.getSheetIndex(sheetName.toUpperCase());
            if (index == -1)
                return false;
            else
                return true;
        } else
            return true;
    }

    /**
     * Retrieves the number of columns in the specified sheet.
     *
     * @param sheetName - The name of the sheet to count columns in.
     * @return - The number of columns in the sheet, or -1 if the sheet does not exist or is empty.
     */
    public int getColumnCount(String sheetName) {
        // check if sheet exists
        if (!isSheetExist(sheetName))
            return -1;

        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(0);

        if (row == null)
            return -1;

        return row.getLastCellNum();


    }

    /**
     * Adds a hyperlink to a specific cell in the sheet, allowing you to associate a URL with a message.
     * The hyperlink is inserted into the specified column for the given test case.
     *
     * @param sheetName - The name of the sheet to add the hyperlink to.
     * @param screenShotColName - The name of the column where the hyperlink will be added.
     * @param testCaseName - The name of the test case to search for.
     * @param index - The index at which to insert the hyperlink.
     * @param url - The URL to associate with the hyperlink.
     * @param message - The message to display in the cell containing the hyperlink.
     * @return - True if the hyperlink was added successfully, false if an error occurred.
     */
    public boolean addHyperLink(String sheetName, String screenShotColName, String testCaseName, int index, String url, String message) {
        //System.out.println("ADDING addHyperLink******************");

        url = url.replace('\\', '/');
        if (!isSheetExist(sheetName))
            return false;

        sheet = workbook.getSheet(sheetName);

        for (int i = 2; i <= getRowCount(sheetName); i++) {
            if (getCellData(sheetName, 0, i).equalsIgnoreCase(testCaseName)) {
                //System.out.println("**caught "+(i+index));
                setCellData(sheetName, screenShotColName, i + index, message, url);
                break;
            }
        }


        return true;
    }
    /**
     * Retrieves the row number in the sheet where a specific cell value is found within a given column.
     *
     * @param sheetName - The name of the sheet to search in.
     * @param colName - The name of the column to search in.
     * @param cellValue - The value to search for in the specified column.
     * @return - The row number where the value is found, or -1 if the value is not found.
     */
    public int getCellRowNum(String sheetName, String colName, String cellValue) {

        for (int i = 2; i <= getRowCount(sheetName); i++) {
            if (getCellData(sheetName, colName, i).equalsIgnoreCase(cellValue)) {
                return i;
            }
        }
        return -1;

    }
    /**
     * Retrieves the names of all the columns in the specified sheet.
     *
     * @param sheetName - The name of the sheet to retrieve column names from.
     * @return - A List of column names in the specified sheet.
     */
    public List<String> getColumnNames(String sheetName) {
        List<String> columnNames = new ArrayList<>();
        Sheet sheet = workbook.getSheet(sheetName);

        if (sheet != null) {
            Row firstRow = sheet.getRow(0);

            if (firstRow != null) {
                int columnCount = firstRow.getLastCellNum();

                for (int i = 0; i < columnCount; i++) {
                    String columnName = firstRow.getCell(i).getStringCellValue();
                    columnNames.add(columnName);
                }
            }
        }

        return columnNames;
    }
    /**
     * Reads data from an Excel sheet and returns it as an ArrayList of Object arrays,
     * where each array contains values from a row corresponding to the given column names.
     *
     * @param filePath - The path to the Excel file.
     * @param sheetName - The name of the sheet to read data from.
     * @param columnNames - The names of the columns to fetch data from.
     * @return - An ArrayList of Object arrays, each representing a row of data.
     */
    public static ArrayList<Object[]> getDataFromSheet(String filePath, String sheetName, String[] columnNames) {
        ArrayList<Object[]> myData = new ArrayList<>();
        reader = new XlsReader(filePath);

        for (int rowNum = 2; rowNum <= reader.getRowCount(sheetName); rowNum++) {
            Object[] rowData = new Object[columnNames.length];

            for (int i = 0; i < columnNames.length; i++) {
                rowData[i] = reader.getCellData(sheetName, columnNames[i], rowNum);
            }

            myData.add(rowData);
        }

        return myData;
    }

    /**
     * Updates the value of a specified property in the READWRITEFILE Excel file.
     * Searches for the given property in the first column of the sheet and updates its corresponding value in the adjacent column.
     *
     * @param property - The name of the property to find in the Excel file.
     * @param value - The new value to be set for the property.
     * @return - Returns `true` if the property was found and the value was successfully updated,
     *           or `false` if there was an error (e.g., property not found or IOException).
     */
    public static boolean storeDetails(String property, String value) {
        try {
            // Open the Excel file
            FileInputStream fis = new FileInputStream(file_path);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);

            boolean found = false;

            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getStringCellValue().equalsIgnoreCase(property)) {
                        row.getCell(cell.getColumnIndex() + 1).setCellValue(value);
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }

            // Write the updated data to the Excel file
            fis.close();
            FileOutputStream fos = new FileOutputStream(file_path);
            workbook.write(fos);
            fos.close();
            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Retrieves the value associated with a specified property from the READWRITEFILE Excel file.
     * Searches for the given property in the first column of the sheet and returns its corresponding value from the adjacent column.
     *
     * @param property - The name of the property to search for in the Excel file.
     * @return - Returns the value of the property if found, or `null` if the property is not found or there is an error (e.g., IOException).
     */
    public static String getDetails(String property) {
        String value = null;

        try {
            // Open the Excel file
            FileInputStream fis = new FileInputStream(file_path);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (cell.getStringCellValue().equalsIgnoreCase(property)) {
                        // Get the value from the next cell in the row
                        value = row.getCell(cell.getColumnIndex() + 1).getStringCellValue();
                        break;
                    }
                }
                if (value != null) break;
            }

            fis.close();
            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }



}


