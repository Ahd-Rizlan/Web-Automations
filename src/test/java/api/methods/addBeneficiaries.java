package api.methods;

import api.utils.baseRequest;
import api.utils.validatePayload;
import api.utils.validateResponse;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static api.utils.ConstantApiUtils.*;
import static utils.CommonUtils.USER_DIR;

public class addBeneficiaries extends baseMethod {

    private final String BASE_PATH = GET_ADD_BENEFICIARIES_PATH;
    private final baseRequest baseRequest = new baseRequest(BASE_PATH );
    private final validatePayload validatePayload = new validatePayload(baseRequest);
    private final validateResponse validateResponse = new validateResponse(baseRequest);
    private final String PATH_TO_PAYLOAD = GET_ADD_BENEFICIARIES_BODY;
    private static final String JSON_PATH = USER_DIR + GET_ADD_BENEFICIARIES_RESPONSE;


    //-----------------------------------------------  Token SETUP  -----------------------------------------------

    public void authorisedWithValidToken() {
        validatePayload.authorisedWithValidToken();
    }
    public void authorisedWithInValidToken() {
        validatePayload.authorisedWithInValidToken();
    }

    //-----------------------------------------------  SETUP PAYLOAD  -----------------------------------------------


    public void setPayloadWithInvalidAccountType() throws IOException, ParseException {
        validatePayload.setPayloadWithValidData(PATH_TO_PAYLOAD,"addBeneficiaries","accountType", INVALID_ACCOUNT_TYPE);
    }

    public void setPayloadWithValidData() throws IOException, ParseException {
        validatePayload.setPayloadWithValidData(PATH_TO_PAYLOAD,"addBeneficiaries","accountType", VALID_ACCOUNT_TYPE);
        validatePayload.setPayloadWithValidData(PATH_TO_PAYLOAD,"addBeneficiaries","accountName", VALID_ACCOUNT_NAME);
        validatePayload.setPayloadWithValidData(PATH_TO_PAYLOAD,"addBeneficiaries","accountNumber", CORRECT_ACCOUNT_NUMBER_VALUE);
        validatePayload.setPayloadWithValidData(PATH_TO_PAYLOAD,"addBeneficiaries","bank", CORRECT_BANK_CODE_VALUE);

    }

    //-----------------------------------------------  INVOKE METHODS  -----------------------------------------------

    public void invokeAddBeneficiariesApi() {
        baseRequest.invokePostRequest();
    }

    //-----------------------------------------------  RESPONSE CODE VALIDATIONS  -----------------------------------------------
    public void validateResponseCode(int responseCode) {
    baseRequest.validateResponseCode(responseCode);
    }

    //-----------------------------------------------  RESPONSE VALIDATIONS  -----------------------------------------------

    public void validateResponsePayload() {
        validateResponse.validateResponse(JSON_PATH);
    }

}
