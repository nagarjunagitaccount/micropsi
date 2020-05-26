@summaryfull
Feature: Summary full
  Get profile details by Address ,email and phone
  @smoke
  Scenario Outline: Validate primary segmentation category 1: 1|0 through 1|5
    Given customer provides summary endpoint with profile details at excel row "<row_index>" dataset
    When  post request to summary full
    Then  the status code should be matching for summary "<row_index>"
   # And   validateresponse at jsonpath "giving.p2g_score.text" and "giving.p2g_score.value" at excel row for summary "<row_index>"
    And Validate Schema for Summary-Full
    Examples:
      |row_index|
      |2|