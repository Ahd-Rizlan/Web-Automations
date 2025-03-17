package api.utils;

import api.utils.ConstantApiUtils;
import api.utils.baseRequest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static api.utils.ConstantApiUtils.TXT_AUTHORIZATION;
//import static api.utils.ConstantApiUtils.USER_DIR;
import static utils.CommonUtils.*;
public class validatePayload {
    //    private  File jsonBody;
    private FileReader file;
    private JSONObject jsonObject;
    private JSONParser jsonParser = new JSONParser();

    private baseRequest baseRequest ;
    public validatePayload(baseRequest baseRequest) {
        this.baseRequest =baseRequest;
    }
    public void authorisedWithValidToken() {
        baseRequest.headersMap.put(TXT_AUTHORIZATION, ConstantApiUtils.TXT_AUTHORIZATION_VALID_VAL);
    }

    public void authorisedWithInValidToken() {
        baseRequest.headersMap.put(TXT_AUTHORIZATION, ConstantApiUtils.TXT_AUTHORIZATION_INVALID_VAL);
    }

    public void setPayloadWithValidData(String PathToPayload, String jsonPayloadObject, String dataTobeAdded, String Data) throws IOException, ParseException {
        baseRequest.jsonBody = new File(USER_DIR.concat(PathToPayload));
        file = new FileReader(baseRequest.jsonBody);
        jsonObject = (JSONObject) jsonParser.parse(file);
        JSONObject testFeature = (JSONObject) jsonObject.get(jsonPayloadObject);
        testFeature.put(dataTobeAdded, Data);
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


}
