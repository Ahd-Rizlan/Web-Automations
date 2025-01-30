package api.test;

import api.methods.baseMethod;
import api.methods.getBankList;
import api.methods.getPayments;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;

public class api_GetBankListTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public getBankList getBankList;

	@BeforeClass()
	public void setUp() {
		getBankList = new getBankList();
	}
	@BeforeMethod()
	public void resetData (Method m){
		setTestName(m.getName());
	}
	@Test(priority = 1)
	public void checkGetBankListWithUnauthorizedAccess()  {
		getBankList.authorisedWithInvalidToken();
		getBankList.invokeGetBankListApi();
		getBankList.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}

	@Test(priority = 2)
	public void checkGetBankListWithAuthorizedAccess()  {
		getBankList.authorisedWithValidToken();
		getBankList.invokeGetBankListApi();
		getBankList.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		getBankList.validatePayload();
	}

}
