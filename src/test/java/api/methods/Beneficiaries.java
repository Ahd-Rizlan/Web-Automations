package api.methods;

import api.utils.baseRequest;
import api.utils.validatePayload;
import api.utils.validateResponse;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static api.utils.ConstantApiUtils.*;
import static utils.CommonUtils.USER_DIR;

public class Beneficiaries extends baseMethod {

    private final String BASE_PATH = GET_ADD_BENEFICIARIES_PATH;
    private final baseRequest baseRequest = new baseRequest(BASE_PATH);
    private final validatePayload validatePayload = new validatePayload(baseRequest);
    private final validateResponse validateResponse = new validateResponse(baseRequest);


    private final String PATH_TO_PAYLOAD = GET_ADD_BENEFICIARIES_BODY;
    private static final String INVALID_ACCOUNT_TYPE_RESPONSE = USER_DIR + GET_ADD_BENEFICIARIES_WITH_INCORRECT_ACCOUNT_TYPE_RESPONSE;
    private static final String EMPTY_ACCOUNT_NAME_VALUE_RESPONSE = USER_DIR +GET_ADD_BENEFICIARIES_WITH_EMPTY_ACCOUNT_NAME_RESPONSE;
    private static final String INVALID_ACCOUNT_NUMBER_RESPONSE = USER_DIR +GET_ADD_BENEFICIARIES_WITH_INCORRECT_ACCOUNT_NUMBER_RESPONSE;
    private static final String EMPTY_BANK_CODE_RESPONSE = USER_DIR +GET_ADD_BENEFICIARIES_WITH_EMPTY_BANK_CODE_RESPONSE;
    private static final String AUTHORIZATION_RESPONSE = USER_DIR + GET_ADD_BENEFICIARIES_RESPONSE;


    //-----------------------------------------------  Token SETUP  -----------------------------------------------

    public void authorisedWithValidToken() {
        validatePayload.authorisedWithValidToken();
    }

    public void authorisedWithInValidToken() {
        validatePayload.authorisedWithInValidToken();
    }

    //-----------------------------------------------  SETUP PAYLOAD  -----------------------------------------------
    public void setPayloadWithInvalidAccountType() throws IOException, ParseException {
        validatePayload.setPayloadWithValidData(PATH_TO_PAYLOAD, "addBeneficiaries", "accountType", INVALID_ACCOUNT_TYPE);
    }

    public void setPayloadWithEmptyAccountName() throws IOException, ParseException {
        validatePayload.setPayloadWithValidData(PATH_TO_PAYLOAD, "addBeneficiaries", "accountName", EMPTY_ACCOUNT_NAME_VALUE);
    }

    public void setPayloadWithIncorrectAccountNumber() throws IOException, ParseException {
        validatePayload.setPayloadWithValidData(PATH_TO_PAYLOAD, "addBeneficiaries", "accountNumber", INVALID_ACCOUNT_NUMBER);
    }

    public void setPayloadWithEmptyBankCode() throws IOException, ParseException {
        validatePayload.setPayloadWithValidData(PATH_TO_PAYLOAD, "addBeneficiaries", "bank", EMPTY_BANK_CODE_VALUE);
    }

    public void setPayloadWithValidData() throws IOException, ParseException {
        validatePayload.setPayloadWithValidData(PATH_TO_PAYLOAD, "addBeneficiaries", "accountType", VALID_ACCOUNT_TYPE);
        validatePayload.setPayloadWithValidData(PATH_TO_PAYLOAD, "addBeneficiaries", "accountName", VALID_ACCOUNT_NAME);
        validatePayload.setPayloadWithValidData(PATH_TO_PAYLOAD, "addBeneficiaries", "accountNumber", CORRECT_ACCOUNT_NUMBER_VALUE);
        validatePayload.setPayloadWithValidData(PATH_TO_PAYLOAD, "addBeneficiaries", "bank", CORRECT_BANK_CODE_VALUE);

    }

    //-----------------------------------------------  INVOKE METHODS  -----------------------------------------------


    public void invokeBeneficiariesPOSTApi() {
        baseRequest.invokePostRequest();
    }

    //-----------------------------------------------  RESPONSE CODE VALIDATIONS  -----------------------------------------------
    public void validateResponseCode(int responseCode) {
        baseRequest.validateResponseCode(responseCode);
    }

    //-----------------------------------------------  RESPONSE VALIDATIONS  -----------------------------------------------


    public void validateResponsePayloadForIncorrectAccountType() {
        validateResponse.validateResponse(INVALID_ACCOUNT_TYPE_RESPONSE);
    }
    public void validateResponsePayloadForEmptyAccountName() {
        validateResponse.validateResponse(EMPTY_ACCOUNT_NAME_VALUE_RESPONSE);
    }
    public void validateResponsePayloadForIncorrectAccountNumber() {
        validateResponse.validateResponse(INVALID_ACCOUNT_NUMBER_RESPONSE);
    }
    public void validateResponsePayloadForEmptyBankCode() {
        validateResponse.validateResponse(EMPTY_BANK_CODE_RESPONSE);
    }
    public void validateResponsePayload() {
        validateResponse.validateResponse(AUTHORIZATION_RESPONSE);
    }





}
