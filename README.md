Java-Technical-Test
===================


## Instructions

1. Clone this git repository
2. Read the README.md file containing the stories and acceptance tests for the test 
3. Create your local branch
4. Provide tests in your preferred style to support your solution.  
The application has to be developed using spring framework, JPA, H2 as database and Jetty as container. 
Apart from that, you can use your own technology stack for testing and implementation.
5. Please commit every 30 minutes
6. Push to your remote branch ( **not to master** )
7. The user stories are just instructions, if you want you can refactor them as you wish, including the acceptance tests.

## NOTE: DON'T PUSH TO MASTER





Feature:
As a user I want to be able to search for train stations by train station name
  
  
      Background:
        And the following train stations exist:
        |name                 |
        |DARTFORD             |
        |DARTMOUTH            |
        |TOWER HILL           |
        |DERBY                |
        |LIVERPOOL            |
        |LIVERPOOL LIME STREET|
        |PADDINGTON           |
        |EUSTON               |
        |LONDON BRIDGE        |
        |VICTORIA             |



  Scenario: Search for DART
  
        Given the service is up an running
        And I am on a RESTFull client screen
        When I enter the train station name 'DART'
        Then the response code should be 200
        And the search should return a JSON response as following:
          {
            "stations": [
              {
                "name": "DARTFORD"
              },
              {
                "name": "DARTMOUTH"
              }
            ]
          }


  Scenario: Search for LIVERPOOL
  
        Given the service is up an running
        And I am on a RESTFull client screen
        When I enter the train station name 'LIVERPOOL'
        Then the response code should be 200
        And the search should return a JSON response as following:
              {
                "stations": [
                  {
                    "name": "LIVERPOOL"
                  },
                  {
                    "name": "LIVERPOOL LIME STREET"
                  }
                ]
              }
          
   Scenario: Search for DERBY
   
        Given the service is up an running
        And I am on a RESTFull client screen
        When I enter the train station name 'DERBY'
        Then the response code should be 200
        And the search should return a JSON response containing the 'DEBY' station
             
  Scenario: Search for KINGS CROSS
  
      Given the service is up an running
      And I am on a RESTFull client screen
      When the input 'KINGS CROSS'
      Then the search should return no characters and no stations
   
  Scenario: Search for All
  
     Given the service is up an running
     And I am on a RESTFull client screen
     When I call the searchAll API
     Then the response code should be 200
     And the search should return all the stations available in a JSON format
  
  Scenario: service is down
  
     Given the service not is up an running
     And I am on a RESTFull client screen
     When I call the searchAll API
     Then the response code should be 503
     
  Scenario: api doesnt's exist
  
       Given the service is up an running
       And I am on a RESTFull client screen
       When I call the searchAllThatDoesNotExist API
       Then the response code should be 404
       
   Scenario: check health of the service
   
          Given the service is up an running
          And I am on a RESTFull client screen
          When I call the health API
          Then the response code should be 200
          And the response body should contain a JSON message telling that the service is UP
     
