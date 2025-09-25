/***
 * Author: TJ
 * ***/


package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import static utils.CommonUtils.ROOT_PATH_TO_DATA_STORE_CSV;
import static utils.CommonUtils.USER_DIR;

public class DataStoreReadWriteApi {

    private static final String FILE_PATH = USER_DIR + ROOT_PATH_TO_DATA_STORE_CSV;

    public static boolean storeAPIDetails(String property, String value) {

        try {
            CSVReader csvReader = new CSVReader(new FileReader(FILE_PATH));
            List<String[]> csvBody = null;
            try {
                csvBody = csvReader.readAll();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // get CSV row column and replace with by using row and column
            for (int i = 0; i < csvBody.size(); i++) {
                String[] strArray = csvBody.get(i);
                for (int j = 0; j < strArray.length; j++) {
                    if (strArray[j].equalsIgnoreCase(property)) { //String to be replaced
                        csvBody.get(i)[j + 1] = value; //Target replacement
                    }
                }
            }
            try {
                csvReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Write to CSV file which is open
            CSVWriter writer = null;
            try {
                writer = new CSVWriter(new FileWriter(FILE_PATH));
            } catch (IOException e) {
                e.printStackTrace();
            }
            writer.writeAll(csvBody);
            try {
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException | CsvException e) {
            e.printStackTrace();
        }

        return true;

    }

    public static String getAPIDetails(String property) {
        String value = null;
        try {
            CSVReader csvReader = new CSVReader(new FileReader(FILE_PATH));
            List<String[]> csvBody = null;
            try {
                csvBody = csvReader.readAll();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // get CSV row column and replace with by using row and column
            for (int i = 0; i < csvBody.size(); i++) {
                String[] strArray = csvBody.get(i);
                for (int j = 0; j < strArray.length; j++) {
                    if (strArray[j].equalsIgnoreCase(property)) { //String to be replaced
                        value = csvBody.get(i)[j + 1];
                    }
                }
            }
            try {
                csvReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException | CsvException e) {
            e.printStackTrace();
        }

        return value;
    }

}

