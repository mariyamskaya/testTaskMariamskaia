Feature: User login

  Scenario: Login
    Given open login page
    And fill username field "admin1"
    And fill password field "[9k<k8^z!+$$GkuP"
    When click sign-in button
    Then I should be logged-in

  Scenario: Close browser
    Then close browser