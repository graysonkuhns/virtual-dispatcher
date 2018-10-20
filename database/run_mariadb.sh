#!/usr/bin/env bash

docker run \
  --name mariadb \
  -e MYSQL_ROOT_PASSWORD="virtual-dispatcher" \
  -v "${HOME}/mariadb":/var/lib/mysql \
  --network host \
  -d \
  mariadb:10.3
