package api.test;

import api.methods.baseMethod;
import api.methods.doTransferForVishwa;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;

public class api_doTransferForVishwaTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public doTransferForVishwa doTransferForVishwa;

	@BeforeClass()
	public void setUp() {
		doTransferForVishwa = new doTransferForVishwa();
	}
	@BeforeMethod()
	public void resetData (Method m){
		setTestName(m.getName());
	}
	@Test(priority = 1,testName = "Verify that Transfer for Vishwa Cannot be Done with Unauthorized Access")
	public void checkDoTransferForVishwaWithUnauthorizedAccess()  {
		doTransferForVishwa.authorisedWithInvalidToken();
		doTransferForVishwa.invokeDoTransferForVishwaApi();
		doTransferForVishwa.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}

	@Test(priority = 2,testName = "Verify that Transfer for Vishwa Can be Done with Authorized Access")
	public void checkDoTransferForVishwaWithAuthorizedAccess()  {
		doTransferForVishwa.authorisedWithValidToken();
		doTransferForVishwa.invokeDoTransferForVishwaApi();
		doTransferForVishwa.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		doTransferForVishwa.validatePayload();
	}

}
