@ellucian
Feature: ellucian
ellucian
  @smoke
  Scenario Outline: Ellucian
    Given customer provides ellucian endpoint with address at excel row "<row_index>" dataset
    When  post request to ellucian
    Then  the status code should be matching for ellucian "<row_index>"
    And validate Schema for ellucian

    Examples:
      |row_index|
      |2|

