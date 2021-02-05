RestAssured API for Weather - with filter conditions

Installation (Pre-requisites)

* jdk15
* Maven
*IntelliJ IDEA/jdk15
* IntelliJ IDEA - plugins for  cucumber, configuration,compiler, cucumber reporting
*Reports and also in the console:
http://localhost:63342/RestAutomation/reports/html-reports/cucumber-html-reports/report-feature_149025780.html?
  /RestAutomation/target/cucumber-reports/cucumber.html?
  *Dependencies for the reports are included in pom.xml
  

### Features

Feature file includes the scenario of checking on the weather options with scenario outline with examples

### How to run tests
mvn  clean , mvn  install verify used for executing and generating reports.



 * API key and base URL is hardcoded in services package
*  Filters are places in FilterUtility
* mvn  clean , mvn  install verify used for executing and generating reports.
* used jdk15 , mvn compiler plugin is to 10 (target and source)
* Reports are generated in http://localhost:63342/RestAutomation/reports/html-reports/cucumber-html-reports/report-feature_149025780.html?
and /RestAutomation/target/cucumber-reports/cucumber.html?
  * Scenario outline in the feature file
 

Considerations:
* Could use utils for API key and the baseURL


