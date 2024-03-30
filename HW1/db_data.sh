#!/bin/bash
# This script inserts data into the database
# The docker compose must be running

# Insert data into the database
docker exec -i hw1-database-1 mysql -uroot -proot tqs_hw1 < db_dml.sql