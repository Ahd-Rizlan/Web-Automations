package utils.constants;

import java.util.List;
import java.util.Map;

public class MultipleBillersConstants {

    public static final int MAX_SELECTABLE_SAVED_BLLERS = 5;
    public static final int PAGINATION_LIMIT = 10;
    public static final String OTP = "111111";

    // -------------- KEY WORDS FOR HEADERS ------------------
    public static final String MAINHEADER = "Saved Billers";
    public static final String MAINHEADER_SIBLING = "Use wide range of biller network to make your payments with ease.";
    public static final String SUBHEADER = "Saved Billers";
    public static final String SUBHEADER_SIBLING = "Use filters to fetch different account types.";
    public static final String TEMPLATE_NAME ="Template Name";
    public static final String BILLER_NAME ="Biller Name";
    public static final String Amount ="Amount";
    public static final String Total_Amount ="Total Amount";
    public static final String lbl_SAVING_ACCOUNT = "Savings Account";
    public static final String lbl_FC_SAVING_ACCOUNT = "Savings Foreign Account";
    public static final String OTP_CONFIRMATION_PAGE_MULTIPLE_BILLER_HEADER_TEXT = "Payment Confirmation";



    public static final String PAYFROM = "Pay From";
    public static final String MAX_BILLER_ERROR = "Limit reached: You can select up to 5 billers.";
    public static final String QUICK_BILL_PAYMENTS = "Quick Bill Payments";
    public static final String MULTIPLE_BILL_PAYMENTS = "Multiple Bill Payments";




    public static final String GSM_PHONE ="Your GSM Phone Number";
    public static final String REENTER_GSM_PHONE ="Re-enter Your GSM Phone Number";
    public static final String MOBITEL_PHONE ="Mobitel Phone Number";
    public static final String REENTER_MOBITEL_PHONE ="Re-enter Mobitel Phone Number";




    // -------------- KEY WORDS BUTTON ------------------
    public static final String OLD_VISHWA = "Old Vishwa Saved Billers";
    public static final String NEW_VISHWA = "New Vishwa Saved Billers";
    public static final String Add_NEW_BILLER = "Add New Biller";
    public static final String RDO_ACCOUNT = "Account";
    public static final String RDO_CREDIT_CARD = "Credit Card";

//-----------------Error Message ---------------------------
    public static final String ERROR_MSG_AMOUNT = "Amount is required";
    public static final String ERROR_TST_INSUF_BALANCE = "Insufficient funds";
    public static final String SUCCESS_OTP_SENT = "sent successfully";

    // -------------- SUCCESS PAGE CONSTANTS ------------------
    public static final String PAYMENT_STATUS_LABEL = "Payment Status";
    public static final String PAYMENT_STATUS_SUCCESS_TEXT = "Bill payment successful";
    public static final String SUCCESS_MESSAGE_HEADER = "Success";
    public static final String SUCCESS_MESSAGE_SUBTEXT = "Your Payment Has Been Processed Successfully.";


    public static final String ERROR_MSG_GSM_MISMATCH = "Your GSM Phone Number values do not match";
    public static final String ERROR_MSG_AMOUNT_LIMIT = "Amount exceeds the limit.";
    public static final String ERROR_MSG_AMOUNT_ZERO = "Amount should be greater than zero.";
    public static final String ERROR_MSG_AMOUNT_MAX_LIMIT = "Amount exceeds the maximum limit of LKR 1,000,000.00";
    public static final String ERROR_MSG_NO_ACCOUNT = "No accounts available to select.";
    public static final String ERROR_MSG_NO_CREDIT_CARD = "No credit cards available to select.";
    public static final String ERROR_MSG_NO_BILLER = "No billers available to select.";
    public static final String ERROR_MSG_FETCH = "No records found for the search criteria.";





    public static final Map<String, List<String>> BILLER_DATA = Map.of(
            "Dialog Mobile", List.of(
                    "Your GSM Phone Number",          // 0 → Placeholder input
                    "Re-enter", // 1 → Placeholder reenter input
                    "Your GSM Phone Number is required", // 2 → Error required
                    "Your GSM Phone Number does not match" // 3 → Error mismatch
            ),
            "Mobitel Pvt Ltd", List.of(
                    "Mobitel Phone Number",
                    "Re-enter",
                    "Mobitel Phone Number is required",
                    "Mobitel Phone Number values do not match"
            ),
            "HUTCH", List.of(
                    "Mobile Number",
                    "Re-enter ",
                    "Mobitel Phone Number is required",
                    "Mobile Number values do not match"
            ),
            "Toyota Lanka (Pvt) Ltd", List.of(
                    "Invoice No",
                    "Re-enter",
                    "Invoice No is required",
                    "Invoice No values do not match"
            )
            // ➝ Add more billers here
    );

}

