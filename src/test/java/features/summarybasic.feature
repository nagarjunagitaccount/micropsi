
Feature: Summary Basic API
Get profile details by Address,email,phone
 
  Scenario: WE Customer Calls Summary API with  Address to get the Profile details
   
    Given A valid api key and Address for SummaryApi
    #Given Prepare the request for summary api with Address "Firstname","LastName","Address","City","State","Zip"
    When Invoke the post Api
    Then the status code should be "Statuscode"
    And validateresponse "giving.p2g_score.text"
    
   

  