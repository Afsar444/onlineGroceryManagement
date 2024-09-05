
  Scenario: Successfully place a new order
    Given the user with ID 2 is logged in
    And the user wants to order product with ID 3 (Sugar)
    And the user wants to purchase 5 units
    When the user places an order with the following details:
      | userId    | productId | quantity | totalPrice |
      | 2         | 3         | 5        | 300        |
    Then the order should be successfully created
    And the system should return a status code of 201
    And the order details should be included in the response
    And the stock quantity of product with ID 3 should be updated