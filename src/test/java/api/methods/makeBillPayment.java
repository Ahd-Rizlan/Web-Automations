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

public class makeBillPayment extends baseMethod {
    File jsonBody = new File(POST_BODY);
    private static final Map<String, String> headersMap = new HashMap<>();
    private static final String baseHost = config.getProperty("sitSampathHost");
    private static Response response;
    private FileReader file;
    JSONObject jsonObject;
    JSONParser jsonParser = new JSONParser();
    private static final String JSON_PATH = USER_DIR + GET_MAKE_BILL_PAYMENT_RESPONSE;
    private static final String POST_BODY = USER_DIR + GET_MAKE_BILL_PAYMENT_BODY;

    private static final String INCORRECT_BILLER_ID = USER_DIR + GET_MAKE_BILL_PAYMENT_WITH_INCORRECT_BILLER_ID_RESPONSE;
    private static final String INCORRECT_DEBIT_ACCOUNT = USER_DIR + GET_MAKE_BILL_PAYMENT_WITH_INCORRECT_DEBIT_ACCOUNT_RESPONSE;
    private static final String INCORRECT_CURRENCY = USER_DIR + GET_MAKE_BILL_PAYMENT_WITH_INCORRECT_CURRENCY_RESPONSE;

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
        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("makeBillPaymentBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("makeBillPaymentBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        JSONObject addBeneficiaries = (JSONObject) jsonObject.get("makeBillPayment");
        addBeneficiaries.put("billerID", MAKE_BILL_PAYMENT_INVALID_BILLER_ID);
        FileWriter writer = new FileWriter(jsonBody, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void setPayloadWithValidData() throws IOException, ParseException {
        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("makeBillPaymentBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("makeBillPaymentBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        JSONObject addBeneficiaries = (JSONObject) jsonObject.get("makeBillPayment");
        addBeneficiaries.put("billerID", MAKE_BILL_PAYMENT_VALID_BILLER_ID);
        addBeneficiaries.put("debitAccount", MAKE_BILL_PAYMENT_VALID_DEBIT_ACCOUNT);
        addBeneficiaries.put("currency", VALID_CURRENCY);
        FileWriter writer = new FileWriter(jsonBody, false);
        writer.write(jsonObject.toString());
        writer.close();
    }

 public void setPayloadWithIncorrectDebitAccount() throws IOException, ParseException {
        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("makeBillPaymentBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("makeBillPaymentBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        JSONObject withdrawMobileCash = (JSONObject) jsonObject.get("makeBillPayment");
        withdrawMobileCash.put("debitAccount", MAKE_BILL_PAYMENT_INVALID_DEBIT_ACCOUNT);
        FileWriter writer = new FileWriter(jsonBody, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }


    public void setPayloadWithIncorrectCurrency() throws IOException, ParseException {
        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("makeBillPaymentBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("makeBillPaymentBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        JSONObject withdrawMobileCash = (JSONObject) jsonObject.get("makeBillPayment");
        withdrawMobileCash.put("currency", INVALID_CURRENCY);
        FileWriter writer = new FileWriter(jsonBody, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void invokeMakeBillPaymentApi() {
        setHeaders();
        RestAssured.useRelaxedHTTPSValidation();
        response = RestAssured.given()
                .baseUri(baseHost)
                .headers(headersMap)
                .basePath(GET_MAKE_BILL_PAYMENT_PATH)
                .body(jsonBody)
                .when()
                .log()
                .all()
                .post();
        System.out.println("API Response (makeBillPayment) : " + response.prettyPrint());

    }

    public void validateResponseCode(int responseCode) {
        response.then().assertThat().statusCode(responseCode);
    }

    public void validatePayload() {
        new PayloadValidator().validateJsonFileWithExcludedDataFields(JSON_PATH,response,new String[]{"reference","paymentRefId"});
    }

    public void validatePayloadForIncorrectBillerId() {
        new PayloadValidator().validateJsonFileWithResponse(INCORRECT_BILLER_ID, response);
    }

    public void validatePayloadForIncorrectDebitAccount() {
        new PayloadValidator().validateJsonFileWithResponse(INCORRECT_DEBIT_ACCOUNT, response);
    }

    public void validatePayloadForIncorrectCurrency() {
        new PayloadValidator().validateJsonFileWithResponse(INCORRECT_CURRENCY, response);
    }


}
