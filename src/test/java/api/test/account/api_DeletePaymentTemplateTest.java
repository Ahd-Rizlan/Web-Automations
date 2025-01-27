package api.test.account;

import api.methods.baseMethod;
import api.methods.deletePaymentTemplate;
import api.methods.savePaymentTemplate;
import api.methods.updatePaymentTemplate;
import api.utils.ConstantApiUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;


public class api_DeletePaymentTemplateTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public deletePaymentTemplate deletePaymentTemplate;
	public api.methods.savePaymentTemplate savePaymentTemplate;

	@BeforeClass()
	public void setUp() {
		deletePaymentTemplate = new deletePaymentTemplate();
		savePaymentTemplate = new savePaymentTemplate();
	}

	@Test(priority = 1)
	public void checkDeletePaymentTemplateApiUnauthorizedAccess() {
		deletePaymentTemplate.authorisedWithInvalidToken();
		deletePaymentTemplate.invokeDeletePaymentTemplate();
		deletePaymentTemplate.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);
	}

	@Test(priority = 2)
	public void addPaymentTemplate(){
		//Create a template and save the templateID in data-store
		savePaymentTemplate.authorisedWithValidToken();
		savePaymentTemplate.setHeaders();
		savePaymentTemplate.invokeSavePaymentTemplateApi();
	}

	@Test (dependsOnMethods = { "addPaymentTemplate" })
	public void deletePaymentTemplateApiAuthorizedAccess() throws IOException, ParseException {
		deletePaymentTemplate.authorisedWithValidToken();
		deletePaymentTemplate.setPayloadWithValidTemplateID();
		deletePaymentTemplate.invokeDeletePaymentTemplate();
		deletePaymentTemplate.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		deletePaymentTemplate.validatePayload();
	}
}
