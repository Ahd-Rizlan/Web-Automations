package api.utils;

import io.restassured.path.json.JsonPath;

import java.util.List;
import java.util.Map;

import static utils.DataStoreReadWriteApi.storeAPIDetails;

public class helper {
    private final baseRequest baseRequest;

    public helper(baseRequest baseRequestObject) {
        this.baseRequest = baseRequestObject;

    }

    public void extractUniqueID(String accountName) {
        JsonPath jsonPath = baseRequest.getResponse().jsonPath();
        List<Map<String, Object>> beneficiaryList = jsonPath.getList("getTransferPayeeListResponse.beneficiaryListDetails.beneficiaryList");

        for (Map<String, Object> beneficiary : beneficiaryList) {
            String currentAccountName = (String) beneficiary.get("accountName");
            if (accountName.equals(currentAccountName)) {
                Integer beneficiaryID = (Integer) beneficiary.get("beneficiaryID");
                System.out.println("Found beneficiaryID: " + beneficiaryID + " for accountName: " + accountName);
                storeAPIDetails("beneficiaryID", beneficiaryID.toString());  // Store the accountName value
                break;
            }
        }
    }
}
