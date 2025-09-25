package utils.constants;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This Class is used to store all the test constant variables.
 */
public class MyAccountsConstants {

    //-------------------- Tab Titles --------------------
    public static final String TAB_ACCOUNTS = "Accounts";
    public static final String TAB_DEPOSITS = "Deposits";
    public static final String TAB_LOANS = "Loans";
    public static final String TAB_PAWNING = "Pawning";
    public static final String TAB_PAWNING_HISTORY = "Pawning History";
    public static final String TAB_T_BILLS = "T-Bills";
    public static final String TAB_REPO = "Repo";
    public static final String TAB_OTHER_ACCOUNTS = "Other Accounts";
    public static final String TAB_OWN_ACCOUNT = "Own Account";
    public static final String[] TAB_NAMES = {"Accounts", "Deposits", "Loans", "T-Bills", "Repo"};
    public static final String[] TAB_NAME_PAWNING = {"Pawning"};
    public static final String[] TAB_NAMES_FOR_NICKNAME = {"Accounts", "Deposits"};

    //-------------------- Tile Headers --------------------
    public static final String[] TILE_HEADER_PAWNING = {"Accounts / Pawning"};
    public static final String[] TILE_HEADERS = {
            "Operative", "My Accounts / FD", "My Accounts / Loans",
            "Treasury Bills", "Repo Investments"
    };
    public static final String[] TILE_HEADERS_FOR_NICKNAME = {
            "Operative", "My Accounts / FD"
    };

    //-------------------- Table Headers --------------------
    public static final String[] SAVINGS_ACCOUNTS_LIST_TABLE_HEADERS = {
            "Account Number", "Currency", "Available Balance", "Status of the Account", "Account Type", "Nickname"
    };
    public static final String[] ALL_ISSUED_CHEQUES_TABLE_HEADERS = {
            "Cheque Number", "Issues Account", "Presented Bank", "Currency", "Amount", "Date", "Carve Flag", "Branch"
    };

    public static final String[] ACCOUNT_TABLE_HEADERS = {
            "Date", "Description", "Type", "Amount (LKR)", "Balance (LKR)"
    };

    public static final String[] SUPPLEMENTARY_CARD_TABLE_HEADERS = {
            "Card Number", "Card Holder Name"
    };

    public static final String[] DEPOSITS_TABLE_HEADERS = {
            "Account Number", "Deposit Amount", "Maturity Amount", "Maturity Date", "Account Type", "Nickname", "Action"
    };

    public static final String[] DEPOSITS_RENEWAL_HISTORY_TABLE_HEADERS = {
            "Payment Type", "Due Date", "Amount ( LKR )", "Account Balance ( LKR )", "Paid Date"
    };

    public static final String[] LOANS_TABLE_HEADERS = {
            "Account Number", "Currency", "Loan Amount", "Outstanding Amount", "Rate", "Loan Period [Months]"
    };

    public static final String[] LOANS_PAID_TABLE_HEADERS = {
            "Due Date", "Paid Date", "Capital Demand", "Interest Demand", "Collection Amount"
    };

    public static final String[] LOANS_PARTIAL_TABLE_HEADERS = {
            "Paid Amount", "Transaction Date"
    };

    public static final String[] PAWNING_LIST_TABLE_HEADERS = {
            "Pawning Number", "Advanced Amount", "Pawned Date", "Outstanding Amount", "Status"
    };

    public static final String[] T_BILL_TABLE_HEADERS = {
            "Deal Ref Number :", "Investment Amount :", "Investment Date :", "Maturity Amount :",
            "Maturity Date :", "Yield :", "Face Value :", "Number of Days :"
    };

    public static final String[] REPO_TABLE_HEADERS = {
            "Repo Number :", "Deposited Value :", "Deposited Date :", "Maturity Value :", "Maturity Date :", "Interest Rate :"
    };

    public static final String[] TRANSACTION_TABLE_HEADERS = {
            "Date", "Description", "Type", "Amount (LKR)", "Balance (LKR)"
    };

    public static final String[] CHEQUEBOOK_LEAFS = {
            "Select Count", "20"
    };
    public static final String[] CHEQUEBOOK_COUNT = {
            "Select Count", "1"
    };

    public static final String[] CHEQUE_TABLE_HEADERS = {
            "Cheque No", "Status", "Action"
    };


    //-------------------- Labels / Messages --------------------
    public static final String NO_DATA_FOUND = "No Data Found";
    public static final String CURRENT_OUTSTANDING = "Current Outstanding";
    public static final String VIEW_LIST = "View List";
    public static final String PARTIAL_INSTALLMENTS = "Partial Installments";
    public static final String PAID_INSTALLMENTS = "Paid Installments";
    public static final String ACCOUNT_HISTORY = "Account History";
    public static final String FIXED_DEPOSIT_SUMMARY = "Fixed Deposit Summary";
    public static final String RENEWAL_HISTORY = "Renewal History";
    public static final String ENABLE_EDITING = "Enable Editing";
    public static final String SAVE_CHANGES = "Save Changes";
    public static final String NICK_NAME_UPDATED = "Nickname Updated Successfully";
    public static final String NEXT = "Next";
    public static final String PDF_DOWNLOADED_SUCCESSFULLY = "PDF downloaded successfully!";
    public static final String CUSTOMER_ACCOUNT_NUMBER = "Customer Account Number";
    public static final String EXPIRY_DATE = "Expiry Date";
    public static final String CARD_STATUS = "Card Status";
    public static final String CARD_TYPE = "Card Type";
    public static final String AVAILABLE_BALANCE = "Available Balance";
    public static final String CARD_NUMBER = "Card Number";
    public static final String AVAILABLE = "Available";

    public static final String CAN = "CAN";
    public static final String SEND_REQUEST = "Send Request";
    public static final String INACTIVE = "Inactive";
    public static final String MASTER_INACTIVE = "Master Regular";
    public static final String VSDC_GOLD = "VSDC Gold";
    public static final String ACCOUNTS_CREDIT_CARDS = "Accounts / Credit Cards";
    public static final String CREDIT_CARDS = "Credit Cards";
    public static final String CONTACT_NUMBER = "Contact Number";
    public static final String SUPPLEMENTARY_CARD = "Supplementary Card";
    public static final String CARD_HOLDER_NAME = "Card Holder Name";
    public static final String NO_DATA_FOUND_LC = "No data found";
    public static final String COLLECTING_BRANCH = "Collecting Branch";
    public static final String NUMBERS_OF_CHEQUE_BOOKS = "Numbers Of Cheque Books";
    public static final String NUMBER_OF_LEAVES_PER_BOOK = "Number of leaves per book";
    public static final String ACCOUNT_STATEMENT_TITLE = "Sampath Vishwa Internet Banking - Account Statement";
    public static final String TRAN_DATE = "Tran Date";
    public static final String PARTICULARS = "Particulars";
    public static final String DR_CR = "DR/CR";
    public static final String AMOUNT = "Amount";
    public static final String BALANCE = "Balance";

    public static final String[] ACCOUNT_STATEMENT_VALIDATION_FIELDS = {

            MyAccountsConstants.ACCOUNT_STATEMENT_TITLE,
            MyAccountsConstants.TRAN_DATE,
            MyAccountsConstants.PARTICULARS,
            MyAccountsConstants.DR_CR,
            MyAccountsConstants.AMOUNT,
            MyAccountsConstants.BALANCE
    };

    //-------------------- Filters / Form Fields --------------------
    public static final String INVESTMENT_DATE = "Investment Date";
    public static final String NUMBER_OF_DAYS = "Number of Days";
    public static final String MATURITY_DATE = "Maturity Date";
    public static final String MATURITY_VALUE = "Maturity Value";
    public static final String YIELD = "Yield";
    public static final String FACE_VALUE = "Face Value";
    public static final String INTEREST_RATE = "Interest Rate";

    public static final String PERMANENET_OD_LIMIT = "Permanent OD Limit";
    public static final String TEMPORARY_OD_LIMIT = "Temporary OD Limit";
    public static final String OVERDUE_LIABILITY = "Overdue Liability";
    public static final String SYSTEM_RESERVED_AMOUNT = "System Reserved Amount";
    public static final String SYSTEM_RESERVED = "System Reserved";
    public static final String ACCOUNT_TYPE = "Account Type";
    public static final String ACCOUNT_OPENED_ON = "Account Opened On";
    public static final String ACCOUNT_OPENED_ON_LC = "Account opened on";
    public static final String ACCOUNT_BALANCE = "Account Balance";
    public static final String ACCOUNT_CURRENCY = "Currency of the account";
    public static final String CURRENT_ACCOUNT = "Current Account";
    public static final String ACCOUNT_HOLDER = "Account Holder";
    public static final String LIEN_AMOUNT = "Lien Amount";
    public static final String FLOAT_BALANCE = "Float Balance";
    public static final String CHEQUE_BOOK_REQUEST = "Cheque Book Request";
    public static final String CHEQUE_BOOK_NO = "Cheque Book No";
    public static final String STOP_CHEQUES = "Stop Cheques";
    public static final String INWARD_CHEQUES = "Inward Cheques";
    public static final String TRANSFER_ZONE_CHEQUES = "Transfer Zone Cheques";
    public static final String ISSUED_CHEQUES = "Issued Cheques";
    public static final String TRANSACTION_DATE = "Transaction Date";
    public static final String FILTER_AMOUNT_FROM = "Amount From";
    public static final String FILTER_AMOUNT_TO = "Amount To";
    public static final String FILTER_TRANSACTION_DATE = "Transaction Date";
    public static final String CREDIT = "Credit";
    public static final String DEBIT = "Debit";
    public static final String TRANSFER_TYPE = "Transfer Type";
    public static final String TRANSACTION_TYPE = "Transaction Type";

    //-------------------- Buttons --------------------
    public static final String BUTTON_ADVANCE_SEARCH = "Advanced Search";
    public static final String BUTTON_TEXT_BACK = "Back";
    public static final String BUTTON_APPLY_FILTERS = "Apply Filters";
    public static final String BUTTON_TEXT_CLEAR = "Clear";
    public static final String BUTTON_TEXT_CANCEL = "Cancel";
    public static final String BUTTON_APPLY = "Apply";
    public static final String BUTTON_STOP = "Stop";
    public static final String BUTTON_Revoke = "Revoke";

    //-------------------- Currency --------------------
    public static final String AMOUNT_USD = "Amount (USD)";
    public static final String BALANCE_USD = "Balance (USD)";
    public static final String ACCOUNT_BALANCE_USD = "Account Balance (USD)";
    public static final String[] CURRENCY_VALUES = {"LKR", "USD", "INR"};

    //-------------------- Widget / Status / Misc --------------------
    public static final String STATUS_PRIMARY = "Primary";

    //-------------------- Keywords --------------------
    public static final String DOWNLOAD = "Download";

    public static final Map<String, String> TEST_NICKNAME_MAP = new LinkedHashMap<>();

    static {
        TEST_NICKNAME_MAP.put("../<>123", "Nickname can only contain letters and numbers.");
        TEST_NICKNAME_MAP.put("ABCDEFGHIJKLMNOP", "''");
    }

    public static final String PROCEEDING_THIS_STEP = "Proceeding this step, you can stop the cheque number ";
    public static final String INVALID_NICKNAME_MSG = "Nickname can only contain letters and numbers.";
    public static final String NICKNAME_TOO_LONG_MSG = "Nickname must not exceed 15 characters";
    public static final String STATEMENT_PERIOD = "Statement Period: ";
    public static final int MAX_NICKNAME_LENGTH = 10;
    //-----------------One time data ----------------------
    public static final String OTP = "111111";

    //-----------------regex data ----------------------
    public static final String REGEX_TRANSACTION_ID = "^\\d+$";  // Only digits
    public static final String REGEX_ACCOUNT_NUMBER = "^\\d{12}$"; // 12 digits
    public static final String REGEX_BANK_NAME = "^[A-Za-z ]+$"; // Alphabets and spaces
    public static final String REGEX_CURRENCY = "^[A-Z]{3}$"; // 3 uppercase letters
    public static final String REGEX_AMOUNT = "^[A-Z]{3}\\s\\d{1,3}(,\\d{3})*(\\.\\d{2})?$";
    public static final String REGEX_DATE = "^\\d{2}-\\d{2}-\\d{4}$"; // DD-MM-YYYY
    public static final String REGEX_STATUS = "^[A-Za-z ]+$"; // Status text
    public static final String REGEX_BRANCH = "^[A-Za-z ]+$"; // Branch name

    public static final String[] INWARD_CHEQUE_COLUMN_PATTERNS = {
            REGEX_TRANSACTION_ID,
            REGEX_ACCOUNT_NUMBER,
            REGEX_BANK_NAME,
            REGEX_CURRENCY,
            REGEX_AMOUNT,
            REGEX_DATE,
            REGEX_STATUS,
            REGEX_BRANCH
    };

}
