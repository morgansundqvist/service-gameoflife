# service-gameoflife

A REST api implementation of [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life) in Java and Spring Boot 3

# Introduction

An example implementation of Conway's Game of Life.

# Prerequisits

1. JDK 18 or higher
2. Gradle
3. Spring Boot
4. jacoco (for test coverage report)

# Setup and installation

## Clone the project

Clone this project either through ssh or https

## Build the project

You can either use

```
make build
```

or

```
./gradlew clean build
```

## Docker

You can either build and run the docker image manually by

```
docker build .
docker run
```

Or with docker compose

```
docker compose up
```

## Running

You can start the application with

```
make run
```

or

```
./gradlew bootRun
```

## Testing

In order to execute the tests and get a test coverage report please execute the following commands

```
make build
```

```
./gradlew jacocoTestReport
```

The test report can then be found in

build/reports/jacoco/test/html/
