Feature: List all available devices
  List all available devices

  @smoke
  Scenario: Verify Api Lists all available devices
    Given User Sets Devices Service api endpoint
    When User sends get request to devices
    Then User receive valid Http response code "200"
    And User receive valid response
    And verify if listed Ips are in valid format
    #Todo step ,connect to DB and compare the json response with DB


  