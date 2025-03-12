package api.test;

import api.methods.baseMethod;
import api.methods.getTransferPayeeList;
import api.utils.ConstantApiUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

import static api.utils.ConstantApiUtils.VALID_ACCOUNT_NAME;

public class api_getTransferPayeeListTest extends baseMethod {

	public getTransferPayeeList getTransferPayeeList;

	@BeforeClass()
	public void setUp() {
		getTransferPayeeList = new getTransferPayeeList();
	}
	@BeforeMethod()
	public void resetData (Method m){
		setTestName(m.getName());
	}
	@Test(priority = 1,testName = "Verify that the Transfer Payee List Cannot be Retrieve with Unauthorized Access")
	public void checkGetTransferPayeeListWithUnauthorizedAccess()  {
		getTransferPayeeList.authorisedWithInvalidToken();
		getTransferPayeeList.invokeGetTransferPayeeListApi();
		getTransferPayeeList.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);
	}

	@Test(priority = 2,testName = "Verify that the Transfer Payee List Retrieve with Authorized Access")
	public void checkGetTransferPayeeListWithAuthorizedAccess() throws IOException, ParseException {
		getTransferPayeeList.authorisedWithValidToken();
		getTransferPayeeList.setPayloadWithValidData();
		getTransferPayeeList.invokeGetTransferPayeeListApi();
		getTransferPayeeList.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);

//		getTransferPayeeList.validatePayload();

		getTransferPayeeList.extractBeneficiaryIDForAccount(VALID_ACCOUNT_NAME);

	}

}
