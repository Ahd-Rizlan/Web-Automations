package utils.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * This Class is used to store all the test constant variables.
 */
public class TransactionConstants {


    //-----------------TAB NAMES ----------------------
    public static final String TAB_NAME_SEND_MONEY = "Send Money";
    public static final String TAB_NAME_SAVED_PAYEE = "Saved Payees";
    public static final String TAB_NAME_OWN_ACCOUNT = "Own Account";
    public static final String TAB_NAME_OTHER_ACCOUNT = "Other Accounts";
    public static final String TAB_NAME_OTHER_CREDIT_CARDS = "Other Credit Cards";
    public static final String TAB_NAME_MOBILE_CASH = "Mobile Cash";

    //---------------CURRENCY_ABBREVIATIONS_VALUES-------------------
    public static final String[] CURRENCY_VALUES = {"LKR", "USD", "INR"};

    //---------------NUMERIC_VALUES-------------------
    public static final String NUMERICAL_ONE = "1";

    //--------------KEY WORDS------------------
    public static final String AUTO = "Auto_";
    public static final String MOBILE_NO = "mobileNo";
    public static final String REMOBILE_NO = "reMobileNo";
    public static final String NAME = "name";
    public static final String AMOUNT = "amount";
    public static final String REMARK = "remark";
    public static final String MOBILE_CASH = "MOBILE CASH";
    public static final String CARD_NAME = "Card Name";
    public static final String PAY_NOW = "Pay Now";
    public static final String BACK = "Back";
    public static final String SENT = "Sent";
    public static final String BENEFICIARY_DELETED = "Beneficiary Deleted Successfully!";

    public static final Map<String, String> CONSTANTS_MAP = new HashMap<>();

    static {
        CONSTANTS_MAP.put("KW_CEB", "CEB");
        CONSTANTS_MAP.put("KW_CEYILON_ELECTRICITY_BOARD", "Ceylon Electricity Board");
        CONSTANTS_MAP.put("KW_LECO", "LECO");
        CONSTANTS_MAP.put("KW_LANKA_ELECTRICITY_COMPANY", "Lanka Electricity Company (Pvt) Ltd");
        CONSTANTS_MAP.put("KW_NWSDB", "NWSDB");
        CONSTANTS_MAP.put("KW_NATIONAL_WATER_SUPPLY_AND_DRAINAGE_BOARD", "National Water Supply And Drainage Board");
        CONSTANTS_MAP.put("KW_PAY_FROM", "Pay From");
        CONSTANTS_MAP.put("KW_Transfer_FROM", "Transfer From");
        CONSTANTS_MAP.put("KW_AMOUNT", "Amount");
        CONSTANTS_MAP.put("KW_PAYMENT_MODE", "Payment Mode");
        CONSTANTS_MAP.put("KW_TELEPHONE", "Telephone");
        CONSTANTS_MAP.put("KW_PHONE_NUMBER", "Phone Number");
        CONSTANTS_MAP.put("KW_TELEPHONE_NUMBER_WITH_AREA_CODE", "Telephone Number with Area Code");
        CONSTANTS_MAP.put("KW_MOBILE_NUMBER", "Mobile Number");
        CONSTANTS_MAP.put("KW_RE_ENTER_MOBILE_NUMBER", "Re Enter Mobile Number");
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
        CONSTANTS_MAP.put("KW_SENDER", "Sender");
        CONSTANTS_MAP.put("KW_BENEFICIARY_REMARKS", "Beneficiary Remarks");
        CONSTANTS_MAP.put("KW_BENEFICIARY_ACCOUNT_NUMBER", "Beneficiary Account Number");
        CONSTANTS_MAP.put("KW_TRANSFER_MODE", "Transfer Mode");
        CONSTANTS_MAP.put("KW_BANK", "Bank");
        CONSTANTS_MAP.put("KW_TRANSFER_DATE", "Transfer Date");
        CONSTANTS_MAP.put("KW_BENEFICIARY_CARD_NUMBER", "Beneficiary Card Number");
        CONSTANTS_MAP.put("KW_RECEIVERS_MOBILE_NUMBER", "Receiver’s Mobile number");
        CONSTANTS_MAP.put("KW_RECEIVERS_NIC", "Receiver’s NIC");
        CONSTANTS_MAP.put("KW_REMARKS", "Remarks");
//        CONSTANTS_MAP.put("KW_MOBILE_CASH_AMOUNT", "Mobile Cash Amount");

    }

}
