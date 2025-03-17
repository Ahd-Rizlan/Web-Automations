package api.methods;

import api.utils.ConstantApiUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.utils.ConstantApiUtils.*;
import static utils.CommonUtils.USER_DIR;
import static utils.DataStoreReadWriteApi.getAPIDetails;

public class deletePaymentTemplate extends baseMethod {
    File jsonBody = new File(POST_BODY);
    private static final Map<String, String> headersMap = new HashMap<>();
    private static final String baseHost = config.getProperty("sitSampathHost");
    private static Response response;
    private FileReader file;
    JSONObject jsonObject;
    JSONParser jsonParser = new JSONParser();
    private static final String JSON_PATH = USER_DIR + DELETE_PAYMENT_TEMPLATE_RESPONSE;
    private static final String POST_BODY = USER_DIR + DELETE_PAYMENT_TEMPLATE_BODY;

    // Initial data structure
    Map<String, Object> jsonMap = new HashMap<>();
    Map<String, Object> deletePaymentTemplate = new HashMap<>();

    // TemplateId array
    List<Map<String, String>> templateIds = new ArrayList<>();
    Map<String, String> templateId = new HashMap<>();

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

    public void invokeDeletePaymentTemplate() {
        setHeaders();
        RestAssured.useRelaxedHTTPSValidation();
        response = RestAssured.given()
                .baseUri(baseHost)
                .headers(headersMap)
                .basePath(GET_DELETE_PAYMENT_TEMPLATE_PATH)
                .body(jsonBody)
                .when()
                .log()
                .all()
                .post();
        System.out.println("API Response (deletePaymentTemplate)" + response.prettyPrint());
        printResponseLogInReport(response);
    }

    public void setPayloadWithValidTemplateID() throws IOException, ParseException {
        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("deletePaymentTemplateBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("deletePaymentTemplateBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);

        //------------------------customized----------------------------------
        templateId.put("templateID", getAPIDetails("templateID"));
        templateIds.add(templateId);

        deletePaymentTemplate.put("templateIds", templateIds);
        jsonMap.put("deletePaymentTemplate", deletePaymentTemplate);
        updateTemplateID(jsonMap, getAPIDetails("templateID"));
        try (FileWriter writer = new FileWriter(jsonBody, false)) { // false means overwrite the content

            JSONObject updatedJson = new JSONObject(jsonMap);
            writer.write(updatedJson.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //----------------------------------------------------------------------
    }

    public void validateResponseCode(int responseCode) {
        response.then().assertThat().statusCode(responseCode);
    }

    public void validatePayload() {
        new PayloadValidator().validateJsonFileWithExcludedDataFields(JSON_PATH,response,new String[]{"statusDescription"} );
    }

    public  void updateTemplateID(Map<String, Object> jsonMap, String newTemplateID) {
        Map<String, Object> deletePaymentTemplate = (Map<String, Object>) jsonMap.get("deletePaymentTemplate");
        List<Map<String, String>> templateIds = (List<Map<String, String>>) deletePaymentTemplate.get("templateIds");
        if (!templateIds.isEmpty()) {
            Map<String, String> firstTemplate = templateIds.get(0);
            firstTemplate.put("templateID", newTemplateID);
        }
    }
}
