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

public class deleteBeneficiaries extends baseMethod {
    File jsonBody = new File(POST_BODY);
    private static final Map<String, String> headersMap = new HashMap<>();
    private static final String baseHost = config.getProperty("sitSampathHost");
    private static Response response;

    private FileReader file;
    JSONObject jsonObject;
    JSONParser jsonParser = new JSONParser();
    private static final String JSON_PATH = USER_DIR + GET_DELETE_BENEFICIARIES_RESPONSE;
    private static final String POST_BODY = USER_DIR + GET_DELETE_BENEFICIARIES_BODY;


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

    public void setPayloadWithValidBeneId() throws IOException, ParseException {
        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("deleteBeneficiariesBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("deleteBeneficiariesBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        JSONObject deleteBeneficiaries = (JSONObject) jsonObject.get("deleteBeneficiaries");
        deleteBeneficiaries.put("beneficiariID",  getAPIDetails("beneficiaryID"));
        FileWriter writer = new FileWriter(jsonBody, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }


    public void setResponceWithValidBeneId() throws IOException, ParseException {
        jsonBody = new File(JSON_PATH);
        file = new FileReader(JSON_PATH);
        jsonObject = (JSONObject) jsonParser.parse(file);

        JSONObject deleteBeneficiariesResponse = (JSONObject) jsonObject.get("deleteBeneficiariesResponse");
        JSONObject responseHeader = (JSONObject) deleteBeneficiariesResponse.get("responseHeader");
        responseHeader.put("statusDescription", getAPIDetails("beneficiaryID") + " record deleted.");
        FileWriter writer = new FileWriter(jsonBody, false);
        writer.write(jsonObject.toString());
        writer.close();
    }
    public void invokeDeleteBeneficiariesApi() {
        setHeaders();
        RestAssured.useRelaxedHTTPSValidation();
        response = RestAssured.given()
                .baseUri(baseHost)
                .headers(headersMap)
                .basePath(GET_DELETE_BENEFICIARIES_PATH)
                .body(jsonBody)
                .when()
                .log()
                .all()
                .post();
        System.out.println("API Response (deleteBeneficiaries)" + response.prettyPrint());
        System.out.println(getAPIDetails("beneficiaryID"));
        printResponseLogInReport(response);
    }

    public void validateResponseCode(int responseCode) {
        response.then().assertThat().statusCode(responseCode);
    }

    public void validatePayload() {
        new PayloadValidator().validateJsonFileWithResponse(JSON_PATH, response);
    }
}
