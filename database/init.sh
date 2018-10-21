#!/usr/bin/env bash

# Run the database initialization script
mysql -u root -pvirtual-dispatcher < /opt/virtual-dispatcher/database/init.sql
