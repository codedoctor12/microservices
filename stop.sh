#!/usr/bin/env bash
 docker stop $(docker ps -a -q)
leafpad &
 docker rm $(docker ps -a -q)
 leafpad &
 docker rmi $(docker images -q -q)