package api.test;

import api.methods.baseMethod;
import api.methods.getPayments;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;

public class api_GetPaymentsTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public getPayments getPayments;

	@BeforeClass()
	public void setUp() {
		getPayments = new getPayments();
	}
	@BeforeMethod()
	public void resetData (Method m){
		setTestName(m.getName());
	}
	@Test(priority = 1)
	public void checkPaymentsWithUnauthorizedAccess()  {
		getPayments.authorisedWithInvalidToken();
		getPayments.invokeGetPaymentsApi();
		getPayments.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}

	@Test(priority = 2)
	public void checkPaymentsWithAuthorizedAccess()  {
		getPayments.authorisedWithValidToken();
		getPayments.invokeGetPaymentsApi();
		getPayments.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		getPayments.validatePayload();  //The response is changed frequently
	}

}
