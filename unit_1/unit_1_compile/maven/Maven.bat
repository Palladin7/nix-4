@echo off
Rem -----MAVEN-----

Rem -----Set Maven-----
mvn archetype:generate -DgroupId=ua.com.alevel -DartifactId=app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
cd app
Rem -----Create jar-----
mvn clean install
Rem -----Run------
java -jar target/app-1.0-SNAPSHOT.jar
