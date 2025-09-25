package api.methods;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import static api.utils.ConstantApiUtils.*;
import static utils.CommonUtils.USER_DIR;

public class getCustAccountDetails extends baseMethod {
    //File jsonBody = new File(POST_BODY);
    private static final Map<String, String> headersMap = new HashMap<>();
    private static final String baseHost = config.getProperty("sitSampathHost");
    private static Response response;

    private static final String JSON_PATH = USER_DIR + GET_CUST_ACCOUNT_DETAILS_RESPONSE;

    public void authorisedWithInvalidToken() {
        headersMap.put(TXT_AUTHORIZATION, TXT_AUTHORIZATION_INVALID_VAL);
    }

    public void authorisedWithValidToken() {
        headersMap.put(TXT_AUTHORIZATION, config.getProperty("accessToken"));
    }

    public void setHeaders() {
        headersMap.put(TXT_CONTENT_TYPE, TXT_APPLICATION_JSON);
        headersMap.put(TXT_X_REQUEST_ID, TXT_X_REQUEST_ID_VALUE);
        headersMap.put(TXT_SERVICE_NAME, TXT_SERVICE_NAME_VALUE_DASHBOARD_WEB_CARD);
        headersMap.put(TXT_IDENTITY_TYPE, TXT_IDENTITY_TYPE_VALUE);
        headersMap.put(TXT_IDENTITY_VALUE, TXT_IDENTITY_VALUE_VALUE);
        headersMap.put(TXT_TOKEN_ID, TXT_TOKEN_ID_VALUE);
    }

    public void invokeGetCustAccountDetailsApi() {
        setHeaders();
        RestAssured.useRelaxedHTTPSValidation();
        response = RestAssured.given()
                .baseUri(baseHost)
                .headers(headersMap)
                .basePath(GET_CUST_ACCOUNT_DETAILS_PATH)
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
        new PayloadValidator().validateJsonFileWithResponse(JSON_PATH, response);
    }
}
