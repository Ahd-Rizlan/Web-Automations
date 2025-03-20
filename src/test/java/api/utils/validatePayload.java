package api.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import static api.utils.ConstantApiUtils.TXT_AUTHORIZATION;
import static utils.CommonUtils.USER_DIR;

public class validatePayload {
    private FileReader file;
    private JSONObject jsonObject;
    private JSONParser jsonParser = new JSONParser();

    private baseRequest baseRequest;

    public validatePayload(baseRequest baseRequest) {
        this.baseRequest = baseRequest;
    }

    public void authorisedWithValidToken() {
        baseRequest.headersMap.put(TXT_AUTHORIZATION, ConstantApiUtils.TXT_AUTHORIZATION_VALID_VAL);
    }

    public void authorisedWithInValidToken() {
        baseRequest.headersMap.put(TXT_AUTHORIZATION, ConstantApiUtils.TXT_AUTHORIZATION_INVALID_VAL);
    }


    public void setPayloadWithValidData(String PathToPayload, String jsonPayloadObject, Map<String, String> dataToBeAdded) throws IOException, ParseException {
        baseRequest.jsonBody = new File(USER_DIR.concat(PathToPayload));
        file = new FileReader(baseRequest.jsonBody);
        jsonObject = (JSONObject) jsonParser.parse(file);
        JSONObject testFeature = (JSONObject) jsonObject.get(jsonPayloadObject);
        for (Map.Entry<String, String> entry : dataToBeAdded.entrySet()) {
            testFeature.put(entry.getKey(), entry.getValue());
        }
        System.out.println("JSON- OBJECT" + testFeature.toString());
        FileWriter writer = new FileWriter(baseRequest.jsonBody, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void setPayloadWithInValidData(String PathToPayload, String jsonPayloadObject, String dataTobeAdded, String InvalidData) throws IOException, ParseException {
        baseRequest.jsonBody = new File(USER_DIR.concat(PathToPayload));
        file = new FileReader(baseRequest.jsonBody);
        jsonObject = (JSONObject) jsonParser.parse(file);
        JSONObject testFeature = (JSONObject) jsonObject.get(jsonPayloadObject);
        testFeature.put(dataTobeAdded, InvalidData);
        FileWriter writer = new FileWriter(baseRequest.jsonBody, false); //overwrites the content of file
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void setBaseRequest(api.utils.baseRequest baseRequest) {
        this.baseRequest = baseRequest;

    }
}
