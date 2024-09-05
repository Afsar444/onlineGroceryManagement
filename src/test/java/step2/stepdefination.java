package step2;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class stepdefination{
	private Response response;
    private String baseURI = "http://localhost:3000";

    @Given("I set the base URI")
    public void i_set_the_base_uri() {
        RestAssured.baseURI = baseURI;
    }

    @When("I send a POST request to {string} with a JSON body")
    public void i_send_a_post_request_to_with_a_json_body(String endpoint) {
        Map<String, Object> requestBody = new HashMap<>();
        if (endpoint.equals("/users/register")) {
            requestBody.put("username", "newuser");
            requestBody.put("password", "newpassword");
            requestBody.put("email", "newuser@example.com");
        } else if (endpoint.equals("/users/login")) {
            requestBody.put("username", "Afsar");
            requestBody.put("password", "Afsar@1999");
        } else if (endpoint.equals("/products")) {
            requestBody.put("name", "NewProduct");
            requestBody.put("category", "Grocery1");
            requestBody.put("price", 25.0);
            requestBody.put("stockQuantity", 50);
        } else if (endpoint.equals("/orders")) {
            requestBody.put("userId", 1);
            requestBody.put("productId", 1);
            requestBody.put("quantity", 3);
            requestBody.put("totalPrice", 60.0);
        }

        response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(endpoint);
    }

    @When("I send a POST request to {string} with incorrect credentials")
    public void i_send_a_post_request_to_with_incorrect_credentials(String endpoint) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("username", "wronguser");
        requestBody.put("password", "wrongpassword");

        response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(endpoint);
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        response = given().get(endpoint);
    }

    @When("I send a PUT request to {string} with a JSON body to update the profile")
    public void i_send_a_put_request_to_with_a_json_body_to_update_the_profile(String endpoint) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("username", "updatedUser");
        requestBody.put("email", "updatedemail@example.com");

        response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .put(endpoint);
    }

    @When("I send a DELETE request to {string}")
    public void i_send_a_delete_request_to(String endpoint) {
        response = given().delete(endpoint);
    }

    @Then("I should get a {int} status code")
    public void i_should_get_a_status_code(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain the registered user's details")
    public void the_response_should_contain_the_registered_user_s_details() {
        response.then().body("username", equalTo("newuser"))
                .body("email", equalTo("newuser@example.com"));
    }

    @Then("the response should indicate successful login")
    public void the_response_should_indicate_successful_login() {
        response.then().body("message", equalTo("Login successful"));
    }

    @Then("the response should indicate a failed login attempt")
    public void the_response_should_indicate_a_failed_login_attempt() {
        response.then().body("message", equalTo("Invalid credentials"));
    }

    @Then("the response should contain all users' details")
    public void the_response_should_contain_all_users_details() {
        response.then().statusCode(200);
        // Additional assertions can be added here based on the expected response format
    }

    @Then("the response should contain the searched product details")
    public void the_response_should_contain_the_searched_product_details() {
        response.then().body("[0].name", equalTo("Wheat"));
    }

    @Then("the response should contain the filtered product details")
    public void the_response_should_contain_the_filtered_product_details() {
        response.then().body("[0].category", equalTo("Grocery2"));
    }

    @Then("the response should contain the added product's details")
    public void the_response_should_contain_the_added_product_s_details() {
        response.then().body("name", equalTo("NewProduct"))
                .body("category", equalTo("Grocery1"));
    }

    @Then("the response should contain the product details")
    public void the_response_should_contain_the_product_details() {
        response.then().body("id", equalTo(1))
                .body("name", equalTo("Wheat"));
    }

    @Then("the response should contain the order's details")
    public void the_response_should_contain_the_order_s_details() {
        response.then().body("userId", equalTo(1))
                .body("productId", equalTo(1));
    }

    @Then("the response should contain the updated user details")
    public void the_response_should_contain_the_updated_user_details() {
        response.then().body("username", equalTo("updatedUser"))
                .body("email", equalTo("updatedemail@example.com"));
    }

    @Then("the user should be removed from the system")
    public void the_user_should_be_removed_from_the_system() {
        response.then().statusCode(204);
    }
}
