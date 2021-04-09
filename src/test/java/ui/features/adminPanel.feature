Feature: Admin panel

  Scenario: Login
    Given I setup browser
    Given open login page
    And fill username field "admin1"
    And fill password field "[9k<k8^z!+$$GkuP"
    When click sign-in button
    Then I should be logged-in

  Scenario: View players table
    Given I open players page
    When I see players table
    Then It should be loaded
    Then I sort table by username

  Scenario: Close browser
    Then close browser