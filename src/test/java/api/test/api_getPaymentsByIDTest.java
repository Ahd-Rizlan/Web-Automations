package api.test;

import api.methods.baseMethod;
import api.methods.casaAccountApiMethods;
import api.methods.getPaymentsByID;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;

public class api_getPaymentsByIDTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public getPaymentsByID getPaymentsByID ;

	@BeforeClass()
	public void setUp() {
		getPaymentsByID = new getPaymentsByID();
	}
	@BeforeMethod()
	public void resetData (Method m){
		setTestName(m.getName());
	}
	@Test(priority = 1)
	public void checkGetPaymentsByIDApiWithUnauthorizedAccess() {
		getPaymentsByID.authorisedWithInvalidToken();
		getPaymentsByID.setPaymentId(ConstantApiUtils.PAYMENT_ID);
		getPaymentsByID.invokeGetPaymentsByIDApi();
		getPaymentsByID.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}

	@Test(priority = 2)
	public void checkGetPaymentsByIDApiWithAuthorizedAccess() {
		getPaymentsByID.authorisedWithValidToken();
		getPaymentsByID.setPaymentId(ConstantApiUtils.PAYMENT_ID);
		getPaymentsByID.invokeGetPaymentsByIDApi();
		getPaymentsByID.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		getPaymentsByID.validatePayload();  // Need a valid paymentId

	}

}
