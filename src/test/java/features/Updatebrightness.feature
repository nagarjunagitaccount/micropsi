Feature: Update Brightness
  User updates the brightness

  @smoke
  Scenario Outline: Verify if User is able to update the brightness of a device
    Given User Sets brightness api endpoint brightness "<brightness>"
    When User sends post request to brightness
    And User receive valid Http response code for update "<statusCode>"
    Then validate if api returns success response for update "<success>"
    And verify if the value is updated in state Api "<attribute>"
    Examples:
      |brightness |statusCode|success|attribute|
      |1.0|200|true|brightness|
      |1.1|200|true|brightness|
      |1.999|200|true|brightness|
      |9|200|true|brightness|
      #|10|200|true|brightness|

    
   

  