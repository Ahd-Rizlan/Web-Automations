package api.test;

import api.methods.baseMethod;
import api.methods.makeBillPayment;
import api.utils.ConstantApiUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class api_makeBillPaymentTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public makeBillPayment makeBillPayment;

	@BeforeClass()
	public void setUp() {
		makeBillPayment = new makeBillPayment();
	}
	@BeforeMethod()
	public void resetData (Method m){
		setTestName(m.getName());
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
	public void checkVBillPaymentsWithIncorrectDebitAccount() throws IOException, ParseException {
		makeBillPayment.authorisedWithValidToken();
		makeBillPayment.setPayloadWithIncorrectDebitAccount();
		makeBillPayment.invokeMakeBillPaymentApi();
		makeBillPayment.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		makeBillPayment.validatePayloadForIncorrectDebitAccount();
		makeBillPayment.setPayloadWithValidData(); //revert changes for the next test
	}

	@Test(priority = 2)
	public void checkBillPaymentsWithIncorrectCurrencyType() throws IOException, ParseException {
		makeBillPayment.authorisedWithValidToken();
		makeBillPayment.setPayloadWithIncorrectCurrency();
		makeBillPayment.invokeMakeBillPaymentApi();
		makeBillPayment.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		makeBillPayment.validatePayloadForIncorrectCurrency();
		makeBillPayment.setPayloadWithValidData(); //revert changes for the next test
	}

	@Test(priority = 3)
	public void checkBillPaymentsWithAuthorizedAccess() throws IOException, ParseException {
		makeBillPayment.authorisedWithValidToken();
		makeBillPayment.setPayloadWithValidData();
		makeBillPayment.invokeMakeBillPaymentApi();
		makeBillPayment.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		makeBillPayment.validatePayload(); // response has been changed on 2025-Feb-06. Need a valid payload now
	}

}
