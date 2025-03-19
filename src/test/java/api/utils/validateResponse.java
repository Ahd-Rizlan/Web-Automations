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
    private baseRequest baseRequest;


    public validateResponse(baseRequest baseRequest) {
        this.baseRequest = baseRequest;
    }

    public void validateResponse(String JsonResponseFile) {
        new baseMethod.PayloadValidator().validateJsonFileWithResponse(JsonResponseFile, baseRequest.getResponse());
    }

    public void validateResponseWithExcludedDataFields(String JsonResponseFile,String [] ignores){
        new baseMethod.PayloadValidator().validateJsonFileWithExcludedDataFields(JsonResponseFile, baseRequest.getResponse(),ignores);
    }
    public void setResponseWithValidUniqueId(String ResponseJsonPayloadObject, String ResponseResponseHeader, String KeyToAppendPayload, String InputToAppendPayload) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        String filePath = baseRequest.getPOST_BODY();
        File file = new File(filePath);
        try (FileReader fileReader = new FileReader(file)) {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);
            System.out.println("JSON Object: " + jsonObject.toString());
            JSONObject payloadObject = (JSONObject) jsonObject.get(ResponseJsonPayloadObject);
            JSONObject responseHeader = (JSONObject) payloadObject.get(ResponseResponseHeader);
            responseHeader.put(KeyToAppendPayload, InputToAppendPayload);
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
