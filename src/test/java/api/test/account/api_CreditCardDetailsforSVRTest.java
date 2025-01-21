package api.test.account;

import api.methods.baseMethod;
import api.methods.creditCardDetailsApiMethods;
import api.utils.ConstantApiUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class api_CreditCardDetailsforSVRTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public creditCardDetailsApiMethods creditcardDetailsApiMethods;

	@BeforeClass()
	public void setUp() {
		creditcardDetailsApiMethods = new creditCardDetailsApiMethods();
	}

	@Test(priority = 1)
	public void checkCreditCardDetailsApiUnauthorizedAccess() throws IOException, ParseException {
		creditcardDetailsApiMethods.authorisedWithInvalidToken();
		creditcardDetailsApiMethods.setPayloadWithValidDeviceId();
		creditcardDetailsApiMethods.invokeInitiateCardCountApi();
		creditcardDetailsApiMethods.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}

	@Test(priority = 2)
	public void checkCreditCardDetailsApiAuthorizedAccess() throws IOException, ParseException {
		creditcardDetailsApiMethods.authorisedWithValidToken();
		creditcardDetailsApiMethods.setPayloadWithValidDeviceId();
		creditcardDetailsApiMethods.invokeInitiateCardCountApi();
		creditcardDetailsApiMethods.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		creditcardDetailsApiMethods.validatePayload();
	}

}
