#!/bin/sh

rm -f *.class
javacc -OUTPUT_HTML=true ParserRustyV1.jj 
javac *.java
