package api.test;

import api.methods.baseMethod;
import api.methods.getLoanAccounts;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;

public class api_GetLoanAccountsTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public getLoanAccounts getLoanAccounts ;

	@BeforeClass()
	public void setUp() {
		getLoanAccounts = new getLoanAccounts();
	}
	@BeforeMethod()
	public void resetData (Method m){
		setTestName(m.getName());
	}
	@Test(priority = 1)
	public void checkLoanAccountsApiWithUnauthorizedAccess() {
		getLoanAccounts.authorisedWithInvalidToken();
		getLoanAccounts.setPageNo(ConstantApiUtils.PAGE_NO_ONE);
		getLoanAccounts.invokeGetLoanAccountsApi();
		getLoanAccounts.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}

	@Test(priority = 2)
	public void checkLoanAccountsApiWithAuthorizedAccess() {

		getLoanAccounts.authorisedWithValidToken();
		getLoanAccounts.setPageNo(ConstantApiUtils.PAGE_NO_ONE);
		getLoanAccounts.invokeGetLoanAccountsApi();
		getLoanAccounts.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		getLoanAccounts.validatePayload();
	}

}
