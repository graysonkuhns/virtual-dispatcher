#!/usr/bin/env bash

docker run \
  --name mariadb \
  -e MYSQL_ROOT_PASSWORD="virtual-dispatcher" \
  -v "${HOME}/mariadb":/var/lib/mysql \
  -p 3306:3306 \
  -d \
  mariadb:10.3
