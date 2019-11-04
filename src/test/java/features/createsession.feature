Feature: Create session
  Get session key from create session endpoint

  Scenario:create session
    Given customer provides createsession endpoint with duration "500"
    When post to createsession api
    Then validate response code "201"
