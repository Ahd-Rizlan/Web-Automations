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

public class getBillerDetails extends baseMethod {
    File jsonBody = new File(POST_BODY);
    private FileReader file;
    JSONObject jsonObject;
    JSONParser jsonParser = new JSONParser();
    private static final Map<String, String> headersMap = new HashMap<>();
    private static final String baseHost = config.getProperty("sitSampathHost");
    private static Response response;

    private static final String JSON_PATH = USER_DIR + GET_BILLER_DETAIL_RESPONSE;
    private static final String POST_BODY = USER_DIR + GET_BILLER_DETAIL_BODY;
    private static final String INCORRECT_MERCHANT_CODE = USER_DIR + GET_BILLER_DETAILS_WITH_INCORRECT_MERCHANT_ID_RESPONSE;

    public void authorisedWithInvalidToken() {
        headersMap.put(TXT_AUTHORIZATION, TXT_AUTHORIZATION_INVALID_VAL);
    }

    public void authorisedWithValidToken() {
        headersMap.put(TXT_AUTHORIZATION, config.getProperty("accessToken"));
    }

    public void setHeaders() {
        headersMap.put(TXT_CONTENT_TYPE, TXT_APPLICATION_JSON);
    }


    public void setPayloadWithInvalidMerchantCode() throws IOException, ParseException {
        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("getBillerDetailsBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("getBillerDetailsBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        JSONObject billerDetails = (JSONObject) jsonObject.get("getBillerDetails");
        billerDetails.put("merchantCode", INVALID_MERCHANT_CODE);
        FileWriter writer = new FileWriter(jsonBody, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }
    public void setPayloadWithValidMerchantCode() throws IOException, ParseException {
        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("getBillerDetailsBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("getBillerDetailsBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        JSONObject billerDetails = (JSONObject) jsonObject.get("getBillerDetails");
        billerDetails.put("merchantCode", VALID_MERCHANT_CODE);
        FileWriter writer = new FileWriter(jsonBody, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void validateResponseCode(int responseCode) {
        response.then().assertThat().statusCode(responseCode);
    }
    public void validatePayload() {
        new PayloadValidator().validateJsonFileWithResponse(JSON_PATH, response);
    }

    public void validatePayloadForIncorrectMerchantCode() {
        new PayloadValidator().validateJsonFileWithResponse(INCORRECT_MERCHANT_CODE, response);
    }

        public void invokeBillerDetailsApi() {
        setHeaders();
        RestAssured.useRelaxedHTTPSValidation();
        response = RestAssured.given()
                .baseUri(baseHost)
                .headers(headersMap)
                .basePath(GET_BILLER_DETAIL_PATH)
                .body(jsonBody)
                .when()
                .log()
                .all()
                .post();
        System.out.println("API Response (BillerDetails) : " + response.prettyPrint());
        printResponseLogInReport(response);
    }



}
