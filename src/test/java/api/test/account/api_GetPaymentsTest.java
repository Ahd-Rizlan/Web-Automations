package api.test.account;

import api.methods.baseMethod;
import api.methods.getPayments;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class api_GetPaymentsTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public getPayments getPayments;

	@BeforeClass()
	public void setUp() {
		getPayments = new getPayments();
	}

	@Test(priority = 1)
	public void checkGetMobileCashUnauthorizedAccess()  {
		getPayments.authorisedWithInvalidToken();
		getPayments.invokeGetPaymentsApi();
		getPayments.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}

	@Test(priority = 2)
	public void checkMobileCashAuthorizedAccess()  {
		getPayments.authorisedWithValidToken();
		getPayments.invokeGetPaymentsApi();
		getPayments.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		getPayments.validatePayload();
	}

}
