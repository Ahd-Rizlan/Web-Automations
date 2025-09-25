package utils.constants;

import java.util.HashMap;
import java.util.Map;
public class CreditCardConstants {

    public static final String CARD_NUMBER_LABEL = "Card Number";
    public static final String CUSTOMER_NUMBER_LABEL = "Customer Account Number";
    public static final String EXPIRY_DATE_LABEL = "Expiry Date";
    public static final String CARD_STATUS_LABEL = "Card Status";
    public static final String CARD_TYPE_LABEL = "Card Type";
    public static final String AVAILABLE_BALANCE_LABEL = "Available Balance";
    public static final String CAN_LABEL = "CAN";

    // Transaction statuses
    public static final String STATUS_APPROVED = "Approved";
    public static final String STATUS_PENDING = "Pending";
    public static final String STATUS_DECLINED = "Declined";
    public static final String BLOCK = "BLOCK CARD";
    public static final String UNBLOCK = "UNBLOCK CARD";
    public static final String STATUS_ACTIVE = "Active";
    public static final String VISA_LOGO = "VisaLogo";
    public static final Map<String, String> MONTH_MAP = new HashMap<>();
    static {
        MONTH_MAP.put("Jan", "01");
        MONTH_MAP.put("Feb", "02");
        MONTH_MAP.put("Mar", "03");
        MONTH_MAP.put("Apr", "04");
        MONTH_MAP.put("May", "05");
        MONTH_MAP.put("Jun", "06");
        MONTH_MAP.put("Jul", "07");
        MONTH_MAP.put("Aug", "08");
        MONTH_MAP.put("Sep", "09");
        MONTH_MAP.put("Oct", "10");
        MONTH_MAP.put("Nov", "11");
        MONTH_MAP.put("Dec", "12");
    }
    public static final String REDEEM = "Redeem";
    public static final String SETTLE = "Settle";
    public static final String CLOSE = "Close";
    public static final String BACK = "Back";
    public static final String UNBILLED = "Unbilled";
    public static final String PENDING = "Pending";
    public static final String INSTALLMENT = "Installments";
    public static final String STATEMENT = "Statement";
    public static final String NEXT = "Next";
    public static final String CONFIRM = "Confirm";
    public static final String SET_AS_PRIMARY_ACCOUNT = "Set as Primary Account";
    public static final String CLOSE_CARD_VIEW = "Close Card View";
    public static final String EXPECTED_PART = "loyalty.sampathbank.lk";
    public static final String BILLING_DATE_LABEL = "Billing Date";
    public static final String MINIMUM_DUE_LABEL = "Minimum Due";
    public static final String DUE_DATE_LABEL = "Due Date";
    public static final String OPENING_BALANCE_LABEL = "Opening Balance";
    public static final String DEBITS_LABEL = "Debits";
    public static final String CREDITS_LABEL = "Credits";
    public static final String CLOSING_BALANCE_LABEL = "Closing Balance";
    public static final String DOWNLOAD_STATEMENT = "Download Statement";
    public static final String CREDIT_LIMIT = "Credit limit";
    public static final String AGREEMENT = "View / Download Agreement";

}
