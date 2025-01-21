package api.test.account;

import api.methods.baseMethod;
import api.methods.deletePaymentTemplate;
import api.methods.updatePaymentTemplate;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;


public class api_DeletePaymentTemplateTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public deletePaymentTemplate deletePaymentTemplate;

	@BeforeClass()
	public void setUp() {
		deletePaymentTemplate = new deletePaymentTemplate();
	}

	@Test(priority = 1)
	public void checkDeletePaymentTemplateApiUnauthorizedAccess() {
		deletePaymentTemplate.authorisedWithInvalidToken();
		deletePaymentTemplate.invokeDeletePaymentTemplate();
		deletePaymentTemplate.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);
	}

	@Test(priority = 2)
	public void deletePaymentTemplateApiAuthorizedAccess() {
		deletePaymentTemplate.authorisedWithValidToken();
		deletePaymentTemplate.invokeDeletePaymentTemplate();
		deletePaymentTemplate.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		deletePaymentTemplate.validatePayload();
	}

}
