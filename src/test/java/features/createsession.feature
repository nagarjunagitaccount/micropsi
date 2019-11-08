@Smoke
Feature: Create session
  Get session key from create session endpoint

  Scenario Outline::create session
    Given customer provides createsession endpoint with duration at excel row "<row_index>"
    When post to createsession api
    Then validate response code at excel row "<row_index>"
    Examples:
      | row_index |
         |1|
