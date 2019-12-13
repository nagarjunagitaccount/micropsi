@Megacall
Feature: Megacall
  Get profile details
  @smoke
  Scenario Outline: Megacall
    Given customer provides megacall endpoint with profile details at excel row "<start_index>" and "<end_index>" dataset
    When  post request to megacall
    Then  the status code should be matching for megacall "<start_index>"
    Then call megacall jobstatus endpoint to check the status
    Then call megacall results endpoint with batchid
    Then  the status code should be matching for megacall "<start_index>"


    Examples:
      |start_index|end_index|
      |2|3|