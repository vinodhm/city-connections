# city-connections

Assumptions: 
- all the routes between cities provided in the input file are biDirectional
- Only one input file is available and will be loaded during the application start up. 
- Assuming input file size is less then 500 MB 
- After any changes to input file, it required to restart the service.

Maven Command to run: 
- mvn spring-boot:run -Drun.jvmArguments="-Xmx1024m"  

