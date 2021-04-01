# TheTestO7

Solution of the Outfit7 entry test.

Solution is implemented in Java as fat jar with embeded Jetty.

## General
Technology stack overview:
- Java (duh)
- Maven
- Google ApEngine 
- Google Datastore
- Jetty 
- Resteasy

## How to access
### On Google
- Application is hosed on <code>http://celtic-ego-309013.oa.r.appspot.com/api/adMediation/</code> 
Currently unavailable due to service account issues (waiting for response from google)
### Locally 
- build with maven using <code>mvn clean package</code>
- run <code>java -jar [PATH_TO]product/base/target/TheTest.jar</code>

## How to test
- TODO provide Postman collection

## What is missing
- BL tests
- Setup logging
- Service orchestration
- First time using Datastore, so probably messed up a few things there
- Additional code cleanup of poms
