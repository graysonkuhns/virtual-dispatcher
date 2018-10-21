#!/usr/bin/env bash

curl \
  -H "Accept: application/json" \
  -H "Content-Type: application/json" \
  -X POST \
  --data '{"operational":false}' \
  http://127.0.0.1:8080/api/aircraft/1