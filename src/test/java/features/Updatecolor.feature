Feature: Update Color
  User updates the Color

  @smoke
  Scenario Outline: Verify if User is able to update the Color of a device
    Given User Sets Color api endpoint Color as "<colorcode>"
    When User sends post request to Color
    And User receive valid Http response code for Color "<statusCode>"
    Then validate if api returns success response for Color "<success>"
    And verify if the color is updated in state Api "<attribute>"
    Examples:
      |colorcode |statusCode|success|attribute|
      |#00ff00|200|true|color|
      |#ffffff|200|true|color|
      |#00FF00|200|true|color|
      |#FFFFFF|200|true|color|

    
   

  