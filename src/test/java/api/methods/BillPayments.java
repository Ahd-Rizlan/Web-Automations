package api.methods;

import api.utils.baseRequest;
import api.utils.helper;
import api.utils.validatePayload;
import api.utils.validateResponse;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static api.utils.ConstantApiUtils.*;
import static utils.CommonUtils.USER_DIR;

//getAccountsByTranType
//billers
//validate Payment
//make BillPayment
//saveBillTemplate
//getPaymentsById
public class BillPayments {
    //-------------------BASE PATH SETUP ----------------------------

    private final String ADD_MAKE_BILL_PAYMENT_BASE_PATH = GET_MAKE_BILL_PAYMENT_PATH;

    private final api.utils.baseRequest baseRequest = new baseRequest();
    private final api.utils.validatePayload validatePayload = new validatePayload(baseRequest);
    private final api.utils.validateResponse validateResponse = new validateResponse(baseRequest);
    private final api.utils.helper helper = new helper(baseRequest);

    private String[] FILES_TO_IGNORE;
    private Map<String, String> DataTobeAdded;

    //-----------------------------------------------  PAYLOAD SETUP  -----------------------------------------------
    private final String PATH_TO_MAKE_PAYMENTS_PAYLOAD = GET_MAKE_BILL_PAYMENT_BODY;

    //-----------------------------------------------  RESPONSE SETUP  -----------------------------------------------
    private static final String VALID_MAKE_BILL_PAYMENT_RESPONSE = USER_DIR + GET_MAKE_BILL_PAYMENT_RESPONSE;

    private static final String INCORRECT_BILLER_ID_RESPONSE = USER_DIR + GET_MAKE_BILL_PAYMENT_WITH_INCORRECT_BILLER_ID_RESPONSE;
    private static final String INCORRECT_DEBIT_ACCOUNT_RESPONSE = USER_DIR + GET_MAKE_BILL_PAYMENT_WITH_INCORRECT_DEBIT_ACCOUNT_RESPONSE;
    private static final String INCORRECT_CURRENCY_RESPONSE = USER_DIR + GET_MAKE_BILL_PAYMENT_WITH_INCORRECT_CURRENCY_RESPONSE;

    //-----------------------------------------------  Token SETUP  -----------------------------------------------
    public void authorisedWithValidToken() {
        validatePayload.authorisedWithValidToken();
    }

    public void authorisedWithInValidToken() {
        validatePayload.authorisedWithInValidToken();
    }
    // ----------------------------------------------  Set Headers  ------------------------------------------------
    public void setHeader(){
        baseRequest.setHeaders(TXT_X_REQUEST_ID_VALUE_ELEVEN);
    }
    //-----------------------------------------------  Change BasePath  -----------------------------------------------
    public void updateBasePathForMakePayments() {
        baseRequest.setBasePath(ADD_MAKE_BILL_PAYMENT_BASE_PATH);
        baseRequest.setResponse_Body(VALID_MAKE_BILL_PAYMENT_RESPONSE);
    }
    //-----------------------------------------------  SETUP PAYLOAD  -----------------------------------------------
    public void setPayloadForAddBeneficiariesWithValidData() throws IOException, ParseException {
        DataTobeAdded = new HashMap<>();
        DataTobeAdded.put("billerID", MAKE_BILL_PAYMENT_VALID_BILLER_ID);
        DataTobeAdded.put("debitAccount", MAKE_BILL_PAYMENT_VALID_DEBIT_ACCOUNT);
        DataTobeAdded.put("currency", VALID_CURRENCY);
        validatePayload.setPayloadWithValidData(PATH_TO_MAKE_PAYMENTS_PAYLOAD, "makeBillPayment", DataTobeAdded);
    }

    public void setPayloadWithIncorrectDebitAccount() throws IOException, ParseException {
        validatePayload.setPayloadWithInValidData(PATH_TO_MAKE_PAYMENTS_PAYLOAD,"makeBillPayment","debitAccount",MAKE_BILL_PAYMENT_INVALID_DEBIT_ACCOUNT);
    }
    public void setPayloadWithIncorrectCurrency() throws IOException, ParseException {
        validatePayload.setPayloadWithInValidData(PATH_TO_MAKE_PAYMENTS_PAYLOAD,"makeBillPayment","currency",INVALID_CURRENCY);

    }
    public void setPayloadWithIncorrectBillerId() throws IOException, ParseException {
        validatePayload.setPayloadWithInValidData(PATH_TO_MAKE_PAYMENTS_PAYLOAD,"makeBillPayment","billerID",MAKE_BILL_PAYMENT_INVALID_BILLER_ID);

    }
        //-----------------------------------------------  INVOKE METHODS  -----------------------------------------------
    //-----------------------------------------------  RESPONSE CODE VALIDATIONS  -----------------------------------------------
        public void validateResponseCode(int responseCode) {
            baseRequest.validateResponseCode(responseCode);
        }
    //-----------------------------------------------  HELPER METHODS  -----------------------------------------------
    //-----------------------------------------------  RESPONSE VALIDATION SETUP  -----------------------------------------------
    //-----------------------------------------------  RESPONSE VALIDATIONS  -----------------------------------------------

}
