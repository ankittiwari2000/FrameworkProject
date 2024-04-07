
@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation Page

    Examples: 
      | name  							| password 		| productName |
      | ankit8112@gmail.com	| Ankit@6351	| ZARA COAT 3 |
      
