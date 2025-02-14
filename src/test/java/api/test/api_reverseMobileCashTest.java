package api.test;

import api.methods.baseMethod;
import api.methods.reverseMobileCash;
import api.methods.withdrawMobileCash;
import api.utils.ConstantApiUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class api_reverseMobileCashTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public reverseMobileCash reverseMobileCash;

	@BeforeClass()
	public void setUp() {
		reverseMobileCash = new reverseMobileCash();
	}
	@BeforeMethod()
	public void resetData (Method m){
		setTestName(m.getName());
	}
	@Test(priority = 1)
	public void checkReverseMobileCashWithUnauthorizedAccess()  {
		reverseMobileCash.authorisedWithInvalidToken();
		reverseMobileCash.invokeReverseMobileCashApi();
		reverseMobileCash.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}
	@Test(priority = 2)
	public void checkReverseMobileCashWithInvalidNic() throws IOException, ParseException {
		reverseMobileCash.authorisedWithValidToken();
		reverseMobileCash.setPayloadWithInvalidNic();
		reverseMobileCash.invokeReverseMobileCashApi();
		reverseMobileCash.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		reverseMobileCash.validatePayload();
	}
	@Test(priority = 2)
	public void checkReverseMobileCashWithInvalidPin() throws IOException, ParseException {
		reverseMobileCash.authorisedWithValidToken();
		reverseMobileCash.setPayloadWithInvalidPin();
		reverseMobileCash.invokeReverseMobileCashApi();
		reverseMobileCash.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		reverseMobileCash.validatePayload();
	}
	@Test(priority = 3)
	public void checkReverseMobileCashWithAuthorizedAccess() throws IOException, ParseException {
		reverseMobileCash.authorisedWithValidToken();
		reverseMobileCash.setPayloadWithValidData();
		reverseMobileCash.invokeReverseMobileCashApi();
		reverseMobileCash.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		reverseMobileCash.validatePayload();
	}

}
