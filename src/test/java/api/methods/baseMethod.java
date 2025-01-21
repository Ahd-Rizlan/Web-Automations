package api.methods;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import static api.utils.ConstantApiUtils.*;
import static utils.CommonUtils.*;

public class baseMethod {
    static final Logger LOG = LogManager.getLogger(baseMethod.class);
    public static Properties config = new Properties();
    private FileInputStream fis;
    public FileOutputStream fos;


    @BeforeClass
    public void setupSuiteForAPITests() {

        try {
            fis = new FileInputStream(USER_DIR + ROOT_PATH_TO_API_PROPERTY_FILE);
        } catch (FileNotFoundException e) {
            LOG.error("Exception while adding api-config.properties file", e);
        }

        try {
            config.load(fis);
        } catch (IOException e) {
            LOG.error("Exception while loading api-config.properties file", e);
        }
    }

  @BeforeSuite
    public void getAccessToken() {

        try {
            fis = new FileInputStream(USER_DIR + ROOT_PATH_TO_API_PROPERTY_FILE);
            config.load(fis);
        } catch (IOException e) {
            LOG.error("Exception while adding config.properties file", e);
        }

        String iAmToken = RestAssured.given().relaxedHTTPSValidation()
                .baseUri(config.getProperty("iamHostUrl"))
                .auth().basic(config.getProperty("iamUserName"), config.getProperty("iamPassword"))
                .header(HEADER_CONTENT_TYPE, CONTENT_TYPE_URL)
                .formParam(GRANT_TYPE, GRANT_TYPE_PASSWORD)
                .formParam(USERNAME, USERNAMETEXT)
                .formParam(PASSWORD, PASSWORDTEXT)
                .formParam(SCOPE, SCOPETEXT)
                .when()
                .log()
                .all()
                .post()
                .then()
                .log()
                .all()
                .extract()
                .path("access_token");

        String access_token = RestAssured.given().relaxedHTTPSValidation()
                .baseUri(config.getProperty("apimHostUrl"))
                .auth().basic(config.getProperty("apimUserName"), config.getProperty("apimPassword"))
                .header(HEADER_CONTENT_TYPE, CONTENT_TYPE_URL)
                .formParam(GRANT_TYPE, GRANT_TOKEN)
                .formParam(ASSERTION, iAmToken)
                .formParam(SCOPE, SCOPEPARAM)
                .when()
                .log()
                .all()
                .post()
                .then()
                .log()
                .all()
                .extract()
                .path("access_token");
        config.setProperty("accessToken","Bearer " + access_token);
        try {
            fos = new FileOutputStream(USER_DIR + ROOT_PATH_TO_API_PROPERTY_FILE);
            config.store(fos, null);
        } catch (IOException e) {
            LOG.error("Exception while loading api-config.properties file", e);
        }

    }


    public static class PayloadValidator {
        private final List<String> mismatches = new ArrayList<>(); // List to store mismatches
        private Set<String> fieldsToIgnore = new HashSet<>();

        public void setIgnoreFields(Set<String> fieldsToIgnore) {
            this.fieldsToIgnore = fieldsToIgnore;
        }

        public Map<String, Object> payloadMap(String filePath) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.readValue(new File(filePath), Map.class);
            } catch (IOException e) {
                throw new RuntimeException("Error reading JSON file: " + filePath, e);
            }
        }

        public Map<String, Object> extractResponsePayload(Response response) {
            return response.jsonPath().getMap("");
        }

        public void validatePayload(Object expected, Object actual) {
            if (expected instanceof Map && actual instanceof Map) {
                validateNestedMap((Map<String, Object>) expected, (Map<String, Object>) actual);
            } else if (expected instanceof List && actual instanceof List) {
                validateList((List<Object>) expected, (List<Object>) actual);
            } else {
                String expectedStr = String.valueOf(expected).trim();
                String actualStr = String.valueOf(actual).trim();
                if (!expectedStr.equals(actualStr)) {
                    mismatches.add("Mismatch: Expected = " + expectedStr + ", Actual = " + actualStr);
                }
            }
        }

        private void validateNestedMap(Map<String, Object> expected, Map<String, Object> actual) {
            for (String key : expected.keySet()) {
                if (fieldsToIgnore.contains(key)) {
                    continue; // Skip comparison for ignored fields
                }
                if (!actual.containsKey(key)) {
                    mismatches.add("Missing key in actual: " + key);
                    continue;
                }
                validatePayload(expected.get(key), actual.get(key));
            }

            for (String key : actual.keySet()) {
                if (!expected.containsKey(key) && !fieldsToIgnore.contains(key)) {
                    mismatches.add("Unexpected key in actual: " + key);
                }
            }
        }

        private void validateList(List<Object> expected, List<Object> actual) {
            if (expected.size() != actual.size()) {
                mismatches.add("List size mismatch: Expected = " + expected.size() + ", Actual = " + actual.size());
                return;
            }
            for (int i = 0; i < expected.size(); i++) {
                validatePayload(expected.get(i), actual.get(i));
            }
        }
        public List<String> getMismatches() {
            return mismatches;
        }

        public void assertValidation() {
            if (!mismatches.isEmpty()) {
                System.out.println(mismatches);
                Assert.fail("Payload validation failed with the following mismatches:\n" + String.join("\n", mismatches));
            }
        }

        public void validateJsonFileWithResponse(String filePath, Response response) {
            Map<String, Object> expectedPayload = payloadMap(filePath);
            Map<String, Object> actualPayload = extractResponsePayload(response);
            Set<String> fieldsToIgnore = new HashSet<>();
            fieldsToIgnore.add("timestamp");
            validatePayload(expectedPayload, actualPayload);
            assertValidation();
        }

        public void validateJsonFileWithExcludedDataFields(String filePath, Response response, String [] ignores) {
            Map<String, Object> expectedPayload = payloadMap(filePath);
            Map<String, Object> actualPayload = extractResponsePayload(response);
            Set<String> fieldsToIgnore = new HashSet<>();
            fieldsToIgnore.addAll(List.of(ignores));
            setIgnoreFields(fieldsToIgnore);
            validatePayload(expectedPayload, actualPayload);
            assertValidation();
        }
    }

    public String getTimeStamp () { // get the current time stamp, format it according to the required format and return it as a result
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = now.format(formatter);
        System.out.println(formattedTimestamp);
        return  formattedTimestamp;
    }


}
