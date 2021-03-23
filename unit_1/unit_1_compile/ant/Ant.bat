@echo off
Rem -----ANT-----

Rem -----Set Environment-----
call setantenv.bat
Rem -----Clean, Compile, Jar and Run-----
ant clean compile jar run
