package api.utils;


import api.methods.baseMethod;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class validateResponse {
    private baseRequest baseRequest ;

    public void setBaseRequest(api.utils.baseRequest baseRequest) {
        this.baseRequest = baseRequest;
    }

    public validateResponse(baseRequest baseRequest) {
        this.baseRequest =baseRequest;
    }

    public void validateResponse(String JsonResponseFile) {
        new baseMethod.PayloadValidator().validateJsonFileWithResponse( JsonResponseFile, baseRequest.getResponse());
    }

//    public void setResponseWithValidUniqueId(String ResponseJsonPayloadObject, String ResponseResponseHeader,String KeyToAppendPayload,String InputToAppendPayload) throws IOException, ParseException {
//        JSONParser jsonParser = new JSONParser();
//        FileReader  file = new FileReader(baseRequest.getPOST_BODY());
//        if (file == null) {
//            throw new IllegalStateException("File path is null!");
//        }
//        JSONObject jsonObject = (JSONObject) jsonParser.parse(file);
//        JSONObject payloadObject = (JSONObject) jsonObject.get(ResponseJsonPayloadObject);
//        JSONObject responseHeader = (JSONObject) payloadObject.get(ResponseResponseHeader);
//        responseHeader.put(KeyToAppendPayload,InputToAppendPayload);
//        FileWriter writer = new FileWriter(baseRequest.jsonBody, false);
//        writer.write(jsonObject.toString());
//        writer.close();
//    }
public void setResponseWithValidUniqueId(String ResponseJsonPayloadObject, String ResponseResponseHeader, String KeyToAppendPayload, String InputToAppendPayload) throws IOException, ParseException {
    JSONParser jsonParser = new JSONParser();
    String filePath = baseRequest.getPOST_BODY();

    // Check for null file path
    if (filePath == null) {
        throw new IllegalStateException("File path is null!");
    }

    // Check if file exists
    File file = new File(filePath);
    if (!file.exists()) {
        throw new IllegalStateException("File does not exist: " + filePath);
    }

    try (FileReader fileReader = new FileReader(file)) {
        JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);

        // Log or print the content of jsonObject to debug
        System.out.println("JSON Object: " + jsonObject.toString());

        // Check if the ResponseJsonPayloadObject key exists
        JSONObject payloadObject = (JSONObject) jsonObject.get(ResponseJsonPayloadObject);

        if (payloadObject == null) {
            throw new IllegalArgumentException("Key " + ResponseJsonPayloadObject + " not found in the JSON payload.");
        }

        // Modify this check depending on the expected structure of the JSON
        JSONObject responseHeader = (JSONObject) payloadObject.get(ResponseResponseHeader);

        if (responseHeader == null) {
            throw new IllegalArgumentException("Key " + ResponseResponseHeader + " not found in the payload object.");
        }

        responseHeader.put(KeyToAppendPayload, InputToAppendPayload);

        // Write updated JSON back to the file
        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write(jsonObject.toString());
        }
    } catch (IOException | ParseException e) {
        e.printStackTrace();
        throw new RuntimeException("Error processing the file", e);
    }
}


    //todo validate it to exlude the files
    //todo also make sure that responces are neglected
}
