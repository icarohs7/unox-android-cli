# unox-android-cli

## Description
CLI used to create Android projects and modules

## Installing
- Build the library fatJar with the command:
```batch
gradle shadowJar
```
- Then get the generated jar on $projectDir/build/libs/unox-android-cli-all.jar
- Put it into the same folder as the nd.bat file located on the root of the repository
- Add this folder to the Windows path

## Usage
- Creating a new Android project
```
nd new $projectName
```
A folder with the given $projectName will be created, containing a ci config file for Travis and Gitlab CIs, buildscript files and some
default files for gradle, i.e gradle.properties and local.properties. Also, [buildSrc](https://github.com/icarohs7/unox-buildsrc)
and [corelibrary](https://github.com/icarohs7/unox-android-corelibrary) modules are cloned from their respective repositories and added
to the project.

- Creating a new app module
```
nd generate app $moduleName
```
Create a folder with the last part of the $moduleName, e.g the folder awesomeapp will be created with the $moduleName
com.company.awesomeapp. Some basic infrastructure will also be added to the module, based on the architecture I use on my projects.

- Creating a new library module
```
nd generate library $moduleName
```
Uses the same rules as the app generation command, but creating only a build file, a manifest and some empty folders.
