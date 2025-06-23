package utils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

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

    /**
     * Function to check if the text contains alphabetic characters
     *
     * @param text - text content to validate the characters
     * @return boolean - text content availability
     */
    public static boolean containsAlphabaticCharacters(String text) {
        return text.matches(".*[a-zA-Z].*");
    }

    /**
     * Function to check if the text contains numeric characters
     *
     * @param text - text content to validate the characters
     * @return boolean - text content availability
     */
    public static boolean containsNumericCharacters(String text) {
        return text.matches("^[\\d.,]+$");
    }
    /**
     * Function to check if the text contains numeric characters with backslash
     *
     * @param text - text content to validate the characters
     * @return boolean - text content availability
     */
    public static boolean containsValuesOnDate(String text) {
        return text.matches("^\\d{2}[-/]\\d{2}[-/]\\d{4}$");
    }

    /**
     * Function to check if the text contains numeric characters with backslash
     *
     * @param text - text content to validate the characters
     * @return boolean - text content availability
     */
    public static boolean containsValuesOnDateYearFirst(String text) {
        return text.matches("^\\d{4}[-/]\\d{2}[-/]\\d{2}$");
    }
    /**
     * Function to check if the text contains numeric characters including negative values
     *
     * @param text - text content to validate the characters
     * @return boolean - text content availability
     */
    public static boolean containsNumericCharactersWithNegativeValues(String text) {
        return text.matches("^-?\\d{1,3}(,\\d{3})*(\\.\\d+)?$");
    }

    /**
     * Function to check if the text contains alphabetic and numeric characters
     *
     * @param text - text content to validate the characters
     * @return boolean - text content availability
     */
    public static boolean containsAlphAndNumCharacters(String text) {
        return text.matches("^(?=.*[a-zA-Z])(?=.*\\d).+$");
    }

    /**
     * Function to check if the text contains alphabetic, numeric and special characters
     *
     * @param text - text content to validate the characters
     * @return boolean - text content availability
     */
    public static boolean containsAlphNumAndSpecialCharacters(String text) {
        return text.matches("^[a-zA-Z0-9!@#$%^&*()_+{}\\[\\]:;<>,.?/~\\\\-]+$");
    }

    public static boolean containsAlphNumAndSpecialCharacters1(String text) {
        return text.matches("^[a-zA-Z0-9!@#$%^&*()_+{}\\[\\]:;<>,.?/~\\\\\\s-]+$");
    }

    /**
     * Function to check if the text contains alphabetic spaces and hyphen characters
     *
     * @param text - text content to validate the characters
     * @return boolean - text content availability
     */
    public static boolean containsAlphaHypenAndSpaceCharacters(String text) {
        return text.matches("^[a-zA-Z0-9\\s-]+$");

//        return text.matches("[a-zA-Z0-9]+(?:[\\s-][a-zA-Z0-9]+)*");
    }


    /**
     * Function to remove space from string
     *
     * @param text - text to remove space characters
     * @return text - text after removing space characters
     */
    public static String removeSpaceCharacters(String text) {
        return text.replaceAll("\\s", "");
    }

    /**
     * Function to compare two arraylist with or without the order
     *
     * @param list1 - List one to compare
     * @param list2 - List two to compare
     * @param ignoreOrder - True if ignore to check the same order between two lists else false
     * @return - boolean value based on success or failure
     */
    public static boolean compareTwoArraylist(List<String> list1, List<String> list2, boolean ignoreOrder) {
        if (ignoreOrder) {
            Set<?> set1 = new HashSet<>(list1);
            Set<?> set2 = new HashSet<>(list2);
            if (set1.equals(set2)) {
                System.out.println(" Lists have the same elements (ignoring order)");
                return true;
            } else {
                System.out.println(" Lists do not have the same elements (ignoring order)");
                return false;
            }
        } else {
            if (list1.equals(list2)) {
                System.out.println("Lists are exactly equal (including order)");
                return true;
            } else {
                System.out.println("Lists are NOT exactly equal (including order)");
                return false;
            }
        }

    }

    /**
     * Function to return last specified amount of text from the original text
     *
     * @param originalText - Original text
     * @param numberOfLastCharacters - Number of text to trim from last of original text
     * @return - a suffix is returned as text
     */
    public static String suffix(String originalText,int numberOfLastCharacters) {
        return originalText.length() >= numberOfLastCharacters ? originalText.substring(originalText.length() - numberOfLastCharacters) : originalText;
    }

    /**
     * Function convert date time of [ MMM dd, yyyy 'at' hh:mma ] format to [dd/MM/yyyy hh:mm a]
     *
     * @param inputDateTime - Input date
     * @return - converted date and time
     */
    public static String convertDateTime(String inputDateTime) throws ParseException, java.text.ParseException {

        // Define the input and output date formats
        SimpleDateFormat inputFormat = new SimpleDateFormat("MMM dd, yyyy 'at' hh:mma");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");

        // Parse the input date-time string
        Date date = inputFormat.parse(inputDateTime);

        // Format the date to the desired output format
        return outputFormat.format(date);
    }

    public enum sortType {DATE, NUMBER, STRING, SELECT}

    /**
     * Returns the character count of a given input string.
     *
     * @param inputText the text to count characters from
     * @return the length of the string, or 0 if null
     */
    public static int getCharacterCount(String inputText) {
        return inputText != null ? inputText.length() : 0;
    }

    /**
     * Returns today's date formatted as per the user's input pattern.
     *
     * @param formatPattern Date format pattern, e.g., "yyyy-MM-dd", "dd/MM/yyyy"
     * @return Formatted current date as a String
     */
    public static String getTodayDateFormatted(String formatPattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);
        return LocalDate.now().format(formatter);
    }

    /**
     * Copy to clipboard
     * @param text  text that should be copied to clipboard
     */
    public static void copyToClipboard(String text) {
        StringSelection selection = new StringSelection(text);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
    }

    /**
     * Removes leading occurrences of a specified character from a string.
     *
     * @param input the input string (e.g., "xxx709")
     * @param ch    the character to remove (e.g., 'x')
     * @return the string without leading occurrences of that character (e.g., "709")
     */
    public static String removeLeadingCharacters(String input, char ch) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.replaceFirst("^" + Character.toString(ch) + "+(?!$)", "");
    }

    /**
     * Function to check if the text matches masked card number format like '5471 65XX XXXX 6154'
     *
     * @param text - masked card number string to validate
     * @return boolean - true if format is valid
     */
    public static boolean containsMaskedCardFormat(String text) {
        return text != null && text.matches("^\\d{4} \\d{2}XX XXXX \\d{4}$");
    }

/**
     * Generate Random number between 0 -100
     * @return - return the value
     */
    public static String generateRandomAmountTwoDecimals() {
        Random random = new Random();
        double value = 0 + (100 - 0) * random.nextDouble();
        return String.format("%.2f", value); // returns string like "23.89"

    }



}
