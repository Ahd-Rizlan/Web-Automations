package api.test;

import api.methods.baseMethod;
import api.methods.cardCountSummeryApiMethods;
import api.methods.initiateCardsForSVR;
import api.utils.ConstantApiUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class api_GetCardCountSummaryforSVRTest extends baseMethod {
    File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
    public api.methods.cardCountSummeryApiMethods cardcountSummeryApiMethods;
    public api.methods.initiateCardsForSVR initiateCardsForSVR;

    @BeforeClass()
    public void setUp() {
        cardcountSummeryApiMethods = new cardCountSummeryApiMethods();
        initiateCardsForSVR = new initiateCardsForSVR();
    }
    @BeforeMethod // Before every test the correct data setup is prepared
    public void setPayloadWithUpToDateValidData(Method m) throws IOException, ParseException {
        setTestName(m.getName());

        // execute pre-request api : initiateCardsforSVR
        initiateCardsForSVR.authorisedWithValidToken();
        initiateCardsForSVR.setPayloadWithValidDeviceId();
        initiateCardsForSVR.setPayloadWithValidTimeStamp();
        initiateCardsForSVR.invokeInitiateCardsForSVRApi();
        initiateCardsForSVR.saveImportantDataToFile();

        //Execute endpoint : CardCountSummeryApi with the data retrieved from initiateCardsforSVR
        cardcountSummeryApiMethods.authorisedWithValidToken();
        cardcountSummeryApiMethods.setPayloadWithValidTimeStamp();
        cardcountSummeryApiMethods.setPayloadWithValidInitiatedSerno();
        cardcountSummeryApiMethods.setPayloadWithValidInitiatedKey();
        cardcountSummeryApiMethods.setPayloadWithValidChainSerno();
        cardcountSummeryApiMethods.setPayloadWithValidChainAuth();
        cardcountSummeryApiMethods.setPayloadWithValidDeviceId();
    }

    //Negative cases
    @Test(priority = 1,testName = "Verify that Card Count Summary Cannot be Retrieved with Unauthorized Access")
    //For unauthorized access
    public void checkCardCountSummeryApiUnauthorizedAccess() throws IOException, ParseException {
        cardcountSummeryApiMethods.authorisedWithInvalidToken();
        cardcountSummeryApiMethods.setPayloadWithValidDeviceId();
        cardcountSummeryApiMethods.invokeCardCountSummeryApi();
        cardcountSummeryApiMethods.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);
    }

    @Test(priority = 2,testName = "Verify that Card Count Summary Cannot be Retrieved with Invalid InitiatedSerNo")
    //For invalid InitiatedSerno
    public void checkCardCountSummeryApiWithInvalidInitiatedSerno() throws IOException, ParseException {
        cardcountSummeryApiMethods.setPayloadWithInvalid_InitiatedSerno();
        cardcountSummeryApiMethods.invokeCardCountSummeryApi();
        cardcountSummeryApiMethods.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        cardcountSummeryApiMethods.validatePayloadForIncorrectInitiatedSerno();
    }

    @Test(priority = 2,testName = "Verify that Card Count Summary Cannot be Retrieved with Invalid InitiateKey")
    //For invalid InitiatedKey| bug reported [SVR4-458]
    public void checkCardCountSummeryApiWithInvalidInitiatedKey() throws IOException, ParseException {
        cardcountSummeryApiMethods.setPayloadWithInvalid_InitiatedKey();
        cardcountSummeryApiMethods.invokeCardCountSummeryApi();
        cardcountSummeryApiMethods.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        cardcountSummeryApiMethods.validatePayloadForIncorrectInitiatedKey();
    }

    @Test(priority = 2,testName = "Verify that Card Count Summary Cannot be Retrieved with Invalid ChainSerNo")
    //For invalid ChainSerNo | bug reported [SVR4-458]
    public void checkCardCountSummeryApiWithInvalidChainSerNo() throws IOException, ParseException {
        cardcountSummeryApiMethods.setPayloadWithInvalid_ChainSerno();
        cardcountSummeryApiMethods.invokeCardCountSummeryApi();
        cardcountSummeryApiMethods.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        cardcountSummeryApiMethods.validatePayloadForIncorrectChainSerNo();
    }

    @Test(priority = 2,testName = "Verify that Card Count Summary Cannot be Retrieved with Invalid Chain Authorization Key")
    //For invalid ChainAuth
    public void checkCardCountSummeryApiWithInvalidChainAuth() throws IOException, ParseException {
        cardcountSummeryApiMethods.setPayloadWithInvalid_ChainAuth();
        cardcountSummeryApiMethods.invokeCardCountSummeryApi();
        cardcountSummeryApiMethods.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        cardcountSummeryApiMethods.validatePayloadForIncorrectChainAuth();
    }

    @Test(priority = 2,testName = "Verify that Card Count Summary Cannot be Retrieved with Invalid Device Id")
    //For invalid DeviceId
    public void checkCardCountSummeryApiWithInvalidDeviceId() throws IOException, ParseException {
        cardcountSummeryApiMethods.setPayloadWithInvalid_DeviceId();
        cardcountSummeryApiMethods.invokeCardCountSummeryApi();
        cardcountSummeryApiMethods.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        cardcountSummeryApiMethods.validatePayloadForIncorrectDeviceId();
    }

    @Test(priority = 3,testName = "Verify that Card Count Summary Is Retrieved with Valid Authorized Access")
    //For happy path
    public void checkCardCountSummeryApiAuthorizedAccess() {
        cardcountSummeryApiMethods.invokeCardCountSummeryApi();
        cardcountSummeryApiMethods.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
        //cardcountSummeryApiMethods.validatePayload();  Unable to validate the response as all field values are dynamic
    }

}
