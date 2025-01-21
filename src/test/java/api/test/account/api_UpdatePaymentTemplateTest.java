package api.test.account;

import api.methods.baseMethod;
import api.methods.favBeneficiaries;
import api.methods.updatePaymentTemplate;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;


public class api_UpdatePaymentTemplateTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public updatePaymentTemplate updatePaymentTemplate;

	@BeforeClass()
	public void setUp() {
		updatePaymentTemplate = new updatePaymentTemplate();
	}

	@Test(priority = 1)
	public void checkCreditCardDetailsApiUnauthorizedAccess() {
		updatePaymentTemplate.authorisedWithInvalidToken();
		updatePaymentTemplate.invokeUpdatePaymentTemplate();
		updatePaymentTemplate.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);
	}

	@Test(priority = 2)
	public void checkCreditCardDetailsApiAuthorizedAccess() {
		updatePaymentTemplate.authorisedWithValidToken();
		updatePaymentTemplate.invokeUpdatePaymentTemplate();
		updatePaymentTemplate.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		updatePaymentTemplate.validatePayload();
	}

}
