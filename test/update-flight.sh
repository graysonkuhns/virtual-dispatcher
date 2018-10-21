#!/usr/bin/env bash

curl \
  -H "Accept: application/json" \
  -H "Content-Type: application/json" \
  -X POST \
  --data '{"started":true}' \
  http://127.0.0.1:8080/api/flights/5

curl \
  -H "Accept: application/json" \
  -H "Content-Type: application/json" \
  -X POST \
  --data '{"completed":true}' \
  http://127.0.0.1:8080/api/flights/5