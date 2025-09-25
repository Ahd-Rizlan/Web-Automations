package api.test;

import api.methods.baseMethod;
import api.methods.getBillPaymentTemplateList;
import api.utils.ConstantApiUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class getBillPaymentTemplateListTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public getBillPaymentTemplateList getBillPaymentTemplateList;

	@BeforeClass()
	public void setUp() {
		getBillPaymentTemplateList = new getBillPaymentTemplateList();
	}

    @BeforeMethod()
    //Reset data before every test
    public void resetData (Method m) throws IOException, ParseException {
		getBillPaymentTemplateList.setPayloadWithValidMerchantCode();
		setTestName(m.getName());
    }

	//Negative cases
	@Test(priority = 1,testName = "Verify that The Bill Payment Templates are Cannot be Retrieved with Unauthorized Access")
	//For unauthorized access
	public void checkGetBillPaymentTemplateListWithUnauthorizedAccess()  {
		getBillPaymentTemplateList.authorisedWithInvalidToken();
		getBillPaymentTemplateList.invokeGetBillPaymentTemplateListApi();
		getBillPaymentTemplateList.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);
	}

	@Test(priority = 2,testName = "Verify that The Bill Payment Templates are Cannot be Retrieved with Incorrect Merchant Code")
	//For incorrect MerchantCode
	public void checkGetBillPaymentTemplateListWithIncorrectMerchantCode() throws IOException, ParseException {
		getBillPaymentTemplateList.authorisedWithValidToken();
		getBillPaymentTemplateList.setPayloadWithInvalidMerchantCode();
		getBillPaymentTemplateList.invokeGetBillPaymentTemplateListApi();
		getBillPaymentTemplateList.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		getBillPaymentTemplateList.validatePayloadForIncorrectMerchantCode();
	}

	//Happy path
	@Test(priority = 2,testName = "Verify that The Bill Payment Templates are Can be Retrieved with Authorized Access")
	public void checkGetBillPaymentTemplateListWithAuthorizedAccess()  {
		getBillPaymentTemplateList.authorisedWithValidToken();
		getBillPaymentTemplateList.invokeGetBillPaymentTemplateListApi();
		getBillPaymentTemplateList.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		getBillPaymentTemplateList.validatePayload();
	}
}
