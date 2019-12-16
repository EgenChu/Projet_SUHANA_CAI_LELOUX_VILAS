#!/bin/bash

cd Code/DBProject/src
javac  SGBD/*.java -d ../bin
java -cp ../bin/ SGBD.DBMain ../../../DB
