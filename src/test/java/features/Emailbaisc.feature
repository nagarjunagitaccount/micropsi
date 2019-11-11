  @emailbasic
  Feature: Findone by email by Basic
  Get profile details by email
    @smoke
  Scenario Outline: Validate primary segmentation category 1: 1|0 through 1|5
    Given customer provides find_one endpoint with valid email at excel row "<row_index>" dataset
    When  post request to find_one email basic
    Then  the status code should be matching for emailbasic "<row_index>"
    And   validateresponse at jsonpath "giving.p2g_score.text" and "giving.p2g_score.value" at excel row for email "<row_index>"

    Examples:
      |row_index|
      |2|