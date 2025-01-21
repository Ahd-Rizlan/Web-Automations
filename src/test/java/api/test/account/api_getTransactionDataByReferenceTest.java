package api.test.account;

import api.methods.baseMethod;
import api.methods.getTransactionDatabyReference;
import api.utils.ConstantApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class api_getTransactionDataByReferenceTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public getTransactionDatabyReference getTransactionDatabyReference ;

	@BeforeClass()
	public void setUp() {
		getTransactionDatabyReference = new getTransactionDatabyReference();
	}

	@Test(priority = 1)
	public void checkFDAccountApiUnauthorizedAccess() {
		getTransactionDatabyReference.authorisedWithInvalidToken();
		getTransactionDatabyReference.setTransactionId(ConstantApiUtils.TRANSACTION_ID);
		getTransactionDatabyReference.invokeGetTransactionDataByReferenceApi();
		getTransactionDatabyReference.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}

	@Test(priority = 2)
	public void checkFDAccountApiAuthorizedAccess() {

		getTransactionDatabyReference.authorisedWithValidToken();
		getTransactionDatabyReference.setTransactionId(ConstantApiUtils.TRANSACTION_ID);
		getTransactionDatabyReference.invokeGetTransactionDataByReferenceApi();
		getTransactionDatabyReference.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		getTransactionDatabyReference.validatePayload();
	}

}
