package api.test.account;

import api.methods.baseMethod;
import api.methods.reverseMobileCash;
import api.methods.withdrawMobileCash;
import api.utils.ConstantApiUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class api_reverseMobileCashTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public reverseMobileCash reverseMobileCash;

	@BeforeClass()
	public void setUp() {
		reverseMobileCash = new reverseMobileCash();
	}

	@Test(priority = 1)
	public void checkGetMobileCashUnauthorizedAccess()  {
		reverseMobileCash.authorisedWithInvalidToken();
		reverseMobileCash.invokeReverseMobileCashApi();
		reverseMobileCash.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}
	@Test(priority = 2)
	public void checkMobileCashWithInvalidNic() throws IOException, ParseException {
		reverseMobileCash.authorisedWithValidToken();
		reverseMobileCash.setPayloadWithInvalidNic();
		reverseMobileCash.invokeReverseMobileCashApi();
		reverseMobileCash.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		reverseMobileCash.validatePayload();
	}
	@Test(priority = 2)
	public void checkMobileCashWithInvalidPin() throws IOException, ParseException {
		reverseMobileCash.authorisedWithValidToken();
		reverseMobileCash.setPayloadWithInvalidPin();
		reverseMobileCash.invokeReverseMobileCashApi();
		reverseMobileCash.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		reverseMobileCash.validatePayload();
	}
	@Test(priority = 3)
	public void checkMobileCashAuthorizedAccess() throws IOException, ParseException {
		reverseMobileCash.authorisedWithValidToken();
		reverseMobileCash.setPayloadWithValidData();
		reverseMobileCash.invokeReverseMobileCashApi();
		reverseMobileCash.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		reverseMobileCash.validatePayload();
	}

}
