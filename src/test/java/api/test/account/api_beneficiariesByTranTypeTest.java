package api.test.account;

import api.methods.baseMethod;
import api.methods.beneficiariesByTranType;
import api.methods.reverseMobileCash;
import api.utils.ConstantApiUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class api_beneficiariesByTranTypeTest extends baseMethod {
	File schema = new File(System.getProperty("user.dir") + ConstantApiUtils.PATH_TO_SCHEMA_FOLDER + "Category_Schema.json");
	public beneficiariesByTranType beneficiariesByTranType;

	@BeforeClass()
	public void setUp() {
		beneficiariesByTranType = new beneficiariesByTranType();
	}

	@Test(priority = 1)
	public void checkBeneficiariesByTranTypeWithUnauthorizedAccess()  {
		beneficiariesByTranType.authorisedWithInvalidToken();
		beneficiariesByTranType.invokeBeneficiariesByTranTypeApi();
		beneficiariesByTranType.validateResponseCode(ConstantApiUtils.API_STATS_CODE_401);

	}
	@Test(priority = 2)
	public void checkBeneficiariesByTranTypeWithEmptyTranType() throws IOException, ParseException {
		beneficiariesByTranType.authorisedWithValidToken();
		beneficiariesByTranType.setPayloadWithEmptyTranType();
		beneficiariesByTranType.invokeBeneficiariesByTranTypeApi();
		beneficiariesByTranType.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		beneficiariesByTranType.validatePayloadForEmptyTranType();
	}

	@Test(priority = 3)
	public void checkBeneficiariesByTranTypeWithCorrectTranType() throws IOException, ParseException {
		beneficiariesByTranType.authorisedWithValidToken();
		beneficiariesByTranType.setPayloadWithValidData();
		beneficiariesByTranType.invokeBeneficiariesByTranTypeApi();
		beneficiariesByTranType.validateResponseCode(ConstantApiUtils.API_STATS_CODE_200);
		beneficiariesByTranType.validatePayload();
	}

}
