package api.methods;

import api.utils.baseRequest;
import api.utils.helper;
import api.utils.validatePayload;
import api.utils.validateResponse;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Map;

import static api.utils.ConstantApiUtils.*;
import static utils.CommonUtils.USER_DIR;
import static utils.DataStoreReadWriteApi.getAPIDetails;

public class Beneficiaries extends baseMethod {

    private final String ADD_BENEFICIARY_BASE_PATH = GET_ADD_BENEFICIARIES_PATH;
    private final String UPDATE_BENEFICIARY_BASE_PATH = GET_UPDATE_BENEFICIARIES_PATH;
    private final String DELETE_BENEFICIARY_BASE_PATH = GET_DELETE_BENEFICIARIES_PATH;
    public final String GET_BENEFICIARIES_LIST_PATH = GET_TRANSFER_PAYEE_LIST_PATH;
    public final String GET_BENEFICIARIES_BY_TRANSACTION_TYPE_PATH = GET_BENEFICIARIES_BY_TRAN_TYPE_PATH;
    private final String UPDATE_FAVOURITE_PAYEE_BASE_PATH =GET_UPDATE_FAVOURITE_PAYEE_PATH;

    private final String GET_FAVOURITE_PAYEE_LIST_BASE_PATH =GET_FAV_BENEFICIARIES_PATH;


    private final baseRequest baseRequest = new baseRequest();
    private final validatePayload validatePayload = new validatePayload(baseRequest);
    private final validateResponse validateResponse = new validateResponse(baseRequest);
    private final helper helper = new helper(baseRequest);

    //-----------------------------------------------  PAYLOAD SETUP  -----------------------------------------------

    private final String PATH_TO_ADD_BENEFICIARIES_PAYLOAD = GET_ADD_BENEFICIARIES_BODY;
    private final String PATH_TO_UPDATE_BENEFICIARIES_PAYLOAD = GET_UPDATE_BENEFICIARIES_BODY;
    private final String PATH_TO_DELETE_BENEFICIARIES_PAYLOAD = GET_DELETE_BENEFICIARIES_BODY;
    private final String PATH_TO_GET_BENEFICIARIES_LIST_PAYLOAD = GET_TRANSFER_PAYEE_lIST_BODY;
    private final String PATH_TO_GET_BENEFICIARIES_BY_TRANSACTION_TYPE_PAYLOAD =GET_BENEFICIARIES_BY_TRAN_TYPE_BODY;
    private final String PATH_TO_UPDATE_FAVOURITE_PAYEE_PAYLOAD = GET_UPDATE_FAVOURITE_PAYEE_BODY;
    private final String PATH_TO_GET_FAVOURITE_PAYEE_LIST_PAYLOAD = FAV_BENEFICIARIES_BODY;

    //-----------------------------------------------  RESPONSE SETUP  -----------------------------------------------

    private static final String INVALID_ACCOUNT_TYPE_RESPONSE = USER_DIR + GET_ADD_BENEFICIARIES_WITH_INCORRECT_ACCOUNT_TYPE_RESPONSE;
    private static final String EMPTY_ACCOUNT_NAME_VALUE_RESPONSE = USER_DIR + GET_ADD_BENEFICIARIES_WITH_EMPTY_ACCOUNT_NAME_RESPONSE;
    private static final String INVALID_ACCOUNT_NUMBER_RESPONSE = USER_DIR + GET_ADD_BENEFICIARIES_WITH_INCORRECT_ACCOUNT_NUMBER_RESPONSE;
    private static final String EMPTY_BANK_CODE_RESPONSE = USER_DIR + GET_ADD_BENEFICIARIES_WITH_EMPTY_BANK_CODE_RESPONSE;
    private static final String AUTHORIZATION_RESPONSE = USER_DIR + GET_ADD_BENEFICIARIES_RESPONSE;
    private static final String UPDATE_BENEFICIARY_RESPONSE = USER_DIR + GET_UPDATE_BENEFICIARIES_RESPONSE;
    private static final String DELETE_BENEFICIARY_RESPONSE = USER_DIR + GET_DELETE_BENEFICIARIES_RESPONSE;
    private static final String GET_BENEFICIARIES_LIST_RESPONSE = USER_DIR + GET_TRANSFER_PAYEE_lIST_RESPONSE;
    private static final String GET_BENEFICIARIES_BY_TRANSACTION_TYPE_RESPONSE = USER_DIR + GET_BENEFICIARIES_BY_TRAN_TYPE_RESPONSE;
    private static final String UPDATE_FAVOURITE_PAYEE_RESPONSE = USER_DIR + GET_UPDATE_FAVOURITE_PAYEE_RESPONSE;
    private static final String GET_FAVOURITE_PAYEE_LIST_RESPONSE =  USER_DIR + FAV_BENEFICIARIES_RESPONSE;
    private String BENEFICIARY_ID;
    private String[] FILES_TO_IGNORE;
    private Map<String,String> DataTobeAdded;

    //-----------------------------------------------  Token SETUP  -----------------------------------------------

    public void authorisedWithValidToken() {
        validatePayload.authorisedWithValidToken();
    }

    public void authorisedWithInValidToken() {
        validatePayload.authorisedWithInValidToken();
    }

    //-----------------------------------------------  Change BasePath  -----------------------------------------------

    public void updateBasePathForAddBeneficiaries() {
        baseRequest.setBasePath(ADD_BENEFICIARY_BASE_PATH);
        baseRequest.setResponse_Body(AUTHORIZATION_RESPONSE);

    }

    public void updateBasePathForUpdateBeneficiaries() {
        baseRequest.setBasePath(UPDATE_BENEFICIARY_BASE_PATH);
        baseRequest.setResponse_Body(UPDATE_BENEFICIARY_RESPONSE);

    }

    public void updateBasePathForDeleteBeneficiaries() {
        baseRequest.setBasePath(DELETE_BENEFICIARY_BASE_PATH);
        baseRequest.setResponse_Body(DELETE_BENEFICIARY_RESPONSE);

    }
    public void updateBasePathForGetBeneficiariesList() {
        baseRequest.setBasePath(GET_BENEFICIARIES_LIST_PATH);
        baseRequest.setResponse_Body(GET_BENEFICIARIES_LIST_RESPONSE);


    }
    public void updateBasePathForGetBeneficiariesByTransactionType() {
        baseRequest.setBasePath(GET_BENEFICIARIES_BY_TRANSACTION_TYPE_PATH);
        baseRequest.setResponse_Body(GET_BENEFICIARIES_BY_TRANSACTION_TYPE_RESPONSE);

    }
    public void updateBasePathForUpdateFavouritePayee() {
        baseRequest.setBasePath(UPDATE_FAVOURITE_PAYEE_BASE_PATH);
        baseRequest.setResponse_Body(UPDATE_FAVOURITE_PAYEE_RESPONSE);

    }

    public void updateBasePathForGetFavouritePayeeList() {
        baseRequest.setBasePath(GET_FAV_BENEFICIARIES_PATH);
        baseRequest.setResponse_Body(GET_FAVOURITE_PAYEE_LIST_RESPONSE);

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

    public void setPayloadForGetBeneficiaryList() throws IOException, ParseException {
        validatePayload.setPayloadWithInValidData(PATH_TO_GET_BENEFICIARIES_LIST_PAYLOAD, "getTransferPayeeList", "pageLimit", PAGE_LIMIT_SIX_HUNDRED);
        validatePayload.setPayloadWithInValidData(PATH_TO_GET_BENEFICIARIES_LIST_PAYLOAD, "getTransferPayeeList", "pageNo", PAGE_NO_ONE);

    }
    public void setPayloadForGetBeneficiaryByTransactionType() throws IOException, ParseException {
        validatePayload.setPayloadWithInValidData(PATH_TO_GET_BENEFICIARIES_BY_TRANSACTION_TYPE_PAYLOAD, "beneficiariesByTranType", "tranType", CORRECT_TRAN_TYPE_VALUE);
    }

    public void setPayloadForUpdateFavouritePayee() throws IOException, ParseException {
        BENEFICIARY_ID = getAPIDetails("beneficiaryID");
        validatePayload.setPayloadWithInValidData(PATH_TO_UPDATE_FAVOURITE_PAYEE_PAYLOAD, "updateFavouritePayee", "favourite", TRUE_VALUE);
        validatePayload.setPayloadWithValidData(PATH_TO_UPDATE_FAVOURITE_PAYEE_PAYLOAD, "updateFavouritePayee", "beneficiaryID", BENEFICIARY_ID);

    }
    public void setPayloadForGetFavouritePayeeList() throws IOException, ParseException {
        validatePayload.setPayloadWithInValidData(PATH_TO_GET_FAVOURITE_PAYEE_LIST_PAYLOAD, "favBeneficiaries", "pageLimit", PAGE_LIMIT_SIX_HUNDRED);
        validatePayload.setPayloadWithInValidData(PATH_TO_GET_FAVOURITE_PAYEE_LIST_PAYLOAD, "favBeneficiaries", "pageNo", PAGE_NO_ONE);

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

    //-----------------------------------------------  RESPONSE VALIDATION SETUP  -----------------------------------------------

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
    public void setResponseWithUpdatedFavouritePayeeId() throws IOException, ParseException {
        String KeyToAppend = "statusDescription";
        String InputToAppend ="BeneficiaryID :: " + getAPIDetails("beneficiaryID") + " record updated.";
        System.out.println(KeyToAppend + " " + InputToAppend);
        validateResponse.setResponseWithValidUniqueId("updateFavouritePayeeListResponse", "responseHeader", KeyToAppend, InputToAppend);
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

    public void validateResponsePayloadForUpdateBeneficiary() {
        validateResponse.validateResponse(UPDATE_BENEFICIARY_RESPONSE);
    }

    public void validateResponsePayloadForDeleteBeneficiary() {
        validateResponse.validateResponse(DELETE_BENEFICIARY_RESPONSE);
    }
    public void validateResponsePayloadForGetBeneficiaryList() {
        FILES_TO_IGNORE = new String[]{"recordCount","beneficiaryList","pageLimit","pageNo"};
        validateResponse.validateResponseWithExcludedDataFields(GET_BENEFICIARIES_LIST_RESPONSE,FILES_TO_IGNORE);
    }
    public void validateResponsePayloadForGetBeneficiaryByTransactionType() {
        FILES_TO_IGNORE = new String[]{"recordCount","beneficiaryListDetails"};
        validateResponse.validateResponseWithExcludedDataFields(GET_BENEFICIARIES_BY_TRANSACTION_TYPE_RESPONSE,FILES_TO_IGNORE);
    }
    public void validateResponsePayloadUpdateFavouritePayee() {
        validateResponse.validateResponse(UPDATE_FAVOURITE_PAYEE_RESPONSE);
    }
    public void validateResponsePayloadForGetFavouritePayeeList() {
        FILES_TO_IGNORE = new String[]{"recordCount","beneficiaryListDetails","pageLimit","pageNo"};
        validateResponse.validateResponseWithExcludedDataFields(GET_FAVOURITE_PAYEE_LIST_RESPONSE,FILES_TO_IGNORE);
    }
}
