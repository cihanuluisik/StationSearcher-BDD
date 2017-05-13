
TECHNOLOGY STACK
Easily can be listed from pom.xml but here in case :

Spring Boots/Data, Jetty, H2, JPA as required
Maven
Cucumber
JUnit



WHAT REST PATH IMPLEMENTED  :

HOST_URL/stations/search/{name}
HOST_URL/stations/searchAll
HOST_URL/health

HOST_URL/stations/search/bring/up      ( Test profile only )
HOST_URL/stations/search/bring/down    ( Test profile only )


HOW TO RUN THE APP

Either
1) In the project root, run 'mvn clean spring-boot:run'
2) or run 'mvn clean package' and deploy the .war file to jetty/webapps folder



HOW TO RUN TESTS :

Either
1) Open the project in Intelli-J. Right click 'test/java' and select "Run 'All Tests'"
or
2 ) Run 'mvn clean test' in the project root.




