package api.test;

import api.methods.baseMethod;
import api.methods.savePaymentTemplate;
import api.utils.ConstantApiUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class api_SavePaymentTemplateTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public savePaymentTemplate savePaymentTemplate;

	@BeforeClass()
	public void setUp() {
		savePaymentTemplate = new savePaymentTemplate();
	}
	@BeforeMethod()
	public void resetData (Method m){
		setTestName(m.getName());
	}
	@Test(priority = 1)
	public void checkBillPaymentsWithUnauthorizedAccess()  {
		savePaymentTemplate.authorisedWithInvalidToken();
		savePaymentTemplate.invokeSavePaymentTemplateApi();
		savePaymentTemplate.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}
	@Test(priority = 2)
	public void checkBillPaymentsWithIncorrectBillerId() throws IOException, ParseException {
		savePaymentTemplate.authorisedWithValidToken();
		savePaymentTemplate.setPayloadWithIncorrectBillerId();
		savePaymentTemplate.invokeSavePaymentTemplateApi();
		savePaymentTemplate.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		savePaymentTemplate.validatePayloadForIncorrectBillerId();
		savePaymentTemplate.setPayloadWithValidData(); //revert changes for the next test
	}

	@Test(priority = 3)
	public void checkValidatePaymentsWithAuthorizedAccess() throws IOException, ParseException {
		savePaymentTemplate.authorisedWithValidToken();
		savePaymentTemplate.setPayloadWithValidData();
		savePaymentTemplate.invokeSavePaymentTemplateApi();
		savePaymentTemplate.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		savePaymentTemplate.validatePayload(); //'templateID' is excluded in the validation as its value is dynamic
	}

}
