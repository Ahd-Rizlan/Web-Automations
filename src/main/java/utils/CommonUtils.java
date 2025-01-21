package utils;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;

/**
 * This Class is used to create all the test utility functionality
 */
public class CommonUtils extends Drivers {

    public static final String USER_DIR = System.getProperty("user.dir");
    public static final String ROOT_PATH_TO_DATA_STORE_CSV = "//src//test//resources//Data-Store-Api.csv";
    public static final String ROOT_PATH_TO_PROPERTY_FILE = "//src//test//resources//gui-config.properties";
    public static final String ROOT_PATH_TO_API_PROPERTY_FILE = "//src//test//resources//api-config.properties";
    public static final String ROOT_PATH_TO_JSON_FILE = "//src//test//java//api//utils//payloads";
    private static final Logger LOG = LogManager.getLogger(CommonUtils.class);
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String[] FIRST_NAME = {"Kevin", "Steve", "Mark", "Adam", "Eva", "Sachin", "Peter", "Bruce", "William", "Charlie"};
    private static final String[] LAST_NAME = {"Smith", "Johnson", "Brown", "Kingstone", "Miller", "Thomas", "Sydney", "White", "Singh", "Bell"};
    private static final String[] ADDRESS = {"52685 Atwood Terrace", "9684 Elgar Crossing", "73 Prairie Rose Street", "13 Longview Road", "325 Homewood Point", "0 Colorado Lane"};
    private static final String[] PHONE_NUMBER = {"0422672001", "0422672100", "0423472100", "0423472999", "0456789123"};
    private static final String[] POSTCODE = {"2000", "2009", "2016", "2135", "3000", "3005", "4000", "4008", "5000", "5012", "6000", "6006"};
    public static String BROWSER = "Browser";
    public static String VERSION = "Browser.Version";
    public static String URL_VALUE;
    public static String ENVIRONMENT = "environment";
    public static String HEADLESSMODE = "HeadlessMode";
    public static String BROWSERMODE = "browserMode";



    /**
     * Function to return RandomAlphaNumeric
     *
     * @param count of the alphanumeric text
     * @return Alphanumeric String
     */
    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    /**
     * Function to return Random FirstName
     *
     * @return Firstname String
     */
    public static String randomFirstName() {

        Random r = new Random();

        return FIRST_NAME[r.nextInt(FIRST_NAME.length)];

    }


    /**
     * Function to return Random FirstName
     *
     * @return Lastname String
     */

    public static String randomLastName() {

        Random r = new Random();

        return LAST_NAME[r.nextInt(LAST_NAME.length)];

    }

    /**
     * Function to return Random Address
     *
     * @return Address String
     */

    public static String randomAddress() {

        Random r = new Random();

        return ADDRESS[r.nextInt(ADDRESS.length)];

    }

    /**
     * Function to return Random Phone number
     *
     * @return Phonenumber String
     */

    public static String randomPhonenumber() {

        Random r = new Random();

        return PHONE_NUMBER[r.nextInt(PHONE_NUMBER.length)];

    }

    /**
     * Function to return Random Postcode
     *
     * @return Postcode String
     */
    public static String randomPostcode() {

        Random r = new Random();
        return POSTCODE[r.nextInt(POSTCODE.length)];

    }
    //get the last two digits of the current year and add two more
    public static String selectCCYear() {
        DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
        String formattedDate = df.format(Calendar.getInstance().getTime());
        int value = Integer.parseInt(formattedDate);
        value = value + 1;
        return String.valueOf(value);
    }

    public static String subStringText(String word, int value1, int value2) {
        return word.substring(value1, value2);
    }

    public static String[] splitText(String text, String regex) {
        return text.split(regex);
    }

    /**
     * Function to return the number of rows in a table.
     *
     * @param tableRows - By object of the table rows
     * @return int - Number of rows in the table
     */
    public static int findTableSize(By tableRows) {
        return driver.findElements(tableRows).size();
    }

    /**
     * Function to strip the ' of ' from the pagination text
     *
     * @param page - String of the pagination text to be stripped e.g. '1 of 10'
     * @return String[] - Array of the stripped pagination text
     */
    public static String[] stripPagination(String page) {
        page = page.replace("(", "");
        page = page.replace(")", "");
        String[] pages = page.split(" of ");
        LOG.info("{},{}",pages[0],pages[1]);
        return pages;
    }

	public static String readJsonFile(String fileName) throws IOException, ParseException {
		//creating a JSON parser object
		JSONParser jsonParser = new JSONParser();
		//passing the content of the JSON file
		JSONObject jsonobj = (JSONObject) jsonParser.parse(new FileReader(fileName));
		//reading the data from the JSON file
		String id = (String) jsonobj.get("isbn");
		return id;
	}

    /**
     * Function to find the position of a By object in an array of By objects
     *
     * @param headers - Array of By objects
     * @param target  - By object to find the position of
     * @return int - Position of the By object in the array
     */
    public static int findPosition(By[] headers, By target) {
        List<By> list = Arrays.asList(headers);
        return list.indexOf(target) + 1;
    }

    public enum sortType {DATE, NUMBER, STRING, SELECT}

}
