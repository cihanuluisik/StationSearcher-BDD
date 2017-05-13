Feature: As a user I want to be able to check service health and invalid service APIs
  Scenario Outline: Check API
    Given the service is <status> and running
    And I am on a RESTFull client screen
    When I call the <service> API
    Then the response code should be <response code>
    And the response body should be given <json message>
    Examples:
      |status         |service                      |response code        |json message       |
      |up             |"health"                     |200                  |"{'status':'UP'}"  |
      |up             |"searchAllThatDoesNotExist"  |404                  |""                 |
      |not up         |"searchAll"                  |503                  |""                 |