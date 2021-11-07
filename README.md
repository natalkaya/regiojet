#Simple Tests for RegioJet system

Base UI and API tests

Used:
- JUnit5
- rest-assured
- selenide
- typesafe.config
- allure

Update appropriate settings in the config file ``reference.conf`` if it needs to change base domain URLs

##How to run
- Using IDE
- [Or] using command line from the root project directory: ```./gradlew test```

##Test Report
Allure report can be used to generate test report with tests results

To generate and open a report with tests results:

Using command line:
   1. Make sure that the current directory is a directory with this project
   2. Run command ```./gradlew allureServe```. It will generate a report, then open it automatically  in the default browser
