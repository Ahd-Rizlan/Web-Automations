package api.utils;

import api.methods.baseMethod;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static api.utils.ConstantApiUtils.*;
import static utils.CommonUtils.USER_DIR;

public class baseRequest {
    private final api.methods.baseMethod baseMethod = new baseMethod();
    public  Map<String, String> headersMap = new HashMap<>();

    private  Response response;
    private  String POST_BODY;

    public  File jsonBody;
    private String basePath;



    public  void setHeaders() {
        headersMap.put(TXT_CONTENT_TYPE, TXT_APPLICATION_JSON);
        headersMap.put(TXT_X_REQUEST_ID, TXT_X_REQUEST_ID_VALUE_HUNDRED_AND_TWENTY_THREE);
    }

    public  void setHeaders(String X_REQUEST_ID_VALUE) {
        headersMap.put(TXT_CONTENT_TYPE, TXT_APPLICATION_JSON);
        headersMap.put(TXT_X_REQUEST_ID, X_REQUEST_ID_VALUE);
    }

    public  void setHeaders(String CONTENT_TYPE, String X_REQUEST_ID_VALUE) {
        headersMap.put(TXT_CONTENT_TYPE, CONTENT_TYPE);
        headersMap.put(TXT_X_REQUEST_ID, X_REQUEST_ID_VALUE);
    }

    public  void setAuthorization(String AUTHORIZATION) {
        headersMap.put(TXT_AUTHORIZATION, AUTHORIZATION);
    }
    public void validateResponseCode(int responseCode) {
        response.then().assertThat().statusCode(responseCode);
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String setPOST_BODY(String postBody) {
        POST_BODY = USER_DIR + postBody;
        return POST_BODY;
    }

    public String getPOST_BODY() {
        return POST_BODY;
    }

    public Response getResponse() {
        return response;
    }

    public void invokePostRequest() {
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
        System.out.println("API Response : " + response.prettyPrint());
        baseMethod.printResponseLogInReport(response);
    }
    public void invokeGetRequest() {
        setHeaders();
        RestAssured.useRelaxedHTTPSValidation();
        response = RestAssured.given()
                .baseUri(baseMethod.getBaseHost())
                .headers(headersMap)
                .basePath(basePath)
                .when()
                .log()
                .all()
                .post();
        System.out.println("API Response : " + response.prettyPrint());
        baseMethod.printResponseLogInReport(response);
    }
    public void invokeDeleteRequest() {
        setHeaders();
        RestAssured.useRelaxedHTTPSValidation();
        response = RestAssured.given()
                .baseUri(baseMethod.getBaseHost())
                .headers(headersMap)
                .basePath(basePath)
                .when()
                .log()
                .all()
                .post();
        System.out.println("API Response : " + response.prettyPrint());
        baseMethod.printResponseLogInReport(response);
    }
}
