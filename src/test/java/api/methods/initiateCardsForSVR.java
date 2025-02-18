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
import static api.utils.ConstantApiUtils.TXT_X_REQUEST_ID_VALUE_ELEVEN;
import static utils.CommonUtils.USER_DIR;
import static utils.DataStoreReadWriteApi.storeAPIDetails;

public class initiateCardsForSVR extends baseMethod {

    private static final Map<String, String> headersMap = new HashMap<>();
    private static final String baseHost = config.getProperty("sitSampathHost");
    private static Response response;
    private FileReader file;
    private File body;
    JSONObject jsonObject;
    JSONParser jsonParser = new JSONParser();
    private static final String JSON_PATH = USER_DIR + GET_INITIATE_CARDS_FOR_SVR_RESPONSE;

    private static final String JSON_PATH_FOR_INVALID_TIME_STAMP_RESPONSE = USER_DIR + GET_INITIATE_CARDS_FOR_INVALID_TIMESTAMP_RESPONSE;

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

    public void setPayloadWithValidDeviceId() throws IOException, ParseException {
        body = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        jsonObject.put("DeviceId",INITIATE_CARDS_FOR_SVR_WITH_VALID_DEVICE_ID);
        FileWriter writer = new FileWriter(body, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void setPayloadWithInvalidDeviceId() throws IOException, ParseException {
        body = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        jsonObject.put("DeviceId", INITIATE_CARDS_FOR_SVR_WITH_INVALID_DEVICE_ID);
        FileWriter writer = new FileWriter(body, false);
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void setPayloadWithValidTimeStamp() throws IOException, ParseException {
        body = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        jsonObject.put("RequestTime", getTimeStamp());
        FileWriter writer = new FileWriter(body, false);
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void setPayloadWithInvalidTimeStamp() throws IOException, ParseException {
        body = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("svrCardBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        jsonObject.put("RequestTime", INITIATE_CARDS_FOR_SVR_WITH_INVALID_TIMESTAMP);
        FileWriter writer = new FileWriter(body, false);
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void invokeInitiateCardsForSVRApi() {
        setHeaders();
        RestAssured.useRelaxedHTTPSValidation();
        response = RestAssured.given()
                .baseUri(baseHost)
                .headers(headersMap)
                .basePath(GET_INITIATE_CARDS_FOR_SVR_PATH)
                .body(body)
                .when()
                .log()
                .all()
                .post();
        System.out.println("API Response : InitiateCardsForSVR" + response.prettyPrint());
        printResponseLogInReport(response);
    }

    public void validateResponseCode(int responseCode) {
        response.then().assertThat().statusCode(responseCode);
    }


    public void validatePayload() {
        new PayloadValidator().validateJsonFileWithResponse(JSON_PATH, response);
    }

    public void validatePayloadForInvalidTimeStamp() {
        new PayloadValidator().validateJsonFileWithExcludedDataFields(JSON_PATH_FOR_INVALID_TIME_STAMP_RESPONSE, response, new String[]{"RequestID", "ChainSerno"});
    }

    public void saveImportantDataToFile() {
        storeAPIDetails("InitiatedSerno", response.path("InitiatedSerno").toString());
        storeAPIDetails("InitiatedKey", response.path("InitiatedKey").toString());
        storeAPIDetails("ChainSerno", response.path("ChainSerno").toString());
        storeAPIDetails("ChainAuth", response.path("ChainAuth"));
    }
}
