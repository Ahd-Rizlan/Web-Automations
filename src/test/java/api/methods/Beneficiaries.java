package api.methods;

import api.utils.baseRequest;
import api.utils.helper;
import api.utils.validatePayload;
import api.utils.validateResponse;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static api.utils.ConstantApiUtils.*;
import static utils.CommonUtils.USER_DIR;
import static utils.DataStoreReadWriteApi.getAPIDetails;

public class Beneficiaries extends baseMethod {

    private final String ADD_BENEFICIARY_BASE_PATH = GET_ADD_BENEFICIARIES_PATH;
    private final String UPDATE_BENEFICIARY_BASE_PATH = GET_UPDATE_BENEFICIARIES_PATH;
    private final String DELETE_BENEFICIARY_BASE_PATH = GET_DELETE_BENEFICIARIES_PATH;
    public final String GET_BENEFICIARIES_LIST_PATH = GET_TRANSFER_PAYEE_LIST_PATH;


    private final baseRequest baseRequest = new baseRequest();
    private final validatePayload validatePayload = new validatePayload(baseRequest);
    private final validateResponse validateResponse = new validateResponse(baseRequest);
    private final helper helper = new helper(baseRequest);


    private final String PATH_TO_ADD_BENEFICIARIES_PAYLOAD = GET_ADD_BENEFICIARIES_BODY;
    private final String PATH_TO_UPDATE_BENEFICIARIES_PAYLOAD = GET_UPDATE_BENEFICIARIES_BODY;
    private final String PATH_TO_DELETE_BENEFICIARIES_PAYLOAD = GET_DELETE_BENEFICIARIES_BODY;
    private final String PATH_TO_GET_BENEFICIARIES_LIST_PAYLOAD = GET_TRANSFER_PAYEE_lIST_BODY;


    private static final String INVALID_ACCOUNT_TYPE_RESPONSE = USER_DIR + GET_ADD_BENEFICIARIES_WITH_INCORRECT_ACCOUNT_TYPE_RESPONSE;
    private static final String EMPTY_ACCOUNT_NAME_VALUE_RESPONSE = USER_DIR + GET_ADD_BENEFICIARIES_WITH_EMPTY_ACCOUNT_NAME_RESPONSE;
    private static final String INVALID_ACCOUNT_NUMBER_RESPONSE = USER_DIR + GET_ADD_BENEFICIARIES_WITH_INCORRECT_ACCOUNT_NUMBER_RESPONSE;
    private static final String EMPTY_BANK_CODE_RESPONSE = USER_DIR + GET_ADD_BENEFICIARIES_WITH_EMPTY_BANK_CODE_RESPONSE;
    private static final String AUTHORIZATION_RESPONSE = USER_DIR + GET_ADD_BENEFICIARIES_RESPONSE;
    private static final String UPDATE_BENEFICIARY_RESPONSE = USER_DIR + GET_UPDATE_BENEFICIARIES_RESPONSE;
    private static final String DELETE_BENEFICIARY_RESPONSE = USER_DIR + GET_DELETE_BENEFICIARIES_RESPONSE;
    private static final String GET_BENEFICIARIES_LIST_RESPONSE = USER_DIR + GET_TRANSFER_PAYEE_lIST_RESPONSE;

    private String BENEFICIARY_ID;
    //-----------------------------------------------  Token SETUP  -----------------------------------------------

    public void authorisedWithValidToken() {
        validatePayload.authorisedWithValidToken();
    }

    public void authorisedWithInValidToken() {
        validatePayload.authorisedWithInValidToken();
    }

    //-----------------------------------------------  Change BasePath  -----------------------------------------------
    public void updateBasePathForGetBeneficiariesList() {
        baseRequest.setBasePath(GET_BENEFICIARIES_LIST_PATH);
        baseRequest.setPOST_BODY(GET_TRANSFER_PAYEE_lIST_RESPONSE);
    }

    public void updateBasePathForAddBeneficiaries() {
        baseRequest.setBasePath(ADD_BENEFICIARY_BASE_PATH);
        baseRequest.setPOST_BODY(GET_ADD_BENEFICIARIES_RESPONSE);
    }

    public void updateBasePathForUpdateBeneficiaries() {
        baseRequest.setBasePath(UPDATE_BENEFICIARY_BASE_PATH);
        baseRequest.setPOST_BODY(GET_UPDATE_BENEFICIARIES_RESPONSE);
    }

    public void updateBasePathForDeleteBeneficiaries() {
        baseRequest.setBasePath(DELETE_BENEFICIARY_BASE_PATH);
        baseRequest.setPOST_BODY(GET_DELETE_BENEFICIARIES_RESPONSE);
    }

    //-----------------------------------------------  SETUP PAYLOAD  -----------------------------------------------
    public void setPayloadWithInvalidAccountType() throws IOException, ParseException {
        validatePayload.setPayloadWithInValidData(PATH_TO_ADD_BENEFICIARIES_PAYLOAD, "addBeneficiaries", "accountType", INVALID_ACCOUNT_TYPE);
    }

    public void setPayloadWithEmptyAccountName() throws IOException, ParseException {
        validatePayload.setPayloadWithInValidData(PATH_TO_ADD_BENEFICIARIES_PAYLOAD, "addBeneficiaries", "accountName", EMPTY_ACCOUNT_NAME_VALUE);
    }

    public void setPayloadWithIncorrectAccountNumber() throws IOException, ParseException {
        validatePayload.setPayloadWithInValidData(PATH_TO_ADD_BENEFICIARIES_PAYLOAD, "addBeneficiaries", "accountNumber", INVALID_ACCOUNT_NUMBER);
    }
    // --------------------- The Validations removed --------------------------

    public void setPayloadWithEmptyBankCode() throws IOException, ParseException {
        validatePayload.setPayloadWithInValidData(PATH_TO_ADD_BENEFICIARIES_PAYLOAD, "addBeneficiaries", "bank", EMPTY_BANK_CODE_VALUE);
    }

    public void setPayloadForAddBeneficiariesWithValidData() throws IOException, ParseException {
        validatePayload.setPayloadWithValidData(PATH_TO_ADD_BENEFICIARIES_PAYLOAD, "addBeneficiaries", "accountType", VALID_ACCOUNT_TYPE);
        validatePayload.setPayloadWithValidData(PATH_TO_ADD_BENEFICIARIES_PAYLOAD, "addBeneficiaries", "accountName", VALID_ACCOUNT_NAME);
        validatePayload.setPayloadWithValidData(PATH_TO_ADD_BENEFICIARIES_PAYLOAD, "addBeneficiaries", "accountNumber", CORRECT_ACCOUNT_NUMBER_VALUE);
        validatePayload.setPayloadWithValidData(PATH_TO_ADD_BENEFICIARIES_PAYLOAD, "addBeneficiaries", "bank", CORRECT_BANK_CODE_VALUE);

    }

    public void setPayloadForUpdateBeneficiaryForWithBeneficiaryId() throws IOException, ParseException {
        BENEFICIARY_ID = getAPIDetails("beneficiaryID");
        validatePayload.setPayloadWithValidData(PATH_TO_UPDATE_BENEFICIARIES_PAYLOAD, "updateBeneficiaries", "beneficiaryID", BENEFICIARY_ID);
    }

    public void setPayloadForDeleteBeneficiaryForWithBeneficiaryId() throws IOException, ParseException {
        BENEFICIARY_ID = getAPIDetails("beneficiaryID");
        validatePayload.setPayloadWithValidData(PATH_TO_DELETE_BENEFICIARIES_PAYLOAD, "deleteBeneficiaries", "beneficiariID", BENEFICIARY_ID);
    }

    public void setPayloadForGetBeneficiaryListWithPageLimit() throws IOException, ParseException {
        validatePayload.setPayloadWithInValidData(PATH_TO_GET_BENEFICIARIES_LIST_PAYLOAD, "getTransferPayeeList", "pageLimit", PAGE_LIMIT_SIX_HUNDRED);
    }
    public void setPayloadForGetBeneficiaryListWithPageNumber() throws IOException, ParseException {
        validatePayload.setPayloadWithInValidData(PATH_TO_GET_BENEFICIARIES_LIST_PAYLOAD, "getTransferPayeeList", "pageNo", PAGE_NO_ONE);
    }
    //-----------------------------------------------  INVOKE METHODS  -----------------------------------------------


    public void invokeBeneficiariesPOSTApi() {
        baseRequest.invokePostRequest();
    }

    //-----------------------------------------------  RESPONSE CODE VALIDATIONS  -----------------------------------------------
    public void validateResponseCode(int responseCode) {
        baseRequest.validateResponseCode(responseCode);
    }

    //-----------------------------------------------  HELPER METHOS  -----------------------------------------------
    public void extractBeneficiaryIDFomAddedName(){
        helper.extractUniqueID(VALID_ACCOUNT_NAME);
    }

    //-----------------------------------------------  RESPONSE VALIDATIONS  -----------------------------------------------

    public void setResponseWithUpdatedBeneficiaryId() throws IOException, ParseException {
        String KeyToAppend = "statusDescription";
        String InputToAppend = "BeneficiaryID :: " + getAPIDetails("beneficiaryID") + " record updated.";
        System.out.println(KeyToAppend + " " + InputToAppend);
        validateResponse.setResponseWithValidUniqueId("updateBeneficiariesResponse", "responseHeader", KeyToAppend, InputToAppend);
    }

    public void setResponseWithDeletedBeneficiaryId() throws IOException, ParseException {
        String KeyToAppend = "statusDescription";
        String InputToAppend = getAPIDetails("beneficiaryID") + " record deleted.";
        System.out.println(KeyToAppend + " " + InputToAppend);
        validateResponse.setResponseWithValidUniqueId("deleteBeneficiariesResponse", "responseHeader", KeyToAppend, InputToAppend);
    }

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

    public void validateResponsePayloadForUpdateBeneficiary() {
        validateResponse.validateResponse(UPDATE_BENEFICIARY_RESPONSE);
    }

    public void validateResponsePayloadForDeleteBeneficiary() {
        validateResponse.validateResponse(DELETE_BENEFICIARY_RESPONSE);
    }
    public void validateResponsePayloadForGetBeneficiaryList() {
        validateResponse.validateResponse(GET_BENEFICIARIES_LIST_PATH);
    }
}
