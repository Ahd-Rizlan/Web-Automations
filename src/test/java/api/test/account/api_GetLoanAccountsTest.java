package api.test.account;

import api.methods.baseMethod;
import api.methods.fdAccountApiMethods;
import api.methods.getLoanAccounts;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class api_GetLoanAccountsTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public getLoanAccounts getLoanAccounts ;

	@BeforeClass()
	public void setUp() {
		getLoanAccounts = new getLoanAccounts();
	}

	@Test(priority = 1)
	public void checkFDAccountApiUnauthorizedAccess() {
		getLoanAccounts.authorisedWithInvalidToken();
		getLoanAccounts.setPageNo(ConstantApiUtils.PAGE_NO_ONE);
		getLoanAccounts.invokeGetLoanAccountsApi();
		getLoanAccounts.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}

	@Test(priority = 2)
	public void checkFDAccountApiAuthorizedAccess() {

		getLoanAccounts.authorisedWithValidToken();
		getLoanAccounts.setPageNo(ConstantApiUtils.PAGE_NO_ONE);
		getLoanAccounts.invokeGetLoanAccountsApi();
		getLoanAccounts.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		getLoanAccounts.validatePayload();
	}

}
