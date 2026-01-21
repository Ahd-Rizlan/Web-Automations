package utils.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * This Class is used to store all the test constant variables.
 */
public class BillerConstants {

    //--------------Widget content-------------------
    public static final String STATUS_PRIMARY = "Primary";
    public static final String STATUS_ACTIVE = "Active";

    //---------------Menu Options-------------------
    public static final String OPTION_MANAGE_SCHEDULES = "Manage Schedules";
    public static final String OPTION_MY_ACCOUNTS = "My Accounts";

    //---------------OPTIONS-------------------
    public static final String OPTION_SETTINGS = "Settings";

    //---------------Account Types-------------------
    public static final String OWN_ACCOUNT = "Own Account";

    //---------------BUTTONS-------------------
    public static final String BUTTON_TEXT_SAVED_BILLERS = "Saved Billers";
    public static final String BUTTON_TEXT_BILL_PAYMENT_HISTORY = "Bill Payment History";
    public static final String BUTTON_MY_ACCOUNTS = "My Accounts";
    public static final String BUTTON_ACCOUNTS = "Accounts";
    public static final String BUTTON_BACK = "Back";
    public static final String BUTTON_BILL_PAYMENT = "Bill Payment";
    public static final String BUTTON_NEW_PAYMENT = "New Payment";
    public static final String BUTTON_SEND_MONEY = "Send Money";
    public static final String PROCEED_TO_PAY = "Proceed to Pay";
    public static final String PROCEED_TO_PAY_TWO = "Proceed To Pay";
    public static final String BUTTON_FETCH = "Fetch";
    public static final String BUTTON_LOGISTICS = "Logistics";

    //-----------------One time data ----------------------
    public static final String OTP = "111111";

    //---------------CURRENCY_ABBREVIATIONS_VALUES-------------------
    public static final String[] CURRENCY_VALUES = {"LKR", "USD", "INR"};
    public static final String[] STATUS_VALUES = {"Active", "Dormant"};

    //--------------STATUS------------------
    public static final String STATUS_PAYMENT_SUCCESS = "Payment Success";

    //--------------KEY WORDS------------------
    public static final String KW_DIALOG_MOBILE = "Dialog Mobile";
    public static final String MY_ACCOUNT = "myaccount";
    public static final String MANAGE_SCHEDULE = "manage-schedule";
    public static final String SCHEDULE_MANAGEMENT = "Schedule Management";
    public static final String DOWNLOADS = "Downloads";
    public static final String DATE = "Date";
    public static final String STUDENT_NO = "Student No.";
    public static final String SELECTED_INVOICE_NO = "Selected Invoice No.";
    public static final String NUMBER_TWENTY_TWENTY_FOUR = "2024";
    public static final String JULY = "July";
    public static final String APRIL = "April";
    public static final String NUMBER_TWENTY_ONE = "21";
    public static final String NUMBER_SEVEN = "07";
    public static final String APPROX_LKR_LABEL_PREFIX = "Approximate Amount in LKR as of";
    public static final String TIME_PERIOD_REGEX = "(am|pm)";
    public static final String LKR_TEXT = "LKR";
    public static final String USD_EXCHANGE_PREFIX = "USD 1 x";

    public static final Map<String, String> CONSTANTS_MAP = new HashMap<>();

    static {
        CONSTANTS_MAP.put("KW_CEB", "CEB");
        CONSTANTS_MAP.put("KW_CEYILON_ELECTRICITY_BOARD", "Ceylon Electricity Board");
        CONSTANTS_MAP.put("KW_LECO", "LECO");
        CONSTANTS_MAP.put("KW_LANKA_ELECTRICITY_COMPANY", "Lanka Electricity Company (Pvt) Ltd");
        CONSTANTS_MAP.put("KW_NWSDB", "NWSDB");
        CONSTANTS_MAP.put("KW_NATIONAL_WATER_SUPPLY_AND_DRAINAGE_BOARD", "National Water Supply And Drainage Board");
        CONSTANTS_MAP.put("KW_PAY_FROM", "Pay From");
        CONSTANTS_MAP.put("KW_AMOUNT", "Amount");
        CONSTANTS_MAP.put("KW_PAYMENT_MODE", "Payment Mode");
        CONSTANTS_MAP.put("KW_TELEPHONE", "Telephone");
        CONSTANTS_MAP.put("KW_PHONE_NUMBER", "Phone Number");
        CONSTANTS_MAP.put("KW_TELEPHONE_NUMBER_WITH_AREA_CODE", "Telephone Number with Area Code");
        CONSTANTS_MAP.put("KW_MOBILE_NUMBER", "Mobile Number");
        CONSTANTS_MAP.put("KW_INVOICE_NUMBER_WITH", "Invoice Number With (-)");
        CONSTANTS_MAP.put("KW_INSURANCE", "Insurance");
        CONSTANTS_MAP.put("KW_SRI_LANKA_INSURANCE_LIFE_RENEWALS", "Sri Lanka Insurance - Life Renewals");
        CONSTANTS_MAP.put("KW_POLICY_NO", "Policy No");
        CONSTANTS_MAP.put("KW_NAME_OF_POLICY_HOLDER", "Name of Policy Holder");
        CONSTANTS_MAP.put("KW_POLICY_NUMBER", "Policy Number");
        CONSTANTS_MAP.put("KW_NIC_NUMBER", "NIC Number");
        CONSTANTS_MAP.put("KW_CABLE_TV", "Cable - TV");
        CONSTANTS_MAP.put("KW_ACCOUNT_NO", "Account No");
        CONSTANTS_MAP.put("KW_SCHOOL_FEES", "School Fees");
        CONSTANTS_MAP.put("KW_STUDENT_ADMISSION_NUMBER", "Student Admission Number");
        CONSTANTS_MAP.put("KW_STUDENT_NAME", "Student Name");
        CONSTANTS_MAP.put("KW_CLASS", "Class");
        CONSTANTS_MAP.put("KW_PURPOSE", "Purpose");
        CONSTANTS_MAP.put("KW_INTERNET_SERVICE_PROVIDERS", "Internet Service Providers");
        CONSTANTS_MAP.put("KW_SRI_LANKA_TELECOM_4G", "Sri Lanka Telecom - 4G");
        CONSTANTS_MAP.put("KW_4G_TELEPHONE_NUMBER_WITH_AREA_CODE", "4G Telephone Number with Area Code");
        CONSTANTS_MAP.put("KW_HOSPITALS", "Hospitals");
        CONSTANTS_MAP.put("KW_PATIENTS_REFERENCE_NUMBER_BHT", "Patient's Reference Number BHT");
        CONSTANTS_MAP.put("KW_NAME_OF_THE_PATIENT", "Name Of The Patient");
        CONSTANTS_MAP.put("KW_ELECTRICITY_BILL_ACCOUNT_NO", "Electricity Bill Account No");
        CONSTANTS_MAP.put("KW_BILLING_MONTH", "Billing Month (YYYYMM)");
        CONSTANTS_MAP.put("KW_ONLY_THE_FIRST_12_NUMBERS_OF_WATER_BILL_ACCOUNT", "Only the first 12 numbers of Water Bill Account Nu");
        CONSTANTS_MAP.put("KW_YEAR_MONTH_OF_THE_BILL", "Year/Month of the Bill (YYYYMM)");
        CONSTANTS_MAP.put("KW_PRIME_LANDS_PVT_LTD", "Prime Lands (Pvt) Ltd");
        CONSTANTS_MAP.put("KW_CUSTOMER_CODE", "Customer Code");
        CONSTANTS_MAP.put("KW_RESERVATION_NUMBER", "Reservation Number");
        CONSTANTS_MAP.put("KW_FINANCIAL_INSTITUTIONS", "Financial Institutions");
        CONSTANTS_MAP.put("KW_SAVINGS_ACCOUNT_NO", "Savings Account No.");
        CONSTANTS_MAP.put("KW_KAACHA_PHOTOGRAPHY_CLUB_OF_SAMPATH_BANK", "KAACHA - Photography Club Of Sampath Bank");
        CONSTANTS_MAP.put("KW_CALLING_NAME", "Calling Name");
        CONSTANTS_MAP.put("KW_EMPLOYEE_ID", "Employee ID");
        CONSTANTS_MAP.put("KW_BRANCH", "Branch");
        CONSTANTS_MAP.put("KW_EMAIL_ADDRESS", "Email address");
        CONSTANTS_MAP.put("KW_CONTACT_NO", "Contact No");
        CONSTANTS_MAP.put("KW_LEASING", "Leasing");
        CONSTANTS_MAP.put("KW_CUSTOMER_BANK_REFERENCE_NO", "Customer Bank Reference No");
        CONSTANTS_MAP.put("KW_TELEPHONE_NO_10_DIGITS", "Telephone No (10 digits)");
        CONSTANTS_MAP.put("KW_VEHICLE_NO_NIC_NO", "Vehicle No / NIC No");
        CONSTANTS_MAP.put("KW_EDUCATION", "Education");
        CONSTANTS_MAP.put("KW_NATIONAL_SCHOOL_OF_BUSINESS_MANAGEMENT", "National School Of Business Management Ltd");
        CONSTANTS_MAP.put("KW_NIC", "NIC");
        CONSTANTS_MAP.put("KW_STUDENT_REGISTRATION_NUMBER", "Student Registration Number");
        CONSTANTS_MAP.put("KW_NAME", "Name");
        CONSTANTS_MAP.put("KW_STUDY_YEAR", "Study Year");
        CONSTANTS_MAP.put("KW_STUDENT_REGISTRATION_NUMBER_STUDENT_NIC_NUMBER", "Student Registration Number / Student NIC Number");
        CONSTANTS_MAP.put("KW_REASON_FOR_THE_PAYMENT", "Reason For The Payment (Enter only 3 letter code)");
        CONSTANTS_MAP.put("KW_REASON_FOR_THE_PAYMENT_OTHER", "Reason For The Payment - (other than above categor");
        CONSTANTS_MAP.put("KW_MOBILE_MONEY", "Mobile Money");
        CONSTANTS_MAP.put("KW_MOBITEL_MCASH", "Mobitel mCash");
        CONSTANTS_MAP.put("KW_MOBILE_WALLET_NUMBER", "Mobile Wallet Number");
        CONSTANTS_MAP.put("KW_MUSAEUS_COLLEGE", "Musaeus College");
        CONSTANTS_MAP.put("KW_DIALOG_BROADBAND", "Dialog Broadband");
        CONSTANTS_MAP.put("KW_REAL_ESTATE", "Real Estate");
        CONSTANTS_MAP.put("KW_CLUBS_AND_SOCIETIES", "Clubs & Societies");
        CONSTANTS_MAP.put("KW_SRI_LANKA_TELECOM_NEW_INVOICE_NUMBERS_14_DIGITS", "Sri Lanka Telecom - New Invoice Numbers(14 Digits)");
        CONSTANTS_MAP.put("KW_DIALOG_TV", "Dialog TV");
        CONSTANTS_MAP.put("KW_JANASHAKTHI_INSURANCE_CO_LTD_LIFE", "Janashakthi Insurance Co. Ltd - Life");
        CONSTANTS_MAP.put("KW_ONE_TIME_TRANSACTION", "One-time Transaction");
        CONSTANTS_MAP.put("KW_SETUP_STANDING_ORDER_SCHEDULE", "Setup Standing Order/Schedule");
        CONSTANTS_MAP.put("KW_MOBILE_NUMBER_WITHOUT", "Mobile Number (Without");
        CONSTANTS_MAP.put("KW_SAVED_BILLERS", "Saved Billers");
        CONSTANTS_MAP.put("KW_GOVERNMENT_PAYMENTS", "Government Payments");
        CONSTANTS_MAP.put("KW_YOUR_GSM_PHONE_NUMBER", "Your GSM Phone Number");
    }



}
