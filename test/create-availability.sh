#!/usr/bin/env bash

curl \
  -H "Accept: application/json" \
  -H "Content-Type: application/json" \
  -X POST \
  --data '{"pilotId":"4"}' \
  http://127.0.0.1:8080/api/availability
