#!/usr/bin/env bash
mvn clean
leafpad &
mvn compile
 leafpad &
mvn install
 leafpad &
mvn jib:build
 leafpad &
docker compose up -d
exec bash