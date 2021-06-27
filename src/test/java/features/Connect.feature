Feature: Connection to the The IP
  User Connects to the IP

  @smoke
  Scenario: Verify if User connects to the Ip Address Sucessfully
    Given User Sets connect Service api endpoint
    When User sends post request to connect
    Then User receive valid Http response code for connect "200"
    And validate if api returns success response as "true"

    
   

  