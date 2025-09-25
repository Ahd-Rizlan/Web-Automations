package api.test;

import api.methods.baseMethod;
import api.methods.getCustAccountDetails;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;

public class api_getCustAccountDetailsTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public getCustAccountDetails getCustAccountDetails ;

	@BeforeClass()
	public void setUp() {
		getCustAccountDetails = new getCustAccountDetails();
	}
	@BeforeMethod()
	public void resetData (Method m){
		setTestName(m.getName());
	}
	@Test(priority = 1,testName = "Verify that Account Details are Not Retrieved With Unauthorized Access")
	public void checkCustAccountDetailsApiUnauthorizedAccess() {
		getCustAccountDetails.authorisedWithInvalidToken();
		getCustAccountDetails.invokeGetCustAccountDetailsApi();
		getCustAccountDetails.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}

	@Test(priority = 2,testName = "Verify that Account Details are Retrieved With Authorized Access")
	public void checkCustAccountDetailsApiAuthorizedAccess() {

		getCustAccountDetails.authorisedWithValidToken();
		getCustAccountDetails.invokeGetCustAccountDetailsApi();
		getCustAccountDetails.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		getCustAccountDetails.validatePayload();
	}

}
