#!/usr/bin/env bash

# Build the application Docker image
docker build -t virtual-dispatcher:1.0.0 .

# Stop the service
docker stop virtual-dispatcher
docker rm -f virtual-dispatcher

# Run the application
docker run \
  --name virtual-dispatcher \
  --network host \
  -d \
  virtual-dispatcher:1.0.0
