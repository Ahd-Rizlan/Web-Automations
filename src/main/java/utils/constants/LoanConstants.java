package utils.constants;

public class LoanConstants {
    public static final String GRANTED_DATE = "Loan Granted Date";
    public static final String INSTALLMENTS_LEFT = "Installments Left";
    public static final String CAPITAL_INSTALLMENT = "Capital Installment Amount";
    public static final String EXPIRE_DATE = "Loan Expire Date";
    public static final String OVERDUE_AMOUNT = "Overdue Amount";

        // Digit pool (0–9)
        public static final String DIGIT_0 = "0";
        public static final String DIGIT_1 = "1";
        public static final String DIGIT_2 = "2";
        public static final String DIGIT_4 = "4";
        public static final String DIGIT_7 = "7";

        // Date separator
        public static final String DATE_SEPARATOR = "-";

        // Date parts built from digits
        public static final String DAY = DIGIT_2 + DIGIT_1;          // 21
        public static final String MONTH = DIGIT_0 + DIGIT_7;        // 07
        public static final String YEAR = DIGIT_2 + DIGIT_0 + DIGIT_2 + DIGIT_4; // 2024

//    String fullDate = LoanConstants.DAY + LoanConstants.DATE_SEPARATOR +
//            LoanConstants.MONTH + LoanConstants.DATE_SEPARATOR +
//            LoanConstants.YEAR;
//
//System.out.println("Constructed Date: " + fullDate);  // 21-07-2024

}
