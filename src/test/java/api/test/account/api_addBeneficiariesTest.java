package api.test.account;

import api.methods.addBeneficiaries;
import api.methods.baseMethod;
import api.methods.withdrawMobileCash;
import api.utils.ConstantApiUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class api_addBeneficiariesTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public addBeneficiaries addBeneficiaries;

	@BeforeClass()
	public void setUp() {
		addBeneficiaries = new addBeneficiaries();
	}

	@Test(priority = 1)
	public void checkAddBeneficiariesUnauthorizedAccess()  {
		addBeneficiaries.authorisedWithInvalidToken();
		addBeneficiaries.invokeAddBeneficiariesApi();
		addBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}
	@Test(priority = 2)
	public void checkAddBeneficiariesWithIncorrectAccountType() throws IOException, ParseException {
		addBeneficiaries.authorisedWithValidToken();
		addBeneficiaries.setPayloadWithInvalidAccountType();
		addBeneficiaries.invokeAddBeneficiariesApi();
		addBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		addBeneficiaries.validatePayloadForIncorrectAccountType();
		addBeneficiaries.setPayloadWithValidData(); //revert changes for the next test
	}
	@Test(priority = 2)
	public void checkAddBeneficiariesWithEmptyAccountName() throws IOException, ParseException {
		addBeneficiaries.authorisedWithValidToken();
		addBeneficiaries.setPayloadWithEmptyAccountName();
		addBeneficiaries.invokeAddBeneficiariesApi();
		addBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		addBeneficiaries.validatePayloadForEmptyAccountName();
		addBeneficiaries.setPayloadWithValidData(); //revert changes for the next test
	}
	@Test(priority = 2)
	public void checkAddBeneficiariesWithIncorrectAccountNumber() throws IOException, ParseException {
		addBeneficiaries.authorisedWithValidToken();
		addBeneficiaries.setPayloadWithIncorrectAccountNumber();
		addBeneficiaries.invokeAddBeneficiariesApi();
		addBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		addBeneficiaries.validatePayloadForIncorrectAccountNumber();
		addBeneficiaries.setPayloadWithValidData(); //revert changes for the next test
	}

	@Test(priority = 2)
	public void checkAddBeneficiariesWithEmptyBankCode() throws IOException, ParseException {
		addBeneficiaries.authorisedWithValidToken();
		addBeneficiaries.setPayloadWithEmptyBankCode();
		addBeneficiaries.invokeAddBeneficiariesApi();
		addBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		addBeneficiaries.validatePayloadForEmptyBankCode();
		addBeneficiaries.setPayloadWithValidData(); //revert changes for the next test
	}

	@Test(priority = 3)
	public void checkMobileCashAuthorizedAccess() throws IOException, ParseException {
		addBeneficiaries.authorisedWithValidToken();
		addBeneficiaries.setPayloadWithValidData();
		addBeneficiaries.invokeAddBeneficiariesApi();
		addBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		addBeneficiaries.validatePayload();
	}


}
