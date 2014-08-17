#!/bin/bash

java -jar /usr/local/lib/antlr-4.4-complete.jar Mips.g4
javac *.java
java TestMips $1