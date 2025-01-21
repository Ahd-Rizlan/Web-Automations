package api.test.account;

import api.methods.baseMethod;
import api.methods.casaAccountApiMethods;
import api.methods.fdAccountApiMethods;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class api_GetFDAccountsTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public fdAccountApiMethods fdaccountApiMethods ;

	@BeforeClass()
	public void setUp() {
		fdaccountApiMethods = new fdAccountApiMethods();
	}

	@Test(priority = 1)
	public void checkFDAccountApiUnauthorizedAccess() {
		fdaccountApiMethods.authoriseFDAccountsWithInvalidToken();
		fdaccountApiMethods.setPageNo(ConstantApiUtils.PAGE_NO_ONE);
		fdaccountApiMethods.invokeGetFDAccountsApi();
		fdaccountApiMethods.validateFDAccountResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}

	@Test(priority = 2)
	public void checkFDAccountApiAuthorizedAccess() {
		fdaccountApiMethods.authoriseFDAccountsWithValidToken();
		fdaccountApiMethods.setPageNo(ConstantApiUtils.PAGE_NO_ONE);
		fdaccountApiMethods.invokeGetFDAccountsApi();
		fdaccountApiMethods.validateFDAccountResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		fdaccountApiMethods.validatePayload();
	}

}
