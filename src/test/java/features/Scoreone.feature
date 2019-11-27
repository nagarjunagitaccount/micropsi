@scoreone
Feature: Scoreone
  Get scoreby email,address,phone
  @smoke
  Scenario Outline: Validate Score recieved for email
    Given customer provides Scoreone endpoint with email at excel row "<row_index>" dataset
    When  post request to scoreone email
    Then  the status code should be matching for scoreone "<row_index>"


    Examples:
      |row_index|
      |2|

  @smoke
  Scenario Outline: Validate Score recieved for Address
    Given customer provides Scoreone endpoint with Address at excel row "<row_index>" dataset
    When  post request to scoreone address
    Then  the status code should be matching for scoreone "<row_index>"


    Examples:
      |row_index|
      |2|
  @smoke
  Scenario Outline: Validate Score recieved for phone
    Given customer provides Scoreone endpoint with phone at excel row "<row_index>" dataset
    When  post request to scoreone phone
    Then  the status code should be matching for scoreone "<row_index>"


    Examples:
      |row_index|
      |2|

  @smoke
  Scenario Outline: Validate score by profile
    Given customer provides Scoreone endpoint with summary at excel row "<row_index>" and "<modelcount>" dataset
    When  post request to score profile
    Then  the status code should be matching for scoreone "<row_index>"


    Examples:
      |row_index|modelcount|
      |2|3|