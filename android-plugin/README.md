Creedengo Android Java SonarQube plugin
================================

![Logo Ekko](docs/ekko-sonar.png)

Creedengo Android Java SonarQube plugin is an "eco-responsibility" static code analyzer for native Android projects written in Java (Kotlin unsupported yet). Its aim is to detect code smells to indicate weither the source code can be improved to reduce their environmental and social impact.

Build and deploy the plugin
---------------------------

### Prerequisites

Maven
-----

This project is driven by maven so this is a required tool.

Use homebrew to install mvn:

```sh
brew install maven
```

### Plugin tests

The basic command line to run all tests is:

```sh
mvn test
```

To run a single test:

```sh
mvn -Dtest=FusedLocationCheckTest test
```

where `FusedLocationCheckTest` is the test to run.

Tests are located under:

- [/src/test/java/io/creedengo/java](./src/test/java/io/creedengo/java)
- [/src/test/java/io/creedengo/xml](./src/test/java/io/creedengo/xml)

### Plugin build

Build CodeNarc (gradle 6.9.2, Java 11):

```sh
cd /codenarc-converter/CodeNarc
./gradlew build -x test 
```

```sh
 cd .\codenarc-converter\CodeNarc\

```

Add custom CodeNarc to Maven dependencies:

```sh
cd /codenarc-converter
mvn initialize
```

Build codenarc-converter:

```sh
cd /codenarc-converter
mvn clean package
```

Build android-plugin:

```sh
cd /android-plugin
mvn clean package
```

### Local deployment using docker

**This implies to have a machine ready to run containerized applications.** Please refer to Docker documentation: https://www.docker.com/.

The project uses a 8.6 docker instance of SonarQube to run.

Pop docker containers by using:

```sh
docker-compose up --build -d && docker ps
```

The tests instance of SonarQube with the plugin will then be available at: http://localhost:9000  (admin / admin)

Plugin usage
------------

The plugin contains two sets of rules, one for Java and one for XML. They can be found in the **Quality profiles** section of
SonarQube (*Creedengo* profiles).
Do not forget to set them as "DEFAULT" or to configure your Android project on SonarQube to use these profiles before performing an
analysis.

When done, just use SonarQube as you normally do for your classical Android clean code analysis.

Plugin development
------------------

### Plugin architecture

For Java rules:

- Java rules’ code can be found in: [./src/main/java/io/creedengo/java](./src/main/java/io/creedengo/java)
- Java rules’ definition can be found in: [./src/main/resources/org/sonar/l10n/java/rules/squid](./src/main/resources/org/sonar/l10n/java/rules/squid).
  Please do not add / or modify rules here since the code is generated. To update or add a rule, please reach us though our mail
  <contact@green-code-initiative.org>, prior to submitting any code change.
- Java rules’ tests can be found in: [./src/test/java/io/creedengo/java](./src/test/java/io/creedengo/java). One file = one rule.

For XML rules:

- XML rules’ code can be found in: [./src/main/java/io/creedengo/xml](./src/main/java/io/creedengo/xml)
- XML rules’ definition can be found in: [./src/main/resources/io/creedengo/xml/rules](./src/main/resources/io/creedengo/xml/rules).
  Please do not add / or modifiy rules here since the code is generated. To update or add a rule, please reach us though our mail
  <contact@green-code-initiative.org>, prior to submitting any code change.
- XML rules’ tests can be found in: [./src/test/java/io/creedengo/xml](./src/test/java/io/creedengo/xml). One file = one rule.
