package utils.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * This Class is used to store all the test constant variables.
 */
public class OldVishwaConstants {

    // -------------- KEY WORDS OTHER VALUES ------------------
    public static final String OTP = "111111";
    public static final String LAST_MODIFIED_ON = "Last modified on";
    public static final String DRAFT = "Draft";
    public static final String INDEX = "index";
    public static final String NUMERICAL_THREE = "3";
    public static final String MOBILE_CASH=  "Mobile Cash";

    public static final String MESSAGES_UPLOAD = "MESSAGES_UPLOAD";
    public static final String[] CURRENCY_VALUES = {"LKR", "USD", "INR"};

    public static final Map<String, String> PAYEE_TYPE_LIST = new HashMap<>();
    static {
        PAYEE_TYPE_LIST.put("PERSONAL_PAYEES", "Personal Payees");
        PAYEE_TYPE_LIST.put("THIRD_PARTY_SAMPATH", "Third Party Sampath Transfers");
        PAYEE_TYPE_LIST.put("OWN_ACCOUNT_TRANSFERS", "Own Account Transfers");
        PAYEE_TYPE_LIST.put("MOBILE_CASH_TRANSFER", "Mobile Cash");
        PAYEE_TYPE_LIST.put("REGISTERED_CARD_TRANSFER", "Registered Card Transfer");
    }


    //--------------KEY WORDS TF ------------------
    public static final String COMPOSE_NEW_MESSAGE = "Compose New Message";
    public static final String ENTER_TO_WHOM_IT_SHOULD_BE_ADDRESSED = "Enter To whom it should be addressed";
    public static final String ENTER_MESSAGE = "Enter Message";
    public static final String ENTER_MESSAGE_SUBJECT = "Enter Message Subject";
    public static final String SELECT_ATTACHMENTS = "Select Attachments";
    public static final String ENTER_AMOUNT = "Enter Amount";
    public static final String ENTER_ACCOUNT_NUMBER = "Enter Account Number";
    public static final String ENTER_REMARKS = "Enter Remarks";
    public static final String SEARCH_MESSAGES = "Search Messages";
    public static final String OTHER_PURPOSE = "Other Purpose";
    public static final String START_TYPING = "Start typing...";

    // -------------- KEY WORDS RDO ------------------
    public static final String PERIOD = "period";
    public static final String AS_AT_DATE = "As at Date";
    public static final String AS_AT_DATE_LC = "as at date";
    public static final String SAMPATH = "Sampath";
    public static final String OTHER = "Other";

    // -------------- KEY WORDS Date ------------------
    public static final String FROM_DATE = "From Date";
    public static final String TO_DATE = "To Date";

    // -------------- KEY WORDS DD ------------------
    public static final String VISIBLE_TEXT = "visibletext";
    public static final String ALL_OPTIONS = "ALL_OPTIONS";


    // -------------- KEYWORDS - BUTTON LABELS ------------------
    public static final String KEYWORD_OLD_VISHWA = "Old Vishwa";
    public static final String KEYWORD_OLD_VISHWA_INBOX = "Old Vishwa - Inbox";
    public static final String KEYWORD_OLD_VISHWA_SENT = "Old Vishwa - Sent";
    public static final String KEYWORD_FILTER = "Filter";
    public static final String KEYWORD_LOAD_OLD_V_HISTORY = "Load Old Vishwa History";
    public static final String KEYWORD_TRANSACTION_HISTORY = "Transaction History";
    public static final String KEYWORD_BILL_PAYMENT_HISTORY = "Bill Payment History";



    // -------------- TABLE HEADERS ------------------

    public static final Map<String, String> OLD_VISHWA_INBOX = new HashMap<>();
    static {

        OLD_VISHWA_INBOX.put("TH_MESSAGE_SUBJECT", "Message Subject");
        OLD_VISHWA_INBOX.put("TH_MESSAGE_ID", "Message ID");
        OLD_VISHWA_INBOX.put("TH_DATE", "Date");
        OLD_VISHWA_INBOX.put("TH_EMAIL_FROM", "Email From");
        OLD_VISHWA_INBOX.put("TH_ACTION", "Action");
    }

    public static final Map<String, String> OLD_VISHWA_SENT = new HashMap<>();
    static {

        OLD_VISHWA_SENT.put("TH_MESSAGE_SUBJECT", "Message Subject");
        OLD_VISHWA_SENT.put("TH_MESSAGE_ID", "Message ID");
        OLD_VISHWA_SENT.put("TH_DATE", "Date");
        OLD_VISHWA_SENT.put("TH_EMAIL_FROM", "Email To");
        OLD_VISHWA_SENT.put("TH_ACTION", "Action");
    }


    public static final Map<String, String> OLD_VISHWA_BILLER_HEADER = new HashMap<>();
    static {
        OLD_VISHWA_BILLER_HEADER.put("TH_PAYMENT_ID", "Payment ID");
        OLD_VISHWA_BILLER_HEADER.put("TH_PAYMENT_DATE", "Payment Date");
        OLD_VISHWA_BILLER_HEADER.put("TH_FROM_ACCOUNT", "From account");
        OLD_VISHWA_BILLER_HEADER.put("TH_BILLER_NAME", "Biller Name");
        OLD_VISHWA_BILLER_HEADER.put("TH_CURRENCY", "Currency");
        OLD_VISHWA_BILLER_HEADER.put("TH_AMOUNT", "Amount");
        OLD_VISHWA_BILLER_HEADER.put("TH_BILLER_REF", "Biller Ref");
    }


    public static final Map<String, String> OLD_VISHWA_TRANSACTION_HISTORY = new HashMap<>();
    static {
        OLD_VISHWA_TRANSACTION_HISTORY.put("TH_TRANSACTION_ID", "Transaction ID");
        OLD_VISHWA_TRANSACTION_HISTORY.put("TH_TRANSACTION_DATE", "Transaction Date");
        OLD_VISHWA_TRANSACTION_HISTORY.put("TH_ACCOUNT_FROM", "Account From");
        OLD_VISHWA_TRANSACTION_HISTORY.put("TH_ACCOUNT_TO", "Account To");
        OLD_VISHWA_TRANSACTION_HISTORY.put("TH_CURRENCY", "Currency");
        OLD_VISHWA_TRANSACTION_HISTORY.put("TH_AMOUNT", "Amount");
        OLD_VISHWA_TRANSACTION_HISTORY.put("TH_REMARKS", "Remarks");
        OLD_VISHWA_TRANSACTION_HISTORY.put("TH_TRANSFER_TYPE", "Transfer Type");
        OLD_VISHWA_TRANSACTION_HISTORY.put("TH_BANK_NAME", "Bank Name");
    }





    // -------------- PURPOSE DROPDOWN LIST ------------------
    public static final Map<String, String> PURPOSE_DROPDOWN = new HashMap<>();
    static {
        PURPOSE_DROPDOWN.put("KW_VISA", "Visa");
        PURPOSE_DROPDOWN.put("KW_TAX", "Tax");
        PURPOSE_DROPDOWN.put("KW_AUDIT", "Audit");
        PURPOSE_DROPDOWN.put("KW_OTHER", "Other");
    }
    public static final String[] PURPOSE_VALUES = {"Active", "Dormant"};

// -------------- KEY WORDS DATE PICKER ------------------

    public static final String TRANSACTION_DATE = "Transaction date";

}
