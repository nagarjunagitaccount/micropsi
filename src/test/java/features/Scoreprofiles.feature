@scoreprofiles
Feature: Scoreprofiles
  @smoke
  Scenario Outline: Validate score by profiles
    Given customer provides Scoreprofiles endpoint with profiles at excel row "<startindex>" and "<endindex>"and "<modelcount>" dataset
    When  post request to score profiles
    Then  the status code should be matching for profiles "<startindex>"
    Then call jobstatus endpoint to check the status of the batch
    Then call scoreprofiles results endpoint with batchid
    Then  the status code should be matching for profiles "<startindex>"

    Examples:
      |startindex|endindex|modelcount|
      |2|3|3|



  @smoke
  Scenario Outline: Validate score by profiles
    Given customer provides Scoreprofiles endpoint with profiles at excel row "<startindex>" and "<endindex>"and "<modelcount>" dataset
    When  post request to profiles score
    Then  the status code should be matching for profiles "<startindex>"
    Then call jobstatus endpoint to check the status of the batch
    Then call profiles score results endpoint with batchid
    Then  the status code should be matching for profiles "<startindex>"

    Examples:
      |startindex|endindex|modelcount|
      |2|3|3|