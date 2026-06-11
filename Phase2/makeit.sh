#!/bin/sh

rm -f *.class
javacc -OUTPUT_HTML=true ParserRustyV2.jj 
javac *.java
