package step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.hamcrest.Matchers.*;
public class stepdefination {
	 private RequestSpecification request;
	    private Response response;

	    @Given("I set the base URI")
	    public void setBaseUri() {
	        RestAssured.baseURI = "http://localhost:1000";
	        request = RestAssured.given().header("Content-Type", "application/json");
	    }

	    @When("I send a POST request to {string} with a JSON body")
	    public void sendPostRequest(String endpoint) {
	        String jsonBody = "{\"username\": \"JohnDoe\", \"password\": \"Password123\", \"email\": \"johndoe@example.com\"}";
	        response = request.body(jsonBody).post(endpoint);
	    }

	    @When("I send a GET request to {string}")
	    public void sendGetRequest(String endpoint) {
	        response = request.get(endpoint);
	    }

	    @Then("I should get a {int} status code")
	    public void validateStatusCode(int statusCode) {
	        response.then().statusCode(statusCode);
	    }

	    @Then("the response should contain the registered user's details")
	    public void validateUserDetails() {
	        response.then().body("username", equalTo("JohnDoe")).body("email", equalTo("johndoe@example.com"));
	    }

	    @Then("the response should indicate successful login")
	    public void validateLoginResponse() {
	        response.then().body("message", equalTo("Login successful"));
	    }

	    @Then("the response should contain all users' details")
	    public void validateAllUsers() {
	        response.then().body("users", not(empty()));
	    }
	    @Then("the response should contain the product name {string}")
	    public void validateSearchedProduct(String productName) {
	        response.then().body("[0].name", equalTo(productName));
	    }
	   
}

