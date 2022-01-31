# Air_Condition_API

Air Condition API is using data collected from breezometer API. It presents information about air condition in choosen location. 

## How to run the project

1. Download the project to your computer. 
2. Extract the file and open the Air_Condition_API-main folder.
3. Open CMD in this location and run:
      ```
      mvn compile
      mvn package 
      ```
   and:
      ` java -jar target/air-0.0.1-SNAPSHOT.jar `
4. Then browser http://localhost:8080/

## How to use the project

To check contiotion in choosen location input the Latitude and Longitude (ex. Latitude: ` 48.857456 ` Longitude: ` 2.354611 `) and select the atributes. Then click ` Check data ` button.
