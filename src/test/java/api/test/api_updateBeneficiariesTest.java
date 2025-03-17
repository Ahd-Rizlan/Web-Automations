package api.test;

import api.methods.baseMethod;
import api.methods.getTransferPayeeList;
import api.methods.updateBeneficiaries;
import api.methods.addBeneficiaries;
import api.utils.ConstantApiUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import static api.utils.ConstantApiUtils.VALID_ACCOUNT_NAME;

public class api_updateBeneficiariesTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public updateBeneficiaries updateBeneficiaries;
 	public addBeneficiaries addBeneficiaries;
	public getTransferPayeeList getTransferPayeeList;

	@BeforeClass()
	public void setUp() {
		updateBeneficiaries = new updateBeneficiaries();
		addBeneficiaries = new addBeneficiaries();
		getTransferPayeeList = new getTransferPayeeList();

	}
	@BeforeMethod()
	public void resetData (Method m){
		setTestName(m.getName());
	}
	@Test(priority = 1,testName = "Verify that the Beneficiaries cannot be Updated with Unauthorized Access")
	public void checkUpdateBeneficiariesWithUnauthorizedAccess()  {
		updateBeneficiaries.authorisedWithInvalidToken();
		updateBeneficiaries.invokeUpdateBeneficiariesApi();
		updateBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);
	}


//	@Test(priority = 1, testName = "Verify that Adding Beneficiaries can be with Authorized Access")
//	public void checkAddBeneficiariesWithAuthorizedAccess() throws IOException, ParseException {
//		addBeneficiaries.authorisedWithValidToken();
//		addBeneficiaries.setPayloadWithValidData();
//		addBeneficiaries.invokeAddBeneficiariesApi();
//		addBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
//		addBeneficiaries.validatePayload();
//	}

	@Test(priority = 2, testName = "Verify that the Transfer Payee List Retrieve with Authorized Access", dependsOnMethods = "checkAddBeneficiariesWithAuthorizedAccess")
	public void checkGetTransferPayeeListWithAuthorizedAccess() throws IOException, ParseException {
		getTransferPayeeList.authorisedWithValidToken();
		getTransferPayeeList.setPayloadWithValidData();
		getTransferPayeeList.invokeGetTransferPayeeListApi();
		getTransferPayeeList.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		getTransferPayeeList.extractBeneficiaryIDForAccount(VALID_ACCOUNT_NAME);
	}

	@Test(priority = 3, testName = "Verify that the Beneficiaries can be Updated with Authorized Access", dependsOnMethods = "checkGetTransferPayeeListWithAuthorizedAccess")
	public void checkUpdateBeneficiariesWithAuthorizedAccess() throws IOException, ParseException {
		updateBeneficiaries.authorisedWithValidToken();
		updateBeneficiaries.setPayloadWithValidBeneId();
		updateBeneficiaries.invokeUpdateBeneficiariesApi();
		updateBeneficiaries.setResponceWithValidBeneId();
		updateBeneficiaries.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		updateBeneficiaries.validatePayload();
	}
}
