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

public class beneficiariesByTranType extends baseMethod {
    File jsonBody = new File(POST_BODY);
    private static final Map<String, String> headersMap = new HashMap<>();
    private static final String baseHost = config.getProperty("sitSampathHost");
    private static Response response;
    private FileReader file;
    JSONObject jsonObject;
    JSONParser jsonParser = new JSONParser();
    private static final String JSON_PATH = USER_DIR + GET_BENEFICIARIES_BY_TRAN_TYPE_RESPONSE;
    private static final String POST_BODY = USER_DIR + GET_BENEFICIARIES_BY_TRAN_TYPE_BODY;
    private static final String EMPTY_TRANTYPE = USER_DIR + GET_BENEFICIARIES_BY_TRAN_TYPE_WITH_EMPTY_TRAN_TYPE_RESPONSE;

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

    public void setPayloadWithEmptyTranType() throws IOException, ParseException {
        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("beneficiariesByTranTypeBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("beneficiariesByTranTypeBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        JSONObject beneficiariesByTranType = (JSONObject) jsonObject.get("beneficiariesByTranType");
        beneficiariesByTranType.put("tranType", EMPTY_TRAN_TYPE_VALUE);
        FileWriter writer = new FileWriter(jsonBody, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void setPayloadWithValidData() throws IOException, ParseException {
        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("beneficiariesByTranTypeBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("beneficiariesByTranTypeBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        JSONObject beneficiariesByTranType = (JSONObject) jsonObject.get("beneficiariesByTranType");
        beneficiariesByTranType.put("tranType", CORRECT_TRAN_TYPE_VALUE);
        FileWriter writer = new FileWriter(jsonBody, false);
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void invokeBeneficiariesByTranTypeApi() {
        setHeaders();
        RestAssured.useRelaxedHTTPSValidation();
        response = RestAssured.given()
                .baseUri(baseHost)
                .headers(headersMap)
                .basePath(GET_BENEFICIARIES_BY_TRAN_TYPE_PATH)
                .body(jsonBody)
                .when()
                .log()
                .all()
                .post();
        System.out.println("API Response (beneficiariesByTranType) : " + response.prettyPrint());
    }

    public void validateResponseCode(int responseCode) {
        response.then().assertThat().statusCode(responseCode);
    }

    public void validatePayload() {
        new PayloadValidator().validateJsonFileWithResponse(JSON_PATH, response);
    }

    public void validatePayloadForEmptyTranType() {
        new PayloadValidator().validateJsonFileWithResponse(EMPTY_TRANTYPE, response);
    }

}
