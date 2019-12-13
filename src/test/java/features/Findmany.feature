@Findmany
Feature: Findmany
Find many
  @smoke
  Scenario Outline: Findmany Basic
    Given customer provides Findmany endpoint with profile details at excel row "<startindex>" and "<endindex>"
    When  post request to findmany basic
    Then  the status code should be matching for findmany basic "<startindex>"
    Then call jobstatus endpoint to check the status
    Then call findmany results endpoint with batchid
    Then  the status code should be matching for findmany basic "<startindex>"

    Examples:
      |startindex|endindex|
      |2|8|


  @smoke
  Scenario Outline: Findmany full
    Given customer provides Findmany endpoint with profile details at excel row "<startindex>" and "<endindex>"
    When  post request to findmany full
    Then  the status code should be matching for findmany basic "<startindex>"
    Then call jobstatus endpoint to check the status
    Then call findmany results endpoint with batchid
    Then  the status code should be matching for findmany basic "<startindex>"

    Examples:
      |startindex|endindex|
      |2|3|


  @smoke
  Scenario Outline: Profiles summary
    Given customer provides Findmany endpoint with profile details at excel row "<startindex>" and "<endindex>"
    When  post request to Profiles Summary
    Then  the status code should be matching for findmany basic "<startindex>"
    Then call jobstatus endpoint to check the status
    Then call Profiles summary results endpoint with batchid
    Then  the status code should be matching for findmany basic "<startindex>"

    Examples:
      |startindex|endindex|
      |2|3|