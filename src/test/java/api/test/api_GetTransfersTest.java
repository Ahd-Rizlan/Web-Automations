package api.test;

import api.methods.baseMethod;
import api.methods.getTransfers;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;

public class api_GetTransfersTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public getTransfers getTransfers;

	@BeforeClass()
	public void setUp() {
		getTransfers = new getTransfers();
	}
	@BeforeMethod()
	public void resetData (Method m){
		setTestName(m.getName());
	}
	@Test(priority = 1)
	public void checkGetTransfersUnauthorizedAccess()  {
		getTransfers.authorisedWithInvalidToken();
		getTransfers.invokeGetTransfersApi();
		getTransfers.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}

	@Test(priority = 2)
	public void checkGetTransfersAuthorizedAccess()  {
		getTransfers.authorisedWithValidToken();
		getTransfers.invokeGetTransfersApi();
		getTransfers.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		getTransfers.validatePayload();
	}

}
