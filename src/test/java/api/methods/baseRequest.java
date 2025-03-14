package api.methods;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static api.utils.ConstantApiUtils.*;
import static utils.CommonUtils.USER_DIR;

public class baseRequest {
    private static baseMethod baseMethod = new baseMethod();
    public static Map<String, String> headersMap = new HashMap<>();

    public static Response response;
    public static String POST_BODY;
    public static File jsonBody;

    public static void setHeaders() {
        headersMap.put(TXT_CONTENT_TYPE, TXT_APPLICATION_JSON);
        headersMap.put(TXT_X_REQUEST_ID, TXT_X_REQUEST_ID_VALUE_HUNDRED_AND_TWENTY_THREE);
    }

    public static void setHeaders(String X_REQUEST_ID_VALUE) {
        headersMap.put(TXT_CONTENT_TYPE, TXT_APPLICATION_JSON);
        headersMap.put(TXT_X_REQUEST_ID, X_REQUEST_ID_VALUE);
    }

    public static void setHeaders(String CONTENT_TYPE, String X_REQUEST_ID_VALUE) {
        headersMap.put(TXT_CONTENT_TYPE, CONTENT_TYPE);
        headersMap.put(TXT_X_REQUEST_ID, X_REQUEST_ID_VALUE);
    }

    public static void setAUTHORIZATION(String AUTHORIZATION) {
        headersMap.put(TXT_AUTHORIZATION, AUTHORIZATION);
    }

    public String getPOST_BODY() {
        return POST_BODY;
    }

    public void setPOST_BODY(String postBody) {
        POST_BODY = USER_DIR + postBody;
        jsonBody = new File(POST_BODY);
    }

    public static void invokePostRequest(String basePath) {
        setHeaders();
        RestAssured.useRelaxedHTTPSValidation();
        response = RestAssured.given()
                .baseUri(baseMethod.getBaseHost())
                .headers(headersMap)
                .basePath(basePath)
                .body(jsonBody)
                .when()
                .log()
                .all()
                .post();
        System.out.println("API Response (beneficiariesByTranType) : " + response.prettyPrint());
        baseMethod.printResponseLogInReport(response);
    }

}
