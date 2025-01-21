package api.methods;

import api.utils.ConstantApiUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static api.utils.ConstantApiUtils.*;
import static utils.CommonUtils.USER_DIR;
import static utils.DataStoreReadWriteApi.getAPIDetails;

public class creditCardDetailsApiMethods extends baseMethod {

    private static final Map<String, String> headersMap = new HashMap<>();
    private static final String baseHost = config.getProperty("sitSampathHost");
    private static Response response;
    private FileReader file;
    private File body;
    JSONObject jsonObject;
    JSONParser jsonParser = new JSONParser();
    private static final String JSON_PATH = USER_DIR + CREDIT_CARD_DETAILS_RESPONSE;

    public void authorisedWithInvalidToken() {
        headersMap.put(TXT_AUTHORIZATION, TXT_AUTHORIZATION_INVALID_VAL);
    }

    public void authorisedWithValidToken() {
        headersMap.put(TXT_AUTHORIZATION, config.getProperty("accessToken"));
    }

    public void setHeaders() {
        headersMap.put(TXT_CONTENT_TYPE, TXT_APPLICATION_JSON);
        headersMap.put(TXT_IDENTITY_TYPE, TXT_IDENTITY_TYPE_VALUE);
        headersMap.put(TXT_IDENTITY_VALUE, TXT_IDENTITY_VALUE_VALUE);
        headersMap.put(TXT_TOKEN_ID, TXT_TOKEN_ID_VALUE);
        headersMap.put(TXT_X_REQUEST_ID, TXT_X_REQUEST_ID_VALUE);
    }

    public void setPayloadWithValidPageLimit() throws IOException, ParseException {
        body = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        jsonObject.put("DeviceID", getAPIDetails("deviceUdid"));
        FileWriter writer = new FileWriter(body, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void setPayloadWithInValidDeviceId() throws IOException, ParseException {
        body = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        jsonObject.put("DeviceID", "030939111");
        FileWriter writer = new FileWriter(body, false);
        writer.write(jsonObject.toString());
        writer.close();
    }


    public void invokeInitiateCardCountApi() {
        setHeaders();
        RestAssured.useRelaxedHTTPSValidation();
        response = RestAssured.given()
                .baseUri(baseHost)
                .headers(headersMap)
                .basePath(CREDIT_CARD_DETAILS_PATH)
                .body(body)
                .when()
                .log()
                .all()
                .post();
        System.out.println("API Response" + response.prettyPrint());

    }

    public void validateResponseCode(int responseCode) {
        response.then().assertThat().statusCode(responseCode);
    }

    public void validateUnAuthorizedAPIKeyResponseMessage(String responseMessage) {
        Assert.assertEquals(responseMessage, response.path("Message").toString());
    }

    public void validateResponseMessage(String responseMessage) {
        Assert.assertEquals(responseMessage, response.path("messages[0]").toString());
    }

    public void validateResponseData(String path, String responseMessage) {
        String result = null;
        try {
            result = response.path(path).toString();

        }catch (NullPointerException e){
            result = "null";
        }
        Assert.assertEquals(responseMessage, result);
    }

    public void validatePayload() {
        new PayloadValidator().validateJsonFileWithResponse(JSON_PATH, response);
    }

    public void setPayloadWithValidDeviceId() throws IOException, ParseException {
        body = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        jsonObject.put("DeviceID", getAPIDetails("deviceUdid"));
        FileWriter writer = new FileWriter(body, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }



}



