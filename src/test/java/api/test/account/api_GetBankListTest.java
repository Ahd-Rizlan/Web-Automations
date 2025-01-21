package api.test.account;

import api.methods.baseMethod;
import api.methods.getBankList;
import api.methods.getPayments;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class api_GetBankListTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public getBankList getBankList;

	@BeforeClass()
	public void setUp() {
		getBankList = new getBankList();
	}

	@Test(priority = 1)
	public void checkGetMobileCashUnauthorizedAccess()  {
		getBankList.authorisedWithInvalidToken();
		getBankList.invokeGetBankListApi();
		getBankList.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}

	@Test(priority = 2)
	public void checkMobileCashAuthorizedAccess()  {
		getBankList.authorisedWithValidToken();
		getBankList.invokeGetBankListApi();
		getBankList.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		getBankList.validatePayload();
	}

}
