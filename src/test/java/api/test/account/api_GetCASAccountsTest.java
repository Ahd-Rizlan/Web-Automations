package api.test.account;

import api.methods.baseMethod;
import api.methods.casaAccountApiMethods;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;

public class api_GetCASAccountsTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public api.methods.casaAccountApiMethods casaaccountApiMethods ;

	@BeforeClass()
	public void setUp() {
		casaaccountApiMethods = new casaAccountApiMethods();
	}

	@Test(priority = 1)
	public void checkCasaAccountApiUnauthorizedAccess() {
		casaaccountApiMethods.authorisedWithInvalidToken();
		casaaccountApiMethods.setPageNo(ConstantApiUtils.PAGE_NO_ONE);
		casaaccountApiMethods.invokeGetCASAAccountsApi();
		casaaccountApiMethods.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);
	}

	@Test(priority = 2)
	public void checkCasaAccountApiAuthorizedAccess() {
		casaaccountApiMethods.authorisedWithValidToken();
		casaaccountApiMethods.setPageNo(ConstantApiUtils.PAGE_NO_ONE);
		casaaccountApiMethods.invokeGetCASAAccountsApi();
		casaaccountApiMethods.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		casaaccountApiMethods.validatePayload();

	}

}
