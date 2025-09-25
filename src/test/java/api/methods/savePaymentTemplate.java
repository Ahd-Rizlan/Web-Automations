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
import static utils.DataStoreReadWriteApi.storeAPIDetails;

public class savePaymentTemplate extends baseMethod {
    File jsonBody = new File(POST_BODY);
    private static final Map<String, String> headersMap = new HashMap<>();
    private static final String baseHost = config.getProperty("sitSampathHost");
    private static Response response;
    private FileReader file;
    JSONObject jsonObject;
    JSONParser jsonParser = new JSONParser();
    private static final String JSON_PATH = USER_DIR + GET_SAVE_PAYMENT_TEMPLATE_RESPONSE;
    private static final String POST_BODY = USER_DIR + GET_SAVE_PAYMENT_TEMPLATE_BODY;

    private static final String INCORRECT_BILLER_ID = USER_DIR + GET_SAVE_PAYMENT_TEMPLATE_WITH_INCORRECT_BILLER_ID_RESPONSE;

    public void authorisedWithInvalidToken() {
        headersMap.put(TXT_AUTHORIZATION, TXT_AUTHORIZATION_INVALID_VAL);
    }

    public void authorisedWithValidToken() {
        headersMap.put(TXT_AUTHORIZATION, config.getProperty("accessToken"));
    }

    public void setHeaders() {
        headersMap.put(TXT_CONTENT_TYPE, TXT_APPLICATION_JSON);
        headersMap.put(TXT_X_REQUEST_ID, TXT_X_REQUEST_ID_VALUE_TRIPLE_ONE);
    }

    public void setPayloadWithIncorrectBillerId() throws IOException, ParseException {
        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("savePaymentTemplateBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("savePaymentTemplateBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        JSONObject addBeneficiaries = (JSONObject) jsonObject.get("savePaymentTemplate");
        addBeneficiaries.put("billerID", SAVE_PAYMENT_TEMPLATE_INVALID_BILLER_ID);
        FileWriter writer = new FileWriter(jsonBody, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void setPayloadWithValidData() throws IOException, ParseException {
        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("savePaymentTemplateBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("savePaymentTemplateBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        JSONObject addBeneficiaries = (JSONObject) jsonObject.get("savePaymentTemplate");
        addBeneficiaries.put("billerID", SAVE_PAYMENT_TEMPLATE_VALID_BILLER_ID);
        FileWriter writer = new FileWriter(jsonBody, false);
        writer.write(jsonObject.toString());
        writer.close();
    }



    public void invokeSavePaymentTemplateApi() {
        setHeaders();
        RestAssured.useRelaxedHTTPSValidation();
        response = RestAssured.given()
                .baseUri(baseHost)
                .headers(headersMap)
                .basePath(GET_SAVE_PAYMENT_TEMPLATE_PATH)
                //The API URL
                .body(jsonBody)
                //payloads//savePaymentTemplateBody.json";
                .when()
                .log()
                .all()
                .post();
        System.out.println("API Response SavePaymentTemplateApi : " + response.prettyPrint());
        printResponseLogInReport(response);
    }

    public void validateResponseCode(int responseCode) {
        response.then().assertThat().statusCode(responseCode);
    }

    public void validatePayload() {
        new PayloadValidator().validateJsonFileWithExcludedDataFields(JSON_PATH,response,new String[]{"templateID"});
    }

    public void validatePayloadForIncorrectBillerId() {
        new PayloadValidator().validateJsonFileWithResponse(INCORRECT_BILLER_ID, response);
    }

    public void saveTemplateIdToFile() {
        //goes through the API responce and update the template ID
        storeAPIDetails("templateID", response.path("savePaymentTemplateResponse.templateID").toString());
    }

}
