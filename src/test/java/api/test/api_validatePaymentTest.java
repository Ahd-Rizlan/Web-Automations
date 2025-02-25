package api.test;

import api.methods.addBeneficiaries;
import api.methods.baseMethod;
import api.methods.validatePayment;
import api.utils.ConstantApiUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class api_validatePaymentTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public validatePayment validatePayment;

	@BeforeClass()
	public void setUp() {
		validatePayment = new validatePayment();
	}
	@BeforeMethod()
	public void resetData (Method m){
		setTestName(m.getName());
	}
	@Test(priority = 1, testName = "Verify that Bill Payments Cannot be Validated with Unauthorized Access")
	public void checkValidatePaymentsWithUnauthorizedAccess()  {
		validatePayment.authorisedWithInvalidToken();
		validatePayment.invokeValidatePaymentApi();
		validatePayment.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}
	@Test(priority = 2, testName = "Verify that Bill Payments Cannot be Validated with Incorrect Biller Id")
	public void checkValidatePaymentsWithIncorrectBillerId() throws IOException, ParseException {
		validatePayment.authorisedWithValidToken();
		validatePayment.setPayloadWithIncorrectBillerId();
		validatePayment.invokeValidatePaymentApi();
		validatePayment.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		validatePayment.validatePayloadForIncorrectBillerId(); // Response has been changed on 2025-Feb-6
		validatePayment.setPayloadWithValidData(); //revert changes for the next test
	}

	@Test(priority = 2, testName = "Verify that Bill Payments Cannot be Validated with Incorrect Account Number ")
	public void checkValidatePaymentsWithIncorrectAccountNumber() throws IOException, ParseException {
		validatePayment.authorisedWithValidToken();
		validatePayment.setPayloadWithIncorrectAccountNumber();
		validatePayment.invokeValidatePaymentApi();
		validatePayment.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		validatePayment.validatePayloadForIncorrectAccountNumber();
		validatePayment.setPayloadWithValidData(); //revert changes for the next test
	}
	@Test(priority = 2, testName = "Verify that Bill Payments Cannot be Validated with Incorrect Currency Type")
	public void checkValidatePaymentsWithIncorrectCurrencyType() throws IOException, ParseException {
		validatePayment.authorisedWithValidToken();
		validatePayment.setPayloadWithIncorrectCurrency();
		validatePayment.invokeValidatePaymentApi();
		validatePayment.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		validatePayment.validatePayloadForIncorrectCurrency();
		validatePayment.setPayloadWithValidData(); //revert changes for the next test
	}

	@Test(priority = 2, testName = "Verify that Bill Payments Cannot be Validated with Empty Bank Code")
	public void checkValidatePaymentsWithEmptyBankCode() throws IOException, ParseException {
		validatePayment.authorisedWithValidToken();
		validatePayment.setPayloadWithIncorrectTransactionType();
		validatePayment.invokeValidatePaymentApi();
		validatePayment.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		validatePayment.validatePayloadForIncorrectTransactionType();
		validatePayment.setPayloadWithValidData(); //revert changes for the next test
	}

	@Test(priority = 3, testName = "Verify that Bill Payments Can be Validated with Authorized Access")
	public void checkValidatePaymentsWithAuthorizedAccess() throws IOException, ParseException {
		validatePayment.authorisedWithValidToken();
		validatePayment.setPayloadWithValidData();
		validatePayment.invokeValidatePaymentApi();
		validatePayment.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		validatePayment.validatePayload(); // Response has been changed on 2025-Feb-6
	}



}
