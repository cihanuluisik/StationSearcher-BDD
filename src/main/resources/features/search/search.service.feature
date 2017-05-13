Feature: As a user I want to be able to search for train stations by train station name

  Background:
    Given I am on a RESTFull client screen
    And the service is up an running
    And following train stations exist:
      | name                  |
      | DARTFORD              |
      | DARTMOUTH             |
      | TOWER HILL            |
      | DERBY                 |
      | LIVERPOOL             |
      | LIVERPOOL LIME STREET |
      | PADDINGTON            |
      | EUSTON                |
      | LONDON BRIDGE         |
      | VICTORIA              |

  Scenario: Search for text result
    When I call the searchAll API
    Then the response code should be 200
    And the search should return all the stations available in a JSON format

  Scenario Outline: Search for DERBY
    When I call search API with <name> for text output
    Then the response code should be 200
    And the search should return the text <json>:
    Examples:
      | name          | json                              |
      | "DERBY"       | "{'stations':[{'name':'DERBY'}]}" |
      | "KINGS CROSS" | ""                                |

  Scenario Outline: Search for stations result
    When I call search API with <name> for station list output in json format
    Then the response code should be 200
    And the search should return those stations in Json format :<output stations> :
    Examples:
      | name        | output stations                      |
      | "DART"      | "DARTFORD", "DARTMOUTH"              |
      | "LIVERPOOL" | "LIVERPOOL", "LIVERPOOL LIME STREET" |