package api.test.account;

import api.methods.baseMethod;
import api.methods.getTransfers;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class api_GetTransfersTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public getTransfers getTransfers;

	@BeforeClass()
	public void setUp() {
		getTransfers = new getTransfers();
	}

	@Test(priority = 1)
	public void checkGetMobileCashUnauthorizedAccess()  {
		getTransfers.authorisedWithInvalidToken();
		getTransfers.invokeGetTransfersApi();
		getTransfers.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}

	@Test(priority = 2)
	public void checkMobileCashAuthorizedAccess()  {
		getTransfers.authorisedWithValidToken();
		getTransfers.invokeGetTransfersApi();
		getTransfers.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		getTransfers.validatePayload();
	}

}
