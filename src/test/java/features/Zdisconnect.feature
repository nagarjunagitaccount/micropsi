Feature:Disconnect
  Disconnect from all Devices

  @smoke
  Scenario: Verify if User is able to disconnect from all devices
    Given User Sets disconnect api endpoint
    When User sends post request to disconnect
    And User receive valid Http response code for disconnect "200"
    Then validate if api returns success response for disconnect "true"

    
   

  