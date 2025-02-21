package api.test;

import api.methods.baseMethod;
import api.methods.deletePaymentTemplate;
import api.methods.savePaymentTemplate;
import api.utils.ConstantApiUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;


public class api_DeletePaymentTemplateTest extends baseMethod {
	public deletePaymentTemplate deletePaymentTemplate;
	public api.methods.savePaymentTemplate savePaymentTemplate;

	@BeforeClass()
	public void setUp() {
		deletePaymentTemplate = new deletePaymentTemplate();
		savePaymentTemplate = new savePaymentTemplate();
	}
	@BeforeMethod()
	public void resetData (Method m){
		setTestName(m.getName());
	}
	@Test(priority = 1,testName = "Verify that payment template cannot be Deleted with Unauthorized Access")
	public void checkDeletePaymentTemplateApiUnauthorizedAccess() {
		deletePaymentTemplate.authorisedWithInvalidToken();
		deletePaymentTemplate.invokeDeletePaymentTemplate();
		deletePaymentTemplate.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);
	}

	@Test(priority = 2,testName = "Verify that a new payment Created before Deleting the Template")
	public void addPaymentTemplate(){
		//Create a template and save the templateID in data-store
		savePaymentTemplate.authorisedWithValidToken();
		savePaymentTemplate.setHeaders();
		savePaymentTemplate.invokeSavePaymentTemplateApi();
		savePaymentTemplate.saveTemplateIdToFile();
	}

	@Test (dependsOnMethods = { "addPaymentTemplate" },testName = "Verify that payment template can be Deleted with Authorized Access")
	public void deletePaymentTemplateApiAuthorizedAccess() throws IOException, ParseException {
		deletePaymentTemplate.authorisedWithValidToken();
		deletePaymentTemplate.setPayloadWithValidTemplateID();
		deletePaymentTemplate.invokeDeletePaymentTemplate();
		deletePaymentTemplate.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		deletePaymentTemplate.validatePayload();
	}
}
