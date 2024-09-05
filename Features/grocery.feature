Feature: User Management API Tests

  Scenario: Register a new user
    Given I set the base URI
    When I send a POST request to "/users/register" with a JSON body
    Then I should get a 201 status code
    And the response should contain the registered user's details

  Scenario: Login with correct credentials
    Given I set the base URI
    When I send a POST request to "/users/login" with a JSON body
    Then I should get a 200 status code
    And the response should indicate successful login

  Scenario: Fetch all users
    Given I set the base URI
    When I send a GET request to "/data"
    Then I should get a 200 status code
    And the response should contain all users' details

  Scenario: Search for a product by name
    Given I set the base URI
    When I send a GET request to "/products/search?name=Wheat"
    Then I should get a 200 status code
    And the response should contain the product name "Wheat"
