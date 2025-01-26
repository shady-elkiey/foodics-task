package utils;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    private static final Logger logger = LoggerFactory.getLogger(ApiUtils.class);

    public static Response sendPostRequest(String baseUrl, String endpoint, String payload) {
        try {
            logger.info("Sending POST request to: {}{}", baseUrl, endpoint);
            logger.info("Payload: {}", payload);
            Response response = given()
                    .baseUri(baseUrl)
                    .contentType("application/json")
                    .body(payload)
                    .when()
                    .post(endpoint);

            logResponse(response);
            return response;
        } catch (Exception e) {
            logger.error("Failed to send POST request. Error: {}", e.getMessage());
            throw new RuntimeException("POST request failed: " + e.getMessage(), e);
        }
    }

    public static Response sendGetRequest(String baseUrl, String endpoint) {
        try {
            logger.info("Sending GET request to: {}{}", baseUrl, endpoint);
            Response response = given()
                    .baseUri(baseUrl)
                    .when()
                    .get(endpoint);

            logResponse(response);
            return response;
        } catch (Exception e) {
            logger.error("Failed to send GET request. Error: {}", e.getMessage());
            throw new RuntimeException("GET request failed: " + e.getMessage(), e);
        }
    }

    public static Response sendPutRequest(String baseUrl, String endpoint, String payload) {
        try {
            logger.info("Sending PUT request to: {}{}", baseUrl, endpoint);
            logger.info("Payload: {}", payload);
            Response response = given()
                    .baseUri(baseUrl)
                    .contentType("application/json")
                    .body(payload)
                    .when()
                    .put(endpoint);

            logResponse(response);
            return response;
        } catch (Exception e) {
            logger.error("Failed to send PUT request. Error: {}", e.getMessage());
            throw new RuntimeException("PUT request failed: " + e.getMessage(), e);
        }
    }

    private static void logResponse(Response response) {
        logger.info("Response Status Code: {}", response.statusCode());
        logger.info("Response Body: {}", response.body().asPrettyString());
    }
}
