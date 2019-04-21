@echo off
:: Command used to execute the cli jar, add this file
:: and the jar named unox-android-cli-all.jar on the
:: same directory included in the Windows path
java -jar "%~dp0\unox-android-cli-all.jar" %*
