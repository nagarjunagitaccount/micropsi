@addressbasic
Feature: Findone by Address by Basic
Get profile details by Address

  Scenario Outline: Validate primary segmentation category 1: 1|0 through 1|5
    Given customer provides find_one endpoint with valid address at excel row "<row_index>" dataset
    When  post request to find_one address basic
    Then  the status code should be matching at excel row "<row_index>"
    And   validateresponse at jsonpath "giving.p2g_score.text" and "giving.p2g_score.value" at excel row "<row_index>"

    Examples:
      |row_index|
      |1|
      |2|
      |3|
      |4|
      |5|
      |6|
      |7|

  Scenario Outline: Validate primary segmentation category 2: 2|1 through 2|5
    Given customer provides find_one endpoint with valid address at excel row "<row_index>" dataset
    When  post request to find_one address basic
    Then  the status code should be matching at excel row "<row_index>"
    And   validateresponse at jsonpath "giving.p2g_score.text" and "giving.p2g_score.value" at excel row "<row_index>"

    Examples:
      |row_index|
      |8|
      |9|
      |10|
      |11|
      |12|

  Scenario Outline: Validate primary segmentation category 3: 3|1 through 3|6
    Given customer provides find_one endpoint with valid address at excel row "<row_index>" dataset
    When  post request to find_one address basic
    Then  the status code should be matching at excel row "<row_index>"
    And   validateresponse at jsonpath "giving.p2g_score.text" and "giving.p2g_score.value" at excel row "<row_index>"

    Examples:
      |row_index|
      |13|
      |14|
      |15|
      |16|
      |17|
      |18|
  @smoke
  Scenario Outline: Validate primary segmentation category 4
    Given customer provides find_one endpoint with valid address at excel row "<row_index>" dataset
    When  post request to find_one address basic
    Then  the status code should be matching at excel row "<row_index>"
    And   validateresponse at jsonpath "giving.p2g_score.text" and "giving.p2g_score.value" at excel row "<row_index>"

    Examples:
      |row_index|
      |19|


    
   

  