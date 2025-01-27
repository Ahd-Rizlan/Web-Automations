package api.test.account;

import api.methods.baseMethod;
import api.methods.makeBillPayment;
import api.utils.ConstantApiUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class api_makeBillPaymentTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public makeBillPayment makeBillPayment;

	@BeforeClass()
	public void setUp() {
		makeBillPayment = new makeBillPayment();
	}

	@Test(priority = 1)
	public void checkBillPaymentsWithUnauthorizedAccess()  {
		makeBillPayment.authorisedWithInvalidToken();
		makeBillPayment.invokeMakeBillPaymentApi();
		makeBillPayment.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}
	@Test(priority = 2)
	public void checkBillPaymentsWithIncorrectBillerId() throws IOException, ParseException {
		makeBillPayment.authorisedWithValidToken();
		makeBillPayment.setPayloadWithIncorrectBillerId();
		makeBillPayment.invokeMakeBillPaymentApi();
		makeBillPayment.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		makeBillPayment.validatePayloadForIncorrectBillerId();
		makeBillPayment.setPayloadWithValidData(); //revert changes for the next test
	}

	@Test(priority = 2)
	public void checkValidatePaymentsWithIncorrectDebitAccount() throws IOException, ParseException {
		makeBillPayment.authorisedWithValidToken();
		makeBillPayment.setPayloadWithIncorrectDebitAccount();
		makeBillPayment.invokeMakeBillPaymentApi();
		makeBillPayment.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		makeBillPayment.validatePayloadForIncorrectDebitAccount();
		makeBillPayment.setPayloadWithValidData(); //revert changes for the next test
	}

	@Test(priority = 2)
	public void checkValidatePaymentsWithIncorrectCurrencyType() throws IOException, ParseException {
		makeBillPayment.authorisedWithValidToken();
		makeBillPayment.setPayloadWithIncorrectCurrency();
		makeBillPayment.invokeMakeBillPaymentApi();
		makeBillPayment.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		makeBillPayment.validatePayloadForIncorrectCurrency();
		makeBillPayment.setPayloadWithValidData(); //revert changes for the next test
	}

	@Test(priority = 3)
	public void checkValidatePaymentsWithAuthorizedAccess() throws IOException, ParseException {
		makeBillPayment.authorisedWithValidToken();
		makeBillPayment.setPayloadWithValidData();
		makeBillPayment.invokeMakeBillPaymentApi();
		makeBillPayment.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		makeBillPayment.validatePayload(); // 'reference' and '10738' have been excluded in the validation as both values are dynamic
	}

}
