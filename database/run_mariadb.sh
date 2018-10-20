#!/usr/bin/env bash

docker run \
  --name mariadb \
  -e MYSQL_ROOT_PASSWORD="root" \
  -v /home/gkuhns/mariadb:/var/lib/mysql \
  --network host \
  -d \
  mariadb:10.3