#!/usr/bin/env bash

# Stop the service
docker stop virtual-dispatcher
docker rm -f virtual-dispatcher

# Build the application Docker image
docker build -t virtual-dispatcher:latest .

# Run the application
docker run \
  --name virtual-dispatcher \
  --network host \
  -d \
  virtual-dispatcher:latest
