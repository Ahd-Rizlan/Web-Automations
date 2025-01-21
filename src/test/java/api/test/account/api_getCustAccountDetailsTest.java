package api.test.account;

import api.methods.baseMethod;
import api.methods.getCustAccountDetails;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class api_getCustAccountDetailsTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public getCustAccountDetails getCustAccountDetails ;

	@BeforeClass()
	public void setUp() {
		getCustAccountDetails = new getCustAccountDetails();
	}

	@Test(priority = 1)
	public void checkFDAccountApiUnauthorizedAccess() {
		getCustAccountDetails.authorisedWithInvalidToken();
		getCustAccountDetails.invokeGetCustAccountDetailsApi();
		getCustAccountDetails.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}

	@Test(priority = 2)
	public void checkFDAccountApiAuthorizedAccess() {

		getCustAccountDetails.authorisedWithValidToken();
		getCustAccountDetails.invokeGetCustAccountDetailsApi();
		getCustAccountDetails.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		getCustAccountDetails.validatePayload();
	}

}
