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

  Scenario: Login with incorrect credentials
    Given I set the base URI
    When I send a POST request to "/users/login" with incorrect credentials
    Then I should get a 401 status code
    And the response should indicate a failed login attempt

  Scenario: Fetch all users
    Given I set the base URI
    When I send a GET request to "/data"
    Then I should get a 200 status code
    And the response should contain all users' details

  Scenario: Search for a product by name
    Given I set the base URI
    When I send a GET request to "/products/search?name=Wheat"
    Then I should get a 200 status code
    And the response should contain the searched product details

  Scenario: Filter products by category
    Given I set the base URI
    When I send a GET request to "/products/filter?category=Grocery2"
    Then I should get a 200 status code
    And the response should contain the filtered product details

  Scenario: Add a new product
    Given I set the base URI
    When I send a POST request to "/products" with a JSON body for a new product
    Then I should get a 201 status code
    And the response should contain the added product's details


  Scenario: Place a new order
    Given I set the base URI
    When I send a POST request to "/orders" with a JSON body for a new order
    Then I should get a 201 status code
    And the response should contain the order's details

  Scenario: Retrieve order details by ID
    Given I set the base URI
    When I send a GET request to "/orders/1"
    Then I should get a 200 status code
    And the response should contain the order details

  Scenario: Update user profile
    Given I set the base URI
    When I send a PUT request to "/users/1" with a JSON body to update the profile
    Then I should get a 200 status code
    And the response should contain the updated user details

  Scenario: Delete a user account
    Given I set the base URI
    When I send a DELETE request to "/users/1"
    Then I should get a 204 status code
    And the user should be removed from the system
