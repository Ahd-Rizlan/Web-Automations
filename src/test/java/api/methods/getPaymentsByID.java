package api.methods;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static api.utils.ConstantApiUtils.*;
import static utils.CommonUtils.USER_DIR;

public class getPaymentsByID extends baseMethod {
    private static final String paymentId = "paymentId";
    private static final Map<String, String> headersMap = new HashMap<>();
    private static final String baseHost = config.getProperty("sitSampathHost");
    private static Response response;
    private String paymentById;
    private static final String JSON_PATH = USER_DIR + CASA_GET_PAYMENTS_BY_ID_RESPONSE;

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

    public void setPaymentId(String paymentById) {
        this.paymentById = paymentById;
    }

    public void invokeGetPaymentsByIDApi() {
        setHeaders();
        response = RestAssured.given()
                .relaxedHTTPSValidation()
                .baseUri(baseHost)
                .headers(headersMap)
                .basePath(GET_PAYMENTS_BY_ID_PATH)
                .queryParam(paymentId, paymentById)
                .when()
                .log()
                .all()
                .get();
        System.out.println("API Response (getPaymentsByID) : " + response.prettyPrint());
        printResponseLogInReport(response);
    }

    public void validateResponseCode(int responseCode) {
        response.then().assertThat().statusCode(responseCode);
    }

    public void validatePayload() {
        new PayloadValidator().validateJsonFileWithResponse(JSON_PATH, response);
    }


}
