# city-connections

Description:
       
 - program determines if two cities are connected
 - List of roads is available in a file city.txt 
 - File contains a list of city pairs (one pair per line, comma separated), which indicates that there’s a road between those cities
 - It will be deployed as a spring-boot app and expose one endpoint:      
     http://localhost:8080/connected?origin=Hoboken&destination=Weehawken
 - respond with ‘yes’ if city1 is connected to city2, ’no’ if city1 is not connected to city2
 - Swagger URL : http://localhost:8080/swagger-ui.html 
 

Assumptions: 
- all the routes between cities provided in the input file are biDirectional
- Only one input file is available and will be loaded during the application start up. 
- Assuming input file size is less then 500 MB 
- After any changes to input file, it required to restart the service.
- origin and destination parameters must be provided

Maven Command to run: 
- mvn spring-boot:run -Drun.jvmArguments="-Xmx1024m"  

