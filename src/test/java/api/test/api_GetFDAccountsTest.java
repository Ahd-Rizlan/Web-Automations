package api.test;

import api.methods.baseMethod;
import api.methods.fdAccountApiMethods;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;

public class api_GetFDAccountsTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public fdAccountApiMethods fdaccountApiMethods ;

	@BeforeClass()
	public void setUp() {
		fdaccountApiMethods = new fdAccountApiMethods();
	}
	@BeforeMethod()
	public void resetData (Method m){
		setTestName(m.getName());
	}
	@Test(priority = 1,testName = "Verify that the FD Account is Not Accessible with Unauthorized Access")
	public void checkFDAccountApiUnauthorizedAccess() {
		fdaccountApiMethods.authoriseFDAccountsWithInvalidToken();
		fdaccountApiMethods.setPageNo(ConstantApiUtils.PAGE_NO_ONE);
		fdaccountApiMethods.invokeGetFDAccountsApi();
		fdaccountApiMethods.validateFDAccountResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}

	@Test(priority = 2,testName = "Verify that the FD Account is  Accessible with Authorized Access")
	public void checkFDAccountApiAuthorizedAccess() {
		fdaccountApiMethods.authoriseFDAccountsWithValidToken();
		fdaccountApiMethods.setPageNo(ConstantApiUtils.PAGE_NO_ONE);
		fdaccountApiMethods.invokeGetFDAccountsApi();
		fdaccountApiMethods.validateFDAccountResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		fdaccountApiMethods.validatePayload();
	}

}
