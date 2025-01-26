package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiUtils;

public class ApiTest {

    private final String baseUrl = "https://reqres.in";

    @Test
    public void testCreateRetrieveAndUpdateUser() {
        // Step 1: Create User
        String createPayload = "{\"name\":\"John Doe\", \"job\":\"Developer\"}";
        Response createResponse = ApiUtils.sendPostRequest(baseUrl, "/api/users", createPayload);
        Assert.assertEquals(createResponse.statusCode(), 201, "User creation failed!");
        int userId = createResponse.jsonPath().getInt("id");

        // Step 2: Retrieve User
        Response retrieveResponse = ApiUtils.sendGetRequest(baseUrl, "/api/users/" + userId);
        Assert.assertEquals(retrieveResponse.statusCode(), 200, "User retrieval failed!");

        // Step 3: Update User
        String updatePayload = "{\"name\":\"Jane Doe\", \"job\":\"Manager\"}";
        Response updateResponse = ApiUtils.sendPutRequest(baseUrl, "/api/users/" + userId, updatePayload);
        Assert.assertEquals(updateResponse.statusCode(), 200, "User update failed!");
    }

    @Test
    public void testRetrieveNonExistentUser() {
        Response response = ApiUtils.sendGetRequest(baseUrl, "/api/users/gkjggk");
        Assert.assertEquals(response.statusCode(), 404, "Expected 404 for non-existent user!");
    }
}
