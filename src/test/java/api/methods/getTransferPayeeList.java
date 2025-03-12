package api.methods;

import api.utils.ConstantApiUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.restassured.path.json.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import static utils.DataStoreReadWriteApi.storeAPIDetails;


import static api.utils.ConstantApiUtils.*;
import static utils.CommonUtils.USER_DIR;

public class getTransferPayeeList extends baseMethod {
    File jsonBody = new File(POST_BODY);
    private static final Map<String, String> headersMap = new HashMap<>();
    private static final String baseHost = config.getProperty("sitSampathHost");
    private static Response response;
    private static final String JSON_PATH = USER_DIR + GET_TRANSFER_PAYEE_lIST_RESPONSE;
    private static final String POST_BODY = USER_DIR + GET_TRANSFER_PAYEE_lIST_BODY;
    private FileReader file;
    JSONObject jsonObject;
    JSONParser jsonParser = new JSONParser();
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

    public void setPayloadWithValidData() throws IOException, ParseException {
        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("getTransferPayeeListBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("getTransferPayeeListBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        JSONObject getTransferPayeeList = (JSONObject) jsonObject.get("getTransferPayeeList");
        getTransferPayeeList.put("pageLimit", PAGE_LIMIT_SIX_HUNDRED);
        getTransferPayeeList.put("pageNo", PAGE_NO_ONE);

        FileWriter writer = new FileWriter(jsonBody, false);
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void invokeGetTransferPayeeListApi() {
        setHeaders();
        RestAssured.useRelaxedHTTPSValidation();
        response = RestAssured.given()
                .baseUri(baseHost)
                .headers(headersMap)
                .basePath(GET_TRANSFER_PAYEE_LIST_PATH)
                .body(jsonBody)
                .when()
                .log()
                .all()
                .post();
        System.out.println("API Response" + response.prettyPrint());
        printResponseLogInReport(response);
    }

    public void validateResponseCode(int responseCode) {
        response.then().assertThat().statusCode(responseCode);
    }

    public void validatePayload() {
        new PayloadValidator().validateJsonFileWithExcludedDataFields(JSON_PATH,response,new String[]{"recordCount"});
    }

    public void extractBeneficiaryIDForAccount(String accountName) {
        // Convert the response to a JSON path object
        JsonPath jsonPath = response.jsonPath();

        // Extract the list of beneficiaries
        List<Map<String, Object>> beneficiaryList = jsonPath.getList("getTransferPayeeListResponse.beneficiaryListDetails.beneficiaryList");

        // Iterate through the list and find the 'beneficiaryID' for the specific 'accountName'
        for (Map<String, Object> beneficiary : beneficiaryList) {
            String currentAccountName = (String) beneficiary.get("accountName");
            if (accountName.equals(currentAccountName)) {
                Integer beneficiaryID = (Integer) beneficiary.get("beneficiaryID");
                System.out.println("Found beneficiaryID: " + beneficiaryID + " for accountName: " + accountName );
                storeAPIDetails("beneficiaryID", beneficiaryID.toString());  // Store the accountName value
                break;
            }
        }
    }

}


