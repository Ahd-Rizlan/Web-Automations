package api.test.account;

import api.methods.baseMethod;
import api.methods.getUnutilizedMobileCash;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class api_getUnutilizedMobileCashTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public getUnutilizedMobileCash getUnutilizedMobileCash;

	@BeforeClass()
	public void setUp() {
		getUnutilizedMobileCash = new getUnutilizedMobileCash();
	}

	@Test(priority = 1)
	public void checkGetMobileCashUnauthorizedAccess()  {
		getUnutilizedMobileCash.authorisedWithInvalidToken();
		getUnutilizedMobileCash.invokeGetUnutilizedMobileCashApi();
		getUnutilizedMobileCash.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}

	@Test(priority = 2)
	public void checkMobileCashAuthorizedAccess()  {
		getUnutilizedMobileCash.authorisedWithValidToken();
		getUnutilizedMobileCash.invokeGetUnutilizedMobileCashApi();
		getUnutilizedMobileCash.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		getUnutilizedMobileCash.validatePayload();

	}

}
