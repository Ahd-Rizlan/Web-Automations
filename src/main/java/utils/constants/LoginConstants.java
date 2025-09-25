package utils.constants;

/**
 * This Class is used to store all the test constant variables.
 */
public class LoginConstants {

    //--------------Title content-------------------
    public static final String LOGIN_TILE_NAME = "Sampath Vishwa";
    public static final String EXPECTED_TITLE = "Sampath Vishwa | Login";
    public static final String OTP_PAGE_HEADER = "Enter OTP to Verify";
    public static final String[] LINK_TITLES = {
            "Important Notice", "Interest Rates", "Exchange Rates", "Promotions", "Service Charges"
    };
    public static final String[] URLs = {
            "https://www.sampath.lk/api/uploads/English_a8049a67e5_44ce79e38c_7f0e02ac75.jpg",
            "https://www.sampath.lk/rates-and-charges?activeTab=interest-rates-local",
            "https://www.sampath.lk/rates-and-charges?activeTab=exchange-rates",
            "https://www.sampath.lk/sampath-cards/credit-card-offer?firstTab=hotels",
            "https://www.sampathvishwa.com/downloads/svr_service_charges.pdf"
    };

    //-------------- messages------------------
    public static final String BOTH_EMAIL_AND_SMS_SENT_SUCCESSFULLY_MSG = "Both sms and email sending are success";
    public static final String EMAIL_SENT_SUCCESSFULLY_MSG = "Email sent successfully to the registered email address";
    public static final String LOGOUT_CONFIRMATION_MSG = "Are you sure you want to logout?";
    public static final String USING_SECURITY_QUESTIONS = "Using Security Questions";


    //--------------Button content-------------------
    public static final String LOGOUT_BUTTON_TEXT = "Logout";
    public static final String RESET_BUTTON_TEXT = "Reset";
    public static final String CONFIRM_AND_LOGOUT_BUTTON_TEXT = "Confirm & Logout";
    public static final String LOGIN_BUTTON_TEXT = "Login";

    //-----------------One time data ----------------------
    public static final String OTP = "111111";

    //-----------------One time data ----------------------
    public static final boolean TRUE = true;
    public static final boolean FALASE = false;

    //-----------------Security question Answer -------------
    public static final String QUESTION_ANSWER = "test";
    public static final String SECURITY_QUESTION = "Your Mother's Maiden name?";
    public static final String PASSWORD_POLICY_HEADING = "Instructions for the password";
    public static final String POLICY_ERROR_MSG_01 = "Password is required";
    public static final String POLICY_ERROR_MSG_02 = "Password must be between 8 and 15 characters";
    public static final String POLICY_ERROR_MSG_03 = "Password must not contain repeated consecutive characters";
    public static final String POLICY_ERROR_MSG_04 = "Password must contain at least one uppercase letter"; //abcabcabc
    public static final String POLICY_ERROR_MSG_05 = "Password must contain at least one lowercase letter"; //ABCABCABC
    public static final String POLICY_ERROR_MSG_06 = "Password must contain at least one number"; //Testabcd
    public static final String POLICY_ERROR_MSG_07 = "Password must contain at least one special character"; //Testabcd1

    public static final String PASSWORD_CHANGE_CONFIRMATION = "Your Sampath Vishwa password has changed. Please use the login option.";
}
