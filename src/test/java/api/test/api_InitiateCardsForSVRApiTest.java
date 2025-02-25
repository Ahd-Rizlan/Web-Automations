package api.test;

import api.methods.baseMethod;
import api.methods.initiateCardsForSVR;
import api.utils.ConstantApiUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

public class api_InitiateCardsForSVRApiTest extends baseMethod {
	public api.methods.initiateCardsForSVR initiatecardsForSVR;

	@BeforeClass()
	public void setUp() {
		initiatecardsForSVR = new initiateCardsForSVR();
	}
	@BeforeMethod()
	public void resetData (Method m){
		setTestName(m.getName());
	}
	@Test(priority = 1,testName = "Verify that Cards for SVR are not initiated with Unauthorized Access")
	public void checkInitiateCardsForSVRApiUnauthorizedAccess() throws IOException, ParseException {
		initiatecardsForSVR.authorisedWithInvalidToken();
		initiatecardsForSVR.setPayloadWithValidDeviceId();
		initiatecardsForSVR.setPayloadWithValidTimeStamp();
		initiatecardsForSVR.invokeInitiateCardsForSVRApi();
		initiatecardsForSVR.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);
	}


	@Test(priority = 2,testName = "Verify that Cards for SVR are not initiated with Invalid DeviceId while other details are valid ")
	public void checkInitiateCardsForSVRApiWithInvalidDeviceID() throws IOException, ParseException {
		initiatecardsForSVR.authorisedWithValidToken();
		initiatecardsForSVR.setPayloadWithInvalidDeviceId();
		initiatecardsForSVR.setPayloadWithValidTimeStamp();
		initiatecardsForSVR.invokeInitiateCardsForSVRApi();
		initiatecardsForSVR.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		//(SVR4-454) Issue detected (even an incorrect device-id is submitted with a correct time-stamp, a valid response is detected)
	}

	@Test(priority = 2,testName = "Verify that Cards for SVR are not initiated with Invalid TimeStamp while other details are valid")
	public void checkInitiateCardsForSVRApiWithInvalidTimeStamp() throws IOException, ParseException {
		initiatecardsForSVR.authorisedWithValidToken();
		initiatecardsForSVR.setPayloadWithValidDeviceId();
		initiatecardsForSVR.setPayloadWithInvalidTimeStamp();
		initiatecardsForSVR.invokeInitiateCardsForSVRApi();
		initiatecardsForSVR.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		initiatecardsForSVR.validatePayloadForInvalidTimeStamp();

	}

	@Test(priority = 3,testName = "Verify that Cards for SVR are initiated with Authorized Access")
	public void checkInitiateCardsForSVRApiAuthorizedAccess() throws IOException, ParseException {
		initiatecardsForSVR.authorisedWithValidToken();
		initiatecardsForSVR.setPayloadWithValidDeviceId();
		initiatecardsForSVR.setPayloadWithValidTimeStamp();
		initiatecardsForSVR.invokeInitiateCardsForSVRApi();
		initiatecardsForSVR.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		//initiatecardsForSVR.validatePayload(); //Unable to validate the response since all are dynamic fields
		initiatecardsForSVR.saveImportantDataToFile();
	}
}
