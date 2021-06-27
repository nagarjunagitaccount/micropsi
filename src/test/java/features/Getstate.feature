Feature: State
  User gets the state of the Connected device

  @smoke
  Scenario: Verify if User successfully receives the state of th device
    Given User Sets state  api endpoint
    When User sends get request to state
    Then User receive valid Http response code for state "200"

    
   

  