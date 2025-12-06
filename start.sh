#!/bin/bash
cd bliblioteca
./mvnw clean package
java -jar target/bliblioteca-0.0.1-SNAPSHOT.jar
