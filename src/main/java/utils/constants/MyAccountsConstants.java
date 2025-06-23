package utils.constants;

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
    public static final String[] TILE_HEADER_PAWNING = {"'Accounts / Pawning'"};
    public static final String[] TILE_HEADERS = {
            "'Accounts / Operative'", "'My Accounts / FD'", "'My Accounts / Loans'",
            "'Treasury Bills'", "'Repository Account Investments'"
    };
    public static final String[] TILE_HEADERS_FOR_NICKNAME = {
            "'Accounts / Operative'", "'My Accounts / FD'"
    };

    //-------------------- Table Headers --------------------
    public static final String[] SAVINGS_ACCOUNTS_LIST_TABLE_HEADERS = {
            "Account Number", "Currency", "Available Balance", "Status of the Account", "Account Type", "Nickname"
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
            "Account Number", "Currency", "Loan Amount", "Outstanding Amount", "Rate", "Loan Period"
    };

    public static final String[] LOANS_PAID_TABLE_HEADERS = {
            "Paid Amount", "Transaction Date"
    };

    public static final String[] LOANS_PARTIAL_TABLE_HEADERS = {
            "Paid Amount", "Currency"
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

    public static final String[] CHECKBOOK_LEAFS = {
            "Select count", "20"
    };
    public static final String[] CHECKBOOK_COUNT = {
            "Select count", "1"
    };

    //-------------------- Labels / Messages --------------------
    public static final String NO_DATA_FOUND = "No Data Found";
    public static final String CURRENT_OUTSTANDING = "Current Outstanding";
    public static final String VIEW_LIST = "'View List'";
    public static final String PARTIAL_INSTALLMENTS = "'Partial Installments'";
    public static final String PAID_INSTALLMENTS = "'Paid Installments'";
    public static final String ACCOUNT_HISTORY = "Account History";
    public static final String FIXED_DEPOSIT_SUMMARY = "Fixed Deposit Summary";
    public static final String RENEWAL_HISTORY = "Renewal History";
    public static final String ENABLE_EDITING = "'Enable Editing'";
    public static final String SAVE_CHANGES = "'Save Changes'";
    public static final String NICK_NAME_UPDATED = "'Nickname Updated Successfully'";
    public static final String NEXT = "'Next'";
    public static final String PDF_DOWNLOADED_SUCCESSFULLY = "PDF downloaded successfully!";

    public static final String CUSTOMER_ACCOUNT_NUMBER = "Customer Account Number";
    public static final String EXPIRY_DATE = "Expiry Date";
    public static final String CARD_STATUS = "Card Status";
    public static final String CARD_TYPE = "Card Type";
    public static final String AVAILABLE_BALANCE = "Available Balance";
    public static final String CARD_NUMBER = "Card Number";
    public static final String AVAILABLE = "Available";

    public static final String CAN = "CAN";
    public static final String INACTIVE = "Inactive";
    public static final String MASTER_INACTIVE = "Master Regular";

    public static final String ACCOUNTS_CREDIT_CARDS = "'Accounts / Credit Cards'";
    public static final String CREDIT_CARDS = "Credit Cards";

    public static final String SUPPLEMENTARY_CARDS = "'Supplementary Cards'";
    public static final String CARD_HOLDER_NAME = "Card Holder Name";



    //-------------------- Filters / Form Fields --------------------
    public static final String INVESTMENT_DATE = "Investment Date";
    public static final String NUMBER_OF_DAYS = "Number of Days";
    public static final String MATURITY_DATE = "Maturity Date";
    public static final String MATURITY_VALUE = "Maturity Value";
    public static final String YIELD = "Yield";
    public static final String FACE_VALUE = "Face Value";
    public static final String INTEREST_RATE = "Interest Rate";

    public static final String PERMANENET_OD_LIMIT = "'Permanent OD Limit'";
    public static final String TEMPORARY_OD_LIMIT = "Temporary OD Limit";
    public static final String OVERDUE_LIABILITY = "Overdue Liability";
    public static final String SYSTEM_RESERVED_AMOUNT = "System Reserved Amount";
    public static final String SYSTEM_RESERVED = "System Reserved";
    public static final String ACCOUNT_TYPE = "Account Type";
    public static final String ACCOUNT_OPENED_ON = "Account opened on";
    public static final String ACCOUNT_BALANCE = "Account Balance";
    public static final String ACCOUNT_CURRENCY = "Currency of the account";
    public static final String CURRENT_ACCOUNT = "Current Account";
    public static final String ACCOUNT_HOLDER = "Account holder";
    public static final String LIEN_AMOUNT = "Lien Amount";
    public static final String FLOAT_BALANCE = "Float Balance";

    public static final String CHEQUE_BOOK_REQUEST = "'Cheque Book Request'";

    public static final String TRANSACTION_DATE = "Transaction date";
    public static final String FILTER_AMOUNT_FROM = "Amount From";
    public static final String FILTER_AMOUNT_TO = "Amount To";
    public static final String FILTER_TRANSACTION_DATE = "Transaction Date";
    public static final String CREDIT = "Credit";
    public static final String DEBIT = "Debit";
    public static final String TRANSFER_TYPE = "Transfer Type";

    //-------------------- Buttons --------------------
    public static final String BUTTON_ADVANCE_SEARCH = "'Advance Search'";
    public static final String BUTTON_TEXT_BACK = "'Back'";
    public static final String BUTTON_APPLY_FILTERS = "'Apply Filters'";
    public static final String BUTTON_TEXT_CLEAR = "'Clear'";
    public static final String BUTTON_APPLY = "'Apply'";

    //-------------------- Currency --------------------
    public static final String AMOUNT_USD = "Amount (USD)";
    public static final String BALANCE_USD = "Balance (USD)";
    public static final String ACCOUNT_BALANCE_USD = "Account Balance (USD)";
    public static final String[] CURRENCY_VALUES = {"LKR", "USD", "INR"};

    //-------------------- Widget / Status / Misc --------------------
    public static final String STATUS_PRIMARY = "Primary";

    //-------------------- Keywords --------------------
    public static final String DOWNLOAD = "'Download'";


}
