# Simple Registration Example
A JavaFX Application demonstrating how to implement a simplistic approach of a registration use case using the following technologies:
* [Java 16](https://www.oracle.com/java/technologies/javase-downloads.html)
* [JavaFX](https://openjfx.io/openjfx-docs/) (as GUI)
* [Maven](https://maven.apache.org/)
* [Nitrite Java](https://www.dizitart.org/nitrite-database.html) (as Database)

## Prerequisites
To be able to install and run this project, please make sure you have installed Java 11 or higher. Otherwise, the setup will note work!
To check your java version, please run `java -version` in the command line.

To install a newer version of Java, you can go to [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://jdk.java.net/).

It would be good if you also installed Maven to your system. To check if you have Maven installed run `mvn -version`.

If you need to install any of them, please refer to this [Maven tutorial](https://www.baeldung.com/install-maven-on-windows-linux-mac) .

Make sure you install JavaFX SDK on your machine, using the instructions provided in the [Official Documentation](https://openjfx.io/openjfx-docs/#install-javafx). Make sure to export the `PATH_TO_FX` environment variable, or to replace it in every command you will find in this documentation from now on, with the `path/to/javafx-sdk-15.0.1/lib`.

_Note: you can download version 15 of the javafx-sdk, by replacing in the download link for version 16 the `16` with `15`._

## Setup & Run
To set up and run the project locally on your machine, please follow the next steps.

### Clone the repository
Clone the repository using:
```git
git clone https://github.com/fis2021/Rezervare_cabana
```

### Verify that the project Builds locally
Open a command line session and `cd Rezervare_cabana`.
If you have installed all the prerequisites, you should be able to run any of the following commands:
```
mvn clean install
```
If you prefer to run using the wrappers, you could also build the project using 
```
./mvnw clean install (for Linux or MacOS)
or 
mvnw.cmd clean install (for Windows)

```

### Open in IntelliJ IDEA
To open the project in IntelliJ idea, you have to import it as a Maven project, depending on what you prefer. 
After you import it, in order to be able to run it, you need to set up your IDE according to the [official documentation](https://openjfx.io/openjfx-docs/). Please read the section for `Non-Modular Projects from IDE`.
If you managed to follow all the steps from the tutorial, you should also be able to start the application by pressing the run key to the left of the Main class.

### Run the project with Maven
The project has already been setup for Maven according to the above link.
To start and run the project use one of the following commands:
* `mvn javafx:run` or `./mvnw javafx:run` (run the `run` goal of the `javafx` maven plugin)


To understand better how to set up a project using JavaFX 11+ and [Maven](https://openjfx.io/openjfx-docs/#maven), please check the [official OpenJFX documentation](https://openjfx.io/). 