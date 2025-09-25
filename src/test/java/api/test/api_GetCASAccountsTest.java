package api.test;

import api.methods.baseMethod;
import api.methods.casaAccountApiMethods;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.lang.reflect.Method;

public class api_GetCASAccountsTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public api.methods.casaAccountApiMethods casaaccountApiMethods ;

	@BeforeClass()
	public void setUp() {
		casaaccountApiMethods = new casaAccountApiMethods();
	}
	@BeforeMethod()
	public void resetData (Method m){
		setTestName(m.getName());
	}
	@Test(priority = 1, testName = "Verify that the CASA Account is Not Accessible with Unauthorized Access")
	public void checkCasaAccountApiUnauthorizedAccess() {
		casaaccountApiMethods.authorisedWithInvalidToken();
		casaaccountApiMethods.setPageNo(ConstantApiUtils.PAGE_NO_ONE);
		casaaccountApiMethods.invokeGetCASAAccountsApi();
		casaaccountApiMethods.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);
	}

	@Test(priority = 2,testName = "Verify that the CASA Account is Accessible with Authorized Access")
	public void checkCasaAccountApiAuthorizedAccess() {
		casaaccountApiMethods.authorisedWithValidToken();
		casaaccountApiMethods.setPageNo(ConstantApiUtils.PAGE_NO_ONE);
		casaaccountApiMethods.invokeGetCASAAccountsApi();
		casaaccountApiMethods.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		casaaccountApiMethods.validatePayload();

	}

}
