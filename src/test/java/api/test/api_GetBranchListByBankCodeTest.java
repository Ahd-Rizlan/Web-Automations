package api.test;

import api.methods.baseMethod;
import api.methods.getBankList;
import api.methods.getBranchListByBankCode;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;

public class api_GetBranchListByBankCodeTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public getBranchListByBankCode getBranchListByBankCode;

	@BeforeClass()
	public void setUp() {
		getBranchListByBankCode = new getBranchListByBankCode();
	}
	@BeforeMethod()
	public void resetData (Method m){
		setTestName(m.getName());
	}
	@Test(priority = 1)
	public void checkGetMobileCashUnauthorizedAccess()  {
		getBranchListByBankCode.authorisedWithInvalidToken();
		getBranchListByBankCode.invokeGetBranchListByBankCodeApi();
		getBranchListByBankCode.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}

	@Test(priority = 2)
	public void checkMobileCashAuthorizedAccess()  {
		getBranchListByBankCode.authorisedWithValidToken();
		getBranchListByBankCode.invokeGetBranchListByBankCodeApi();
		getBranchListByBankCode.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		getBranchListByBankCode.validatePayload();
	}

}
