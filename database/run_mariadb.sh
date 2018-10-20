#!/usr/bin/env bash

docker run \
  --name mariadb \
  -e MYSQL_ROOT_PASSWORD="root" \
  -v /home/gkuhns/mariadb:/var/lib/mysql \
  -p 3306:3306 \
  -d \
  mariadb:10.3