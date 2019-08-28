# unox-android-cli
[![Actions Status](https://github.com/icarohs7/unox-android-cli/workflows/ci/badge.svg)](
https://github.com/icarohs7/unox-android-cli/actions)

## Installing
- Build the library fatJar with the command:
```batch
gradle shadowJar
```
- Then get the generated jar on $projectDir/build/libs/unox-android-cli-all.jar
- Put it into the same folder as the nd.bat file located on the root of the repository
- Add this folder to the Windows path

## Usage
- Creating a new project
```
nd new $projectName
```

- Creating a new android app module
```
nd generate androidapp $moduleName
```

- Creating a new android library module
```
nd generate androidlibrary $moduleName
```

- Creating a new javafx desktop app module
```
nd generate javafxapp $moduleName
```
