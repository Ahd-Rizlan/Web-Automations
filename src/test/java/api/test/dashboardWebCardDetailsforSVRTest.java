package api.test;

import api.methods.baseMethod;
import api.methods.dashboardCreditCardDetailsforSVR;
import api.methods.dashboardWebCardDetailsforSVR;
import api.methods.initiateCardsForSVR;
import api.utils.ConstantApiUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class dashboardWebCardDetailsforSVRTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public api.methods.dashboardWebCardDetailsforSVR dashboardWebCardDetailsforSVR;
	public api.methods.initiateCardsForSVR initiateCardsForSVR;
	@BeforeMethod()
	public void resetData (Method m){
		setTestName(m.getName());
	}
	@BeforeClass()
	public void setUp() {
		dashboardWebCardDetailsforSVR = new dashboardWebCardDetailsforSVR();
		initiateCardsForSVR = new initiateCardsForSVR();
	}

	@BeforeMethod // Before every test the correct data setup is prepared
	public void setPayloadWithUpToDateValidData () throws IOException, ParseException {

		// execute pre-request api : initiateCardsforSVR
		initiateCardsForSVR.authorisedWithValidToken();
		initiateCardsForSVR.setPayloadWithValidDeviceId();
		initiateCardsForSVR.setPayloadWithValidTimeStamp();
		initiateCardsForSVR.invokeInitiateCardsForSVRApi();
		initiateCardsForSVR.saveImportantDataToFile();

		//Execute endpoint : dashboardCreditCardDetailsforSVR with the data retrieved from initiateCardsforSVR
		dashboardWebCardDetailsforSVR.authorisedWithValidToken();
		dashboardWebCardDetailsforSVR.setPayloadWithValidTimeStamp();
		dashboardWebCardDetailsforSVR.setPayloadWithValidInitiatedSerno();
		dashboardWebCardDetailsforSVR.setPayloadWithValidInitiatedKey();
		dashboardWebCardDetailsforSVR.setPayloadWithValidChainSerno();
		dashboardWebCardDetailsforSVR.setPayloadWithValidChainAuth();
		dashboardWebCardDetailsforSVR.setPayloadWithValidDeviceId();
	}

	@Test(priority = 1)
	public void checkDashboardWebCardDetailsForSVRApiUnauthorizedAccess() throws IOException, ParseException {
		dashboardWebCardDetailsforSVR.authorisedWithInvalidToken();
		dashboardWebCardDetailsforSVR.setPayloadWithValidDeviceId();
		dashboardWebCardDetailsforSVR.invokeDashboardWebCardDetailsForSVRApi();
		dashboardWebCardDetailsforSVR.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);
	}

	@Test(priority = 2) //Negative case for invalid InitiatedSerno
	public void checkDashboardWebCardDetailsForSVRWithInvalidInitiatedSerno() throws IOException, ParseException {
		dashboardWebCardDetailsforSVR.setPayloadWithInvalid_InitiatedSerno();
		dashboardWebCardDetailsforSVR.invokeDashboardWebCardDetailsForSVRApi();
		dashboardWebCardDetailsforSVR.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		dashboardWebCardDetailsforSVR.validatePayloadForIncorrectInitiatedSerno();
	}

	@Test(priority = 2) //Negative case for invalid InitiatedKey | bug reported [SVR4-458]
	public void checkDashboardWebCardDetailsForSVRWithInvalidInitiatedKey() throws IOException, ParseException {
		dashboardWebCardDetailsforSVR.setPayloadWithInvalid_InitiatedKey();
		dashboardWebCardDetailsforSVR.invokeDashboardWebCardDetailsForSVRApi();
		dashboardWebCardDetailsforSVR.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		dashboardWebCardDetailsforSVR.validatePayloadForIncorrectInitiatedKey();
	}

	@Test(priority = 2) //Negative case for invalid ChainSerNo | bug reported [SVR4-458]
	public void checkDashboardWebCardDetailsForSVRWithInvalidChainSerNo() throws IOException, ParseException {
		dashboardWebCardDetailsforSVR.setPayloadWithInvalid_ChainSerno();
		dashboardWebCardDetailsforSVR.invokeDashboardWebCardDetailsForSVRApi();
		dashboardWebCardDetailsforSVR.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		dashboardWebCardDetailsforSVR.validatePayloadForIncorrectChainSerNo();
	}

	@Test(priority = 2) //Negative case for invalid ChainAuth
	public void checkDashboardWebCardDetailsForSVRWithInvalidChainAuth() throws IOException, ParseException {
		dashboardWebCardDetailsforSVR.setPayloadWithInvalid_ChainAuth();
		dashboardWebCardDetailsforSVR.invokeDashboardWebCardDetailsForSVRApi();
		dashboardWebCardDetailsforSVR.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		dashboardWebCardDetailsforSVR.validatePayloadForIncorrectChainAuth();
	}

	@Test(priority = 2) //Negative case for invalid DeviceId
	public void checkDashboardWebCardDetailsForSVRWithInvalidDeviceId() throws IOException, ParseException {
		dashboardWebCardDetailsforSVR.setPayloadWithInvalid_DeviceId();
		dashboardWebCardDetailsforSVR.invokeDashboardWebCardDetailsForSVRApi();
		dashboardWebCardDetailsforSVR.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		dashboardWebCardDetailsforSVR.validatePayloadForIncorrectDeviceId();
	}
	@Test(priority = 3)
	public void checkDashboardWebCardDetailsForSVRAuthorizedAccess() {
		dashboardWebCardDetailsforSVR.invokeDashboardWebCardDetailsForSVRApi();
		dashboardWebCardDetailsforSVR.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		dashboardWebCardDetailsforSVR.validatePayload();
	}

}
