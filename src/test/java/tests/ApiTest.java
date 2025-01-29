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
        Assert.assertEquals(createResponse.getBody().jsonPath().getString("name"), "John Doe", "Name is incorrect");
        Assert.assertEquals(createResponse.getBody().jsonPath().getString("job"), "Developer", "Job is incorrect");

        // Step 2: Retrieve User
        Response retrieveResponse = ApiUtils.sendGetRequest(baseUrl, "/api/users/" + userId);
        Assert.assertEquals(retrieveResponse.statusCode(), 200, "User retrieval failed!");
        Assert.assertEquals(createResponse.getBody().jsonPath().getString("name"), "John Doe", "Name is incorrect");
        Assert.assertEquals(createResponse.getBody().jsonPath().getString("job"), "Developer", "Job is incorrect");

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

    @Test
    public void testUpdateUser(){
        String updatePayload = "{\"name\":\"Jane Doe\", \"job\":\"Manager\", \"age\":20}";
        Response updateResponse = ApiUtils.sendPutRequest(baseUrl, "/api/users/" + 2, updatePayload);
        Assert.assertEquals(updateResponse.statusCode(), 200, "User update failed!");
        Assert.assertEquals(updateResponse.getBody().jsonPath().getString("name"), "Jane Doe", "Name is incorrect");
        Assert.assertEquals(updateResponse.getBody().jsonPath().getString("job"), "Manager", "Job is incorrect");
        Assert.assertEquals(updateResponse.getBody().jsonPath().getString("age"), "20", "age is incorrect");
    }

    @Test
    public void testUpdateUserWithoutThePathParameter(){
        String updatePayload = "{\"name\":\"Jane Doe\", \"job\":\"Manager\", \"age\":20}";
        Response updateResponse = ApiUtils.sendPutRequest(baseUrl, "/api/users/", updatePayload);
        Assert.assertEquals(updateResponse.statusCode(), 400, "path parameter is required!");
    }

    @Test
    public void testCreatUserWithUserPathParameter(){
        String createPayload = "{\"name\":\"Jane Doe\", \"job\":\"Manager\", \"age\":20}";
        Response createResponse = ApiUtils.sendPostRequest(baseUrl, "/api/users/2", createPayload);
        Assert.assertEquals(createResponse.statusCode(), 400, "can't add a user that is already existing!");
    }
}
