# AppiumCucumber-Parallel
Run Appium tests parallel on iOS and Android using Cucumber-TestNG

## Pre-requisites
* Appium 1.8.1 and above
* Java 8
* Maven
* Intellij IDE with Cucumber Plugin installed
* For Appium setup and Mac, please refer

## Project Structure
* Configuration package - Hooks, DeviceHelper, LocalDriverManager, ServiceManager
* Runners - Cucumber Runner Test
* Screens - Page Classes with Page Objects defined
* Step Definitions - Screen and its Steps Defs.
* Utils - Reporting and Listeneres
* Features - cucumber features files


## Dependencies
* Appium Java Client
* Cucumber JVM
* Cucumber Java
* json.org
* testng
* Cucumber-extent report

## Test Execution
$ `git clone https://github.com/SudhiHathwar/AppiumCucumber-Parallel.git

$ `mvn clean test`


