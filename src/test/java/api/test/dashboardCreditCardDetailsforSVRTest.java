package api.test;

import api.methods.baseMethod;
import api.methods.dashboardCreditCardDetailsforSVR;
import api.methods.initiateCardsForSVR;
import api.utils.ConstantApiUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class dashboardCreditCardDetailsforSVRTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public api.methods.dashboardCreditCardDetailsforSVR dashboardCreditCardDetailsforSVR;
	public api.methods.initiateCardsForSVR initiateCardsForSVR;

	@BeforeClass()
	public void setUp() {
		dashboardCreditCardDetailsforSVR = new dashboardCreditCardDetailsforSVR();
		initiateCardsForSVR = new initiateCardsForSVR();
	}
	@BeforeMethod // Before every test the correct data setup is prepared
	public void setPayloadWithUpToDateValidData (Method m) throws IOException, ParseException {
		setTestName(m.getName());
		// execute pre-request api : initiateCardsforSVR
		initiateCardsForSVR.authorisedWithValidToken();
		initiateCardsForSVR.setPayloadWithValidDeviceId();
		initiateCardsForSVR.setPayloadWithValidTimeStamp();
		initiateCardsForSVR.invokeInitiateCardsForSVRApi();
		initiateCardsForSVR.saveImportantDataToFile();

		//Execute endpoint : dashboardCreditCardDetailsforSVR with the data retrieved from initiateCardsforSVR
		dashboardCreditCardDetailsforSVR.authorisedWithValidToken();
		dashboardCreditCardDetailsforSVR.setPayloadWithValidTimeStamp();
		dashboardCreditCardDetailsforSVR.setPayloadWithValidInitiatedSerno();
		dashboardCreditCardDetailsforSVR.setPayloadWithValidInitiatedKey();
		dashboardCreditCardDetailsforSVR.setPayloadWithValidChainSerno();
		dashboardCreditCardDetailsforSVR.setPayloadWithValidChainAuth();
		dashboardCreditCardDetailsforSVR.setPayloadWithValidDeviceId();
	}

	@Test(priority = 1)
	public void checkDashboardCreditCardDetailsForSVRApiUnauthorizedAccess() throws IOException, ParseException {
		dashboardCreditCardDetailsforSVR.authorisedWithInvalidToken();
		dashboardCreditCardDetailsforSVR.setPayloadWithValidDeviceId();
		dashboardCreditCardDetailsforSVR.invokeDashboardCreditCardDetailsForSVRApi();
		dashboardCreditCardDetailsforSVR.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);
	}

	@Test(priority = 2) //Negative case for invalid InitiatedSerno
	public void checkDashboardCreditCardDetailsForSVRWithInvalidInitiatedSerno() throws IOException, ParseException {
		dashboardCreditCardDetailsforSVR.setPayloadWithInvalid_InitiatedSerno();
		dashboardCreditCardDetailsforSVR.invokeDashboardCreditCardDetailsForSVRApi();
		dashboardCreditCardDetailsforSVR.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		dashboardCreditCardDetailsforSVR.validatePayloadForIncorrectInitiatedSerno();
	}

	@Test(priority = 2) //Negative case for invalid InitiatedKey | bug reported [SVR4-458]
	public void checkDashboardCreditCardDetailsForSVRWithInvalidInitiatedKey() throws IOException, ParseException {
		dashboardCreditCardDetailsforSVR.setPayloadWithInvalid_InitiatedKey();
		dashboardCreditCardDetailsforSVR.invokeDashboardCreditCardDetailsForSVRApi();
		dashboardCreditCardDetailsforSVR.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		dashboardCreditCardDetailsforSVR.validatePayloadForIncorrectInitiatedKey();
	}

	@Test(priority = 2) //Negative case for invalid ChainSerNo | bug reported [SVR4-458]
	public void checkDashboardCreditCardDetailsForSVRWithInvalidChainSerNo() throws IOException, ParseException {
		dashboardCreditCardDetailsforSVR.setPayloadWithInvalid_ChainSerno();
		dashboardCreditCardDetailsforSVR.invokeDashboardCreditCardDetailsForSVRApi();
		dashboardCreditCardDetailsforSVR.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		dashboardCreditCardDetailsforSVR.validatePayloadForIncorrectChainSerNo();
	}

	@Test(priority = 2) //Negative case for invalid ChainAuth
	public void checkDashboardCreditCardDetailsForSVRWithInvalidChainAuth() throws IOException, ParseException {
		dashboardCreditCardDetailsforSVR.setPayloadWithInvalid_ChainAuth();
		dashboardCreditCardDetailsforSVR.invokeDashboardCreditCardDetailsForSVRApi();
		dashboardCreditCardDetailsforSVR.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		dashboardCreditCardDetailsforSVR.validatePayloadForIncorrectChainAuth();
	}

	@Test(priority = 2) //Negative case for invalid DeviceId
	public void checkDashboardCreditCardDetailsForSVRWithInvalidDeviceId() throws IOException, ParseException {
		dashboardCreditCardDetailsforSVR.setPayloadWithInvalid_DeviceId();
		dashboardCreditCardDetailsforSVR.invokeDashboardCreditCardDetailsForSVRApi();
		dashboardCreditCardDetailsforSVR.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		dashboardCreditCardDetailsforSVR.validatePayloadForIncorrectDeviceId();
	}
	@Test(priority = 3)
	public void checkDashboardCreditCardDetailsForSVRAuthorizedAccess() {
		dashboardCreditCardDetailsforSVR.invokeDashboardCreditCardDetailsForSVRApi();
		dashboardCreditCardDetailsforSVR.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		//cardcountSummeryApiMethods.validatePayload();  Unable to validate the response as all field values are dynamic
	}


}
