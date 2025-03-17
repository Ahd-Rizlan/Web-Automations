package api.methods;

import api.utils.baseRequest;
import api.utils.validatePayload;
import api.utils.validateResponce;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static api.utils.ConstantApiUtils.*;
import static utils.CommonUtils.USER_DIR;

public class addBeneficiaries extends baseMethod {

    private final String BASE_PATH = GET_ADD_BENEFICIARIES_PATH;
    private final String PATH_TO_PAYLOAD = GET_ADD_BENEFICIARIES_BODY;
    private static final String JSON_PATH = USER_DIR + GET_ADD_BENEFICIARIES_RESPONSE;

    private final baseRequest baseRequest = new baseRequest(BASE_PATH );
    private final validatePayload validatePayload = new validatePayload(baseRequest);
    private final validateResponce validateResponce = new validateResponce(baseRequest);



//    File jsonBody = new File(POST_BODY);
    //imp on requestBase
//    private static final Map<String, String> headersMap = new HashMap<>();
    //imp on requestBase

//    private static final String baseHost = config.getProperty("sitSampathHost");
    //imp on baseMethods

//    private static Response response;
    //imp on requestBase

//    private FileReader file;
    //imp on request Validate Payload
//    JSONObject jsonObject;
    //imp on request Validate Payload

//    JSONParser jsonParser = new JSONParser();
    //imp on request Validate Payload

    //-----------------------------------imp on requestBase but have this to pass
    //imp on requestBase but have this to pass

//    private static final String POST_BODY = USER_DIR + GET_ADD_BENEFICIARIES_BODY;
//    //imp on requestBase but have this to pass
//
//    private static final String INCORRECT_ACCOUNT_TYPE = USER_DIR + GET_ADD_BENEFICIARIES_WITH_INCORRECT_ACCOUNT_TYPE_RESPONSE;
//    private static final String EMPTY_ACCOUNT_NAME = USER_DIR + GET_ADD_BENEFICIARIES_WITH_EMPTY_ACCOUNT_NAME_RESPONSE;
//    private static final String INCORRECT_ACCOUNT_NUMBER = USER_DIR + GET_ADD_BENEFICIARIES_WITH_INCORRECT_ACCOUNT_NUMBER_RESPONSE;
//    private static final String EMPTY_BANK_CODE = USER_DIR + GET_ADD_BENEFICIARIES_WITH_EMPTY_BANK_CODE_RESPONSE;
//--------------------------------------------------------


//    public void authorisedWithInvalidToken() {
//        headersMap.put(TXT_AUTHORIZATION, TXT_AUTHORIZATION_INVALID_VAL);
//    }
    //imp on requestBase

    public void authorisedWithValidToken() {
        validatePayload.authorisedWithValidToken();
    }
    //imp on request Validate Payload

//    public void setHeaders() {
//        headersMap.put(TXT_CONTENT_TYPE, TXT_APPLICATION_JSON);
//        headersMap.put(TXT_X_REQUEST_ID, TXT_X_REQUEST_ID_VALUE_HUNDRED_AND_TWENTY_THREE);
//    }
    // imp on requestBase


    //---------------------------------------------------------------------imp on request Validate Payload



    public void setPayloadWithInvalidAccountType() throws IOException, ParseException {
        validatePayload.setPayloadWithValidData(PATH_TO_PAYLOAD,"addBeneficiaries","accountType", INVALID_ACCOUNT_TYPE);
    }

    public void setPayloadWithValidData() throws IOException, ParseException {
        validatePayload.setPayloadWithValidData(PATH_TO_PAYLOAD,"addBeneficiaries","accountType", VALID_ACCOUNT_TYPE);
        validatePayload.setPayloadWithValidData(PATH_TO_PAYLOAD,"addBeneficiaries","accountName", VALID_ACCOUNT_NAME);
        validatePayload.setPayloadWithValidData(PATH_TO_PAYLOAD,"addBeneficiaries","accountNumber", CORRECT_ACCOUNT_NUMBER_VALUE);
        validatePayload.setPayloadWithValidData(PATH_TO_PAYLOAD,"addBeneficiaries","bank", CORRECT_BANK_CODE_VALUE);

    }

//    public void setPayloadWithEmptyAccountName() throws IOException, ParseException {
//        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("addBeneficiariesBody.json"));
//        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("addBeneficiariesBody.json"));
//        jsonObject = (JSONObject) jsonParser.parse(file);
//        JSONObject addBeneficiaries = (JSONObject) jsonObject.get("addBeneficiaries");
//        addBeneficiaries.put("accountName", EMPTY_ACCOUNT_NAME_VALUE);
//        FileWriter writer = new FileWriter(jsonBody, false); //overwrites the content of file
//        writer.write(jsonObject.toString());
//        writer.close();
//    }

//    public void setPayloadWithIncorrectAccountNumber() throws IOException, ParseException {
//        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("addBeneficiariesBody.json"));
//        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("addBeneficiariesBody.json"));
//        jsonObject = (JSONObject) jsonParser.parse(file);
//        JSONObject addBeneficiaries = (JSONObject) jsonObject.get("addBeneficiaries");
//        addBeneficiaries.put("accountNumber", INCORRECT_ACCOUNT_NUMBER_VALUE);
//        FileWriter writer = new FileWriter(jsonBody, false); //overwrites the content of file
//        writer.write(jsonObject.toString());
//        writer.close();
//    }

//    public void setPayloadWithEmptyBankCode() throws IOException, ParseException {
//        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("addBeneficiariesBody.json"));
//        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("addBeneficiariesBody.json"));
//        jsonObject = (JSONObject) jsonParser.parse(file);
//        JSONObject addBeneficiaries = (JSONObject) jsonObject.get("addBeneficiaries");
//        addBeneficiaries.put("bank", EMPTY_BANK_CODE_VALUE);
//        FileWriter writer = new FileWriter(jsonBody, false); //overwrites the content of file
//        writer.write(jsonObject.toString());
//        writer.close();
//    }
    //---------------------------------------------------------------------imp on request Validate Payload




    public void invokeAddBeneficiariesApi() {
        baseRequest.invokePostRequest();
    }
//        setHeaders();
//        RestAssured.useRelaxedHTTPSValidation();
//        response = RestAssured.given()
//                .baseUri(baseHost)
//                .headers(headersMap)
//                .basePath(GET_ADD_BENEFICIARIES_PATH)
//                .body(jsonBody)
//                .when()
//                .log()
//                .all()
//                .post();
//        System.out.println("API Response (AddBeneficiaries) :" + response.prettyPrint());
//        printResponseLogInReport(response);
//    }

    // imp on requestBase

    //imp on request RequestBase
    public void validateResponseCode(int responseCode) {
//        response.then().assertThat().statusCode(responseCode);
    baseRequest.validateResponseCode(responseCode);
    }


    //---------------------------------------------------------------------imp on request Validate responce
//
    public void validatePayload() {
        validateResponce.validateResponce(JSON_PATH);
    }
//
//    public void validatePayloadForIncorrectAccountType() {
//        new PayloadValidator().validateJsonFileWithResponse(INCORRECT_ACCOUNT_TYPE, response);
//    }
//
//    public void validatePayloadForEmptyAccountName() {
//        new PayloadValidator().validateJsonFileWithResponse(EMPTY_ACCOUNT_NAME, response);
//    }
//
//    public void validatePayloadForIncorrectAccountNumber() {
//        new PayloadValidator().validateJsonFileWithResponse(INCORRECT_ACCOUNT_NUMBER, response);
//    }
//
//    public void validatePayloadForEmptyBankCode() {
//        new PayloadValidator().validateJsonFileWithResponse(EMPTY_BANK_CODE, response);
//    }
}
