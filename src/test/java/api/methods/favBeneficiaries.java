//package api.methods;
//
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//
//import java.io.File;
//import java.io.FileReader;
//import java.util.HashMap;
//import java.util.Map;
//
//import static api.utils.ConstantApiUtils.*;
//import static utils.CommonUtils.USER_DIR;
//
//public class favBeneficiaries extends baseMethod {
//    File jsonBody = new File(POST_BODY);
//
//    File jsonBodyWithEmptyBene = new File(POST_BODY_WITH_EMPTY_BENE);
//    private static final Map<String, String> headersMap = new HashMap<>();
//    private static final String baseHost = config.getProperty("sitSampathHost");
//    private static Response response;
//    private FileReader file;
//    JSONObject jsonObject;
//    JSONParser jsonParser = new JSONParser();
//    private static final String JSON_PATH = USER_DIR + FAV_BENEFICIARIES_RESPONSE;
//    private static final String JSON_PATH_FOR_EMPTY_BENE = USER_DIR + FAV_BENEFICIARIES_RESPONSE_FOR_EMPTY_BENE;
//
//    private static final String POST_BODY = USER_DIR + FAV_BENEFICIARIES_BODY;
//    private static final String POST_BODY_WITH_EMPTY_BENE= USER_DIR + EMPTY_FAV_BENEFICIARIES_BODY;
//
//    public void authorisedWithInvalidToken() {
//        headersMap.put(TXT_AUTHORIZATION, TXT_AUTHORIZATION_INVALID_VAL);
//    }
//
//    public void authorisedWithValidToken() {
//        headersMap.put(TXT_AUTHORIZATION, config.getProperty("accessToken"));
//    }
//
//    public void setHeaders() {
//        headersMap.put(TXT_CONTENT_TYPE, TXT_APPLICATION_JSON);
//        headersMap.put(TXT_X_REQUEST_ID, TXT_X_REQUEST_ID_VALUE_HUNDRED_AND_TWENTY_THREE);
//    }
//
//
//    public void invokeFavBeneficiaries() {
//        setHeaders();
//        RestAssured.useRelaxedHTTPSValidation();
//        response = RestAssured.given()
//                .baseUri(baseHost)
//                .headers(headersMap)
//                .basePath(GET_FAV_BENEFICIARIES_PATH)
//                .body(jsonBody)
//                .when()
//                .log()
//                .all()
//                .post();
//        System.out.println("API Response" + response.prettyPrint());
//        printResponseLogInReport(response);
//    }
//    public void invokeFavBeneficiariesWithEmptyBene() {
//        setHeaders();
//        RestAssured.useRelaxedHTTPSValidation();
//        response = RestAssured.given()
//                .baseUri(baseHost)
//                .headers(headersMap)
//                .basePath(GET_FAV_BENEFICIARIES_PATH)
//                .body(jsonBodyWithEmptyBene)
//                .when()
//                .log()
//                .all()
//                .post();
//        System.out.println("API Response" + response.prettyPrint());
//        printResponseLogInReport(response);
//    }
//    public void validateResponseCode(int responseCode) {
//        response.then().assertThat().statusCode(responseCode);
//    }
//
//    public void validatePayload() {
//        new PayloadValidator().validateJsonFileWithResponse(JSON_PATH, response);
//    }
//    public void validatePayloadWithFavBene() {
//        new PayloadValidator().validateJsonFileWithResponse(JSON_PATH_FOR_EMPTY_BENE, response);
//    }
//}
