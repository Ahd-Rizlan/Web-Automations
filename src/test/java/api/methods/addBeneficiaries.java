package api.methods;

import api.utils.ConstantApiUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static api.utils.ConstantApiUtils.*;
import static utils.CommonUtils.USER_DIR;

public class addBeneficiaries extends baseMethod {
    File jsonBody = new File(POST_BODY);
    private static final Map<String, String> headersMap = new HashMap<>();
    private static final String baseHost = config.getProperty("sitSampathHost");
    private static Response response;
    private FileReader file;
    JSONObject jsonObject;
    JSONParser jsonParser = new JSONParser();
    private static final String JSON_PATH = USER_DIR + GET_ADD_BENEFICIARIES_RESPONSE;
    private static final String POST_BODY = USER_DIR + GET_ADD_BENEFICIARIES_BODY;

    private static final String INCORRECT_ACCOUNT_TYPE = USER_DIR + GET_ADD_BENEFICIARIES_WITH_INCORRECT_ACCOUNT_TYPE_RESPONSE;
    private static final String EMPTY_ACCOUNT_NAME = USER_DIR + GET_ADD_BENEFICIARIES_WITH_EMPTY_ACCOUNT_NAME_RESPONSE;
    private static final String INCORRECT_ACCOUNT_NUMBER = USER_DIR + GET_ADD_BENEFICIARIES_WITH_INCORRECT_ACCOUNT_NUMBER_RESPONSE;
    private static final String EMPTY_BANK_CODE = USER_DIR + GET_ADD_BENEFICIARIES_WITH_EMPTY_BANK_CODE_RESPONSE;

    public void authorisedWithInvalidToken() {
        headersMap.put(TXT_AUTHORIZATION, TXT_AUTHORIZATION_INVALID_VAL);
    }

    public void authorisedWithValidToken() {
        headersMap.put(TXT_AUTHORIZATION, config.getProperty("accessToken"));
    }

    public void setHeaders() {
        headersMap.put(TXT_CONTENT_TYPE, TXT_APPLICATION_JSON);
        headersMap.put(TXT_X_REQUEST_ID, TXT_X_REQUEST_ID_VALUE_HUNDRED_AND_TWENTY_THREE);
    }

    public void setPayloadWithInvalidAccountType() throws IOException, ParseException {
        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("addBeneficiariesBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("addBeneficiariesBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        JSONObject addBeneficiaries = (JSONObject) jsonObject.get("addBeneficiaries");
        addBeneficiaries.put("accountType", INVALID_ACCOUNT_TYPE);
        FileWriter writer = new FileWriter(jsonBody, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void setPayloadWithValidData() throws IOException, ParseException {
        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("addBeneficiariesBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("addBeneficiariesBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        JSONObject addBeneficiaries = (JSONObject) jsonObject.get("addBeneficiaries");
        addBeneficiaries.put("accountType", VALID_ACCOUNT_TYPE);
        addBeneficiaries.put("accountName", VALID_ACCOUNT_NAME);
        addBeneficiaries.put("accountNumber", CORRECT_ACCOUNT_NUMBER_VALUE);
        addBeneficiaries.put("bank", CORRECT_BANK_CODE_VALUE);
        FileWriter writer = new FileWriter(jsonBody, false);
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void setPayloadWithEmptyAccountName() throws IOException, ParseException {
        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("addBeneficiariesBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("addBeneficiariesBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        JSONObject addBeneficiaries = (JSONObject) jsonObject.get("addBeneficiaries");
        addBeneficiaries.put("accountName", EMPTY_ACCOUNT_NAME_VALUE);
        FileWriter writer = new FileWriter(jsonBody, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void setPayloadWithIncorrectAccountNumber() throws IOException, ParseException {
        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("addBeneficiariesBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("addBeneficiariesBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        JSONObject addBeneficiaries = (JSONObject) jsonObject.get("addBeneficiaries");
        addBeneficiaries.put("accountNumber", INCORRECT_ACCOUNT_NUMBER_VALUE);
        FileWriter writer = new FileWriter(jsonBody, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void setPayloadWithEmptyBankCode() throws IOException, ParseException {
        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("addBeneficiariesBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("addBeneficiariesBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        JSONObject addBeneficiaries = (JSONObject) jsonObject.get("addBeneficiaries");
        addBeneficiaries.put("bank", EMPTY_BANK_CODE_VALUE);
        FileWriter writer = new FileWriter(jsonBody, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void invokeAddBeneficiariesApi() {
        setHeaders();
        RestAssured.useRelaxedHTTPSValidation();
        response = RestAssured.given()
                .baseUri(baseHost)
                .headers(headersMap)
                .basePath(GET_ADD_BENEFICIARIES_PATH)
                .body(jsonBody)
                .when()
                .log()
                .all()
                .post();
        System.out.println("API Response (AddBeneficiaries) :" + response.prettyPrint());

    }

    public void validateResponseCode(int responseCode) {
        response.then().assertThat().statusCode(responseCode);
    }

    public void validatePayload() {
        new PayloadValidator().validateJsonFileWithResponse(JSON_PATH, response);
    }

    public void validatePayloadForIncorrectAccountType() {
        new PayloadValidator().validateJsonFileWithResponse(INCORRECT_ACCOUNT_TYPE, response);
    }

    public void validatePayloadForEmptyAccountName() {
        new PayloadValidator().validateJsonFileWithResponse(EMPTY_ACCOUNT_NAME, response);
    }

    public void validatePayloadForIncorrectAccountNumber() {
        new PayloadValidator().validateJsonFileWithResponse(INCORRECT_ACCOUNT_NUMBER, response);
    }

    public void validatePayloadForEmptyBankCode() {
        new PayloadValidator().validateJsonFileWithResponse(EMPTY_BANK_CODE, response);
    }
}
