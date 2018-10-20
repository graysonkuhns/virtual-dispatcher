#!/usr/bin/env bash

# Build the application
mvn clean install

# Run the application
java -jar target/virtual-dispatcher-0.1.0-SNAPSHOT.jar server config.yml
