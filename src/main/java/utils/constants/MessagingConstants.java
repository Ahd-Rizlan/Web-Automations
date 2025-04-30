package utils.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * This Class is used to store all the test constant variables.
 */
public class MessagingConstants {

    // -------------- KEY WORDS OTHER VALUES ------------------
    public static final String OTP = "111111";
    public static final String LAST_MODIFIED_ON = "Last modified on";
    public static final String DRAFT = "Draft";
    public static final String INDEX = "index";
    public static final String NUMERICAL_THREE = "3";
    public static final String KW_LKR = "LKR ";



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

    // -------------- KEY WORDS BUTTON ------------------
    public static final String SEND = "Send";
    public static final String SAVE_AS_DRAFT = "Save as Draft";
    public static final String CONFIRM = "Confirm";
    public static final String TRASH = "Trash";
    public static final String ALL = "All";

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

    // -------------- BRANCH LIST ------------------
    public static final Map<String, String> BRANCHES = new HashMap<>();
    static {
        BRANCHES.put("KW_Ahangama", "Ahangama");
        BRANCHES.put("KW_Akkaraipattu", "Akkaraipattu");
        BRANCHES.put("KW_Akuressa", "Akuressa");
        BRANCHES.put("KW_Alawwa", "Alawwa");
        BRANCHES.put("KW_Aluthgama", "Aluthgama");
        BRANCHES.put("KW_Ambalangoda", "Ambalangoda");
        BRANCHES.put("KW_Ambalantota", "Ambalantota");
        BRANCHES.put("KW_Ampara", "Ampara");
        BRANCHES.put("KW_Anamaduwa", "Anamaduwa");
        BRANCHES.put("KW_AnuradhapuraNewTown", "Anuradhapura New Town");
        BRANCHES.put("KW_AnuradhapuraSuper", "Anuradhapura Super");
        BRANCHES.put("KW_Aralaganwila", "Aralaganwila");
        BRANCHES.put("KW_Athurugiriya", "Athurugiriya");
        BRANCHES.put("KW_Attidiya", "Attidiya");
        BRANCHES.put("KW_Avissawella", "Avissawella");
        BRANCHES.put("KW_Baddegama", "Baddegama");
        BRANCHES.put("KW_Badulla", "Badulla");
        BRANCHES.put("KW_Balangoda", "Balangoda");
        BRANCHES.put("KW_Bambalapitiya", "Bambalapitiya");
        BRANCHES.put("KW_Bandaragama", "Bandaragama");
        BRANCHES.put("KW_Bandarawela", "Bandarawela");
        BRANCHES.put("KW_Battaramulla", "Battaramulla");
        BRANCHES.put("KW_Batticaloa", "Batticaloa");
        BRANCHES.put("KW_BatticaloaIi", "Batticaloa Ii");
        BRANCHES.put("KW_Beliatta", "Beliatta");
        BRANCHES.put("KW_Bibile", "Bibile");
        BRANCHES.put("KW_Boralesgamuwa", "Boralesgamuwa");
        BRANCHES.put("KW_Borella", "Borella");
        BRANCHES.put("KW_BorellaSuper", "Borella Super");
        BRANCHES.put("KW_Buttala", "Buttala");
        BRANCHES.put("KW_Chankanai", "Chankanai");
        BRANCHES.put("KW_Chavakachcheri", "Chavakachcheri");
        BRANCHES.put("KW_Chenkalady", "Chenkalady");
        BRANCHES.put("KW_Chilaw", "Chilaw");
        BRANCHES.put("KW_Chunnakam", "Chunnakam");
        BRANCHES.put("KW_City", "City");
        BRANCHES.put("KW_ColomboSuper", "Colombo Super");
        BRANCHES.put("KW_Dambulla", "Dambulla");
        BRANCHES.put("KW_Dankotuwa", "Dankotuwa");
        BRANCHES.put("KW_Dehiattakandiya", "Dehiattakandiya");
        BRANCHES.put("KW_Dehiwala", "Dehiwala");
        BRANCHES.put("KW_Delgoda", "Delgoda");
        BRANCHES.put("KW_Deniyaya", "Deniyaya");
        BRANCHES.put("KW_Deraniyagala", "Deraniyagala");
        BRANCHES.put("KW_Dickwella", "Dickwella");
        BRANCHES.put("KW_Digana", "Digana");
        BRANCHES.put("KW_Divulapitiya", "Divulapitiya");
        BRANCHES.put("KW_Eheliyagoda", "Eheliyagoda");
        BRANCHES.put("KW_Elpitiya", "Elpitiya");
        BRANCHES.put("KW_Embilipitiya", "Embilipitiya");
        BRANCHES.put("KW_Embuldeniya", "Embuldeniya");
        BRANCHES.put("KW_Fort", "Fort");
        BRANCHES.put("KW_Galewela", "Galewela");
        BRANCHES.put("KW_GalleBazaar", "Galle Bazaar");
        BRANCHES.put("KW_GalleSuper", "Galle Super");
        BRANCHES.put("KW_Gampaha", "Gampaha");
        BRANCHES.put("KW_GampahaSuper", "Gampaha Super");
        BRANCHES.put("KW_Gampola", "Gampola");
        BRANCHES.put("KW_Ganemulla", "Ganemulla");
        BRANCHES.put("KW_Gangodawila", "Gangodawila");
        BRANCHES.put("KW_Giriulla", "Giriulla");
        BRANCHES.put("KW_Godakawela", "Godakawela");
        BRANCHES.put("KW_GothatuwaNewTown", "Gothatuwa New Town");
        BRANCHES.put("KW_Grandpass", "Grandpass");
        BRANCHES.put("KW_GregoryRoad", "Gregory Road");
        BRANCHES.put("KW_Habaraduwa", "Habaraduwa");
        BRANCHES.put("KW_Hanwella", "Hanwella");
        BRANCHES.put("KW_HarbourView", "Harbour View");
        BRANCHES.put("KW_Hatton", "Hatton");
        BRANCHES.put("KW_Headquarters", "Headquarters");
        BRANCHES.put("KW_Heerassagala", "Heerassagala");
        BRANCHES.put("KW_Hendala", "Hendala");
        BRANCHES.put("KW_Hettipola", "Hettipola");
        BRANCHES.put("KW_Hikkaduwa", "Hikkaduwa");
        BRANCHES.put("KW_Hingurakgoda", "Hingurakgoda");
        BRANCHES.put("KW_Homagama", "Homagama");
        BRANCHES.put("KW_Horana", "Horana");
        BRANCHES.put("KW_Ingiriya", "Ingiriya");
        BRANCHES.put("KW_Jaela", "Ja-ela");
        BRANCHES.put("KW_Jaffna", "Jaffna");
        BRANCHES.put("KW_Kadawatha", "Kadawatha");
        BRANCHES.put("KW_Kaduruwela", "Kaduruwela");
        BRANCHES.put("KW_Kaduwela", "Kaduwela");
        BRANCHES.put("KW_Kahatagasdigiliya", "Kahatagasdigiliya");
        BRANCHES.put("KW_Kalawana", "Kalawana");
        BRANCHES.put("KW_Kalmunai", "Kalmunai");
        BRANCHES.put("KW_Kalutara", "Kalutara");
        BRANCHES.put("KW_Kaluwanchikudy", "Kaluwanchikudy");
        BRANCHES.put("KW_Kamburupitiya", "Kamburupitiya");
        BRANCHES.put("KW_Kandana", "Kandana");
        BRANCHES.put("KW_KandyCityCentre", "Kandy City Centre");
        BRANCHES.put("KW_KandyMetro", "Kandy Metro");
        BRANCHES.put("KW_KandySuper", "Kandy Super");
        BRANCHES.put("KW_Kantale", "Kantale");
        BRANCHES.put("KW_Karagampitiya", "Karagampitiya");
        BRANCHES.put("KW_Karapitiya", "Karapitiya");
        BRANCHES.put("KW_Kattankudy", "Kattankudy");
        BRANCHES.put("KW_Katugastota", "Katugastota");
        BRANCHES.put("KW_Kayts", "Kayts");
        BRANCHES.put("KW_Kegalle", "Kegalle");
        BRANCHES.put("KW_Kekirawa", "Kekirawa");
        BRANCHES.put("KW_Kelaniya", "Kelaniya");
        BRANCHES.put("KW_Kesbewa", "Kesbewa");
        BRANCHES.put("KW_Keselwatta", "Keselwatta");
        BRANCHES.put("KW_Killinochchi", "Killinochchi");
        BRANCHES.put("KW_Kinniya", "Kinniya");
        BRANCHES.put("KW_Kiribathgoda", "Kiribathgoda");
        BRANCHES.put("KW_KiribathgodaSuper", "Kiribathgoda Super");
        BRANCHES.put("KW_Kirindiwela", "Kirindiwela");
        BRANCHES.put("KW_Kirulapone", "Kirulapone");
        BRANCHES.put("KW_Kochchikade", "Kochchikade");
        BRANCHES.put("KW_Kohuwala", "Kohuwala");
        BRANCHES.put("KW_Kollupitiya", "Kollupitiya");
        BRANCHES.put("KW_Kotahena", "Kotahena");
        BRANCHES.put("KW_Kottawa", "Kottawa");
        BRANCHES.put("KW_Kuliyapitiya", "Kuliyapitiya");
        BRANCHES.put("KW_Kundasale", "Kundasale");
        BRANCHES.put("KW_KurunegalaMetro", "Kurunegala Metro");
        BRANCHES.put("KW_KurunegalaSuper", "Kurunegala Super");
        BRANCHES.put("KW_Madampe", "Madampe");
        BRANCHES.put("KW_Maharagama", "Maharagama");
        BRANCHES.put("KW_MaharagamaSuper", "Maharagama Super");
        BRANCHES.put("KW_Mahiyanganaya", "Mahiyanganaya");
        BRANCHES.put("KW_Maho", "Maho");
        BRANCHES.put("KW_MainStreet", "Main Street");
        BRANCHES.put("KW_Makola", "Makola");
        BRANCHES.put("KW_Malabe", "Malabe");
        BRANCHES.put("KW_Mallavi", "Mallavi");
        BRANCHES.put("KW_Manipay", "Manipay");
        BRANCHES.put("KW_Mannar", "Mannar");
        BRANCHES.put("KW_Maradana", "Maradana");
        BRANCHES.put("KW_Marandagahamula", "Marandagahamula");
        BRANCHES.put("KW_Marawila", "Marawila");
        BRANCHES.put("KW_Matale", "Matale");
        BRANCHES.put("KW_MataraBazaar", "Matara Bazaar");
        BRANCHES.put("KW_MataraSuper", "Matara Super");
        BRANCHES.put("KW_Mattegoda", "Mattegoda");
        BRANCHES.put("KW_Matugama", "Matugama");
        BRANCHES.put("KW_Mawanella", "Mawanella");
        BRANCHES.put("KW_Mawathagama", "Mawathagama");
        BRANCHES.put("KW_Menikhinna", "Menikhinna");
        BRANCHES.put("KW_Middeniya", "Middeniya");
        BRANCHES.put("KW_Minuwangoda", "Minuwangoda");
        BRANCHES.put("KW_Mirigama", "Mirigama");
        BRANCHES.put("KW_Monaragala", "Monaragala");
        BRANCHES.put("KW_Moratumulla", "Moratumulla");
        BRANCHES.put("KW_Moratuwa", "Moratuwa");
        BRANCHES.put("KW_Morawaka", "Morawaka");
        BRANCHES.put("KW_MountLavinia", "Mount Lavinia");
        BRANCHES.put("KW_Muttur", "Muttur");
        BRANCHES.put("KW_Mutwal", "Mutwal");
        BRANCHES.put("KW_Narahenpita", "Narahenpita");
        BRANCHES.put("KW_Narammala", "Narammala");
        BRANCHES.put("KW_Nattandiya", "Nattandiya");
        BRANCHES.put("KW_Nawala", "Nawala");
        BRANCHES.put("KW_NawalaKoswatta", "Nawala Koswatta");
        BRANCHES.put("KW_Nawalapitiya", "Nawalapitiya");
        BRANCHES.put("KW_NawamMawatha", "Nawam Mawatha");
        BRANCHES.put("KW_Negombo", "Negombo");
        BRANCHES.put("KW_NegomboIi", "Negombo Ii");
        BRANCHES.put("KW_Nelliady", "Nelliady");
        BRANCHES.put("KW_Neluwa", "Neluwa");
        BRANCHES.put("KW_Nikaweratiya", "Nikaweratiya");
        BRANCHES.put("KW_Ninthavur", "Ninthavur");
        BRANCHES.put("KW_Nittambuwa", "Nittambuwa");
        BRANCHES.put("KW_Nochchiyagama", "Nochchiyagama");
        BRANCHES.put("KW_Nugegoda", "Nugegoda");
        BRANCHES.put("KW_Nuwaraeliya", "Nuwaraeliya");
        BRANCHES.put("KW_Oddamavadi", "Oddamavadi");
        BRANCHES.put("KW_OldMoorStreet", "Old Moor Street");
        BRANCHES.put("KW_Orugodawatte", "Orugodawatte");
        BRANCHES.put("KW_Padukka", "Padukka");
        BRANCHES.put("KW_PanaduraWekada", "Panadura - Wekada");
        BRANCHES.put("KW_PanaduraSuper", "Panadura Super");
        BRANCHES.put("KW_Panchikawatte", "Panchikawatte");
        BRANCHES.put("KW_Pannala", "Pannala");
        BRANCHES.put("KW_Pannipitiya", "Pannipitiya");
        BRANCHES.put("KW_Passara", "Passara");
        BRANCHES.put("KW_Pelawatta", "Pelawatta");
        BRANCHES.put("KW_Peliyagoda", "Peliyagoda");
        BRANCHES.put("KW_Pelmadulla", "Pelmadulla");
        BRANCHES.put("KW_Peradeniya", "Peradeniya");
        BRANCHES.put("KW_Pettah", "Pettah");
        BRANCHES.put("KW_Pilimatalawa", "Pilimatalawa");
        BRANCHES.put("KW_Piliyandala", "Piliyandala");
        BRANCHES.put("KW_Pitakotte", "Pitakotte");
        BRANCHES.put("KW_PlatinumPlus", "Platinum Plus");
        BRANCHES.put("KW_Polgahawela", "Polgahawela");
        BRANCHES.put("KW_Poojapitiya", "Poojapitiya");
        BRANCHES.put("KW_Pottuvil", "Pottuvil");
        BRANCHES.put("KW_PrinceStreet", "Prince Street");
        BRANCHES.put("KW_Pussellawa", "Pussellawa");
        BRANCHES.put("KW_Puttalam", "Puttalam");
        BRANCHES.put("KW_Ragama", "Ragama");
        BRANCHES.put("KW_Rajagiriya", "Rajagiriya");
        BRANCHES.put("KW_Rambukkana", "Rambukkana");
        BRANCHES.put("KW_Ratmalana", "Ratmalana");
        BRANCHES.put("KW_Ratnapura", "Ratnapura");
        BRANCHES.put("KW_RatnapuraMetro", "Ratnapura Metro");
        BRANCHES.put("KW_Rikillagaskada", "Rikillagaskada");
        BRANCHES.put("KW_Ruwanwella", "Ruwanwella");
        BRANCHES.put("KW_Sainthamaruthu", "Sainthamaruthu");
        BRANCHES.put("KW_Seeduwa", "Seeduwa");
        BRANCHES.put("KW_Sooriyawewa", "Sooriyawewa");
        BRANCHES.put("KW_Talahena", "Talahena");
        BRANCHES.put("KW_Talawakele", "Talawakele");
        BRANCHES.put("KW_Tangalle", "Tangalle");
        BRANCHES.put("KW_Thalawathugoda", "Thalawathugoda");
        BRANCHES.put("KW_Thambuttegama", "Thambuttegama");
        BRANCHES.put("KW_Thimbirigasyaya", "Thimbirigasyaya");
        BRANCHES.put("KW_Thirunelveli", "Thirunelveli");
        BRANCHES.put("KW_Tissamaharamaya", "Tissamaharamaya");
        BRANCHES.put("KW_Trincomalee", "Trincomalee");
        BRANCHES.put("KW_Vavuniya", "Vavuniya");
        BRANCHES.put("KW_VavuniyaMetro", "Vavuniya Metro");
        BRANCHES.put("KW_Veyangoda", "Veyangoda");
        BRANCHES.put("KW_Wadduwa", "Wadduwa");
        BRANCHES.put("KW_Warakapola", "Warakapola");
        BRANCHES.put("KW_Wariyapola", "Wariyapola");
        BRANCHES.put("KW_Wattala", "Wattala");
        BRANCHES.put("KW_Wattegama", "Wattegama");
        BRANCHES.put("KW_Weligama", "Weligama");
        BRANCHES.put("KW_Welimada", "Welimada");
        BRANCHES.put("KW_Wennappuwa", "Wennappuwa");
        BRANCHES.put("KW_Wellampitiya", "Wellampitiya");
        BRANCHES.put("KW_WellawatthaSuper", "Wellawattha Super");
        BRANCHES.put("KW_Wellawaya", "Wellawaya");
        BRANCHES.put("KW_WorldTradeCenter", "World Trade Center");
        BRANCHES.put("KW_Yakkala", "Yakkala");
    }

    public static final Map<String, String> SUB_CATEGORY = new HashMap<>();
    static {
        SUB_CATEGORY.put("KW_CARD_ACTIVATION", "Card Activation");
        SUB_CATEGORY.put("KW_CREDIT_CARD_RENEWAL_RE_ISSUE_REPLACEMENT", "Credit Card Renewal/ Re-issue/ Replacement");
        SUB_CATEGORY.put("KW_ADD_CHANGE_SMS_ALERTS_NUMBER_ADDRESS_EMAIL_NAME", "Add/ Change SMS Alerts Number/ Address/ Email/ Name");
        SUB_CATEGORY.put("KW_REQUEST_LETTERS_LIMITS_CRIB_BALANCE_CANCELLATION_TRAVEL_INSURANCE", "Request Letters (Limits, CRIB, Balance, Cancellation)/ Travel Insurance");
        SUB_CATEGORY.put("KW_CARD_CANCELLATION", "Card Cancellation");
        SUB_CATEGORY.put("KW_TEMPORARY_DEACTIVATION", "Temporary Deactivation");
        SUB_CATEGORY.put("KW_DUE_DATE_CHANGE", "Due Date Change");
        SUB_CATEGORY.put("KW_GENERAL_REQUEST", "General Request");
        SUB_CATEGORY.put("KW_AUTOPAY_ACTIVATION_CHANGE_DEACTIVATION", "Autopay Activation/ Change/ Deactivation");
        SUB_CATEGORY.put("KW_FUND_TRANSFER_REQUEST", "Fund Transfer Request");
        SUB_CATEGORY.put("KW_LIMIT_ENHANCEMENT_CARD_UPGRADE", "Limit Enhancement/ Card Upgrade");
        SUB_CATEGORY.put("KW_UNSUCCESSFUL_POS_TRANSACTIONS", "Unsuccessful POS Transactions");
    }

}
