Feature: Admin panel

  Scenario: Login
    Given I setup browser
    And I open login page
    And I fill username field "admin1"
    And I fill password field "[9k<k8^z!+$$GkuP"
    When I click sign-in button
    Then I should be logged-in

  Scenario: View players table
    Given I open players page
    When I see players table
    Then It should be loaded
    And I sort table by username

  Scenario: Close browser
    Then I close browser