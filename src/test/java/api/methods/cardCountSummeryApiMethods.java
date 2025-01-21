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
import static utils.DataStoreReadWriteApi.getAPIDetails;

public class cardCountSummeryApiMethods extends baseMethod {

    private static final Map<String, String> headersMap = new HashMap<>();
    private static final String baseHost = config.getProperty("sitSampathHost");
    private static Response response;
    private FileReader file;
    private File body;
    JSONObject jsonObject;
    JSONParser jsonParser = new JSONParser();
    private static final String JSON_PATH = USER_DIR + CARD_COUNT_SUMMARY_RESPONSE;
    private static final String JSON_PATH_INCORRECT_INITIATED_SERNO = USER_DIR + CARD_COUNT_SUMMARY_FOR_INCORRECT_INITIATED_SERNO_RESPONSE;
    private static final String JSON_PATH_INCORRECT_INITIATED_KEY = USER_DIR + CARD_COUNT_SUMMARY_FOR_INCORRECT_INITIATED_KEY_RESPONSE;
    private static final String JSON_PATH_INCORRECT_CHAINSERNO = USER_DIR + CARD_COUNT_SUMMARY_FOR_INCORRECT_CHAINSERNO_RESPONSE;
    private static final String JSON_PATH_INCORRECT_CHAINAUTH = USER_DIR + CARD_COUNT_SUMMARY_FOR_INCORRECT_CHAINAUTH_RESPONSE;
    private static final String JSON_PATH_INCORRECT_DEVICEID = USER_DIR + CARD_COUNT_SUMMARY_FOR_INCORRECT_DEVICEID_RESPONSE;

    public void authorisedWithInvalidToken() {
        headersMap.put(TXT_AUTHORIZATION, TXT_AUTHORIZATION_INVALID_VAL);
    }

    public void authorisedWithValidToken() {
        headersMap.put(TXT_AUTHORIZATION, config.getProperty("accessToken"));
    }

    public void setHeaders() {
        headersMap.put(TXT_CONTENT_TYPE, TXT_APPLICATION_JSON);
        headersMap.put(TXT_SERVICE_NAME, TXT_SERVICE_NAME_VALUE);
        headersMap.put(TXT_IDENTITY_TYPE, TXT_IDENTITY_TYPE_VALUE);
        headersMap.put(TXT_IDENTITY_VALUE, TXT_IDENTITY_VALUE_VALUE);
        headersMap.put(TXT_TOKEN_ID, TXT_TOKEN_ID_VALUE);
        headersMap.put(TXT_X_REQUEST_ID, TXT_X_REQUEST_ID_VALUE);
    }

    public void setPayloadWithValidDeviceId() throws IOException, ParseException {
        body = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        jsonObject.put("DeviceId", getAPIDetails("deviceUdid"));
        FileWriter writer = new FileWriter(body, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void setPayloadWithValidInitiatedSerno() throws IOException, ParseException {
        body = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        jsonObject.put("InitiatedSerno", getAPIDetails("InitiatedSerno"));
        FileWriter writer = new FileWriter(body, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void setPayloadWithValidInitiatedKey() throws IOException, ParseException {
        body = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        jsonObject.put("InitiatedKey", getAPIDetails("InitiatedKey"));
        FileWriter writer = new FileWriter(body, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void setPayloadWithValidChainSerno() throws IOException, ParseException {
        body = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        jsonObject.put("ChainSerno", getAPIDetails("ChainSerno"));
        FileWriter writer = new FileWriter(body, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void setPayloadWithValidChainAuth() throws IOException, ParseException {
        body = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        jsonObject.put("ChainAuth", getAPIDetails("ChainAuth"));
        FileWriter writer = new FileWriter(body, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }


    public void setPayloadWithValidTimeStamp() throws IOException, ParseException { //To be continued
        body = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        jsonObject.put("RequestTime", getTimeStamp());
        FileWriter writer = new FileWriter(body, false);
        writer.write(jsonObject.toString());
        writer.close();
    }


    public void invokeCardCountSummeryApi() {
        setHeaders();
        RestAssured.useRelaxedHTTPSValidation();
        response = RestAssured.given()
                .baseUri(baseHost)
                .headers(headersMap)
                .basePath(CARD_ACCOUNT_PATH)
                .body(body)
                .when()
                .log()
                .all()
                .post();
        System.out.println("API Response :CardCountSummery " + response.prettyPrint());

    }

    //Negative scenarios
    public void setPayloadWithInvalid_InitiatedSerno() throws IOException, ParseException {
        body = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        jsonObject.put("InitiatedSerno", CARD_COUNT_SUMMERY_API_WITH_INVALID_INITIATEDSERNO);
        FileWriter writer = new FileWriter(body, false);
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void setPayloadWithInvalid_InitiatedKey() throws IOException, ParseException {
        body = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        jsonObject.put("InitiatedKey", CARD_COUNT_SUMMERY_API_WITH_INVALID_INITIATEDKEY);
        FileWriter writer = new FileWriter(body, false);
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void setPayloadWithInvalid_ChainSerno() throws IOException, ParseException {
        body = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        jsonObject.put("ChainSerno", CARD_COUNT_SUMMERY_API_WITH_INVALID_CHAINSERNO);
        FileWriter writer = new FileWriter(body, false);
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void setPayloadWithInvalid_ChainAuth() throws IOException, ParseException {
        body = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        jsonObject.put("ChainAuth", CARD_COUNT_SUMMERY_API_WITH_INVALID_CHAINAUTH);
        FileWriter writer = new FileWriter(body, false);
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void setPayloadWithInvalid_DeviceId() throws IOException, ParseException {
        body = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        jsonObject.put("DeviceId", CARD_COUNT_SUMMERY_API_WITH_INVALID_DEVICE_ID);
        FileWriter writer = new FileWriter(body, false);
        writer.write(jsonObject.toString());
        writer.close();
    }


    public void validateResponseCode(int responseCode) {
        response.then().assertThat().statusCode(responseCode);
    }

    public void validatePayload() {
        new PayloadValidator().validateJsonFileWithResponse(JSON_PATH, response);
    }

    public void validatePayloadForIncorrectInitiatedSerno () {
        new PayloadValidator().validateJsonFileWithExcludedDataFields(JSON_PATH_INCORRECT_INITIATED_SERNO, response, new String[]{"RequestID"});
    }
    public void validatePayloadForIncorrectInitiatedKey () {
        new PayloadValidator().validateJsonFileWithExcludedDataFields(JSON_PATH_INCORRECT_INITIATED_KEY, response, new String[]{"RequestID"});
    }

    public void validatePayloadForIncorrectChainSerNo () {
        new PayloadValidator().validateJsonFileWithExcludedDataFields(JSON_PATH_INCORRECT_CHAINSERNO, response, new String[]{"RequestID"});
    }

    public void validatePayloadForIncorrectChainAuth () {
        new PayloadValidator().validateJsonFileWithExcludedDataFields(JSON_PATH_INCORRECT_CHAINAUTH, response, new String[]{"RequestID"});
    }

    public void validatePayloadForIncorrectDeviceId () {
        new PayloadValidator().validateJsonFileWithExcludedDataFields(JSON_PATH_INCORRECT_DEVICEID, response, new String[]{"RequestID"});
    }
}
