.PHONY: build run clean test up

build:
	./gradlew clean build

run:
	./gradlew bootRun

clean:
	./gradlew clean

test:
	./gradlew clean test

up:
	docker compose up

