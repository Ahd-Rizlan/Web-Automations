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

public class favouriteBillers extends baseMethod {
    private static final String pageNo = "pageNo";
    private static final String pageLimit = "pageLimit";
    private FileReader file;
    JSONObject jsonObject;
    JSONParser jsonParser = new JSONParser();

    File jsonBody = new File(POST_BODY);
    private static final String POST_BODY = USER_DIR + FAV_BILLERS_BODY;

    private static final Map<String, String> headersMap = new HashMap<>();
    private static final String baseHost = config.getProperty("sitSampathHost");
    private static Response response;
    private String pageNoValue;
    private String pageLimitValue;
    private static final String JSON_PATH = USER_DIR + GET_FAVOURITE_BILLERS_RESPONSE;
    private static final String JSON_PATH_WITH_INVALID_InvalidMerchantCode = USER_DIR + GET_FAVOURITE_BILLERS_WITH_INVALID_MERCHANTCODE_RESPONSE;
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

    public void setPageNo(String pageNo) {
        this.pageNoValue = pageNo;
    }

    public void setPageLimit(String pageLimit) {
        this.pageLimitValue = pageLimit;
    }
    public void setPayloadWithInvalidMerchantCode() throws IOException, ParseException {
        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("favBillersBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("favBillersBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        JSONObject favouriteBillers = (JSONObject) jsonObject.get("favouriteBillers");
        favouriteBillers.put("merchantCode", INVALID_MERCHANT_CODE);
        FileWriter writer = new FileWriter(jsonBody, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void setPayloadWithValidMerchantCode() throws IOException, ParseException {
        jsonBody = new File(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("favBillersBody.json"));
        file = new FileReader(ConstantApiUtils.PATH_TO_PAYLOAD_FOLDER.concat("favBillersBody.json"));
        jsonObject = (JSONObject) jsonParser.parse(file);
        JSONObject favouriteBillers = (JSONObject) jsonObject.get("favouriteBillers");
        favouriteBillers.put("merchantCode", VALID_MERCHANT_CODE);
        FileWriter writer = new FileWriter(jsonBody, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void invokeFavouriteBillersApiPost() {
        setHeaders();
        response = RestAssured.given()
                .relaxedHTTPSValidation()
                .baseUri(baseHost)
                .headers(headersMap)
                .basePath(GET_FAVOURITE_BILLERS_PATH)
                .body(jsonBody)
                .queryParam(pageLimit, pageLimitValue)
                .queryParam(pageNo, pageNoValue)
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

    public void validatePayloadWithInvalidInvalidMerchantCode() {
        new PayloadValidator().validateJsonFileWithResponse(JSON_PATH_WITH_INVALID_InvalidMerchantCode, response);
    }
}
