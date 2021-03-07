@echo off
Rem -----CONSOLE-----

Rem -----Compile-----
javac -sourcepath ./src -d build/classes -cp ./libs/commons-collections4-4.4.jar;./libs/commons-lang3-3.11.jar src/ua/com/alevel/test1/Test1.java src/ua/com/alevel/test2/Test2.java src/ua/com/alevel/Main.java
Rem -----Run-----
java -cp build/classes;./libs/commons-collections4-4.4.jar;./libs/commons-lang3-3.11.jar ua.com.alevel.Main
