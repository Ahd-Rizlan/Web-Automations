package api.test.account;

import api.methods.baseMethod;
import api.methods.getAccountsByTrantype;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class api_getAccountsByTrantypeTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public getAccountsByTrantype getAccountsByTrantype ;

	@BeforeClass()
	public void setUp() {
		getAccountsByTrantype = new getAccountsByTrantype();
	}

	@Test(priority = 1)
	public void checkFDAccountApiUnauthorizedAccess() {
		getAccountsByTrantype.authorisedWithInvalidToken();
		getAccountsByTrantype.invokeGetAccountsByTrantypeApi();
		getAccountsByTrantype.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}

	@Test(priority = 2)
	public void checkFDAccountApiAuthorizedAccess() {
		getAccountsByTrantype.authorisedWithValidToken();
		getAccountsByTrantype.invokeGetAccountsByTrantypeApi();
		getAccountsByTrantype.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		getAccountsByTrantype.validatePayload();
	}

}
