package api.methods;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static api.utils.ConstantApiUtils.*;
import static utils.CommonUtils.USER_DIR;
import static utils.DataStoreReadWriteApi.storeAPIDetails;

public class casaAccountApiMethods extends baseMethod {
    private static final String pageNo = "pageNo";
    private static final Map<String, String> headersMap = new HashMap<>();
    private static final String baseHost = config.getProperty("sitSampathHost");
    private static Response response;
    private String pageNoValue;
    private static final String JSON_PATH = USER_DIR + CASA_ACCOUNT_RESPONSE;

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

    public void setPageNo(String pageNo) {
        this.pageNoValue = pageNo;
    }

    public void invokeGetCASAAccountsApi() {
        setHeaders();
        response = RestAssured.given()
                .relaxedHTTPSValidation()
                .baseUri(baseHost)
                .headers(headersMap)
                .basePath(GET_CASA_ACCOUNT_PATH)
                .queryParam(pageNo, pageNoValue)
                .when()
                .log()
                .all()
                .get();
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
