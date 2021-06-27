Feature: Update Name
  User updates the Name

  @smoke
  Scenario Outline: Verify if User is able to update the brightness of a device
    Given User Sets Name api endpoint with name as "<name>"
    When User sends post request to Name
    And User receive valid Http response code for Name "<statusCode>"
    Then validate if api returns success response for Name "<success>"
    And verify if the name is updated in state Api "<attribute>"
    Examples:
      |name |statusCode|success|attribute|
      |bulb1|200|true|name|
      ||200|true|name|
      |bulb1|200|true|name|



    
   

  