### Generate maven project ###
`
mvn archetype:generate -DgroupId=ua.com.alevel -DartifactId=app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
`
### Create jar file ###
`
mvn clean install
`

### Run program ###
`
java -jar target/app-1.0-SNAPSHOT.jar
`