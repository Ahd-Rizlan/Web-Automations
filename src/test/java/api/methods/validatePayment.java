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

public class validatePayment extends baseMethod {
    File jsonBody = new File(POST_BODY);
    private static final Map<String, String> headersMap = new HashMap<>();
    private static final String baseHost = config.getProperty("sitSampathHost");
    private static Response response;
    private FileReader file;
    JSONObject jsonObject;
    JSONParser jsonParser = new JSONParser();
    private static final String JSON_PATH = USER_DIR + GET_VALIDATE_PAYMENT_RESPONSE;
    private static final String POST_BODY = USER_DIR + GET_VALIDATE_PAYMENT_BODY;

    private static final String INCORRECT_BILLER_ID = USER_DIR + GET_VALIDATE_PAYMENT_WITH_INCORRECT_BILLER_ID_RESPONSE;
    private static final String INCORRECT_CURRENCY = USER_DIR + GET_VALIDATE_PAYMENT_WITH_INCORRECT_CURRENCY_RESPONSE;
    private static final String INCORRECT_ACCOUNT_NUMBER = USER_DIR + GET_VALIDATE_PAYMENT_WITH_INCORRECT_ACCOUNT_NUMBER_RESPONSE;
    private static final String INCORRECT_TRANSACTION_TYPE = USER_DIR + GET_VALIDATE_PAYMENT_WITH_INCORRECT_TRANSACTION_TYPE_RESPONSE;
    public void authorisedWithInvalidToken() {
        headersMap.put(TXT_AUTHORIZATION, TXT_AUTHORIZATION_INVALID_VAL);
    }

    public void authorisedWithValidToken() {
        headersMap.put(TXT_AUTHORIZATION, config.getProperty("accessToken"));
    }

    public void setHeaders() {
        headersMap.put(TXT_CONTENT_TYPE, TXT_APPLICATION_JSON);
        headersMap.put(TXT_X_REQUEST_ID, TXT_X_REQUEST_ID_VALUE_ELEVEN);
    }

    public void setPayloadWithIncorrectBillerId() throws IOException, ParseException {
        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("validatePaymentBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("validatePaymentBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        JSONObject addBeneficiaries = (JSONObject) jsonObject.get("validatePayment");
        addBeneficiaries.put("billerID", INVALID_BILLER_ID);
        FileWriter writer = new FileWriter(jsonBody, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void setPayloadWithValidData() throws IOException, ParseException {
        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("validatePaymentBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("validatePaymentBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        JSONObject addBeneficiaries = (JSONObject) jsonObject.get("validatePayment");
        addBeneficiaries.put("billerID", VALID_BILLER_ID);
        addBeneficiaries.put("account", VALID_ACCOUNT_NUMBER);
        addBeneficiaries.put("currency", VALID_CURRENCY);
        addBeneficiaries.put("transactionType", VALID_TRANSACTION_TYPE);
        FileWriter writer = new FileWriter(jsonBody, false);
        writer.write(jsonObject.toString());
        writer.close();
    }

 public void setPayloadWithIncorrectAccountNumber() throws IOException, ParseException {
        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("validatePaymentBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("validatePaymentBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        JSONObject withdrawMobileCash = (JSONObject) jsonObject.get("validatePayment");
        withdrawMobileCash.put("account", INVALID_ACCOUNT_NUMBER);
        FileWriter writer = new FileWriter(jsonBody, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }


    public void setPayloadWithIncorrectCurrency() throws IOException, ParseException {
        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("validatePaymentBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("validatePaymentBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        JSONObject withdrawMobileCash = (JSONObject) jsonObject.get("validatePayment");
        withdrawMobileCash.put("currency", INVALID_CURRENCY);
        FileWriter writer = new FileWriter(jsonBody, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void setPayloadWithIncorrectTransactionType() throws IOException, ParseException {
        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("validatePaymentBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("validatePaymentBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        JSONObject withdrawMobileCash = (JSONObject) jsonObject.get("validatePayment");
        withdrawMobileCash.put("transactionType", INVALID_TRANSACTION_TYPE);
        FileWriter writer = new FileWriter(jsonBody, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void invokeValidatePaymentApi() {
        setHeaders();
        RestAssured.useRelaxedHTTPSValidation();
        response = RestAssured.given()
                .baseUri(baseHost)
                .headers(headersMap)
                .basePath(GET_VALIDATE_PAYMENT_PATH)
                .body(jsonBody)
                .when()
                .log()
                .all()
                .post();
        System.out.println("API Response (validatePayment) : " + response.prettyPrint());
        printResponseLogInReport(response);
    }

    public void validateResponseCode(int responseCode) {
        response.then().assertThat().statusCode(responseCode);
    }

    public void validatePayload() {
        new PayloadValidator().validateJsonFileWithResponse(JSON_PATH, response);
    }

    public void validatePayloadForIncorrectBillerId() {
        new PayloadValidator().validateJsonFileWithResponse(INCORRECT_BILLER_ID, response);
    }

    public void validatePayloadForIncorrectAccountNumber() {
        new PayloadValidator().validateJsonFileWithResponse(INCORRECT_ACCOUNT_NUMBER, response);
    }

    public void validatePayloadForIncorrectCurrency() {
        new PayloadValidator().validateJsonFileWithResponse(INCORRECT_CURRENCY, response);
    }

    public void validatePayloadForIncorrectTransactionType() {
        new PayloadValidator().validateJsonFileWithResponse(INCORRECT_TRANSACTION_TYPE, response);
    }
}
